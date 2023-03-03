package com.boot.web.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Module.Export;
import org.hibernate.tool.schema.spi.Exporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.exelGenrate.ExcelPractice;
import com.boot.exelGenrate.ExelGenerator;
import com.boot.exelGenrate.PdfFile;
import com.boot.model.Employee;
import com.boot.repository.EmployeeRepository;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class EmployeeWebController {

	public EmployeeWebController() {

		super();
		System.out.println("employee WEB controller called");
	}

	@Autowired
	EmployeeRepository EmpRepo;

	Logger log = LoggerFactory.getLogger(EmployeeWebController.class);

	@GetMapping("/home")
	public String index(Model m) {

		System.out.println("home");
		m.addAttribute("employee", new Employee());

		return "add-employee";

	}

	@PostMapping("/save-employee")
	public String saveEmployeeData(@ModelAttribute("employee") Employee emp) {

		try {
			EmpRepo.save(emp);

			log.info("SAVE EMPLOYEE :" + emp.getEmployeeId() + "...." + emp.getEmployeeName());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("SOMETHING WRONG:" + emp.getEmployeeId());
		}
		return "redirect:/home";
	}

	@GetMapping("/list-employee")
	public String listEmployee(Model m) {

		try {
			m.addAttribute("employeeList", EmpRepo.findAll());
			m.addAttribute("excelList", EmpRepo.findAll());
			m.addAttribute("employeePdf", EmpRepo.findAll());
			
			log.info("VIEW LIST");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("SOMETHING WRONG");
		}
		return "list-employee";
	}

	@GetMapping("/edit-employee/{id}")
	public String editEmployee(@PathVariable(value = "id") int id, Model model) {

		try {
			model.addAttribute("employee", EmpRepo.getEmployeeByEmployeeId(id));
			log.info("EDIT DATA....:" + id);

		} catch (Exception e) {
			// TODO: handle exception
			log.error("SOMETHING WRONG BY:" + id);
		}
		return "add-employee";
	}

	@GetMapping("/delete-employee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") int id, Model model) {
		try {
			EmpRepo.deleteEmployee(id);
			log.info("delete DATA....:" + id);

		} catch (Exception e) {
			// TODO: handle exception
			log.error("SOMETHING WRONG BY:" + id);
		}

		return "redirect:/list-employee";
	}

	@GetMapping("/view-report")
	public String listByName(ModelAndView model ,Employee emp) {
		try {

			log.info("view report successfylly show...:");
		

		} catch (Exception e) {
			// TODO: handle exception
			log.error("SOMETHING WRONG BY:");
		}

		return "report";
	}

	@PostMapping("/report-handler")
	public ModelAndView reportHandler(@ModelAttribute Employee emp, HttpServletRequest request, ModelAndView model) {

		try {
			List<Employee> employeeList = EmpRepo.getEmployeeListByName(emp.getEmployeeName());
			System.out.println(employeeList);
			System.out.println(emp);

			model.addObject("employeeList", employeeList);
			model.addObject("empName", emp.getEmployeeName());
			
			model.setViewName("report");
			log.info("This employee list:" + emp.getEmployeeName());

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Somethig worong:" + emp.getEmployeeId());
		}

		return model;

	}
	
	@GetMapping("/export-to-excel/{employeeName}")
	public void exportIntoExcelFile( HttpServletResponse response, @PathVariable(value = "employeeName") String employeeName, ModelAndView model) throws IOException {
		response.setContentType("application/octet-stream");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date(0));
		System.out.println("ruunnnnn");
		

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
       List<Employee> listByEmployeeName = EmpRepo.getEmployeeListByName(employeeName);
       System.out.println(listByEmployeeName);
       
      
       
     
       
       
       ExelGenerator exelGenerator = new ExelGenerator(listByEmployeeName);
       exelGenerator.generateExcelFile(response);
	}
	
	
	@GetMapping("/export-to-excel2")
	public void exportIntoExcelFile2(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date(0));
		System.out.println("secondExcel");
		

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        
        List<Employee> listOfEmployee = EmpRepo.findAll();
        ExcelPractice excelPractice = new ExcelPractice(listOfEmployee);
        excelPractice.generateExcelFile(response);
        
     
	}
		@GetMapping("/pdf")
		public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date(0));
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        
	        List<Employee> employee = EmpRepo.findAll();
	        
	        	PdfFile exporter = new PdfFile(employee);
	        	exporter.export(response);
	        
	        	
	  
		}   
	 
	

}
