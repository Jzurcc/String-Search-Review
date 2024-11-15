using System;
using System.Diagnostics;
public class BM {
	static int a = 256;
	static int Max(int a, int b) { return (a > b) ? a : b; }

	static void Search(char[] pattern, char[] text) {
		Stopwatch stopwatch = Stopwatch.StartNew();
		int m = pattern.Length;
		int n = text.Length;
		int[] badchar = new int[a];
		int shifts = 0;

		// preprocessing - Bad Character Heuristic
		for (int i = 0; i < a; i++)
			badchar[i] = -1;

		for (int i = 0; i < m; i++)
			badchar[(int)pattern[i]] = i;

		// actual searching
		int s = 0;

		while (s <= (n - m)) {
			int j = m - 1;

			while (j >= 0 && pattern[j] == text[s + j]) {
				shifts++;
				j--;
			}

			shifts++;
			if (j < 0) {
				Console.WriteLine($"Pattern found at index {s}");
				s += (s + m < n) ? m - badchar[text[s + m]] : 1;
			} else {
				s += Max(1, j - badchar[text[s + j]]);
			}
		}

		stopwatch.Stop();
		double runningTime = stopwatch.Elapsed.TotalSeconds;
		double throughput = n / runningTime;
		Console.WriteLine(shifts);
	}

	public static void Main() {
		char[] text = "aabaacaadaabaaba".ToCharArray();
		char[] pattern = "aaba".ToCharArray();
		Search(pattern, text);
	}
}
