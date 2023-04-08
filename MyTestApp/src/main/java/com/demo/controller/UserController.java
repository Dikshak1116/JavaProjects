package com.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.binding.Task;
import com.demo.binding.User;
import com.demo.entity.TaskEntity;
import com.demo.entity.UserEntity;
import com.demo.repo.TaskRepository;
import com.demo.repo.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private TaskRepository repo1;
	
	 private Object email;

	 private Object session;

	// Register
	//http://localhost:8087/
	@GetMapping("/")
	public String loadForm(Model model) {
		loadFormData(model);
		return "index";
	}
	private void loadFormData(Model model) {
		User user=new User();
		model.addAttribute("user", user);
	}
	@PostMapping("/save")
	public String handleSubmit(@Valid User u,BindingResult result, Model model) {

		// logic to save

		UserEntity entity = new UserEntity();

		// copy data from binding obj to entity obj
		BeanUtils.copyProperties(u, entity);
		
		repo.save(entity);
		if (result.hasErrors()) {
			return "index";
		} else {
			model.addAttribute("msg", "User Saved");
		}

		loadFormData(model);

		return "index";
	}
	
	
	
	//Login
	@GetMapping("/logged")
	public String loadFormLogin(Model model) {
		loadFormData(model);
		return "login";
	}
	private void loadFormLoginData(Model model) {
		User user=new User();
		model.addAttribute("user", user);
	}
	//
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
		@RequestParam("email") String email,
		@RequestParam("password") String password,
		//@RequestParam("name") String name,
		HttpSession session,
		Model model) {*/
	 @PostMapping("/login")
	  public String createTask(User u, @RequestParam("email") String email,
				@RequestParam("password") String password,HttpSession session,Model model) {
		UserEntity user = new UserEntity();

	    UserEntity emailAndPassword = repo.findByEmailAndPassword(email, password);
	    if (user == null) {
	      model.addAttribute("msg", "Login failed Enter correct crediantial..");
	      return "invalid";
	    } else if (email.equals(emailAndPassword.getEmail()) && password.equals(emailAndPassword.getPassword())) {
	    	session.setAttribute("email", email);
	    	model.addAttribute("msg", "Login Successfully");
	    	return "welcome";
	    } else {
	    	model.addAttribute("msg", "Login failed Enter correct crediantial..");
	    	return "invalid";
	    	
	        
	      }

	    }

	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
		@RequestParam("email") String email,
		@RequestParam("password") String password,
		//@RequestParam("name") String name,
		HttpSession session,
		Model model) {
		UserEntity entity = new UserEntity();
		Map<String, String> items = new HashMap<>();
		//UserEntity save = repo.save(entity);
		String string = items.get(email);
		String string2 = items.get(password);
		List<UserEntity> findAll = repo.findAll();
		
	   for (int i = 0; i < findAll.size(); i++) {
		
		if(findAll.get(i).getEmail().equals(email)&& findAll.get(i).getPassword().equals(password)) {
		//if(string.equals(email)&&string2.equals(password)) {
		session.setAttribute("email", email);
		repo.save(entity);
			//session.setAttribute("name", name)
			return "welcome";
		} else {
			model.addAttribute("msg", "Invalid Credentials");
			return "invalid";
		}
	   }
			loadFormData(model);

		return "welcome";
	}*/

	/*@PostMapping("/login")
	public String handleSubmitBtn(@Valid User u, BindingResult result,Model model) {

		// logic to save
		
		UserEntity entity = new UserEntity();
		
		List<UserEntity> findAll = repo.findAll();
	    for (int i = 0; i < findAll.size(); i++) {

	    	// if ((findAll.get(i).getEmail()).equals(findAll.get(i).getPassword())){
	    	if ((findAll.get(i).getEmail()).equals(email)&&(findAll.get(i).getPassword()).equals("diksha")){ 
	    	
	        return "welcome";
	      } else 
	        System.out.println("Invalid Credentials");
	    }
		
		// copy data from binding obj to entity obj
		BeanUtils.copyProperties(u, entity);
		
		repo.save(entity);
		if (result.hasErrors()) {
			return "welcome";
		} else {
			model.addAttribute("msg", "User loggedIn");
		}

		loadFormData(model);

		return "welcome";
	}
	*/
	
	
	//Create Task
	@GetMapping("/task")
	public String loadFormTask(Model model) {
		loadFormTaskData(model);
		return "task";
	}
	private void loadFormTaskData(Model model) {
		Task task=new Task();
		model.addAttribute("task", task);
	}
	/*@PostMapping("/tasksave")
	public String handleSubmit(Task t,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		     @DateTimeFormat(pattern = "HH:mm") LocalTime time, Model model) {
		
		
		// logic to save

		TaskEntity entity = new TaskEntity();
		entity.setDate(date);
		entity.setTime(time);

		// copy data from binding obj to entity obj
		BeanUtils.copyProperties(t, entity);

		

		repo1.save(entity);

		model.addAttribute("msg", "Task Saved");

		loadFormData(model);

		return "task";
	}
	*/
/////
	@PostMapping("/tasksave")
	  public String handleSubmitTaskTask(Task t, Model model) throws ParseException {

	    TaskEntity task = new TaskEntity();
	    task.setName(t.getName());
	    
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	      try {
	          Date date = formatter.parse(t.getDate());
	          task.setDate(date);
	      } catch (ParseException e) {
	          // handle parse exception
	      }
	    task.setTime(t.getTime().toString());
	  
	    TaskEntity createdTask = repo1.save(task);
	    if (createdTask != null) {
	        model.addAttribute("msg", "Task is created.");
	      //model.addAttribute("user", user);

	    } else {
	        model.addAttribute("msg", "Task is not created.");
	    }

	      return "task";
	  }
	  
	/*  @GetMapping("/tasks")
	  public String viewAllTasks(Model model) {
	    
	    List<Task> tasks = taskRepo.getAllTasks(this.id);
	    model.addAttribute("tasks", tasks);
	    
	    return "records";
	  }
*/

	
	/*public String handleSubmit(@RequestParam("name")String name,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		      @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time, Model model) {
		
		
		// logic to save

		TaskEntity entity = new TaskEntity();
		

		// copy data from binding obj to entity obj
		//BeanUtils.copyProperties(t, entity);

		

		repo1.save(entity);

		model.addAttribute("msg", "Task Saved");

		loadFormData(model);

		return "task";
	}*/
	/*public String createdTask(@RequestParam("name") String name,
		      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		      @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time, HttpServletRequest request,
		      HttpServletResponse response) throws ServletException, IOException {

		    HttpSession session = request.getSession();
		    String emailId = (String) session.getAttribute("Email");

		    Task t1 = new Task();
		    t1.setName(name);
		    t1.setDate(date);
		    t1.setTime(time);
		    repo1.save(t1);
		    return "task";
	}*/


	// method to display saved students data
	//http://localhost:8087/viewStudents
	@GetMapping("/viewTask")
	public String getUserssData(Model model) {
		
		// logic to fetch and send students data
		
		List<TaskEntity> TasksList = repo1.findAll();
		
		model.addAttribute("task", TasksList);
		
		return "data";
	}
}
