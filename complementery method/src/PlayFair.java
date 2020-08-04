
public class PlayFair {
    private String KeyWord = new String();
    private String Key = new String();
    private char matrix_arr[][] = new char[5][5];

    public String encryptionPlayFair(String msg, String keyword) {
        setKey(keyword);
        KeyGen();
        String cipherEnglish = encryptMessage(msg);
        System.out.println("Amino Seq.: " + msg);
        System.out.println("After Playfair: "+ cipherEnglish);
        return cipherEnglish;
    }

    public String decryptionPlayFair(String msg, String keyword) {
        setKey(keyword);
        KeyGen();
        String decrypted = decryptMessage(msg);
        System.out.println("After Playfair: " + decrypted);
        return decrypted;
    }

    private String decryptMessage(String Code) {
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++) {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0]) {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            } else if (part1[1] == part2[1]) {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            } else {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original
                    + matrix_arr[part2[0]][part2[1]]
                    + matrix_arr[part1[0]][part1[1]];
        }
        return Original;
    }

    //1
    private void setKey(String k) {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++) {
            for (int j = 0; j < K_adjust.length(); j++) {
                if (k.charAt(i) == K_adjust.charAt(j)) {
                    flag = true;
                }
            }
            if (flag == false)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }

    //2
    private void KeyGen() {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++) {
            current = (char) (i + 65);
            if (current == 'J')
                continue;
            for (int j = 0; j < KeyWord.length(); j++) {
                if (current == KeyWord.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current;
            flag = true;
        }
        matrix();
    }

    private void matrix() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix_arr[i][j] = Key.charAt(counter);
//                System.out.print(matrix_arr[i][j] + " ");
                counter++;
            }
//            System.out.println();
        }
    }

    //3
    private String encryptMessage(String Source) {
        String src_arr[] = Divid2Pairs(Source);
        String Code = new String();
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++) {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0]) {
                if (part1[1] < 4)
                    part1[1]++;
                else
                    part1[1] = 0;
                if (part2[1] < 4)
                    part2[1]++;
                else
                    part2[1] = 0;
            } else if (part1[1] == part2[1]) {
                if (part1[0] < 4)
                    part1[0]++;
                else
                    part1[0] = 0;
                if (part2[0] < 4)
                    part2[0]++;
                else
                    part2[0] = 0;
            } else {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Code = Code
                    + matrix_arr[part2[0]][part2[1]]
                    + matrix_arr[part1[0]][part1[1]];
        }
        return Code;
    }

    //3
    private String format(String old_text) {
        if (old_text.length() % 2 != 0) {
            old_text = old_text + 'X';
        }
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++) {
            if (old_text.charAt(tmp) == 'J') {
                text = text + 'I';
            } else
                text = text + old_text.charAt(tmp);
        }
        for (i = 0; i < text.length()-1; i = i + 2) {
            if (text.charAt(i + 1) == text.charAt(i)) {
                text = text.substring(0, i + 1) + 'X' + text.substring(i + 1);
            }
        }
        return text;
    }

    //3
    private String[] Divid2Pairs(String new_string) {
        String Original = format(new_string);
//        System.out.println("After add Xs to encrypt " + Original);
        int size = Original.length();
        if (size % 2 != 0) {
            size++;
            Original = Original + 'X';
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++) {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }
    //3
    private int[] GetDiminsions(char letter) {
        int[] key = new int[2];
        if (letter == 'J')
            letter = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix_arr[i][j] == letter) {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

}