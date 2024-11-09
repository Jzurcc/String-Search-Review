using System.Diagnostics;
public class RK {
    static void Search(string pattern, string text, int q) {
        Stopwatch stopwatch = Stopwatch.StartNew();
        int m = pattern.Length;
        int n = text.Length;
        int p = 0;
        int t = 0;
        int h = 1;
        int a = 256;
        double comparisons = 0;

        for (int i = 0; i < m - 1; i++)
            h = h * a % q;

        for (int i = 0; i < m; i++) {
            p = (a * p + pattern[i]) % q;
            t = (a * t + text[i]) % q;
        }

        for (int i = 0; i < n - m + 1; i++) {
            if (p == t) {
                int j;
                for (j = 0; j < m; j++) {
                    comparisons++;
                    if (text[i + j] != pattern[j])
                        break;
                }

                if (j == m)
                    Console.WriteLine("Pattern found at index " + i);
            }

            if (i < n - m) {
                t = (a * (t - text[i] * h) + text[i + m]) % q;

                if (t < 0)
                    t += q;
            }
        }

        stopwatch.Stop();
        double runningTime = stopwatch.Elapsed.TotalSeconds;
        double throughput = n / runningTime;
        Console.WriteLine(comparisons);
    }

    public static void Main() {
        string text = "aabaacaadaabaaba";
        string pattern = "aaba";
        int q = 11;
        Search(pattern, text, q);
    }
}
