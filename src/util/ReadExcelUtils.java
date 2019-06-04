package util;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import vo.Student;


public class ReadExcelUtils {

	public static List<Student> readExcel(File file) throws Exception {

		//String totalPath = filePath + File.separator + fileName;
		List<Student> list = new ArrayList<Student>();
		InputStream is = new FileInputStream(file);

		Workbook wb = null;
		wb = WorkbookFactory.create(is);
		Sheet sheet = wb.getSheetAt(0);

		int begin = sheet.getFirstRowNum();
		int end = sheet.getLastRowNum();
		int cols = 0;
		int count = 0;
		boolean flag = true;
		while (flag) {
			Row row = sheet.getRow(begin);
			if (row.getCell(count) != null) {
				cols++;
				count++;
				flag = true;
			} else {
				flag = false;
			}
		}
		// System.out.println("总列数为:"+cols);

		
		int array[] =new int[3];

		Row row = sheet.getRow(begin);
		for (int i = 0; i < cols; i++) {
			Cell cell = row.getCell(i);
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
			}
			
			if (cell.getStringCellValue().equals("学号")) {
				array[0] = i;
			}
			if (cell.getStringCellValue().equals("姓名") ) {
				array[1] = i;
			}
			if (cell.getStringCellValue().equals("班级") ) {
				array[2] = i;
			}
		}

		//System.out.println("下标"+array[0]+" "+array[1]+" "+array[2]+array[3]);
		for (int i = begin + 1; i <= end; i++) {
			if (null == sheet.getRow(i)) {
				continue;
			}
			Student students = new Student();
			Row r = sheet.getRow(i);
			

			Cell cell2 = r.getCell(array[0]);
			if (cell2.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cell2.setCellType(Cell.CELL_TYPE_STRING);
			}
			students.setNumber(cell2.getStringCellValue());

			Cell cell3 = r.getCell(array[1]);
			if (cell3.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cell3.setCellType(Cell.CELL_TYPE_STRING);
			}
			students.setName(cell3.getStringCellValue());

			Cell cell4 = r.getCell(array[2]);
			if (cell4.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cell4.setCellType(Cell.CELL_TYPE_STRING);
			}
			students.setSclass(cell4.getStringCellValue());
			// System.out.println(students.getNumber()+"
			// "+students.getName()+" "+students.getSclass());
			list.add(students);

		}

		return list;

	}
}
