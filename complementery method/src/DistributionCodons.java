import java.util.HashMap;

public class DistributionCodons {

	private String[][] AA =
	{
			{"GCT" , "GCC" , "GCA", "GCG"},
			{"TAA" , "TGA" , "TAG"},
			{ "TGT" , "TGC"},
			{"GAT" , "GAC"},
			{"GAA" , "GAG"},
			{"TTT" , "TTC"},
			{"GGT" , "GGC" , "GGA" , "GGG"},
			{"CAT" , "CAC"},
			{"ATT" , "ATC" , "ATA"},
			{"AAG"},
			{"CTT" , "CTC" , "CTA" , "CTG"},
			{"ATG"},
			{"AAT" , "AAC"},
			{"TTA" , "TTG"},
			{"CCT" , "CCC" , "CCA" , "CCG"},
			{"CAA" , "CAG"},
			{"CGT" , "CGC" , "CGA" , "CGG"},
			{"TCT" , "TCC" , "TCA" , "TCG"},
			{"ACT" , "ACC" , "ACA" , "ACG"},
			{"AGA" , "AGG"},
			{"GTT" , "GCT" , "GTA" , "GTG"},
			{"TGG"},
			{"AGT" , "AGC"},
			{"TAT"},
			{"TAC"}
	};

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


	public char getAlphabet(String RNA) {
		for (int i = 0 ; i < AA.length ; i++ ) {
			for (int j = 0 ; j < AA[i].length ; j++) {
				if (AA[i][j].equals(RNA)) {
					int ascii = i+65 ;
					if (ascii > 73) 
						ascii ++ ;
					return (char) ascii ;
				}
			}
		}
		System.out.println("Not Found Alphabet of the RNA : " + RNA);
		return '0';
	}
	
	public int getAMBIG(String RNA) {
		for (int i = 0 ; i < AA.length ; i++ ) {
			for (int j = 0 ; j < AA[i].length ; j++) {
				if (AA[i][j].equals(RNA)) {
					int ascii = i+65 ;
					if (ascii > 73) 
						ascii ++ ;
					return j;
				}
			}
		}
		System.out.println("Not Found Alphabet of the RNA : " + RNA);
		return '0';
	}
	public String returnRNA(char EnglishLetter, int ambig) {
		int tmp ; 
		if ((int)EnglishLetter>74)
			tmp = (int) EnglishLetter-66 ;
		else 
			tmp = (int) EnglishLetter-65 ;
		return  AA[tmp][ambig]; 
	}

}
