import java.util.ArrayList;
import java.util.List;

public class ConvertMsgHelper {

	private DistributionCodons distributionCodons = new DistributionCodons();
	private PlayFair playFair = new PlayFair();

	public CipherDetails processEncryption(String msg, String keyword) {
		msg = msg.replaceAll("\\s", "");
		keyword = keyword.replaceAll("\\s", "");

		CipherDetails cipherDetails = processConversionBeforeEncryption(msg);
		String cipherEnglish = playFair.encryptionPlayFair(cipherDetails.getMEnglish(), keyword);
		cipherDetails.setCipherBin(processConversionAfterEncryption(cipherEnglish, cipherDetails.getAMBIG()));

//		System.out.println("Done Encryption..");

		return cipherDetails;
	}

	public CipherDetails processDecryption(String cipher, String keyword) {
		CipherDetails cipherDetails = processConversionBeforeDecryption(cipher);
		String decryptedMsg = playFair.decryptionPlayFair(cipherDetails.getMEnglish(), keyword);
		String correctDecryptedMsg = editDecryptedMsg(decryptedMsg, cipherDetails.getAMBIG());
		String msg = processConversionAfterDecryption(correctDecryptedMsg, cipherDetails.getAMBIG());
		cipherDetails.setMsg(msg);
		return cipherDetails;
	}

	private String editDecryptedMsg(String decryptedMsg, String AMBIG) {
		int removerSize = decryptedMsg.length() - AMBIG.length();
		for (int j = removerSize; j > 0; j--) {
			for (int i = 0; i < decryptedMsg.length() - 1; i++) {
				if (decryptedMsg.charAt(i) == 'X') {
					if (decryptedMsg.charAt(i - 1) == decryptedMsg.charAt(i + 1)) {
						decryptedMsg = decryptedMsg.substring(0, i) + decryptedMsg.substring(i + 1);
						removerSize--;
					}
				}
			}
		}
		if (removerSize > 0 && decryptedMsg.charAt(decryptedMsg.length() - 1) == 'X') {
			decryptedMsg = decryptedMsg.substring(0, decryptedMsg.length() - 1);
		}
		return decryptedMsg;
	}

	public CipherDetails processConversionBeforeEncryption(String msg) {
//		System.out.println("Message " + msg);
		String MBin = MsgToMBin(msg);
		System.out.println("Msg. in Binary: " + MBin);
		String Dna = MBinToMDna(MBin);
		System.out.println("Msg. in DNA (sM): " + Dna);
		String Rna = MDnaToMRna(Dna);
		CipherDetails cipherDetails = MRnaToMEng(Rna);

		return cipherDetails;
	}

	public String processConversionAfterEncryption(String cipherEnglish, String AMBIG) {
		String cipherRNA = cipherEnglishToRNAZERO(cipherEnglish);
		String finalCipherRNA = mergeTogether(cipherRNA, AMBIG);
		return finalCipherRNA;
	}

	public CipherDetails processConversionBeforeDecryption(String cipher) {
		int i = 0;
		String cipherRNA = new String();
		String ambig = new String();

		while (cipher.length() > i) {
			for (int j = 0; j < 3; j++) {
				cipherRNA += cipher.charAt(i);
				i++;
			}
			if(cipher.length() > i)
				ambig += cipher.charAt(i);
			i++;

		}
//		System.out.println("cipher RNA: " + cipherRNA);
		System.out.println("Ambig RNA: " + ambig);
		
		String ambigDic = AmbigtoDicmal(ambig);
		String English = MRnaToMEngWihoutSave(cipherRNA);

		CipherDetails cipherDetails = new CipherDetails();
		cipherDetails.setAMBIG(ambigDic);
		cipherDetails.setMEnglish(English);

		return cipherDetails;
	}

	public String processConversionAfterDecryption(String cipher, String AMBIG) {
		String cipherRNA = cipherEnglishToRNA(cipher, AMBIG);
		String cipherDNA = MRnaToMDna(cipherRNA);
		String cipherBin = MDnaToMBin(cipherDNA);
		String msg = ToText(cipherBin);
		return msg;
	}

	private String AmbigConversion(String ambig) {
		String[] splitAmbig = splitToNChar(ambig, 1);
		String MDna = new String();
		for (int i = 0; i < splitAmbig.length; i++) {
			if (splitAmbig[i].equals("0")) {
				MDna += "A";
			} else if (splitAmbig[i].equals("1")) {
				MDna += "C";
			} else if (splitAmbig[i].equals("2")) {
				MDna += "G";
			} else if (splitAmbig[i].equals("3")) {
				MDna += "T";
			}
		}
		System.out.println("convert AMBIG to Dna: " + MDna);
		return MDna;
	}

	private String AmbigtoDicmal(String ambig) {
		String[] splitAmbig = splitToNChar(ambig, 1);
		String MBin = new String();
		for (int i = 0; i < splitAmbig.length; i++) {
			if (splitAmbig[i].equals("A")) {
				MBin += "0";
			} else if (splitAmbig[i].equals("C")) {
				MBin += "1";
			} else if (splitAmbig[i].equals("G")) {
				MBin += "2";
			} else if (splitAmbig[i].equals("T")) {
				MBin += "3";
			}
		}
//		System.out.println("convert AMBIG to Binary: " + MBin);
		return MBin;
	}

	private String mergeTogether(String cipherRNA, String AMBIG) {
		String finalCipherRNA = new String();
		int countCipher = 0;
		int countAmbig = 0;
		while (finalCipherRNA.length() < (cipherRNA.length() + AMBIG.length())) {
			for (int j = 0; j < 3; j++) {
				finalCipherRNA += cipherRNA.charAt(countCipher);
				countCipher++;
			}
			if(AMBIG.length() > countAmbig)
				finalCipherRNA += AMBIG.charAt(countAmbig);
			countAmbig++;
		}
//		System.out.println("Final Cipher RNA: " + finalCipherRNA);
		return finalCipherRNA;
	}

	// DERTFLNZCNZG
	private String ToText(String Decrypt_Bin) {
		String[] splitBin = splitToNChar(Decrypt_Bin, 8);
		String msg = new String();
		for (int i = 0; i < splitBin.length; i++)
			msg += (char) Integer.parseInt(splitBin[i], 2);
//		System.out.println("finally msg is :  " + msg);
		return msg;
	}

	private String cipherEnglishToRNA(String CipherEnglish, String AMBIG) {
		String cipherRNA = new String();
		for (int k = 0; k < CipherEnglish.length(); k++) {
			if ((k >= AMBIG.length())) {
				break;
			}
			int ambig = Integer.parseInt(String.valueOf(AMBIG.charAt(k)));
			cipherRNA += distributionCodons.returnRNA(CipherEnglish.charAt(k), ambig);
		}
//		System.out.println("convert to ciphered RNA: " + cipherRNA);
		return cipherRNA;
	}

	private String cipherEnglishToRNAZERO(String CipherEnglish) {
		String cipherRNA = new String();
		for (int k = 0; k < CipherEnglish.length(); k++) {
			int ambig = 0;
			cipherRNA += distributionCodons.returnRNA(CipherEnglish.charAt(k), ambig);
		}
//		System.out.println("convert to ciphered RNA: " + cipherRNA);
		return cipherRNA;
	}

	// b
	private String MDnaToMBin(String MDna) {
		String[] splitMDna = splitToNChar(MDna, 1);
		String MBin = new String();
		for (int i = 0; i < splitMDna.length; i++) {
			if (splitMDna[i].equals("A")) {
				MBin += "00";
			} else if (splitMDna[i].equals("C")) {
				MBin += "01";
			} else if (splitMDna[i].equals("G")) {
				MBin += "10";
			} else if (splitMDna[i].equals("T")) {
				MBin += "11";
			}
		}
		System.out.println("convert to Binary: " + MBin);
		return MBin;
	}

	// a
	private String MRnaToMDna(String MRna) {
		String MDna = new String();
		char[] a = MRna.toCharArray();
		for (char i : a) {
//			if (i == 'T')
//				MDna += 'T';
//			else
				MDna += i;
		}
		System.out.println("convert to ciphered DNA: " + MDna);
		return MDna;
	}

	// 1
	private String MsgToMBin(String msg) {
		byte[] bytes = msg.getBytes();
//		System.out.print("convert to ascii code : ");
//		for (int i = 0; i < bytes.length; i++)
//			System.out.print(bytes[i] + " ");
//		System.out.println("");

		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
//		System.out.println("convert to binary : " + binary);
		return binary.toString();
	}

	private String[] splitToNChar(String text, int size) {
		List<String> parts = new ArrayList<>();
		int length = text.length();
		for (int i = 0; i < length; i += size) {
			parts.add(text.substring(i, Math.min(length, i + size)));
		}
		return parts.toArray(new String[0]);
	}

	// 2
	private String MBinToMDna(String MBin) {
		String[] splitMBin = splitToNChar(MBin, 2);
		String MDna = new String();
		for (int i = 0; i < splitMBin.length; i++) {
			if (splitMBin[i].equals("00")) {
				MDna += "A";
			} else if (splitMBin[i].equals("01")) {
				MDna += "C";
			} else if (splitMBin[i].equals("10")) {
				MDna += "G";
			} else if (splitMBin[i].equals("11")) {
				MDna += "T";
			}
		}
//		System.out.println("convert to DNA : " + MDna);
		return MDna;
	}

	// 3
	private String MDnaToMRna(String MDna) {
		String MRna = new String();
		char[] a = MDna.toCharArray();
		for (char i : a) {
//			if (i == 'T')
//				MRna += 'T';
//			else
				MRna += i;
		}
//		System.out.println("convert to RNA : " + MRna);
		return MRna;
	}

	// 4
	private CipherDetails MRnaToMEng(String MRna) {
		String[] splitMRna = splitToNChar(MRna, 3);
		String MEng = new String();
		String AMBIG = new String();
		for (int k = 0; k < splitMRna.length; k++) {
			char alphabet = distributionCodons.getAlphabet(splitMRna[k]);
			int ambig = distributionCodons.getAMBIG(splitMRna[k]);
			if (alphabet != '0' && ambig != '0') {
				MEng += alphabet;
				AMBIG += ambig;
			}

		}
//		System.out.println("convert to English alphabet : " + MEng);
//		System.out.println("AMBIG : " + AMBIG);
		AMBIG = AmbigConversion(AMBIG);

		CipherDetails cipherDetails = new CipherDetails();
		cipherDetails.setAMBIG(AMBIG);
		cipherDetails.setMEnglish(MEng);
		return cipherDetails;
	}

	private String MRnaToMEngWihoutSave(String MRna) {
		String[] splitMRna = splitToNChar(MRna, 3);
		String MEng = new String();
		String AMBIG = new String();
		for (int k = 0; k < splitMRna.length; k++) {
			char alphabet = distributionCodons.getAlphabet(splitMRna[k]);
			int ambig = distributionCodons.getAMBIG(splitMRna[k]);
			if (alphabet != '0' && ambig != '0') {
				MEng += alphabet;
				AMBIG += ambig;
			}
		}
//		System.out.println("convert to English alphabet : " + MEng);
		return MEng;
	}
}
