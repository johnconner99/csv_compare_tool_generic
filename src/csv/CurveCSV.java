package csv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurveCSV implements GenericCSV<CurveCSV>{
	int indexID;
	String indexName;
	String curveDate;
	String expiryDate;
	String contractCode;
	BigDecimal price;
	String header = "curve_date, expiry_date, price, contract_code, index_name, index_id ";
	
	@Override
	public CurveCSV readCSVRow(String[] line) {
		CurveCSV curveCSV = new CurveCSV();
		curveCSV.curveDate = line[0];
		curveCSV.expiryDate = line[1];
		curveCSV.price = BigDecimal.valueOf(Double.parseDouble(line[2]));
		curveCSV.contractCode = line[4];
		curveCSV.indexName = line[5];
		curveCSV.indexID = Integer.parseInt(line[6]);

		return curveCSV;		

	}

	@Override
	public String getCSVRowToWrite(CurveCSV curveCSV) {
		return curveCSV.curveDate + "," + 
				curveCSV.expiryDate + "," + 
				curveCSV.price + "," +
				curveCSV.contractCode + "," + 
				curveCSV.indexName + "," + 
				curveCSV.indexID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractCode == null) ? 0 : contractCode.hashCode());
		result = prime * result + ((curveDate == null) ? 0 : curveDate.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + indexID;
		result = prime * result + ((indexName == null) ? 0 : indexName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurveCSV other = (CurveCSV) obj;
		if (contractCode == null) {
			if (other.contractCode != null)
				return false;
		} else if (!contractCode.equals(other.contractCode))
			return false;
		if (curveDate == null) {
			if (other.curveDate != null)
				return false;
		} else if (!curveDate.equals(other.curveDate))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (indexID != other.indexID)
			return false;
		if (indexName == null) {
			if (other.indexName != null)
				return false;
		} else if (!indexName.equals(other.indexName))
			return false;
		return true;
	}

}
