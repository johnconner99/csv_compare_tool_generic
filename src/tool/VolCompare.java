package tool;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import csv.VolCSV;
import file.FileUtil;

public class VolCompare {
	public static void main(String [] args){
		VolCSV vol = new VolCSV();
		FileUtil<VolCSV> util = new FileUtil<VolCSV>();
		try {
			
			Map<VolCSV, BigDecimal> volUAT = util.readCSVFile("F:\\practice\\book_vol_uat.csv", vol);
			Map<VolCSV, BigDecimal> volProd = util.readCSVFile("F:\\practice\\book_vol_prod.csv", vol);

			util.compareCSVFile(volUAT, volProd,vol);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
