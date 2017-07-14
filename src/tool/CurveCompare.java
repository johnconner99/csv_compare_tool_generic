package tool;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import csv.CurveCSV;
import file.FileUtil;

public class CurveCompare {
	public static void main(String [] args){
		FileUtil util = new FileUtil();
		CurveCSV curve = new CurveCSV();
		try {
			Map<CurveCSV, BigDecimal> curveUAT = util.readCSVFile("F:\\practice\\Book1.csv",curve);
			Map<CurveCSV, BigDecimal> curveProd = util.readCSVFile("F:\\practice\\Book2.csv",curve);

			util.compareCSVFile(curveUAT, curveProd,curve);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
