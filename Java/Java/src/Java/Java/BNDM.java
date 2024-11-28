package Java.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BNDM {
	public static List<Double> search(String pattern, String text) {
		int m = pattern.length();
		int n = text.length();
		int a = 2;
		double shifts = 0;
		int[] B = new int[a];

		long startTime = System.nanoTime();

		int s = 1;
		for (int i = m - 1; i >= 0; i--) {
			B[pattern.charAt(i) - '0'] |= s;
			s <<= 1;
		}

		int i = 0;
		while (i <= n - m) {
			int k = m - 1;
			int last = m;
			int d = ~0;
			while (k >= 0 && d != 0) {
				shifts++;
				d &= B[text.charAt(i + k) - '0'];
				k--;
				if (d != 0) {
					if (k >= 0) {
						last = k + 1;
					} 
				} else {
					System.out.print("");
				}
				d <<= 1;
			}
			i += last;
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
		
		String txt = "RFC8010IPP/11:EncodingandTransportJanuary2017315AdditionalvalueEach\"additionalvalue\"fieldisencodedasfollows:|valuetag|1byte|namelength(valueis0x0000)|2bytes|valuelength(valueisw)|2bytes|value|wbytesFigure5:AdditionalAttributeValueEncodingAn\"additionalvalue\"isencodedwithfoursubfields:oThe\"valuetag\"fieldspecifiestheattributesyntax,eg,0x44fortheattributesyntax'keyword'oThe\"namelength\"fieldhasthevalueof0inordertosignifythatitisan\"additionalvalue\"Thevalueofthe\"namelength\"fielddistinguishesan\"additionalvalue\"field(\"namelength\"is0)froman\"attributewithonevalue\"field(\"namelength\"isnot0)oThe\"valuelength\"fieldspecifiesthelengthofthe\"value\"fieldinbytes,eg,wintheabovediagramor19forthe(keyword)value'twosidedlongedge'oThe\"value\"fieldcontainsthevalueoftheattribute,eg,thetextualvalue'twosidedlongedge'Sweet&McDonaldStandardsTrack[Page11]RFC8010IPP/11:EncodingandTransportJanuary2017316CollectionAttributeCollectionattributescreateanamedgroupcontainingrelated\"member\"attributesThe\"attributewithonevalue\"fiel";
		String pat = "namelength(valueis0x0000)";
		// String txt = "AATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAA";
		// String pat = "GCAAAAAAACTGGAAAATTGGCGAG";
		// String txt = "GTCTAGCCGCTGGTAAACACTCCATGACCCCGGCTCTCCATTGATGCCACGGCGATTGTTGGAGAGCCAGCAGCGACTGCAAACGTCAGATCAGAGTAATACTAGCAAGCGATAAGTCCCTAACTGGTTGTGGCCTTCTGTAGAGTGAACTTCACCACATATGCTGTCTCTGGCACGTGGATGGTTTGGAGAAATCAGATTCAAGTCTGATCAACCTTCAAACAGATCTAGAGTCTAAAACAGTGATCTCCTGCGTGCGAGATAGAAATACTAGGTAACTACAGGGACTGCGACGTTTTAAACGTTGGTCCGTCAGAAGCGCCATTCAGGATCACGTTACCCCGAAAAAAAGGTACCAGGAGCTCTTCTCCTCTGCAGTCAGGTCTATAGAAACTACACCATTAACCTTCCTGAGAACCGGGAGGTGGGAATCCGTCACATATGAGAAGGTATTTGCCCGATAATCAATACTCCAGGCTTCTAACTTTTTCCACTCGCTTGAGCTACTGTGGCCTTTCTGCCTGAAGATTCGTTGGACTGGTGCCAACGCTGAGCTACTGTTCCAGGAGAATTATCCGGGGGCAGTGACAACCAACATCTCGGGTCTTGCCCAACCGGTCTACACGCTGATATATGAGCTACTGGAGAACCCGGCGCCACGCAATGGAACGTCCTTAACTCTGGCAGGCAATTAAAGGGAACGTATATATAACGCAAAAAAACTGGAAAATTGGCGAGAGAATCTTCTCTCTGTCTATCGAAGAATGGCCACGCGGAGGCATGCGTCATGCTAGCGTGCGGGGTACTCTTGCTATCCATTTGGGTCACAGGACACTCGCTGTTTTCGAATTTACCCTTTATGCGCCGGTATTGAACCACGCTTATGCCCAGCATCGTTACAACCAGACTGATACTAGATGTATAATGAGCTACTGGCAGACGAAACCAGTCGGAGATTACCGAGCATTCTATCACGTCGGCGACCACTAGTGAGCTACTG";
		// txt = new String(new char[100]).replace("\0", txt);
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
