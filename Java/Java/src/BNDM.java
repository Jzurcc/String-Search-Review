public class BNDM {

	public static void search(String pattern, String text) {
		int m = pattern.length();
		int n = text.length();
		int a = 256;
		int shifts = 0;
		int[] B = new int[a];

		long startTime = System.nanoTime();

		int s = 1;
		for (int i = m - 1; i >= 0; i--) {
			B[pattern.charAt(i)] |= s;
			s <<= 1;
		}

		int i = 0;
		while (i <= n - m) {
			int k = m - 1;
			int last = m;
			int d = ~0;
			while (k >= 0 && d != 0) {
				shifts++;
				d &= B[text.charAt(i + k)];
				k--;
				if (d != 0) {
					if (k >= 0) {
						last = k + 1;
					} 
					// else {
					// 	System.out.println("Pattern found at index " + i);
					// }
				}
				d <<= 1;
			}
			i += last;
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) n / runningTime;

		System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " ms");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters/ms");
	}

	public static void main(String[] args) {
		String text = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG";
		String pattern = "TGAGCTACTG";
		search(pattern, text);
	}
}
