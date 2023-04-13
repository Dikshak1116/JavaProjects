package in.ashokit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;

@Component
public class PdfGenerator {
	
	//@Autowired
	//private CitizenPlanRepository planRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> allPdfRecords,File f) throws Exception {
	
	/*
	 * Create a document object with pagesize A4 and attach that document to our
	 * response object by using PdfWriter.getInstance. To write the data document
	 * should be open.So we are opening document. Then,we are creating a paragraph
	 * with some text and adding that paragraph to document and closing the
	 * document.
	 * 
	 * We need to create a table using PdfPTable and specify the columns. set the
	 * data for table and at last add the table to document. Add the cells header,no
	 * need to give index and set the data rows
	 * 
	 *
	 */
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter .getInstance(document, new FileOutputStream(f));
		
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Citizen Plans", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		// Adding the created paragraph in document
		document.add(paragraph);

		/*Paragraph p = new Paragraph("Citizen Plans Info");
		document.add(p);*/

		PdfPTable table = new PdfPTable(11);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");
		table.addCell("Terminated Date");
		table.addCell("Terminated Reason");

		//List<CitizenPlan> allPdfRecords = planRepo.findAll();
		for (CitizenPlan plan : allPdfRecords) {

			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			
			if (null != plan.getPlanStartDate()) {
				table.addCell(plan.getPlanStartDate() + "");
			}else {
				table.addCell("N/A");
			}
			
			if (null != plan.getPlanEndDate()) {
				table.addCell(plan.getPlanEndDate() + "");
			}else {
				table.addCell("N/A");
			}
			
			if (null != plan.getBenefitAmount()) {
				table.addCell(String.valueOf(plan.getBenefitAmount()));
			}else {
				table.addCell("N/A");
			}
			table.addCell(plan.getDenialReason());
			
			if (null != plan.getTerminatedDate()) {
				table.addCell(plan.getTerminatedDate() + "");
			}else {
				table.addCell("N/A");
			}
			
			table.addCell(plan.getTerminatedReason());
		}

		document.add(table);
		document.close();

	
	}

}
