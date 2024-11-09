using System.Diagnostics;

class KMP {
	static void Search(string pattern, string text) {
		int n = text.Length;
		int m = pattern.Length;
		int comparisons = 0;

		int[] lps = new int[m];
		List<int> res = [];

		Stopwatch stopwatch = Stopwatch.StartNew();

		// preprocessing - calculating LPS
		int len = 0;
		lps[0] = 0;

		int i = 1;
		while (i < pattern.Length) {
			comparisons++;
			if (pattern[i] == pattern[len]) {
				len++;
				lps[i] = len;
				i++;
			}
			else {
				if (len != 0) {
					len = lps[len - 1];
				}
				else {
					lps[i] = 0;
					i++;
				}
			}
		}

		// actual searching
		i = 0;
		int j = 0;

		while (i < n) {
			if (text[i] == pattern[j]) {
				i++;
				j++;
				if (j == m) {
					Console.WriteLine($"Pattern found at index {i}");
					j = lps[j - 1];
				}
			}
			else {
				if (j != 0)
					j = lps[j - 1];
				else
					i++;
			}
		}
		stopwatch.Stop();
		double runningTime = stopwatch.Elapsed.TotalSeconds;
		double throughput = n / runningTime;
	}

	static void Main(string[] args) {
		string text = "aabaacaadaabaabaa";
		string pattern = "aaba";
		Search(pattern, text);
	}
}
