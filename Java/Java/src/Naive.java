public class Naive {
	public static double Search(String pattern, String text) {
		double startTime = System.nanoTime();
		int m = pattern.length();
		int n = text.length();
		int shifts = 0;

		for (int i = 0; i < n - m + 1; i++) {
			boolean match = true;
			for (int j = 0; j < m; j++) {
				shifts++;
				if (text.charAt(i + j) != pattern.charAt(j)) {
					match = false;
				}
			}

			// if (match) {
			// 	System.out.println("Pattern found at index " + i);
			// }
		}

		double endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1000000;
		// double runningTimeMs = runningTime ;
		double throughput = (double) n / runningTime;

		// System.out.println("Total shifts: " + shifts);
		// System.out.println("Running time: " + String.format("%.6f", runningTimeMs) + " ms");
		// System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters per ms");
		
		if (throughput > 0)
		{
			return throughput;
		}
		else
		{
			return Search(pattern, text);
		}
	}

	public static void main(String[] args) {
		double sum = 0;
		for (int i = 0; i < 100; i++) {
			String txt1 = "aabaacaadaabaaba";
			String pat1 = "aaba";
			sum += Search(pat1, txt1);
		}
		System.out.println(sum/100);
		
		
	}
}
