package com.wed18302.majorproject.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureWebTestClient
@Profile("test")
public class UserTest {
	
    @Autowired
    private UserRepository userRepository;
   
    @Test
    public void registration_testDatabaseUserAddition() {
    	var testUser = new User("paul.smith@gmail.com", "password", 0, "Paul", "Smith");
    	userRepository.save(testUser);

        List<User> userSearch = (List<User>) userRepository.findAll();
        Assert.assertTrue(userSearch.size() == 1);
        var resultUser = userSearch.get(0);
        Assert.assertTrue(resultUser.equals(testUser));
    }    

    @Test
    public void registration_testDatabaseUserSearch() {
    	registration_testDatabaseUserAddition();
    	
    	String search = "paul.smith@gmail.com";
        
        User resultUser = null;
        for (User user : userRepository.findAll()) {
        	if (user.getEmail() == search) {
        		resultUser = user;
        		break;
        	}
        }
        
        Assert.assertTrue(resultUser.getEmail() == search);
    }    
    
    @Test
    public void registration_testDatabaseUserDelete() {
    	registration_testDatabaseUserAddition();
    	
    	String account = "paul.smith@gmail.com";
    	
        User resultUser = null;
        for (User user : userRepository.findAll()) {
        	if (user.getEmail() == user.getEmail()) {
        		resultUser = user;
        		break;
        	}
        }

        Assert.assertTrue(resultUser != null);
        Assert.assertTrue(userRepository.findByEMAIL(account) != null);
        userRepository.delete(resultUser);
        Assert.assertTrue(userRepository.findByEMAIL(account) == null);

    }   
    
    
    
}
