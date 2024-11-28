package Java.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BM {
	static int max(int a, int b) { return (a > b) ? a : b; }

	static List<Double> search(char[] pat, char[] txt) {
		long startTime = System.nanoTime();
		int m = pat.length;
		int n = txt.length;
		int a = 256;
		double shifts = 0;
		// preprocessing
		int[] badchar = new int[a];
		for (int i = 0; i < a; i++)
			badchar[i] = -1;
		for (int i = 0; i < m; i++)
			badchar[(int)(pat[i])] = i;


		// actual searching
		int i = 0;
		while (i <= (n - m)) {
			int j = m - 1;
			while (j >= 0 && pat[j] == txt[i + j]) {
				shifts++;
				j--;
			}
			shifts++;
			if (j < 0) {
				System.out.print("");
				i += (i + m < n) ? m - badchar[(int)(txt[i + m])] : 1;
			} else {
				i += max(1, j - badchar[(int)(txt[i + j])]);
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) n / runningTime;

		// System.out.println("Total shifts: " + shifts);
		// System.out.println("Running time: " + String.format("%.6f", runningTime) + " ms");
		// System.out.println("Throughput: " + String.format("%.2f", throughput) + " characters/ms");
		List<Double> list = new ArrayList<>(Arrays.asList(runningTime, throughput, shifts));
		return list;
	}

	public static void main(String[] args) {
		
		// String txt = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG";
		char[] txt = "RFC8010IPP/11:EncodingandTransportJanuary2017315AdditionalvalueEach\\\"additionalvalue\\\"fieldisencodedasfollows:|valuetag|1byte|namelength(valueis0x0000)|2bytes|valuelength(valueisw)|2bytes|value|wbytesFigure5:AdditionalAttributeValueEncodingAn\\\"additionalvalue\\\"isencodedwithfoursubfields:oThe\\\"valuetag\\\"fieldspecifiestheattributesyntax,eg,0x44fortheattributesyntax'keyword'oThe\\\"namelength\\\"fieldhasthevalueof0inordertosignifythatitisan\\\"additionalvalue\\\"Thevalueofthe\\\"namelength\\\"fielddistinguishesan\\\"additionalvalue\\\"field(\\\"namelength\\\"is0)froman\\\"attributewithonevalue\\\"field(\\\"namelength\\\"isnot0)oThe\\\"valuelength\\\"fieldspecifiesthelengthofthe\\\"value\\\"fieldinbytes,eg,wintheabovediagramor19forthe(keyword)value'twosidedlongedge'oThe\\\"value\\\"fieldcontainsthevalueoftheattribute,eg,thetextualvalue'twosidedlongedge'Sweet&McDonaldStandardsTrack[Page11]RFC8010IPP/11:EncodingandTransportJanuary2017316CollectionAttributeCollectionattributescreateanamedgroupcontainingrelated\\\"member\\\"attributesThe\\\"attributewithonevalue\\\"fiel".toCharArray();
		// txt = new String(new char[100]).replace("\0", txt);
		// char[] txt1 = new String(new char[1000]).replace("\0", txt).toCharArray();
		char[] pat = "(valueisw)".toCharArray();
		
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
