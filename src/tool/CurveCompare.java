package tool;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import csv.CurveCSV;
import file.FileUtil;

public class CurveCompare {
	public static void main(String [] args){
		
		CurveCSV curve = new CurveCSV();//this object looks useless
		FileUtil<CurveCSV> util = new FileUtil<CurveCSV>();
		try {
			Map<CurveCSV, BigDecimal> curveUAT = util.readCSVFile("F:\\practice\\book_curve_uat.csv",curve);
			Map<CurveCSV, BigDecimal> curveProd = util.readCSVFile("F:\\practice\\book_curve_prod.csv",curve);

			util.compareCSVFile(curveUAT, curveProd);
			util.publishAllReports(curve);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
