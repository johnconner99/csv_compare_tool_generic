package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import csv.GenericCSV;

public class FileUtil<T extends GenericCSV<T>> {
	
	public Map<T, BigDecimal> match = new HashMap<T, BigDecimal>();
	public Map<T, BigDecimal> mismatch = new HashMap<T, BigDecimal>();
	public Map<T, BigDecimal> extraUAT = new HashMap<T, BigDecimal>();
	public Map<T, BigDecimal> extraProd = new HashMap<T, BigDecimal>();


	//generalize
	public Map<T, BigDecimal> readCSVFile(String path, T csv) throws IOException {
		String[] line;
		String oneLiner;
		Map<T, BigDecimal> map = new HashMap<T, BigDecimal>();
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//read first line - this is header
		
		//validate header
		
		
		while ((oneLiner = br.readLine()) != null) {
			line = oneLiner.split(",");

			if (line[0].equalsIgnoreCase("curve_date")) {//not generic!!-- pass as lamda
				line = br.readLine().split(","); // skip the header
			}
			T csv2 = csv.readCSVRow(line);
			map.put(csv2, new BigDecimal(line[2]));
		}
		br.close();

		System.out.println(map);
		return map;
	}

	
	//generalize
	public void prepareCSVReport(Map<T, BigDecimal> input, String destination, T csv) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(destination));
		bw.write(csv.header);// pass as lamda
		bw.newLine();
		for (T csv2 : input.keySet()) {
			bw.write(csv.getCSVRowToWrite(csv2));// pass as lamda
			bw.newLine();
		}
		bw.close();
	}

	public void analyseMismatch(Map<T, BigDecimal> input) {

	}

	public void analyseExtraUAT(Map<T, BigDecimal> input) {

	}

	public void analyseExtraProd(Map<T, BigDecimal> input) {

	}

	//generalize
	public void compareCSVFile(Map<T, BigDecimal> uat, Map<T, BigDecimal> prod) {
	
		for (T key : uat.keySet()) {
			if (prod.containsKey(key)) {//filter??
				if (prod.get(key).equals(uat.get(key))) {
					match.put(key, uat.get(key));
				} else {
					mismatch.put(key, uat.get(key));
				}
			} else {
				extraUAT.put(key, uat.get(key));
			}
		}

		for (T key : prod.keySet()) {
			if (!uat.containsKey(key)) {
				extraProd.put(key, prod.get(key));
			}
		}
	}
	
	public void publishAllReports(T csv) throws IOException{
		prepareCSVReport(match, "F:\\practice\\book_vol_match.csv", csv);
		prepareCSVReport(mismatch, "F:\\practice\\book_vol_mismatch.csv", csv);
		prepareCSVReport(extraProd, "F:\\practice\\book_vol_extraProd.csv", csv);
		prepareCSVReport(extraUAT, "F:\\practice\\book_vol_extraUAT.csv", csv);
	
	}

}
