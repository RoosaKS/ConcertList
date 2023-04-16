package hh.sof3as3.Concerts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.ConcertRepository;
import hh.sof3as3.Concerts.domain.SignupForm;
import hh.sof3as3.Concerts.domain.User;
import hh.sof3as3.Concerts.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private UserRepository repository; 
	@Autowired
	private ConcertRepository concertRepository;
	
	@RequestMapping(value="/login")
    public String login(Model model) {	
			List<Concert> concerts = (List<Concert>)concertRepository.findAll();
			
			model.addAttribute("concerts",concerts);
			
        return "concertlist";
    }
	
    @RequestMapping(value = "/signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}
