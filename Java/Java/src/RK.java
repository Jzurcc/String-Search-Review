import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RK {

	static List<Double> search(String pat, String txt) {
		int q = 2147483647; // max 32 int
		int M = pat.length();
		int N = txt.length();
		int p = 0;
		int t = 0;
		int h = 1;
		int a = 4;
		int shifts = 0;

		long startTime = System.nanoTime();

		for (int i = 0; i < M - 1; i++)
			h = (h * a) % q;

		for (int i = 0; i < M; i++) {
			p = (a * p + pat.charAt(i)) % q;
			t = (a * t + txt.charAt(i)) % q;
		}

		for (int i = 0; i <= N - M; i++) {
			if (p == t) {
				int j;
				for (j = 0; j < M; j++) {
					shifts++;
					if (txt.charAt(i + j) != pat.charAt(j))
						break;
				}

				// if (j == M)
				//     System.out.println("Pattern found at index " + i);
			}

			if (i < N - M) {
				t = (a * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
				if (t < 0)
					t = (t + q);
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) N / runningTime;

		// System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " ms");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters/ms");
		List<Double> list = new ArrayList<>(Arrays.asList(runningTime, throughput));
		return list;
	}

	public static void main(String[] args) {
		String txt = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG";
		String pat = "TGAGCTACTG";
		double runningTime = 0;
		double throughput = 0;
		for (int i = 0; i < 100; i++) {
			List<Double> data = search(pat, txt);
			runningTime += data.get(0);
			throughput += data.get(1);
		}
		System.out.println(runningTime/100);
		System.out.println(throughput/100);
	}
}
