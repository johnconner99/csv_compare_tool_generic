/**
 * 
 */
package test.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.channels.FileLock;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import csv.VolCSV;
import file.FileUtil;

/**
 * @author ADMIN
 *
 */
public class FileUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link file.FileUtil#readCSVFile(java.lang.String, csv.GenericCSV)}.
	 * @throws IOException 
	 */
	@Test
	public final void read_csv_success() throws IOException {
		FileUtil util = new FileUtil();
		VolCSV vol = new VolCSV();
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_uat.csv", vol);

		vol.curveDate = "\"12/24/2017 23:33:33 AM\"";
		vol.expiryDate = "\"12/30/2017 23:33:33 AM\"";
		vol.Strike = BigDecimal.valueOf(3.4444);
		vol.contractCode = "dec-17(12/30/2017)";
		vol.volID = 234232;
		vol.volName = "NG_SKEW";
		BigDecimal value = map.get(vol);
		assertEquals(value,BigDecimal.valueOf(2.3523532));//validate correct vol loaded
	}

	@Test(expected  = NumberFormatException.class)
	public final void read_csv_file_bad_header() throws IOException {
		//need a test file with bad header
		//fix code to validate header .. code is currently failing at wrong place
		FileUtil util = new FileUtil();
		VolCSV vol = new VolCSV();
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_test_bad_header.csv", vol);

		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void read_csv_file_huge_size() {
		//need a test file with heavy data
		fail("Not yet implemented"); // TODO
	}
	

	@Test(expected = FileNotFoundException.class)
	public final void read_csv_file_invalid_file_path() throws IOException {
		FileUtil util = new FileUtil();
		VolCSV vol = new VolCSV();
		Map<VolCSV,BigDecimal> map = util.readCSVFile("", vol);
	}
	
	@Test(expected = IOException.class)
	public final void read_csv_file_file_locked() throws IOException {
		//find a way to lock file
		File file = new File("F:\\practice\\book_vol_test_lock.csv");
		FileLock lock = new RandomAccessFile(file,"rw").getChannel().tryLock();
		
		FileUtil util = new FileUtil();
		VolCSV vol = new VolCSV();
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_test_lock.csv", vol);
		
		lock.release();

	}	
	
	/**
	 * Test method for {@link file.FileUtil#prepareCSVReport(java.util.Map, java.lang.String, csv.GenericCSV)}.
	 */
	@Test
	public final void testPrepareCSVReport() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link file.FileUtil#compareCSVFile(java.util.Map, java.util.Map, csv.GenericCSV)}.
	 */
	@Test
	public final void testCompareCSVFile() {
		fail("Not yet implemented"); // TODO
	}

}
