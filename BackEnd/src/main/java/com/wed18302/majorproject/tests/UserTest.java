package com.wed18302.majorproject.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserTest {
	
    @Autowired
    private UserRepository userRepository;
   
    @Test
    public void registration_testDatabaseUserAddition() {
    	int oldUserCount = userRepository.findAll().size();

    	// make sure test account is not in the database already
    	String email = "paul.smith.test.account@gmail.com";
        Assert.assertTrue(userRepository.findByEMAIL(email) == null);

    	// save new test account to the database
    	var testUser = new User(email, "password", 0, "Paul", "Smith");
    	userRepository.save(testUser);

    	// search for newly added user and make sure that it is ours
        List<User> userSearch = (List<User>) userRepository.findAll();
        Assert.assertTrue(userSearch.size() == oldUserCount + 1);
        Assert.assertTrue(userRepository.findByEMAIL(email) != null);

    	// delete the newly added test user
        userRepository.delete(testUser);
        Assert.assertTrue(userRepository.findByEMAIL(email) == null);
    }    

    /*@Test
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
    
    @Test
    // sets a target user to admin
    public void registration_testDatabaseUserSetToAdmin() {
    	registration_testDatabaseUserAddition();
    	        
        User resultUser = userRepository.findByEMAIL("paul.smith@gmail.com");

        UserType type = resultUser.getUserType();
        Assert.assertTrue(type == UserType.Customer);
        resultUser.SetUserType(UserType.Administrator);
        type = resultUser.getUserType();
        Assert.assertTrue(type == UserType.Administrator);
    }   */
    
}
