class KMP {
	public static void Search(String pattern, String txt) {
		long startTime = System.nanoTime();
		int n = txt.length();
		int m = pattern.length();
		int comparisons = 0;
		int[] lps = new int[m];

		// preprocessing
		int len = 0;
		lps[0] = 0;
		int i = 1;
		while (i < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}

		// actual searching
		i = 0;
		int j = 0;
		while (i < n) {
			comparisons++;
			if (txt.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
				if (j == m) {
					System.out.println("Pattern found at index " + (i-j));
					j = lps[j - 1];
				}
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e9;
		double throughput = (double) n / runningTime;

		System.out.println("Total comparisons: " + comparisons);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " seconds");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters per second");
	}

	public static void main(String[] args) {
		String txt = "aabaacaadaabaaba";
		String pattern = "aaba";
		Search(pattern, txt);
	}
}
