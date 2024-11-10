using System.Diagnostics;
class Naive
{
	public static double Float(double minimum, double maximum)
{ 
    Random random = new Random();
    return random.NextDouble() * (maximum - minimum) + minimum;
}
	static void Search(string pattern, string text)
	{
		Stopwatch stopwatch = Stopwatch.StartNew();
		int m = pattern.Length;
		int n = text.Length;
		double  shifts = 0;

		for (int i = 0; i < n - m + 1; i++)
		{
			bool match = true;
			for (int j = 0; j < m; j++)
			{
				shifts++;
				if (text[i+j] != pattern[j])
				{
					match = false;
				}
			}

			if (match) 
			{
				// Console.WriteLine($"Pattern found at index {i}");
			}
		}

		stopwatch.Stop();
		double runningTime = stopwatch.Elapsed.TotalMilliseconds;
		double throughput = n / runningTime;
		Console.WriteLine(runningTime);
		Console.WriteLine(throughput);
	}

	static void Main()
	{
		// Example 1
		string txt1 = "aabaacaadaabaabaaaaasdsadaaaaaaaaa";
		string pat1 = "aaba";
		for (int i = 0; i < 1; i++) 
		{
			Search(pat1, txt1);
		}
		
	}
}
