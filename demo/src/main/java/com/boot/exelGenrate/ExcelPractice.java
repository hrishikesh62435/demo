package com.boot.exelGenrate;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.boot.model.Employee;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelPractice {
	
	 private List<Employee> employeeList;
	 
	 private XSSFWorkbook workbook;
	 
	 private XSSFSheet sheet;

	public ExcelPractice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		public ExcelPractice(List<Employee> empList) {
		this.employeeList=empList;
		
		 workbook = new XSSFWorkbook();
		
		}
		
		 private void writeHeader2() {
			 sheet = workbook.createSheet("DataEmployee");
			  XSSFRow row = sheet.createRow(0);
			  XSSFCellStyle style = workbook.createCellStyle();
			  XSSFFont font = workbook.createFont();
			  font.setBold(true);
			  font.setFontHeight(12);
			  style.setFont(font);
			  createCell(row, 0, "ID", style);
			  createCell(row, 1, "Employee Name", style);
			  createCell(row, 2, "Employee Address", style);
			  createCell(row, 3, "Employee Salary", style);
			 
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
		 
		 private void write2() {
			 int rowCount = 1;
			 XSSFCellStyle style = workbook.createCellStyle();
			 XSSFFont font = workbook.createFont();
			  font.setFontHeight(10);
			  style.setFont(font);
			  
			  for (Employee record: employeeList) {
				  	Row row = sheet.createRow(rowCount++);
				  	int columnCount = 0;
				  	
				 createCell(row, columnCount++, record.getEmployeeId(),style); 
				 createCell(row, columnCount++, record.getEmployeeName(),style);
				 createCell(row, columnCount++, record.getEmployeeAddress(),style);
		         createCell(row, columnCount++, record.getEmployeeSalary(),style);
		        
			  }
		
			 
			 
		 }
		 
		 
		 public void generateExcelFile(HttpServletResponse response) throws IOException {
	 	        writeHeader2();
	 	        write2();
	 	        ServletOutputStream outputStream = response.getOutputStream();
	 	        workbook.write(outputStream);
	 	        workbook.close();
	 	        outputStream.close();
	  }
		 
		
		
	 
	 

}

