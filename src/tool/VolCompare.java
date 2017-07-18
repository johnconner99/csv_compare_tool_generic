package tool;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import csv.VolCSV;
import file.FileUtil;

public class VolCompare {
	static String uatFilePath = "F:\\practice\\book_vol_uat.csv";
	static String prodFilePath = "F:\\practice\\book_vol_prod.csv";
	public static void main(String [] args){
		VolCSV vol = new VolCSV();//why is this needed , vol does not represents a row of csv 
		//.. just passed as a ref so that readcsv/comparecsv
		//can use them
		FileUtil<VolCSV> util = new FileUtil<VolCSV>();
		try {
			
			Map<VolCSV, BigDecimal> volUAT = util.readCSVFile(uatFilePath, vol);
			Map<VolCSV, BigDecimal> volProd = util.readCSVFile(prodFilePath, vol);

			util.compareCSVFile(volUAT, volProd);
			util.publishAllReports(vol);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//add a proper loggging
			
			e.printStackTrace();
		}
		
	}
}
