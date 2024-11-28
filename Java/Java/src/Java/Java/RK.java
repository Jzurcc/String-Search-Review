package Java.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RK {

	static List<Double> search(String pat, String txt) {
		long startTime = System.nanoTime();
		int q = 2147483629    ; // max 32 int 2147483629
		int M = pat.length();
		int N = txt.length();
		int p = 0;
		int t = 0;
		int h = 1;
		int a = 4;
		double shifts = 0;

		

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

				// if (j == M)
				//     System.out.println("Pattern found at index " + i);
			}

			if (i < N - M) {
				t = (a * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
				if (t < 0)
					t = (t + q);
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) N / runningTime;

		// System.out.println("Total shifts: " + shifts);
		// System.out.println("Running time: " + String.format("%.6f", runningTime) + " ms");
		// System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters/ms");
		List<Double> list = new ArrayList<>(Arrays.asList(runningTime, throughput, shifts));
		return list;
	}

	public static void main(String[] args) {
		// String txt = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG";
		// txt = new String(new char[100]).replace("\0", txt);
		// String txt = "ThenumberofoctetsforanintegerMUSTbe1,2,or4,dependingonusageintheprotocolAoneoctetinteger,henceforthc";
		// String pat = "ndingonusageintheprotocol";
		String txt = "RFC8010IPP/11:EncodingandTransportJanuary2017315AdditionalvalueEach\"additionalvalue\"fieldisencodedasfollows:|valuetag|1byte|namelength(valueis0x0000)|2bytes|valuelength(valueisw)|2bytes|value|wbytesFigure5:AdditionalAttributeValueEncodingAn\"additionalvalue\"isencodedwithfoursubfields:oThe\"valuetag\"fieldspecifiestheattributesyntax,eg,0x44fortheattributesyntax'keyword'oThe\"namelength\"fieldhasthevalueof0inordertosignifythatitisan\"additionalvalue\"Thevalueofthe\"namelength\"fielddistinguishesan\"additionalvalue\"field(\"namelength\"is0)froman\"attributewithonevalue\"field(\"namelength\"isnot0)oThe\"valuelength\"fieldspecifiesthelengthofthe\"value\"fieldinbytes,eg,wintheabovediagramor19forthe(keyword)value'twosidedlongedge'oThe\"value\"fieldcontainsthevalueoftheattribute,eg,thetextualvalue'twosidedlongedge'Sweet&McDonaldStandardsTrack[Page11]RFC8010IPP/11:EncodingandTransportJanuary2017316CollectionAttributeCollectionattributescreateanamedgroupcontainingrelated\"member\"attributesThe\"attributewithonevalue\"fiel";
		String pat = "namelength(valueis0x0000)";
		double runningTime = 0;
		double throughput = 0;
		for (int i = 0; i < 100; i++) {
			List<Double> data = search(pat, txt);
			runningTime += data.get(0);
			throughput += data.get(1);
		}
		System.out.println(runningTime/100);
		System.out.println(throughput/100);

	}
}
