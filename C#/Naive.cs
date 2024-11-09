using System.Diagnostics;
class Naive
{
	static void Search(string pattern, string text)
	{
		Stopwatch stopwatch = Stopwatch.StartNew();
		int m = pattern.Length;
		int n = text.Length;
		double  comparisons = 0;

		for (int i = 0; i < n - m + 1; i++)
		{
			int j = 0;
			while (j < m && text[i+j] == pattern[j])
			{
				j++;
				comparisons++;
			}

			if (j == m)
			{
				Console.WriteLine($"Pattern found at index {i}");
			}
		}

		stopwatch.Stop();
		double runningTime = stopwatch.Elapsed.TotalSeconds;
		double throughput = n / runningTime;
	}

	static void Main()
	{
		// Example 1
		string txt1 = "AABAACAADAABAABA";
		string pat1 = "AABA";
		Console.WriteLine("Example 1:");
		Search(pat1, txt1);
		
		// Example 2
		string txt2 = "agd";
		string pat2 = "g";
		Console.WriteLine("\nExample 2:");
		Search(pat2, txt2);
	}
}
