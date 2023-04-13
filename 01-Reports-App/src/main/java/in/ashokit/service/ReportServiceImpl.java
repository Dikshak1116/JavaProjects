package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.ExcelGenerator;
import in.ashokit.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = planRepo.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatuses() {
		List<String> planStatus = planRepo.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			entity.setPlanStartDate(request.getStartDate());
		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			entity.setPlanEndDate(request.getEndDate());
		}

		// BeanUtils.copyProperties(request, entity);
		List<CitizenPlan> filterPlan = planRepo.findAll(Example.of(entity));
		return filterPlan;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f=new File("Plans.xls");
		//response-used to send file to the browser
		//file f-used to create a file in the system
		//allRecords-Data we want to write in the excel
		List<CitizenPlan> allRecords = planRepo.findAll();
		excelGenerator.generate(response, allRecords,f);
		
		String subject="Test mail subject";
		String body="<h1>Test Mail Body</h1>";
		String to="dikshakeshri12@gmail.com";
	
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		File f=new File("Plans.pdf");
		
		List<CitizenPlan> allPdfRecords = planRepo.findAll();
		pdfGenerator.generate(response, allPdfRecords,f);
		
		String subject="Test mail subject";
		String body="<h1>Test Mail Body</h1>";
		String to="dikshakeshri12@gmail.com";
	
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
		
	}

}
