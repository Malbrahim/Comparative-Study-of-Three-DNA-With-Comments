import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
//        List<String> files = getAllFilesInFolder("src/DNASeq2");
//        for (String file : files) {
//            String data = readFileAsString(file);
//
//            String s = data.substring(data.indexOf("\r\n"))
//                            .replaceAll("\r\n", "");
////                            .replaceAll("[^ACGT]", "");
            String s = "ATGGTGGGACAAGGAGAATCACAGTGACTTTGTAACAGGAAATCTGGATAAAGACAGCTAGCATCCAACCATTGATGCTTCCAGCTATGCTCAGGTCTCTCCTACGGTGACGGCAATGGTGACAGACACTTCTCCTGTCTCTGGAGCT";
            String msg = "DNA"; //readFileAsString("src/message.txt");
            String key = "SECURITY";
//            System.out.println("-----------------------------------");
//            System.out.println("File: " + file);
            System.out.println("------------ Encryption -----------");
            System.out.println("s: " + s);
            System.out.println("Msg: " + msg);
            System.out.println("Key: " + key);
            String cipher = Encryption.encrypt(s, msg, key);
            System.out.println("Encrypted Msg: " + cipher);
            System.out.println("\n------------ Decryption -----------");
            String plain = Decryption.decrypt(cipher, key);
            System.out.println("Decrypted Msg: " + plain);
            System.out.println("------------------------------------");
            System.out.println("S Length: " + s.length());
            System.out.println("Payload: " + ((3/4.0 * s.length() * 2) / s.length()));
            System.out.println("Capacity: " + (1.5 * s.length()));
            System.out.println("\n\n");
//            System.out.println("Robustness: " + (1/Math.pow(Math.pow(2, s.length()) - 1, 2) * 1/6.0 * 1/24.0));
//        }
    }

    private static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    private static List<String> getAllFilesInFolder(String path) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            List<String> files =
                    walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            return files;

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
}
