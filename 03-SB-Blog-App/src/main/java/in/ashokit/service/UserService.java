package in.ashokit.service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegistrationForm;

public interface UserService {

	public boolean register(RegistrationForm form);
	
	public String login(LoginForm form);
}
