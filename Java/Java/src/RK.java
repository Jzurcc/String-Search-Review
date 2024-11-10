public class RK {

    static void search(String pat, String txt, int q) {
        int M = pat.length();
        int N = txt.length();
        int p = 0;
        int t = 0;
        int h = 1;
        int a = 256;
        int shifts = 0;

        long startTime = System.nanoTime();

        for (int i = 0; i < M - 1; i++)
            h = (h * a) % q;

        for (int i = 0; i < M; i++) {
            p = (a * p + pat.charAt(i)) % q;
            t = (a * t + txt.charAt(i)) % q;
        }

        for (int i = 0; i <= N - M; i++) {
            if (p == t) {
                int j;
                for (j = 0; j < M; j++) {
                    shifts++;
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }

            if (i < N - M) {
                t = (a * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }

        long endTime = System.nanoTime();
        double runningTime = (endTime - startTime) / 1e9;
        double throughput = (double) N / runningTime;

        System.out.println("Total shifts: " + shifts);
        System.out.println("Running time: " + String.format("%.6f", runningTime) + " seconds");
        System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters per second");
    }

    public static void main(String[] args) {
        String txt = "aabaacaadaabaaba";
        String pat = "aaba";
        int q = 11;

        search(pat, txt, q);
    }
}
