package csv;

public interface GenericCSV<T> {
	//void skipHeader();
	String header = "";
	T readCSVRow(String [] line);
	String getCSVRowToWrite(T csv);
	
}
