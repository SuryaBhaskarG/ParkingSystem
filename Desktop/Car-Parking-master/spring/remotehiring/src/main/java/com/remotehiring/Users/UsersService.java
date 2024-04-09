package com.remotehiring.Users;

//import java.security.SecureRandom;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.remotehiring.Mail.MailConfiguration;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository repo;
	@Autowired
	private MailConfiguration mailConfiguration;

	public List<Users> listAll(){
		return repo.findAll();
	}
	
	public void save(Users users) {
//	BCryptPasswordEncoder passEncrypt = new BCryptPasswordEncoder(10,new SecureRandom());
//	users.setPassword(passEncrypt.encode(users.getPassword()));
		repo.save(users);
	}
	
	public Users get(String email) {
		return repo.findById(email).get();
	}
	
	public void delete(String email) {
		repo.deleteById(email);
	}
	
	public boolean checkuser(String email) {
		return repo.existsById(email);
		
	}
	
	public boolean checklogin(String emailid, String pass) {
		if (checkuser(emailid)) {
			Users users = get(emailid);
			if(users.getPassword().equals(pass)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public String forgetPassword(String email) {
	Users users=repo.findByEmail(email).orElseThrow
			(()-> new RuntimeException(" user with given email notFound!: "+email));
		try
		{
			mailConfiguration.sendSetPasswordEmail(email);
		}catch(MessagingException e)
		{
			throw new RuntimeException("Unable to send set password email please try again");
		}
		return "mail send successfully please cheeck and resetPassword";
	}

	public String setPassword(String email, String newPassword) {
		Users users=repo.findByEmail(email).orElseThrow
				(()-> new RuntimeException(" user with given email notFound!: "+email));
			users.setPassword(newPassword);
			repo.save(users);
			return "new password set succcessfully login with new password! <^!^>";
		}
	

}
