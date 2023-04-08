package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		repo.deleteAll();
		//Cash plan data
		CitizenPlan c1=new CitizenPlan();
		c1.setCitizenName("Raja");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(5000.00);
		
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenName("Rani");
		c2.setGender("Female");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Approved");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		c2.setBenefitAmount(6000.00);
		
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenName("Rajesh");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Denied");
		c3.setDenialReason("Rental Income");
		
		CitizenPlan c4=new CitizenPlan();
		c4.setCitizenName("Radha");
		c4.setGender("Female");
		c4.setPlanName("Cash");
		c4.setPlanStatus("Denied");
		c4.setDenialReason("Rental Income");
		
		CitizenPlan c5=new CitizenPlan();
		c5.setCitizenName("Ravi");
		c5.setGender("Male");
		c5.setPlanName("Cash");
		c5.setPlanStatus("Terminated");
		c5.setPlanStartDate(LocalDate.now().minusMonths(4));
		c5.setPlanEndDate(LocalDate.now().plusMonths(6));
		c5.setBenefitAmount(3000.00);
		c5.setTerminatedDate(LocalDate.now());
		c5.setTerminatedReason("Employed");
		
		CitizenPlan c6=new CitizenPlan();
		c6.setCitizenName("Rashi");
		c6.setGender("Female");
		c6.setPlanName("Cash");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setBenefitAmount(4000.00);
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminatedReason("Employed");
		
		//Food plan data
		CitizenPlan c7=new CitizenPlan();
		c7.setCitizenName("Shyam");
		c7.setGender("Male");
		c7.setPlanName("Food");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(2000.00);
		
		CitizenPlan c8=new CitizenPlan();
		c8.setCitizenName("Roshni");
		c8.setGender("Female");
		c8.setPlanName("Food");
		c8.setPlanStatus("Approved");
		c8.setPlanStartDate(LocalDate.now());
		c8.setPlanEndDate(LocalDate.now().plusMonths(6));
		c8.setBenefitAmount(4000.00);
		
		CitizenPlan c9=new CitizenPlan();
		c9.setCitizenName("Rishi");
		c9.setGender("Male");
		c9.setPlanName("Food");
		c9.setPlanStatus("Denied");
		c9.setDenialReason("Property Income");
		
		CitizenPlan c10=new CitizenPlan();
		c10.setCitizenName("Rakhi");
		c10.setGender("Female");
		c10.setPlanName("Food");
		c10.setPlanStatus("Denied");
		c10.setDenialReason("Property Income");
		
		CitizenPlan c11=new CitizenPlan();
		c11.setCitizenName("Sujit");
		c11.setGender("Male");
		c11.setPlanName("Food");
		c11.setPlanStatus("Terminated");
		c11.setPlanStartDate(LocalDate.now().minusMonths(4));
		c11.setPlanEndDate(LocalDate.now().plusMonths(6));
		c11.setBenefitAmount(6000.00);
		c11.setTerminatedDate(LocalDate.now());
		c11.setTerminatedReason("Employed");
		
		CitizenPlan c12=new CitizenPlan();
		c12.setCitizenName("Shreya");
		c12.setGender("Female");
		c12.setPlanName("Food");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setBenefitAmount(3000.00);
		c12.setTerminatedDate(LocalDate.now());
		c12.setTerminatedReason("Employed");
		
		//Medical plan data
		CitizenPlan c13=new CitizenPlan();
		c13.setCitizenName("Ram");
		c13.setGender("Male");
		c13.setPlanName("Medical");
		c13.setPlanStatus("Approved");
		c13.setPlanStartDate(LocalDate.now());
		c13.setPlanEndDate(LocalDate.now().plusMonths(6));
		c13.setBenefitAmount(4000.00);
		
		CitizenPlan c14=new CitizenPlan();
		c14.setCitizenName("Sita");
		c14.setGender("Female");
		c14.setPlanName("Medical");
		c14.setPlanStatus("Approved");
		c14.setPlanStartDate(LocalDate.now());
		c14.setPlanEndDate(LocalDate.now().plusMonths(6));
		c14.setBenefitAmount(5000.00);
		
		CitizenPlan c15=new CitizenPlan();
		c15.setCitizenName("Rohan");
		c15.setGender("Male");
		c15.setPlanName("Medical");
		c15.setPlanStatus("Denied");
		c15.setDenialReason("Rental Income");
		
		CitizenPlan c16=new CitizenPlan();
		c16.setCitizenName("Rashmi");
		c16.setGender("Female");
		c16.setPlanName("Medical");
		c16.setPlanStatus("Denied");
		c16.setDenialReason("Rental Income");
		
		CitizenPlan c17=new CitizenPlan();
		c17.setCitizenName("Sonu");
		c17.setGender("Male");
		c17.setPlanName("Medical");
		c17.setPlanStatus("Terminated");
		c17.setPlanStartDate(LocalDate.now().minusMonths(4));
		c17.setPlanEndDate(LocalDate.now().plusMonths(6));
		c17.setBenefitAmount(3000.00);
		c17.setTerminatedDate(LocalDate.now());
		c17.setTerminatedReason("Govt Job");
		
		CitizenPlan c18=new CitizenPlan();
		c18.setCitizenName("Sristi");
		c18.setGender("Female");
		c18.setPlanName("Medical");
		c18.setPlanStatus("Terminated");
		c18.setPlanStartDate(LocalDate.now().minusMonths(4));
		c18.setPlanEndDate(LocalDate.now().plusMonths(6));
		c18.setBenefitAmount(4000.00);
		c18.setTerminatedDate(LocalDate.now());
		c18.setTerminatedReason("Govt Job");
		
		//Employment data plan
		CitizenPlan c19=new CitizenPlan();
		c19.setCitizenName("Raghav");
		c19.setGender("Male");
		c19.setPlanName("Employment");
		c19.setPlanStatus("Approved");
		c19.setPlanStartDate(LocalDate.now());
		c19.setPlanEndDate(LocalDate.now().plusMonths(6));
		c19.setBenefitAmount(6000.00);
		
		CitizenPlan c20=new CitizenPlan();
		c20.setCitizenName("Mala");
		c20.setGender("Female");
		c20.setPlanName("Employment");
		c20.setPlanStatus("Approved");
		c20.setPlanStartDate(LocalDate.now());
		c20.setPlanEndDate(LocalDate.now().plusMonths(6));
		c20.setBenefitAmount(3000.00);
		
		CitizenPlan c21=new CitizenPlan();
		c21.setCitizenName("Harish");
		c21.setGender("Male");
		c21.setPlanName("Employment");
		c21.setPlanStatus("Denied");
		c21.setDenialReason("Property Income");
		
		CitizenPlan c22=new CitizenPlan();
		c22.setCitizenName("Hema");
		c22.setGender("Female");
		c22.setPlanName("Employment");
		c22.setPlanStatus("Denied");
		c22.setDenialReason("Property Income");
		
		CitizenPlan c23=new CitizenPlan();
		c23.setCitizenName("Sukesh");
		c23.setGender("Male");
		c23.setPlanName("Employment");
		c23.setPlanStatus("Terminated");
		c23.setPlanStartDate(LocalDate.now().minusMonths(4));
		c23.setPlanEndDate(LocalDate.now().plusMonths(6));
		c23.setBenefitAmount(4000.00);
		c23.setTerminatedDate(LocalDate.now());
		c23.setTerminatedReason("Employed");
		
		CitizenPlan c24=new CitizenPlan();
		c24.setCitizenName("Sneha");
		c24.setGender("Female");
		c24.setPlanName("Employment");
		c24.setPlanStatus("Terminated");
		c24.setPlanStartDate(LocalDate.now().minusMonths(4));
		c24.setPlanEndDate(LocalDate.now().plusMonths(6));
		c24.setBenefitAmount(2000.00);
		c24.setTerminatedDate(LocalDate.now());
		c24.setTerminatedReason("Employed");
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24);
		repo.saveAll(list);
	}

}
