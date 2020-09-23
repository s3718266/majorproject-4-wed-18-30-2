package com.wed18302.majorproject.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wed18302.majorproject.ServiceManager;
import com.wed18302.majorproject.interfaces.GenericWebJsonResponse;
import com.wed18302.majorproject.model.Booking;
import com.wed18302.majorproject.model.Service;
import com.wed18302.majorproject.util.JsonErrorResponse;
import com.wed18302.majorproject.util.WebResponseUtil;

@RestController
public class ServiceController {

	@Autowired
	ServiceManager serviceManager;

    @RequestMapping(value="/service/add", method = RequestMethod.POST)  
    @ResponseBody 
	// addService?adminEmail={email}&type=type&name=name&description=description
	public ResponseEntity<Object> addService(int adminId, String type, String name, String description) throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serializeToJson(serviceManager.makeService(adminId, type, name, description));
			}
		});
	}
    
    @RequestMapping(value="/service/delete", method = RequestMethod.POST)  
    @ResponseBody 
	// addService?adminEmail={email}&type=type&name=name&description=description
	public ResponseEntity<Object> deleteService(int serviceId) throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serviceManager.deleteService(serviceId);
			}
		});
	}

    @RequestMapping(value="/service/assignworker", method = RequestMethod.POST)  
    @ResponseBody 
    public ResponseEntity<Object> assignWorker(int serviceId, int userId) throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serviceManager.assignWorker(serviceId, userId);
			}
		});
	}
    
    @RequestMapping(value="/service/removeworker", method = RequestMethod.POST)  
    @ResponseBody 
    public ResponseEntity<Object> removeWorker(int serviceId, int userId) throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serviceManager.removeWorker(serviceId, userId);
			}
		});
	}

    @RequestMapping(value="/service/get", method = RequestMethod.POST)  
    @ResponseBody  
	public ResponseEntity<Object> getService(int serviceId) throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serviceManager.getService(serviceId);
			}
		});
	}
    
    @RequestMapping(value="/service/getall", method = RequestMethod.POST)  
    @ResponseBody  
	public ResponseEntity<Object> getServices() throws JsonErrorResponse {
    	return WebResponseUtil.genericWebResponse(new GenericWebJsonResponse() {

			@Override
			public HashMap<String, Object> getResponse() throws JsonErrorResponse {
				return serviceManager.getServices();
			}
		});
	}

	public static HashMap<String, Object> serializeToJson(List<Service> services) {
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        for (Service s : services)
        	hmap.put(Integer.toString(s.getId()), s);
        return hmap;
	}
}
