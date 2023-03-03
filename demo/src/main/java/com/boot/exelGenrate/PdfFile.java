package com.boot.exelGenrate;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.boot.model.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PdfFile {
	//pdf file
	//pdf file 2
	//pdf file 3
	//pdf file 4
	//pdf file 5
	private List<Employee> employeeList;

		public PdfFile(List <Employee> employeeList ) {
		
		this.employeeList=employeeList;
		}
	
		private void writeTableHeader(PdfPTable table) {
			PdfPCell c = new PdfPCell();
			c.setBackgroundColor(Color.LIGHT_GRAY);
			c.setPadding(5);
			
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(Color.white);
			
			c.setPhrase(new Phrase("Employee Id", font));
			table.addCell(c);
			c.setPhrase(new Phrase("Employee Name", font));
			table.addCell(c);
			c.setPhrase(new Phrase("Employee Address", font));
			table.addCell(c);
			c.setPhrase(new Phrase("Employee Salary", font));
			table.addCell(c);
			
		}	
		
		 private void writeTableData(PdfPTable table) {
		        for (Employee emp : employeeList) {
		        	
		            table.addCell(String.valueOf(emp.getEmployeeId()));
		            table.addCell(emp.getEmployeeName());
		            table.addCell(emp.getEmployeeAddress());
		            table.addCell(String.valueOf(emp.getEmployeeSalary()));
		            
		        }
		 }    
		
		public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	        
	        
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	        
	        
	        Paragraph p = new Paragraph("List of Employee", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.5f, 3.0f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
		}  
		
	
	

}
