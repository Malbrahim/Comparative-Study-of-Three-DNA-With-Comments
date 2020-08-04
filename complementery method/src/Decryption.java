import java.util.*;

public class Decryption {

    private static HashMap<Character, Character> genericBasePairing = new HashMap<>();
    static {
        genericBasePairing.put('A', 'C');
        genericBasePairing.put('C', 'T');
        genericBasePairing.put('G', 'A');
        genericBasePairing.put('T', 'G');
    }

    private static HashMap<Character, String[]> codonsMap = new HashMap<>();
    static {
        codonsMap.put('A', new String[] {"GCT", "GCC", "GCA", "GCG"});
        codonsMap.put('B', new String[] {"TAA", "TGA", "TAG"});
        codonsMap.put('C', new String[] {"TGT", "TGC"});
        codonsMap.put('D', new String[] {"GAT", "GAC"});
        codonsMap.put('E', new String[] {"GAA", "GAG"});
        codonsMap.put('F', new String[] {"TTT", "TTC"});
        codonsMap.put('G', new String[] {"GGT", "GGC", "GGA", "GGG"});
        codonsMap.put('H', new String[] {"CAT", "CAC"});
        codonsMap.put('I', new String[] {"ATT", "ATC", "ATA"});
        codonsMap.put('K', new String[] {"AAG"});
        codonsMap.put('L', new String[] {"CTT", "CTC", "CTA", "CTG"});
        codonsMap.put('M', new String[] {"ATG"});
        codonsMap.put('N', new String[] {"AAT", "AAC"});
        codonsMap.put('O', new String[] {"TTA", "TTG"});
        codonsMap.put('P', new String[] {"CCT", "CCC", "CCA", "CCG"});
        codonsMap.put('Q', new String[] {"CAA", "CAG"});
        codonsMap.put('R', new String[] {"CGT", "CGC", "CGA", "CGG"});
        codonsMap.put('S', new String[] {"TCT", "TCC", "TCA", "TCG"});
        codonsMap.put('T', new String[] {"ACT", "ACC", "ACA", "ACG"});
        codonsMap.put('U', new String[] {"AGA", "AGG"});
        codonsMap.put('V', new String[] {"GTT", "GCT", "GTA", "GTG"});
        codonsMap.put('W', new String[] {"TGG"});
        codonsMap.put('X', new String[] {"AGT", "AGC"});
        codonsMap.put('Y', new String[] {"TAT"});
        codonsMap.put('Z', new String[] {"TAC"});
    }

    public static String decrypt(String sDoubleDash, String key){
        System.out.println("Encrypted Msg.: " + sDoubleDash);
        System.out.println("Key: " + key);
        String[] recovery = recoverReference(sDoubleDash, key);
        String sDash = recovery[0];
        String s = recovery[1];
        System.out.println("sDash: " + sDash);
        System.out.println("S: " + s);
        String sMsg = recoverMessage(sDash, s, key);
        System.out.println("sMsg: " + sMsg);
//        String[] sMsgParts = removeAmbiguity(sMsg);
//        String sEnc = sMsgParts[0];
//        String sAmb = sMsgParts[1];
//        System.out.println("sEnc: " + sEnc);
//        System.out.println("sAmb: " + sAmb);
        ConvertMsgHelper convertMsgHelper = new ConvertMsgHelper();
        CipherDetails cipherDetails = convertMsgHelper.processDecryption(sMsg, key);
//        System.out.println(cipherDetails.getMsg());
        return cipherDetails.getMsg();
    }

    private static String[] recoverReference(String sDoubleDash, String key) {
        Random r = new Random((int) key.charAt(0));
        ArrayList<Integer> rList = new ArrayList<>();
        System.out.println("r: " + (int) key.charAt(0));

        Random k = new Random((int) key.charAt(key.length() - 1));
        ArrayList<Integer> kList = new ArrayList<>();
        System.out.println("k: " + (int) key.charAt(key.length() - 1));

        StringBuilder sDash = new StringBuilder();
        StringBuilder s = new StringBuilder();
        int t = 0;
        int sum = 0;
        while (sum <= sDoubleDash.length()/2) {
            int rNum = r.nextInt(sDoubleDash.length()/2/3) + 1;
            int kNum = k.nextInt(sDoubleDash.length()/2/3/2) + 1;
            rList.add(rNum);
            kList.add(kNum);
            sum += rNum;
            t += 1;
        }
        System.out.println("t: " + t);
        System.out.println("rList: " + rList);
        System.out.println("kList: " + kList);
        int startIdx = 0;

        for (int i = 0; i < t-1; i++) {
            sDash.append(sDoubleDash.substring(startIdx, startIdx + rList.get(i)));
            startIdx += rList.get(i);
            s.append(sDoubleDash.substring(startIdx, startIdx + kList.get(i)));
            startIdx += kList.get(i);
        }
        int rLastidx = startIdx + (sDoubleDash.length()/2 - sDash.length());
        sDash.append(sDoubleDash.substring(startIdx, rLastidx));
        s.append(sDoubleDash.substring(rLastidx));
//        System.out.println(sDash);
//        System.out.println(s);

        return new String[] {sDash.toString(), s.toString()};
    }

    private static String recoverMessage(String sDash, String s, String key) {
        int n = s.length();
        String w = "TAGACTCTT";
        Integer[] permutations = generatePermutation(n, stringToSeed(key));
        System.out.println("Permutations: " + Arrays.toString(permutations));
        StringBuilder sMsg = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char bS = sDash.charAt(permutations[i]);
            char bC = s.charAt(permutations[i]);
            if (bS == bC) {
                sMsg.append('A');
            }
            else if (bS == genericBasePairing.get(bC)) {
                sMsg.append('C');
            }
            else if (bS == genericBasePairing
                            .get(genericBasePairing
                            .get(bC))) {
                sMsg.append('G');
            }
            else if (bS == genericBasePairing
                            .get(genericBasePairing
                            .get(genericBasePairing
                            .get(bC)))) {
                sMsg.append('T');
            }

            if (sMsg.toString().contains(w)) {
//                System.out.println(sMsg);
                break;
            }
        }
//        System.out.println(sMsg);
        return sMsg.substring(0, sMsg.length() - w.length()).toString();
    }

    private static String[] removeAmbiguity(String sMsg) {
        StringBuilder sAmb = new StringBuilder();
        StringBuilder sEnc = new StringBuilder();
        ArrayList<Integer> ambIdxs = new ArrayList();
        for (int i = 3; i < sMsg.length(); i += 4) {
            ambIdxs.add(i);
        }

        for (Integer i = 0; i < sMsg.length(); i++) {
            if (ambIdxs.contains(i)) {
                sAmb.append(sMsg.charAt(i));
            }
            else {
                sEnc.append(sMsg.charAt(i));
            }
        }

        return new String[] {remapCodons(sEnc.toString()), sAmb.toString()};
    }

    private static String remapCodons(String sEncCodons) {
        StringBuilder sEnc = new StringBuilder();
        for (int i = 0; i < sEncCodons.length(); i += 3) {
            String codon = sEncCodons.substring(i, i + 3);
            codonsMap.forEach((letter, codons) -> {
                if (arrContains(codons, codon)) {
                    sEnc.append(letter);
                    return;
                }
            });
        }

        return sEnc.toString();
    }

    private static long stringToSeed(String s) {
        if (s == null) {
            return 0;
        }
        long hash = 0;
        for (char c : s.toCharArray()) {
            hash = 31L*hash + c;
        }
        return hash;
    }

    private static boolean arrContains(final String[] array, final String v) {

        boolean result = false;

        for(String s : array){
            if(s.equals(v)){
                result = true;
                break;
            }
        }

        return result;
    }

    private static Integer[] generatePermutation(int n, long seed) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(seed));
        return numbers.toArray(new Integer[0]);
    }
}
