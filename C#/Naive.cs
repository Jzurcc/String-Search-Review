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
			bool match = true;
			for (int j = 0; j < m; j++)
			{
				comparisons++;
				if (text[i+j] != pattern[j])
				{
					match = false;
				}
			}

			if (match) 
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
		string txt1 = "aabaacaadaabaaba";
		string pat1 = "aaba";
		Console.WriteLine("Example 1:");
		Search(pat1, txt1);
	}
}
