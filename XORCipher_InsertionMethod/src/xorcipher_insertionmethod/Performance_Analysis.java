
package xorcipher_insertionmethod;

public class Performance_Analysis {
         /*
        n is the number of bits in the Fake DNA sequence.
        m is the number of bits in the secret message.
        s is the number of bits in the reference DNA sequence.
        M is the message 
        S is the DNA Sequence
*/   
    //Cracking Probibility

    //Capacity
    public double Capacity_mesuring(String M,String S){
      double result=0;
      
      result= (S.length()+(M.length()/2));
      
        return result;
    }
    //payload
    public double payload_mesuring(String M){
      double result=0;
      result= (M.length()/2);
        return result;
    }
    //pbn
    public double pbn_mesuring(String M,String S){
      double result=0;
      //System.out.println("BPN measurements : "+S.length()+M.length());
      double x = S.length()+(M.length()/2);
      //System.out.printf("BPN measurements x: %.5f \n",x);
      result= M.length()/x;
      //System.out.printf("BPN measurements result: %.5f \n",result);
        return result;
    }
}

