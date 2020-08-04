
package xorcipher_insertionmethod;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import static java.lang.Math.random;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XORCipher_InsertionMethod{
          
    public static String readFileAsString(String fileName)throws Exception 
  { 
    String data = ""; 
    data = new String(Files.readAllBytes(Paths.get(fileName))); 
    return data; 
  } 

    public static void main(String[] args)throws Exception  {
        
        //assuming a sample DNA sequence 
       String S ="ATGGTGGGACAAGGAGAATCACAGTGACTTTGTAACAGGAAATCTGGATAAAGACAGCTAGCATCCAACCATTGATGCTTCCAGCTATGCTCAGGTCTCTCCTACGGTGACGGCAATGGTGACAGACACTTCTCCTGTCTCTGGAGCT";

  
  
    ////String S = readFileAsString("C:\\Users\\Bana&Nisreen\\Desktop\\NisreenT\\مستوى سابع وثامن\\مشروع التخرج\\DNA database\\DNA without N\\AC153526.fasta"); 
   

        //massage receve 
        System.out.println("Enter your massage\n");
        Scanner input = new Scanner(System.in);
        String M = input.nextLine();
        ////String M ="zKiVVXYEVgpAkOgZZbUmdsoujKdCtdvTxDJekwSlFhWGWKwWwRwefnNBUCOaMaYoVTUWkJtzwOmYYwdlJDbNOrpmEwIFzDwrlPQMBKmWpqylXNVfidqYywBquvhtncCIFpYKJiObtZDNHwZbubsRzFTChKjtrqQrLhdWqvJhMrGmvzPZnUGTDVtWdRbhkKVXKVGaJfWuuXdgcgXZgXTqTKpeEAceuKBTkQhaSVxAQRxnWgiEPWgjVhIVyAZWOJHiVwXLNvuDeQUfVoRQBUFhJdBXmhaEbzSjlLjldRPPmISdMEEWwjIvGXYleJmqlBOguAkRRjOiqNNiCFbfdHVbnJNGHGSjXWJBsbFDylbPHkWsxXfIWIcGOqNLrzatKNncbiLvzDXqurtLbRBVckOglcWwTegeeKyOFizkBPqazlNkcaGKSgUEWOqXwAKInSRxxqWPoiSvskfiPQZSPJZFAkXQVkfcdsBXLRXxnzAmSJmaYSQlYXpvsWXRGaKlIQRBEpiZwPIGcmXyUqeOchXwzdjYWVIToxRTsHkSvGTqzbyGKcHySXexHCaPmkgGVgrfEGhHOOsKDPDLHsjLDVDcRlVNYnXqDtqaZCjLiJbMLVwHzTgtPTmboqMQpfIsHzxJwMMSXSLxjgqfbkZNGRXrEQkTCvxumMYEypraKeBvrUTTclOLeWlPRYPGvQQWhehjgnoSUDGSxPRNjFPiezEacoTvwyAbgLJSVYiMYgaLaZvBSpcRvbpxxeWNEJxEIZkCnRRGLCgspvlsuHoLVbFywDkAfwjpckYIjRPiTJzmOtZTAuwRTpwrUDOlhlbaIYDGiZwPOdUHWjsovZyWMPaDRrgMRoaAQLQBpGUaeAbDpgDjeRJxdTckaAktNvbFwaFSiWwaTPITnjROvSPzNdaUHGEIpQAdPGeftHKiHaHYpPUEcHRJtqnvONDXWuGqzzHQcPgFyoKCdKquIUWlDWjcDMUXhjuPYBkaitwgKkNe";
                
        
        System.out.println("\n Sender side \n");
//step1:
        String s = RandomBinary(8);
        System.out.println("Key = "+s); // key.
        
        
//step2:
            String M_bin=AsciiToBinary(M);
        System.out.println("Binary value of the masseag ="+ M_bin+ "\n");
            
        
//step3&4:
        String A = new String();
        A=XOR_function(M,s);
        System.out.println("binary XORed sequence of the message is: "+A);

  
//step5:

        String A_DAN = DnaSequences(A);
        System.out.println("DNA of the message ="+A_DAN);
        System.out.println("Amino ascid of secret message = "+ transcribe(A_DAN));
    
//step6:
        int n;
        Random random = new Random();
        n =random.nextInt(M_bin.length())+1;
       // System.out.println("K2 = "+n );


//step7&8: //step9: convert the sample DNA to it is binary value.
        String S_bin= ConvertToBinary(S);
        ///System.out.println("Seq bin value = "+S_bin);
        insertion(S_bin,A,n);
      

//step10: what is the boundaries of the random num ??
        int p;
        p =random.nextInt(4)+1;//1,2,3,4,5 
       // System.out.println("p = "+p );

//step 11:

        series_of_random(S_bin, M_bin, p);

//step12:

        Comanation(S_bin, M_bin);
        New_Binary(p);
        ///System.out.println("New Binary = " + NewBin);

//step 13:
        String Fack_DNA =DnaSequences(NewBin);
        
        System.out.println("The fack DNA S` is : "+ Fack_DNA);//cipher text

//..........................decryption (recever side)..............................//
        System.out.println("\n Receveir side \n");
// step1:
ConvertToBinary(Fack_DNA);
      ///  System.out.println("S` to Bin = "+);
       // System.out.println(NewBin.length());
//Step 2: 
        Re_series_of_random(n);

//step 3&4&5:
        ///System.out.println("Divisions = "+Arrays.toString(Divisions));
        insertion_decryption(Divisions,A);
//step 6&7:
        String Mass=DnaSequences(A_re);
        String Seq =DnaSequences(S_re);
        ///System.out.println(Mass+"\n"+Seq);

//step8:
        Bin_to_char(M_bin);

//step9&10:        
        RE_XOR(s);
//step11:
System.out.println("The reference DNA S is : "+ S);

       String CH="";
       String CHsum="";
for (int i = 0; i < bits.length; i++) {
            CH = bits[i];
       ///System.out.println(convertBinaryStringToString(CH));
       CHsum +=convertBinaryStringToString(CH);
} System.out.println("The message is : "+CHsum+"\n");//plain text
        
     //mesuring properties:
     
     Performance_Analysis measure = new Performance_Analysis();
   
     
     //int diff= Fack_DNA.length()-S.length();
     
        
        System.out.println("The capacity measure is : "+measure.Capacity_mesuring(CHsum, Fack_DNA));
        System.out.println("The payload measure is : "+measure.payload_mesuring(CHsum));
        System.out.println("The BPN measure is : "+measure.pbn_mesuring(CHsum, Fack_DNA));
     
    }   
 
  
    //................... Generate Key randomly ........................//
    public static String RandomBinary(int len){
        String randomBinary = "";
    if(len<256) {
        Random random = new Random();
        boolean res;
        
        for (int i = 0; i < len ; i++) {
            res = random.nextBoolean();
            if (res)
                randomBinary = randomBinary.concat("1");
            else
                randomBinary = randomBinary.concat("0");
        }
        //System.out.println(randomBinary);
    }else {
        System.out.print("KeyGen maximum length allowed is 256. Please enter value below 256");
    }
    return randomBinary ;
}
    
    //........................convert Binary to DNA ......................................//
  /*  
    public static String Binary_to_DNA(String Bin){
    String DNA = "";
    for(int i=0;i<Bin.length();i=i+2){
       // System.out.println("Bin = "+Bin.charAt(0)+Bin.charAt(1));
        if(Bin.charAt(i)=='0' && Bin.charAt(i+1)=='0') 
            DNA= DNA+ 'A';
        if(Bin.charAt(i)=='0' && Bin.charAt(i+1)=='1') 
            DNA= DNA+ 'C';
        if(Bin.charAt(i)=='1' && Bin.charAt(i+1)=='0') 
            DNA= DNA+ 'G';
        if(Bin.charAt(i)=='1' && Bin.charAt(i+1)=='1') 
            DNA= DNA+ 'T';
    }
    return DNA;
}
    */
    //.............................DNA to Binary ...................................//
    
    public static String ConvertToBinary(String seq)
{
    String result = new String();
for(int k=0;k<seq.length();k++)
{
   char h1 = seq.charAt(k);
   if(h1 == 'A'){result = result  + "00";}
   if(h1 == 'C'){result = result + "01";}
   if(h1 == 'G'){result = result + "10";}
   if(h1 == 'T'){result = result + "11";}

}

return result;
}
    
    //...................convert masseg to ASCII then to Binary..........................//
    public static String AsciiToBinary(String asciiString){  

          byte[] bytes = asciiString.getBytes();  
          StringBuilder binary = new StringBuilder();  
          for (byte b : bytes)  
          {  
             int val = b;  
             for (int i = 0; i < 8; i++)  
             {  
                binary.append((val & 128) == 0 ? 0 : 1);  
                val <<= 1;  
             }  
            // binary.append(' ');  
          }  
          return binary.toString();  
    }
    
    //.......................XOR Operation ...............................................//


   public static String xor(String a,String b){
     StringBuilder sb = new StringBuilder();
   for(int k=0;k<a.length();k++)
   sb.append((a.charAt(k) ^ b.charAt(k+(Math.abs(a.length()-b.length()))))) ;
           String result;
             result = sb.toString();
   return result;
    }
   
    
     public static String XOR_function(String M,String s){
       String A="";
       String a[] = new String[M.length()];//7
       String characters[] = new String[M.length()];//7
      List<Integer> ascii = new ArrayList<>(); 
        
      for(int i=0;i<M.length();i++){
    for (char c : M.toCharArray()) {
        ascii.add((int) c);
        characters[i] = String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) c)));
    }      
         }    // System.out.println("characters is : "+Arrays.toString(characters));
         int T=1;
         a[0]=xor(characters[characters.length-1],s);//last element in the message with key
         
         for (int Ass = characters.length-2; Ass >=0; Ass--) {

             a[T]=xor(characters[Ass],a[T-1]);
             T++;
            // System.out.println("a[]= "+Arrays.toString(a));
         }
         for (int v = 0; v < a.length; v++) {
             A+=a[v];
         }
        // System.out.println("XORed value is : "+A);
         return A;
    }
    
   // ...............last character in massege...............//
 

 public static char lastChar(String s) {
     
    System.out.println("last char = " + s.charAt(s.length() - 1));
    return s.charAt(s.length() - 1);
}
 
 //......................... convert last character in the massege to binary ...................//
 
 public static String Int_to_Binary(int n){
  
        String x = "";
        
        while(n > 0)
        {
            int a = n % 2;
            x = a + x;
            n = n / 2;
        }
        return x ;
    }
 
 
    
    //............................. DNA convert to protin(RNA) ..................................//

 

       public static String transcribe(String dnaStrand) {
    	String dna = new String(dnaStrand);
    	dna.toUpperCase();
    	String rnaStrand = new String();
    	rnaStrand = dna.replace("G","C")
				.replace("C","G")
				.replace("T","A")
				.replace("A","U");
		return rnaStrand;
    }

 
    
    //............................. Binary to int to DNA..........................................//

              public static String DnaSequences(String s) {
        int decimal,temp=0;
        String str ="" ;
        String DNA = "";
    
            for(int i=0;i<s.length()/2;i++){
                for (int j = 0; j < 2; j++){ 
              str+=s.charAt(temp);
              temp++;
                }

            decimal=binaryToInteger(str);
            str="";
  
        if(decimal==0) 
            DNA= DNA+ 'A';
        if(decimal==1) 
            DNA= DNA+ 'C';
        if(decimal==2)  
            DNA= DNA+ 'G';
        if(decimal==3)  
            DNA= DNA+ 'T';
           
    }
return DNA;
}
        public static int binaryToInteger(String binary) {
    char[] numbers = binary.toCharArray();
    int result = 0;
    for(int i=numbers.length - 1; i>=0; i--)
        if(numbers[i]=='1')
            result += Math.pow(2, (numbers.length-i - 1));
    return result;
}
   

//.........................................Divide the sequence to a segments then insertion..................................//
        // .............................insertion methode ..................................//
        public static char[] result;
    public static void insertion(String s1, String s2, int n){
        //s2=M , s1=S.
    char[] E1=s1.toCharArray();
        
    char[] E2=s2.toCharArray();
       
    result= new char [E1.length+E2.length];
        
    
    int x=0;
    int Etemp=0;
    int ee2=0;
    
        for (int i = 0; i < result.length; i++) {
            if(ee2!=E2.length){ 
            result[x]=E2[i];
            x++;
            ee2++;}
            for (int j = 0; j < n; j++) {
                if(Etemp!=E1.length){
            result[x]=E1[Etemp];
            Etemp++;
            x++;}
        
            else{
                break;
                }
            }        
        }
        System.out.print("Result after insertion is: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print( result[i]+" ");
        }
        System.out.println("\n");
    }


     
     
     //................................step11............................................//
     
    public static int[] Ss ;
    public static int[] Sr ;
    public static void series_of_random(String S , String M , int p){
        Ss = new int[p];
        Random random = new Random();
        int S_series  , Last_ran=S.length() , Sum_ran=0 ;
        int S_len[] = new int[S.length()/p];
        S_series=S.length()/p; //48/3=16. 
        
        if(S.length()%p!=0){//48/5=9.6.
        S_series=Math.round(S_series);//10
        }
      
        
        for (int i = 0; i < p-1; i++) {
            
        S_len[i]=random.nextInt(S_series)+1;
        //S_len=[0-16],[0-16]
        //S_len=[0-10][0-10][0-10][0-10]
        
        Sum_ran+=S_len[i];// [16][16]=32\ // [10][2][4][8]=24
        }
        
         
        // System.out.println(Sum_ran);
        Last_ran-=Sum_ran;//48-32=16. //48-24=24
        int count=0;
        for (int k = 0; k < p; k++) {
             Ss[k]=S_len[count];
             count++;
             if(count==(p-1))
                 break;
        }
        Ss[p-1] = Last_ran;
       /// System.out.println("S series"+Arrays.toString(Ss));
        //.................................................................
        
               Sr = new int[p];
        //Random random = new Random();
        int r_series  , Last_ranR=M.length() , Sum_ranR=0 ;
        int r_len[] = new int[M.length()/p];
        r_series=M.length()/p; //14/3=4.6. 
        
        if(M.length()%p!=0){//4.6.
        r_series=Math.round(r_series);//5
        }
      
        
        for (int i = 0; i < p-1; i++) {
            
        r_len[i]=random.nextInt(r_series)+1;
        //r_len=[0-5],[0-5]
        
        
        Sum_ranR+=r_len[i];// [5][3]=8
        }
       // System.out.println(Sum_ranR);
        
        Last_ranR-=Sum_ranR;//14-8=6
        int count2=0;
        for (int k = 0; k < p; k++) {
             Sr[k]=r_len[count2];
             count2++;
             if(count2==(p-1))
                 break;
        }
        Sr[p-1] = Last_ranR;
        ///System.out.println("R series"+Arrays.toString(Sr));
    }
    
     //................................step 12.......................................//
    
        public static String St_Series[];
        public static String rt_Series[];
        public static void Comanation(String S , String M){
            int F1;
            String st1 = "";
             St_Series = new String[S.length()];
            for (int i = 0; i <Ss.length ; i++) {
                F1 = Ss[i];
                for (int j = 0; j < F1; j++) { 
                   st1 += S.charAt(j);
                }
                St_Series[i]=st1;
                st1="";
            }
            
            for (int f = 0; f < Ss.length; f++) {
              ///  System.out.println("st1 = "+St_Series[f]);
            }
            
           ///System.out.println("\n\n");
            
            int F2;
            String st2 = new String();
             rt_Series = new String[M.length()];
            for (int i = 0; i <Sr.length ; i++) {
                F2 = Sr[i];
                for (int j = 0; j < F2; j++) { 
                   st2 += M.charAt(j);
                }
                rt_Series[i]=st2;
                st2="";
            }
            
            for (int f = 0; f < Sr.length; f++) {
             ///   System.out.println("st2 = "+rt_Series[f]);
            }
        }

    
     public static String NewBin = new String();
    public static void New_Binary(int p){
        int count = 0 ;
        int size = Ss.length+Sr.length;
        for (int i = 0; i < p; i++) {
            NewBin +=St_Series[count];
            NewBin +=rt_Series[count];
            count++;
                 
        }
        //System.out.println("size = "+size+"  Ss = "+ Ss.length + "  Sr = " + Sr.length);
        //System.out.println("NewBin = "+NewBin);
    }
         
         
         
         
     
     
     //..................................End of the encryption code...........................//
     
     
     
     //..................................start dycription code.....................................//
    
    //......Divide S’ sequence into parts of length s1+r1, s2+r2… sP+rP..............//
     
     public static String Divisions[] ;
    public static void Re_series_of_random(int K2){
        int k = K2+1;//4
        int div_size = (NewBin.length() / k);//62/4= 15.5
        
        if((NewBin.length() % k)!=0){
        float div_f = NewBin.length()/k;
        div_size=Math.round(div_f);
      //  System.out.println("round "+div_size);
        }
            String st1 = "";
            int count=0;
            Divisions = new String[div_size];//will not print all the digets.
            for (int i = 0; i <div_size; i++) {
                for (int j = 0; j < k; j++) { 
                   st1 += NewBin.charAt(count);
                   count++;
                }
                //System.out.println(st1);
                Divisions[i]=st1;
                st1="";
            }
        for (int l = 0; l < div_size; l++) {
           /// System.out.print(Divisions[l]+" ");
            
        }System.out.println("\n");
      // System.out.println(Arrays.toString(Divisions)+" ");
    }
    
    //.......................get the message M and DNA sequence S........................//
    public static String S_re="" , A_re="";
        public static void insertion_decryption(String arr[],String A){
        String str ="";
        for(int x = 0 ; x < arr.length; x++){//0   1    2
        str = arr[x]; //5    7    8
            for (int f = 0; f < str.length(); f++) {
                while(A_re.length()!=A.length()){
                if(f==0){//aop
              A_re +=str.charAt(f);
                }}
                if (f!=0){//ppelriengeinappel
                   S_re +=str.charAt(f);
                }                    
            }   
        } ///System.out.println("Message in binary= "+A_re+"\n"+"DNA in binary= "+S_re);
    }
        
    //......................generate Binary to character.................................//
        public static String bits[];
         public static String Bin_to_char(String BIN_M){
        
        int spilt_Size=BIN_M.length()/8; //16/8=2
        int char_digets=BIN_M.length()/spilt_Size; //16/2=8
        bits = new String[spilt_Size]; int count=0 , temp=0;
        String digets="";
        //System.out.println(BIN_M + spilt_Size + char_digets);
        for (int i = 0; i < BIN_M.length(); i++) {//2
            for (int j = 0; j < char_digets; j++) {//0-7 8-15
                digets +=BIN_M.charAt(count);
                
            count++;
            }//System.out.println("digets= "+digets);
            bits[temp]= digets;
            digets="";
            temp++;
            
           if(count==BIN_M.length())
           break;
        } 
            // System.out.println("Message in binary after encryption is: "+Arrays.toString(bits));
        return Arrays.toString(bits);
    }
        
    //..........................convert binary to Ascii...........................//
        
            public static String convertBinaryStringToString(String string){
    StringBuilder sb = new StringBuilder();
    char[] chars = string.replaceAll("\\s", "").toCharArray();
    int [] mapping = {1,2,4,8,16,32,64,128};

    for (int j = 0; j < chars.length; j+=8) {
        int idx = 0;
        int sum = 0;
        for (int i = 7; i>= 0; i--) {
            if (chars[i+j] == '1') {
                sum += mapping[idx];
            }
            idx++;
        }
     //   System.out.println(sum);//debug
        sb.append(Character.toChars(sum));
    }
    return sb.toString();
}
            
 //................................XOR for decrypthon....................................//           
            public static String RE_XOR(String k1){
        String A=new String();
        //System.out.println(bits.length);
      
        A+=xor(bits[0],k1);//01100101 //0110
        
        for (int i = bits.length-1; i>0; i--) {//1 i=2 i>1
          //a=bits[i];  //[8bits]
          //
          A +=xor(bits[i-1],bits[i]);// [0]XOR[1] (01010111)   
        }
       /// System.out.println("A= "+A); 
        return A;
    }
}

//For testing
//Message: Comparative Study of Three DNA-based information hiding methods.
//S1: AC153526.fasta
//S2: AC166252.fasta
//S3: AC167221.fasta
//S4: AC168901.fasta
//S5: CS236146.fasta
//S6: JX978467.fasta
//S7: NC_021114.fasta
//S8: NC_021116.fasta



