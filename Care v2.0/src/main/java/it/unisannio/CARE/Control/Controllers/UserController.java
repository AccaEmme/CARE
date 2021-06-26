/**
 * 
 */
package it.unisannio.CARE.Control.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.Control.Interfaces.UserRepository;
import it.unisannio.CARE.Model.Beans.UserBean;
import it.unisannio.CARE.Model.User.User;
import it.unisannio.CARE.Model.User.Role;


/**
 * @author AccaEmme on 2021-06-26
 *
 */

@CrossOrigin("*")
@RestController
public class UserController /*implements ContainerResponseFilter*/ {

	/*
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
    */
    
    private final UserRepository userRepo;
    
    public UserController(UserRepository userRepo) {
    	this.userRepo = userRepo;
    }

    
    //===============POST METHODS
    @PostMapping("/addUser")
	public UserBean createNote(@RequestBody UserBean newUser) {
		User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getHiddenPassword(),		// HTTP plainTextPassword
				Role.valueOf(newUser.getUserRole()) // HTTP Role
				);
	
		UserBean saveBean = tempUserObj.getUserBean();
		//saveBean.setCreationDate(null);
		
		return userRepo.save(saveBean);
	}
    
}
