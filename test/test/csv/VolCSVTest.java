/**
 * 
 */
package test.csv;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import csv.VolCSV;

/**
 * @author ADMIN
 *
 */
public class VolCSVTest {
	
	VolCSV vol;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vol = new VolCSV();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		VolCSV vol = null;
	}

	/**
	 * Test method for {@link csv.VolCSV#readCSVRow(java.lang.String[])}.
	 */
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public final void test_readCSVRow_vol_object_bad_csv_column_mismatch() {
		String[] line = new String[6];
		line[0] = "\"12/24/2017 23:33:33 AM\"";
		line[1] = "\"12/30/2017 23:33:33 AM\"";
		line[2] = "3.4444";
		line[3] = "3.4444";
		line[4] = "dec-17(12/30/2017)";
		line[5] = "234232";
		VolCSV vol2 = vol.readCSVRow(line);
	}

	/**
	 * Test method for {@link csv.VolCSV#readCSVRow(java.lang.String[])}.
	 */
	@Test(expected = NumberFormatException.class)
	public final void test_readCSVRow_vol_object_bad_csv_invalid_data_format() {
		String[] line = new String[6];
		line[0] = "\"12/24/2017 23:33:33 AM\"";
		line[1] = "\"12/30/2017 23:33:33 AM\"";
		line[2] = "3.4444";
		line[3] = "dec-17(12/30/2017)";//strike has alphnumeric value
		line[4] = "dec-17(12/30/2017)";
		line[5] = "234232";
		VolCSV vol2 = vol.readCSVRow(line);
	}

}
