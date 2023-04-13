package in.ashokit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;

@Component
public class ExcelGenerator {
	
	//@Autowired
	//private CitizenPlanRepository planRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> allRecords,File f) throws Exception {

		/*
		 * creating excel workbook Here Workbook is an interface and HSSFWorkbook is
		 * it's implementation class for that using workbook we can create a sheet and
		 * give a name for it. Inside sheet create a row.1st row will be header row.Now
		 * in the row we have to set cells and cell data.
		 * 
		 * We created a workbook,with that workbook we created a sheet, with that sheet
		 * we created a row with index 0 which is first row and with that row we created
		 * a cell and in that we are setting the data for the cells.This completes
		 * header row part. Now we need to create data row.
		 * 
		 * Now we want to set the records as data row in excel.Take a loop as it is not
		 * one record but list of records are available. we want to create one row with
		 * each record.Our workbook,sheet, Rows,headerRows and dataRows everything is
		 * ready.
		 * 
		 * Now, we need to write workbook data to the file using FileOutputStream and
		 * close the workbook
		 * 
		 */
		// Workbook workbook = new XSSFWorkbook();//supports .xlsx extension
		// Workbook workbook = new HSSFWorkbook();//supports .xls extension
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);
		// headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");
		headerRow.createCell(8).setCellValue("Denial Reason");
		headerRow.createCell(9).setCellValue("Terminated Date");
		headerRow.createCell(10).setCellValue("Terminated Reason");

		//List<CitizenPlan> allRecords = planRepo.findAll();
		int dataRowIndex = 1;
		for (CitizenPlan plan : allRecords) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());

			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}

			if (null != plan.getBenefitAmount()) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}

			dataRow.createCell(8).setCellValue(plan.getDenialReason());

			if (null != plan.getTerminatedDate()) {
				dataRow.createCell(9).setCellValue(plan.getTerminatedDate() + "");
			} else {
				dataRow.createCell(9).setCellValue("N/A");
			}

			dataRow.createCell(10).setCellValue(plan.getTerminatedReason());

			dataRowIndex++;

		}
		// This is for FileOutputStream,if we want to store in file in our
		// server folder then FileOutputStream is required
		
		  FileOutputStream fos =new FileOutputStream(f);
		  workbook.write(fos); 
		  workbook.close();
		 

		// To send the excel as a response to the browser for that use
		// response.getOutputStream()
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

	}

}
