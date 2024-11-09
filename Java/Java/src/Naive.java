public class Naive {
	public static void Search(String pattern, String text) {
		long startTime = System.nanoTime();
		int m = pattern.length();
		int n = text.length();
		int comparisons = 0;

		for (int i = 0; i < n -  + 1; i++) {
			boolean match = true;
			for (int j = 0; j < m; j++) {
				comparisons++;
				if (text.charAt(i + j) != pattern.charAt(j)) {
					match = false;
				}
			}

			if (match) {
				System.out.println("Pattern found at index " + i);
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
		String txt1 = "Hello world";
		String pat1 = "world";
		System.out.println("Example 1:");
		Search(pat1, txt1);

		String txt2 = "agd";
		String pat2 = "g";
		System.out.println("\nExample 2:");
		Search(pat2, txt2);
	}
}
