package com.boot.exelGenrate;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.boot.model.Employee;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExelGenerator {
		
	 private List<Employee> employeeList;
	
	 private XSSFWorkbook workbook;
	
	 private XSSFSheet sheet;
 		 
	 public ExelGenerator() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public ExelGenerator(List<Employee> employeeList) {
		    this.employeeList = employeeList;
	        workbook = new XSSFWorkbook();
	 }
	
	 private void writeHeader() {
	        sheet = workbook.createSheet("Report");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "Employee Name", style);
	        createCell(row, 2, "Employee Address", style);
	        createCell(row, 3, "Salary", style);
	 }
	 
	 private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
		    
		    sheet.autoSizeColumn(columnCount);
		 	Cell cell = row.createCell(columnCount);
		 	
		 	if (valueOfCell instanceof Integer) {
		 		cell.setCellValue((Integer) valueOfCell);
		 	}else if (valueOfCell instanceof Long) {
		 		cell.setCellValue((Long) valueOfCell);
		 	
			}else if(valueOfCell instanceof String) {
				cell.setCellValue((String) valueOfCell);
				
			}else if(valueOfCell instanceof Boolean) {
				cell.setCellValue((Boolean) valueOfCell);
				
			} else if(valueOfCell instanceof Double) {
				cell.setCellValue((Double) valueOfCell);
			}
		 		cell.setCellStyle(style);
	 }
	 
	 private void write() {
		 	 
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        
	        for (Employee employee: employeeList) {
	        	
	        	Row row = sheet.createRow(rowCount++);
	        	int columnCount = 0;
	            createCell(row, columnCount++, employee.getEmployeeId(),style);
	            createCell(row, columnCount++, employee.getEmployeeName(),style);
	            createCell(row, columnCount++, employee.getEmployeeAddress(),style);
	            createCell(row, columnCount++, employee.getEmployeeSalary(),style);
	        	}
	  }
	 		
	 public void generateExcelFile(HttpServletResponse response) throws IOException {
	 	        writeHeader();
	 	        write();
	 	        ServletOutputStream outputStream = response.getOutputStream();
	 	        workbook.write(outputStream);
	 	        workbook.close();
	 	        outputStream.close();
	  }
}