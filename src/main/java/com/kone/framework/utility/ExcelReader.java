package com.kone.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	Workbook wb;
	Sheet sh;
	Logger log = Logger.getLogger(ExcelReader.class.getName());
	String ExcelPath;

	public ExcelReader(String ExcelPath) {
		this.ExcelPath = ExcelPath;
		try {
			File Exclpath = new File(ExcelPath);
			FileInputStream fstream = new FileInputStream(Exclpath);
			String fileextension = ExcelPath.substring(ExcelPath.indexOf("."));
			if (fileextension.equalsIgnoreCase(".xlsx")) {
				wb = new XSSFWorkbook(fstream);
			} else if (fileextension.equalsIgnoreCase(".xls")) {
				wb = new HSSFWorkbook(fstream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, List<String>> GetData(String sheetname) throws Exception {
		try {
			sh = wb.getSheet(sheetname);
			HashMap<String, List<String>> hm_ex_data = Data_from_Excel(sheetname);
			String Data_Header = null;
			Row header_row = sh.getRow(sh.getFirstRowNum());
			int header_row_Count = header_row.getLastCellNum();
			for (int j = 0; j < header_row_Count; j++) {
				Data_Header = header_row.getCell(j).getStringCellValue();
				List<String> Data_valueS = new LinkedList<String>();
				for (int k = 1; k < sh.getLastRowNum() + 1; k++) {
					int last = sh.getLastRowNum();
					Row value_row = sh.getRow(k);
					Cell a1 = value_row.getCell(j);
					String a_1 = null;
					if (a1 != null) {
						a_1 = cellToString(a1);
					}
					if (a_1 != "NA" && !a_1.isEmpty() && a_1 != null) {
						Data_valueS.add(a_1);
					}
				}
				hm_ex_data.put(Data_Header, Data_valueS);
			}
			return hm_ex_data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, List<String>> Data_from_Excel(String sheet_name) throws Exception {
		HashMap<String, List<String>> hm_data = null;
		hm_data = new HashMap<String, List<String>>();
		return hm_data;
	}
	/*
	 * public static void main(String args[]) throws Exception { String xl_path =
	 * "C:\\Users\\con_svijay02\\Desktop\\ITSM_TestData.xlsx"; Excel_Reader Excel =
	 * new Excel_Reader(xl_path); Excel.GetData("INC_Data"); }
	 */

	private String cellToString(Cell cell) {
		Object result = null;
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		// if (cell != null) {
		switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
		case NUMERIC:
			DataFormatter formatter = new DataFormatter();
			result = formatter.formatCellValue(cell);
			break;
		case STRING:
			result = cell.getStringCellValue();
			break;
		case BLANK:
			result = "NA";
			break;
		default:
			result = "NA";
			// }
		}
		return result.toString();
	}

}
