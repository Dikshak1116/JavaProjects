package in.ashokit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegistrationForm;
import in.ashokit.entity.User;
import in.ashokit.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;

	public boolean register(RegistrationForm form) {
		User entity=new User();
		BeanUtils.copyProperties(form, entity);
		userRepo.save(entity);
		
		return true;
	}

	public String login(LoginForm form) {
		
User entity = userRepo.findByEmailAndPwd(form.getEmail(), form.getPwd());
		
		if(entity==null) {
			return "Invalid Credentials";
		}
		
		
		//create session and store user data in session object
		session.setAttribute("userId", entity.getUserId());
		
		return "success";
		
	}

}
