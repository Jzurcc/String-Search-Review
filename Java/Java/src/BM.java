import java.util.Arrays;
class BM {
	static int max(int a, int b) { return (a > b) ? a : b; }

	static void search(char[] txt, char[] pat) {
		long startTime = System.nanoTime();
		int m = pat.length;
		int n = txt.length;
		int a = 256;
		int shifts = 0;
		// preprocessing
		int[] badchar = new int[a];
		Arrays.fill(badchar, -1);
		for (int i = 0; i < m; i++)
			badchar[pat[i]] = i;

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
				System.out.println("Pattern found at index " + (i));
				i += (i + m < n) ? m - badchar[txt[i + m]] : 1;
			} else {
				i += max(1, j - badchar[txt[i + j]]);
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e9;
		double throughput = (double) n / runningTime;

		System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " seconds");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters per second");
	}

	public static void main(String[] args) {
		char[] txt = "aabaacaadaabaaba".toCharArray();
		char[] pat = "aaba".toCharArray();
		search(txt, pat);
	}
}
