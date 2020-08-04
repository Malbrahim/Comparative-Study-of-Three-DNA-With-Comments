import java.util.*;

public class Encryption {

    static HashMap<Character, Character> genericBasePairing = new HashMap<>();
    static {
        genericBasePairing.put('A', 'C');
        genericBasePairing.put('C', 'T');
        genericBasePairing.put('G', 'A');
        genericBasePairing.put('T', 'G');
    }

    public static String encrypt(String s, String msg, String key) {
        ConvertMsgHelper convertMsgHelper = new ConvertMsgHelper();
        CipherDetails cipherDetails = convertMsgHelper.processEncryption(msg, key);
        System.out.println("sMsg: " + cipherDetails.getCipherBin());
        String sMsg = cipherDetails.getCipherBin() + "TAGACTCTT";
        String sDash = substitutionPhase(sMsg.toCharArray(), s.toCharArray(), stringToSeed(key));
        System.out.println("sDash: " + sDash);
        String sDoubleDash = insertionMethod(sDash, s, key);
//        System.out.println("sDoubleDash: " + sDoubleDash);

        return sDoubleDash;
    }

    static long stringToSeed(String s) {
        if (s == null) {
            return 0;
        }
        long hash = 0;
        for (char c : s.toCharArray()) {
            hash = 31L*hash + c;
        }
        return hash;
    }

    public static String substitutionPhase(char[] smsg, char[] s, long key){
        int n = s.length;
        if (smsg.length >= n) {
            System.out.println("Error - cover string is too short, choose a longer one");
            System.exit(0);
        }
        Integer[] permutations = generatePermutation(n, key);
        System.out.println("Permutaion List: " + Arrays.toString(permutations));
        StringBuilder sDash = new StringBuilder(new String(s));

        for (int i = 0; i < smsg.length; i++) {
            if (smsg[i] == 'C') {
                char ch = genericBasePairing.get(s[permutations[i]]);
                sDash.setCharAt(permutations[i], ch);
            } else if (smsg[i] == 'G') {
                char ch = genericBasePairing.get(genericBasePairing.get(s[permutations[i]]));
                sDash.setCharAt(permutations[i], ch);
            } else if (smsg[i] == 'T') {
                char ch = genericBasePairing.get(
                        genericBasePairing.get(
                                genericBasePairing.get(s[permutations[i]])));
                sDash.setCharAt(permutations[i], ch);
            }

        }
        return sDash.toString();


    }

    public static String insertionMethod(String sDash, String s, String key){
        Random r = new Random((int) key.charAt(0));
        System.out.println("r: " + (int)key.charAt(0));
        ArrayList<Integer> rList = new ArrayList<>();

        Random k = new Random((int) key.charAt(key.length() - 1));
        System.out.println("k: " + (int)key.charAt(key.length() - 1));
        ArrayList<Integer> kList = new ArrayList<>();

        StringBuilder sDoubleDash = new StringBuilder();
        int t = 0;
        int sum = 0;
        while (sum <= sDash.length()) {
            int rNum = r.nextInt(sDash.length() / 3) + 1;
            int kNum = k.nextInt(sDash.length() / 3 / 2) + 1;
            rList.add(rNum);
            kList.add(kNum);
            sum += rNum;
            t += 1;
        }
        System.out.println("t: " + t);
//        int t = 9;
        Integer[] rArr =  rList.toArray(new Integer[rList.size()]); //{16, 6, 4, 3, 4, 11, 10, 17, 19, 9};
        Integer[] kArr =  kList.toArray(new Integer[kList.size()]); //{3, 6, 3, 4, 8, 3, 4, 5, 1, 10};
        System.out.println("rList: " + Arrays.toString(rArr));
        System.out.println("kList: " + Arrays.toString(kArr));
        int rStartIdx = 0;
        int kStartIdx = 0;
        for (int i = 0; i < t; i++) {
            if (i != t-1) {
                sDoubleDash.append(sDash.substring(rStartIdx, rStartIdx + rArr[i]));
                sDoubleDash.append(s.substring(kStartIdx, kStartIdx + kArr[i]));
                rStartIdx += rArr[i];
                kStartIdx += kArr[i];
            }
            else {
                sDoubleDash.append(sDash.substring(rStartIdx));
                sDoubleDash.append(s.substring(kStartIdx));
            }
        }
        return sDoubleDash.toString();
    }

//    private static boolean arrContains(final int[] array, final int v) {
//
//        boolean result = false;
//
//        for(int i : array){
//            if(i == v){
//                result = true;
//                break;
//            }
//        }
//
//        return result;
//    }

    private static Integer[] generatePermutation(int n, long seed) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(seed));
        return numbers.toArray(new Integer[0]);
    }
}
