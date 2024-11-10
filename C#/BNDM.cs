using System.Diagnostics;
class BNDM {
    static void Search(string pattern, string text) {
        Stopwatch stopwatch = Stopwatch.StartNew();
        int m = pattern.Length;
        int n = text.Length;
        int a = 256;
        int shifts = 0;

        int[] B = new int[a];
        int s = 1;
        for (int i = m - 1; i >= 0; i--) {
            B[(int)pattern[i]] |= s;
            s <<= 1;
        }

        int j = 0;
        while (j <= n - m) {
            int i = m - 1;
            int last = m;
            int d = ~0;
            while (i >= 0 && d != 0) {
                shifts++;
                d &= B[(int)text[j + i]];
                i--;
                if (d != 0) {
                    if (i >= 0) {
                        last = i + 1;
                    } else {
                        Console.WriteLine($"Pattern found at index {j}");
                    }
                }
                d <<= 1;
            }
            j += last;
        }

        stopwatch.Stop();
        double runningTime = stopwatch.Elapsed.TotalSeconds;
        double throughput = n / runningTime;
        Console.WriteLine(shifts);
    }

    public static void Main(string[] args) {
        string text = "aabaacaadaabaaba";
        string pattern = "aaba";
        Search(pattern, text);
    }
}
