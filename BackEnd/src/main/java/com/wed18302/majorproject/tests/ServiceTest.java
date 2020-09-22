package com.wed18302.majorproject.tests;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wed18302.majorproject.BookingManager;
import com.wed18302.majorproject.ServiceManager;
import com.wed18302.majorproject.model.Booking;
import com.wed18302.majorproject.model.BookingRepository;
import com.wed18302.majorproject.model.Service;
import com.wed18302.majorproject.model.ServiceRepository;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.util.JsonErrorResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ServiceTest {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    private ServiceManager serviceManager;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    String time_str =ZonedDateTime.now(ZoneId.of("UTC")).toString();
    private void userInitialize (){
        userRepository.save(new User("customer.1@test.com", "password", 0, "customer", "a"));
        userRepository.save(new User("worker.1@test.com", "password", 1, "worker", "a"));
        userRepository.save(new User("worker.2@test.com", "password", 1, "worker", "b"));
        userRepository.save(new User("admin.1@test.com", "password", 2, "admin", "a"));
        userRepository.save(new User("admin.2@test.com", "password", 2, "admin", "b"));
    }

    @Test
    public void setServices_NewService(){
        String status ="pass";
        userInitialize();
        List<Service> services = null;
//        serviceRepository.save(new Service(time_str,userRepository.findByEMAIL("admin.1@test.com"),"type1","service1","stuff"));
        try{
            serviceManager.makeService(userRepository.findByEMAIL("admin.1@test.com").getId(),"type1","service1","stuff");
        }catch (JsonErrorResponse e){
            status = e.toString();
            e.printStackTrace();
        }
        Assert.assertTrue(serviceRepository.findByID(0).getName().equals("service1"));

        Assert.assertTrue(status.contains("pass"));

    }
    @Test
    public void setServices_assignWorker(){

        userInitialize();
        serviceRepository.save(new Service(time_str,userRepository.findByEMAIL("admin.1@test.com"),"type1","foo","bar"));

        try{
            serviceManager.assignWorker(serviceRepository.findByName("foo").getId(),userRepository.findByEMAIL("worker.1@test.com").getId());
            serviceManager.assignWorker(serviceRepository.findByName("foo").getId(),userRepository.findByEMAIL("worker.2@test.com").getId());
        }catch (JsonErrorResponse e){

            e.printStackTrace();
        }

        Assert.assertTrue(serviceRepository.findByName("foo").getWorkers().get(0).equals(userRepository.findByEMAIL("worker.1@test.com")));
        Assert.assertTrue(serviceRepository.findByName("foo").getWorkers().get(1).equals(userRepository.findByEMAIL("worker.2@test.com")));
    }

}
