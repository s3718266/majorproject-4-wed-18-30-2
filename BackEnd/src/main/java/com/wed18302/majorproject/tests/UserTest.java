package com.wed18302.majorproject.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
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
    
}
