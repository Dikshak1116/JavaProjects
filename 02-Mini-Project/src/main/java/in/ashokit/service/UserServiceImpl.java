package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.UserDtlsRepo;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean signup(SignUpForm form) {
		
		//Retrieves record based on given mail id
		UserDtlsEntity user = userDtlsRepo.findByEmail(form.getEmail());
		
		//checks with given mail id record is available or not,if record
		//is available we won't execute any logic and return false value
		if(user!=null) {
			return false;
		}
		
		// TODO: generate random pwd and set to object
		UserDtlsEntity entity=new UserDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		
		// TODO: generate random pwd and set to object
		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setPwd(tempPwd);
		
		// TODO: set account status as LOCKED
		entity.setAccStatus("LOCKED");
		
		// TODO: insert record
		userDtlsRepo.save(entity);
		
		// TODO: send email to user to unlock the account
		String to=form.getEmail();
		String subject="Unlock Your Account to access website";
		
		StringBuffer body=new StringBuffer("");
		body.append("<h1>Use Below temporary pwd to unlock your account</h1>");
		
		body.append("Temporary pwd :"+tempPwd);
		
		body.append("<br/>");
		
		body.append("<a href=\"http://localhost:7080/unlock?email="+to+"\">Click Here To Unlock Your Account</a>");
		
		emailUtils.sendEmail(to, subject, body.toString());
		
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		
		UserDtlsEntity entity = userDtlsRepo.findByEmail(form.getEmail());
		
		if(entity.getPwd().equals(form.getTempPwd())){
			
			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("Unlocked");
			userDtlsRepo.save(entity);
			return true;
			
		}else {
			
			return false;
			
		}
		
	}

	@Override
	public String login(LoginForm form) {
		
		UserDtlsEntity entity = userDtlsRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		
		if(entity==null) {
			return "Invalid Credentials";
		}
		if(entity.getAccStatus().equals("locked")) {
			return "Your Account Locked";
		}
		
		//create session and store user data in session object
		session.setAttribute("userId", entity.getUserId());
		
		return "success";
	}

	@Override
	public boolean forgotPwd(String email) {
		
		//check record is present in DB with given email or not
		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);
		
		//if record is not available send error msg to UI/return false
		if(entity==null) {
			return false;
		}
		
		//if record is available send pwd to email and success msg to UI/return true
		String subject="Recover Password";
		String body="Your Password: "+entity.getPwd();
		
		emailUtils.sendEmail(email, subject, body);
		
		return true;
	}

}
