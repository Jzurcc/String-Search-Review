class KMP {
	public static void Search(String pattern, String text) {
		long startTime = System.nanoTime();
		int n = text.length();
		int m = pattern.length();
		int shifts = 0;
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
			shifts++;
			if (text.charAt(i) == pattern.charAt(j)) {
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

		System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + " seconds");
		System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters per second");
	}

	public static void main(String[] args) {
		String text = "aabaacaadaabaaba";
		String pattern = "aaba";
		for (int i = 0; i < 10; i++) {
			Search(pattern, text);
		}
		
	}
}
