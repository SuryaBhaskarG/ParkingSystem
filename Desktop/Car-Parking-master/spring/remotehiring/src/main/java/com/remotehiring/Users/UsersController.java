package com.remotehiring.Users;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import freemarker.template.TemplateException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@Autowired
	public JavaMailSender emailSender;

	@GetMapping("/users")
	public List<Users> list(){
		return service.listAll();
	}
	
	@GetMapping("/users/{email}")
	public ResponseEntity<Users> get(@PathVariable String email) {
		try {
			Users users =  service.get(email);
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*@PostMapping("/users/signup")
	public ResponseEntity<Boolean> add(@RequestBody Users users) throws MessagingException, IOException, TemplateException {
		if(service.checkuser(users.getEmail()) == true) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		else {
			service.save(users);
			//mailservice.signupMail(users);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}	
	} */
	@PostMapping("/users/signup")
	public ResponseEntity<Boolean> add(@RequestBody Users users) throws MessagingException, IOException, TemplateException {
	    if(service.checkuser(users.getEmail()) == true) {
	        return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	    }
	    else {
	        service.save(users);

	        // Send email
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("noreply@baeldung.com");
	        message.setTo(users.getEmail()); 
	        message.setSubject("Signup Successful"); 
	        message.setText("Signup successful! Please log in now.");
	        emailSender.send(message);

	        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	    }   
	}
	
	@GetMapping("users/getname/{email}")
	public String getUser(@PathVariable String email) {
		String mail = service.get(email).getFullname();
		return mail;
	}
	
	@DeleteMapping("/users/{email}")
	public void delete(@PathVariable String email) {
		service.delete(email);
	}
	
	@PutMapping("users/{email}")
	public ResponseEntity<Users> update(@RequestBody Users users, @PathVariable String email) {
		try {
			Users existUser = service.get(email); 
			System.out.print(existUser);
			service.save(users);
			return new ResponseEntity<Users>(HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND); 
		}
	}

	
	@PostMapping("users/login")
	public boolean check(@RequestBody Users users) {
		return service.checklogin(users.getEmail(), users.getPassword());
		
	}
	@PutMapping("users/forget-password")
	public ResponseEntity<String> forgetPassword(@RequestParam String email) 
	{
		return new ResponseEntity<>(service.forgetPassword(email),HttpStatus.OK);
	}
	@PutMapping("users/set-password")
	public ResponseEntity<String> setPassword(@RequestParam String email,@RequestHeader String newPassword) 
	{
		return new ResponseEntity<>(service.setPassword(email,newPassword),HttpStatus.OK);
	}
	
}








