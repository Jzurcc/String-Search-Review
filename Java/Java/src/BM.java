import java.util.Arrays;
class BM {
	static int max(int a, int b) { return (a > b) ? a : b; }

	static int 	index(char c) {
		switch (c) {
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: throw new IllegalArgumentException("Invalid DNA character: " + c);
		}
	}

	static void search(char[] txt, char[] pat) {
		long startTime = System.nanoTime();
		int m = pat.length;
		int n = txt.length;
		int a = 4;
		int shifts = 0;
		// preprocessing
		int[] badchar = new int[a];
		for (int i = 0; i < a; i++)
			badchar[i] = -1;
		for (int i = 0; i < m; i++)
			badchar[index(pat[i])] = i;


		// actual searching
		int i = 0;
		while (i <= (n - m)) {
			int j = m - 1;
			while (j >= 0 && pat[j] == txt[i + j]) {
				shifts++;
				j--;
			}
			shifts++;
			if (j < 0) {
				// System.out.println("Pattern found at index " + (i));
				i += (i + m < n) ? m - badchar[index(txt[i + m])] : 1;
			} else {
				i += max(1, j - badchar[index(txt[i + j])]);
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) n / runningTime;

		System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " ms");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters/ms");
	}

	public static void main(String[] args) {
		char[] txt = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG".toCharArray();
		char[] pat = "TGAGCTACTG".toCharArray();
		search(txt, pat);
	}
}
