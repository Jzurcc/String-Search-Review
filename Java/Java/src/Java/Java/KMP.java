package Java.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
	public static List<Double> Search(String pattern, String text) {
		int n = text.length();
		int m = pattern.length();
		double shifts = 0;
		int[] lps = new int[m];

		// preprocessing
		int len = 0;
		lps[0] = 0;
		int i = 1;
		while (i < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}

		long startTime = System.nanoTime();
		// actual searching
		i = 0;
		int j = 0;
		while (i < n) {
			shifts++;
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} 
			if (j == m) {
				// System.out.println("Pattern found at index " + (i-j));
				j = lps[j - 1];
			}
			else if (i < n && pattern.charAt(j) != text.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

		long endTime = System.nanoTime();
		double runningTime = (endTime - startTime) / 1e6;
		double throughput = (double) n / runningTime;

		// System.out.println("Total shifts: " + shifts);
		// System.out.println("Running time: " + String.format("%.6f", runningTime) + "s |" + "Throughput: " + String.format("%.2f", throughput) + " characters/ms");
		List<Double> list = new ArrayList<>(Arrays.asList(runningTime, throughput, shifts));
		if (runningTime > 0)
		{
			return list;
		}
		else
		{
			return Search(pattern, text);
		}
		
	}

	public static void main(String[] args) {
		String txt = "RFC8010IPP/11:EncodingandTransportJanuary2017315AdditionalvalueEach\"additionalvalue\"fieldisencodedasfollows:|valuetag|1byte|namelength(valueis0x0000)|2bytes|valuelength(valueisw)|2bytes|value|wbytesFigure5:AdditionalAttributeValueEncodingAn\"additionalvalue\"isencodedwithfoursubfields:oThe\"valuetag\"fieldspecifiestheattributesyntax,eg,0x44fortheattributesyntax'keyword'oThe\"namelength\"fieldhasthevalueof0inordertosignifythatitisan\"additionalvalue\"Thevalueofthe\"namelength\"fielddistinguishesan\"additionalvalue\"field(\"namelength\"is0)froman\"attributewithonevalue\"field(\"namelength\"isnot0)oThe\"valuelength\"fieldspecifiesthelengthofthe\"value\"fieldinbytes,eg,wintheabovediagramor19forthe(keyword)value'twosidedlongedge'oThe\"value\"fieldcontainsthevalueoftheattribute,eg,thetextualvalue'twosidedlongedge'Sweet&McDonaldStandardsTrack[Page11]RFC8010IPP/11:EncodingandTransportJanuary2017316CollectionAttributeCollectionattributescreateanamedgroupcontainingrelated\"member\"attributesThe\"attributewithonevalue\"fiel";
		String pat = "(valueisw)";
		double runningTime = 0;
		double throughput = 0;
		for (int i = 0; i < 100; i++) {
			List<Double> data = Search(pat, txt);
			runningTime += data.get(0);
			throughput += data.get(1);
		}
		System.out.println(runningTime/100);
		System.out.println(throughput/100);
	}
}
