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
			} 
			if (j == m) {
				// System.out.println("Pattern found at index " + (i-j));
				j = lps[j - 1];
			}
			else if (i < n && pattern.charAt(j) != text.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) n / runningTime;

		System.out.println("Total shifts: " + shifts);
		System.out.println("Running time: " + String.format("%.6f", runningTime) + "s |" + "Throughput: " + String.format("%.2f", throughput) + " characters/ms");
	}

	public static void main(String[] args) {
		String text = "0111100011111001101000001101111100101001110010010111110100001011111111110001101010010110110101001011100110111001111111001010101010101011010100110111011011010000011101110100010101111110111100010110100010011000101000100010010100100110001101011001001000011110011001010000111101000110011011001010101100001101010011011010100101110101001110101000011101001001000010111001000010111001111100110111100011010001100010010110100110110110111100011011001011111010001000001001000110100001111101100100100101101000001111000011111010011100101010100000000111110010001000010011001101010011001001110100011010101101100001100001011001101011011111001011011000110011100000001111001101010101101000000100110111001111000000010101100010111010011111100100000011110000101101011010000001001111101101110001101101000111010100101111000110101010110101010000101011111100000111011010101000111100000000111011011111010111101001000110010100010111100110110011011001110110111110000111011000100001100010111110010011011100000100000111110011111000";
		String pattern = "1001111011";
		for (int i = 0; i < 10; i++) {
			Search(pattern, text);
		}
		
	}
}
