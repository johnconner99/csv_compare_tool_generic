package csv;
import java.math.BigDecimal;

public class VolCSV implements GenericCSV<VolCSV>{
	int volID;
	String volName;
	String curveDate;
	String expiryDate;
	String contractCode;
	BigDecimal Strike;
	BigDecimal vol;
	
	public String header = "curve_date, expiry_date, vol, strike, contract_code, vol_name, vol_id ";

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Strike == null) ? 0 : Strike.hashCode());
		result = prime * result + ((contractCode == null) ? 0 : contractCode.hashCode());
		result = prime * result + ((curveDate == null) ? 0 : curveDate.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + volID;
		result = prime * result + ((volName == null) ? 0 : volName.hashCode());
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
		VolCSV other = (VolCSV) obj;
		if (Strike == null) {
			if (other.Strike != null)
				return false;
		} else if (!Strike.equals(other.Strike))
			return false;
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
		if (volID != other.volID)
			return false;
		if (volName == null) {
			if (other.volName != null)
				return false;
		} else if (!volName.equals(other.volName))
			return false;
		return true;
	}

	public VolCSV readCSVRow(String[] line) {
		VolCSV csv = new VolCSV();
		csv.curveDate = line[0];
		csv.expiryDate = line[1];
		csv.vol = BigDecimal.valueOf(Double.parseDouble(line[2]));
		csv.Strike = BigDecimal.valueOf(Double.parseDouble(line[3]));
		csv.contractCode = line[4];
		csv.volName = line[5];
		csv.volID = Integer.parseInt(line[6]);

		return csv;

	}

	public String getCSVRowToWrite(VolCSV csv) {
		return csv.curveDate + "," + 
				csv.expiryDate + "," + 
				csv.vol + "," +
				csv.Strike + "," +
				csv.contractCode + "," + 
				csv.volName + "," + 
				csv.volID;
	}

	
}
