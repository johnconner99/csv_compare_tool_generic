/**
 * 
 */
package test.file;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.channels.FileLock;
import java.util.Map;
import java.util.Set;

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
	
	FileUtil<VolCSV> util;
	VolCSV vol;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		util = new FileUtil<VolCSV>();
		vol = new VolCSV();	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		util = null;
		vol = null;
	}

	/**
	 * Test method for {@link file.FileUtil#readCSVFile(java.lang.String, csv.GenericCSV)}.
	 * @throws IOException 
	 */
	@Test
	public final void read_csv_success() throws IOException {
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);

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
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_test_bad_header.csv", vol);
	}

	/* ** skipping for now. as it needs big test file
	@Test
	public final void read_csv_file_huge_size() {
		//need a test file with heavy data
		fail("Not yet implemented"); // TODO
	}
	*/

	@Test(expected = FileNotFoundException.class)
	public final void read_csv_file_invalid_file_path() throws IOException {
		Map<VolCSV,BigDecimal> map = util.readCSVFile("", vol);
	}
	
	@Test(expected = IOException.class)
	public final void read_csv_file_file_locked() throws IOException {
		//find a way to lock file
		File file = new File("F:\\practice\\book_vol_test_lock.csv");
		FileLock lock = new RandomAccessFile(file,"rw").getChannel().tryLock();
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_test_lock.csv", vol);
		lock.release();
	}	
	
	/**
	 * Test method for {@link file.FileUtil#prepareCSVReport(java.util.Map, java.lang.String, csv.GenericCSV)}.
	 * @throws IOException 
	 */
	@Test(expected = FileNotFoundException.class)
	public final void testPrepareCSVReport_write_to_bad_path() throws IOException {
		Map<VolCSV,BigDecimal> map = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);
		util.prepareCSVReport(map, "", vol);//invalid path
	}

	@Test
	public final void testcompareCSVFile_success_mismatch() throws IOException {
		Map<VolCSV,BigDecimal> mapUAT = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);
		Map<VolCSV,BigDecimal> mapProd = util.readCSVFile("F:\\practice\\book_vol_prod_test.csv", vol);
		util.compareCSVFile(mapUAT,mapProd);
		
		vol.curveDate = "\"12/24/2017 23:33:33 AM\"";
		vol.expiryDate = "\"12/30/2017 23:33:33 AM\"";
		vol.Strike = BigDecimal.valueOf(3.4444);
		vol.contractCode = "dec-17(12/30/2017)";
		vol.volID = 234232;
		vol.volName = "NG_SKEW";
		BigDecimal value = util.mismatch.get(vol);
		assertEquals(value,BigDecimal.valueOf(2.3523532));//validate correct vol loaded	
	}
	
	@Test
	public final void testcompareCSVFile_success_extraProd() throws IOException {
		Map<VolCSV,BigDecimal> mapUAT = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);
		Map<VolCSV,BigDecimal> mapProd = util.readCSVFile("F:\\practice\\book_vol_prod_test.csv", vol);
		util.compareCSVFile(mapUAT,mapProd);
		
		vol.curveDate = "\"10/24/2017 23:33:33 AM\"";
		vol.expiryDate = "\"10/30/2017 23:33:33 AM\"";
		vol.Strike = BigDecimal.valueOf(3.4444);
		vol.contractCode = "oct-17(10/30/2017)";
		vol.volID = 234232;
		vol.volName = "NG_SKEW";
		BigDecimal value = util.extraProd.get(vol);
		assertEquals(value,BigDecimal.valueOf(2.8523532));//validate correct vol loaded	
	}

	@Test
	public final void testcompareCSVFile_success_extraUAT() throws IOException {
		Map<VolCSV,BigDecimal> mapUAT = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);
		Map<VolCSV,BigDecimal> mapProd = util.readCSVFile("F:\\practice\\book_vol_prod_test.csv", vol);
		util.compareCSVFile(mapUAT,mapProd);
		
		vol.curveDate = "\"10/24/2017 23:33:33 AM\"";
		vol.expiryDate = "\"09/30/2017 23:33:33 AM\"";
		vol.Strike = BigDecimal.valueOf(3.4444);
		vol.contractCode = "sep-17(09/30/2017)";
		vol.volID = 234232;
		vol.volName = "NG_SKEW";
		BigDecimal value = util.extraUAT.get(vol);
		assertEquals(value,BigDecimal.valueOf(2.8523532));//validate correct vol loaded	
	}

	@Test
	public final void testcompareCSVFile_success_match() throws IOException {
		Map<VolCSV,BigDecimal> mapUAT = util.readCSVFile("F:\\practice\\book_vol_uat_test.csv", vol);
		Map<VolCSV,BigDecimal> mapProd = util.readCSVFile("F:\\practice\\book_vol_prod_test.csv", vol);
		util.compareCSVFile(mapUAT,mapProd);
		
		vol.curveDate = "\"08/24/2017 23:33:33 AM\"";
		vol.expiryDate = "\"08/30/2017 23:33:33 AM\"";
		vol.Strike = BigDecimal.valueOf(3.4444);
		vol.contractCode = "aug-17(08/30/2017)";
		vol.volID = 234232;
		vol.volName = "NG_SKEW";
		BigDecimal value = util.match.get(vol);
		assertEquals(value,BigDecimal.valueOf(2.7657532));//validate correct vol loaded	
	}

}
