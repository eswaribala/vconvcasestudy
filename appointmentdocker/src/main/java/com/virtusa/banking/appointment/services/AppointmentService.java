package com.virtusa.banking.appointment.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.virtusa.banking.appointment.models.Appointment;
import com.virtusa.banking.appointment.models.AuthenticationRequest;
//import com.virtusa.banking.appointment.models.Customer;
import com.virtusa.banking.appointment.repositories.AppointmentRepository;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;


@Service
public class AppointmentService {
    @Autowired
	private AppointmentRepository appointmentRepo;
   // @Autowired
   // private RestTemplate restTemplate;
	//@Value("${authUrl}")
	//private String authUrl;
	//@Value("${serviceUri}")
	//private String serviceUrl;
  //insert the appointment
  	public Appointment saveAppointment(Appointment appointment)
  	{
		/*
		 * //System.out.println(serviceUrl);
		 * 
		 * Appointment responseAppointment=null; //inter service communication sync one
		 * to one //http://localhost:8765/api/customer/customers/1
		 * 
		 * ResponseEntity<String> response =restTemplate.exchange(serviceUrl+
		 * +appointment.getCustomerId(),HttpMethod.GET,null,String.class);
		 * System.out.println(response.getHeaders()); if(response!=null) {
		 */
  		 //if(parseString(response.getBody())==appointment.getCustomerId()) 
  		  return this.appointmentRepo.save(appointment);
  
   	}
  	
  	//findall
  	public List<Appointment> getAllAppointments()
  	{
  		return this.appointmentRepo.findAll();
  	}
  	

  	//select where by appointmentId
  	public Appointment getAppointmentById(long appointmentId)
  	{
  		return this.appointmentRepo.findById(appointmentId).orElse(null);
  		
  	}
	
  	
  //select where by appointmentId
  /**
  	public String getAppointmentCustomerById(long appointmentId) throws JsonProcessingException
  	{
  		AuthenticationRequest authRequestObj=new AuthenticationRequest();
		 authRequestObj.setUsername("admin");
		 authRequestObj.setPassword("password");
  	        //phase 1 get jwt token
  			ResponseEntity<String> authRequest=restTemplate.
  		      postForEntity(authUrl,authRequestObj, String.class);
  	       System.out.println(parseString(authRequest.getBody()));  
  	       
  			//phase 2
  	       
  	       Appointment appointment=getAppointmentById(appointmentId);
  	       
  	       HttpHeaders headers = new HttpHeaders();
  	       headers.setContentType(MediaType.APPLICATION_JSON);
  	      headers.set("Authorization", "Bearer "+parseString(authRequest.getBody()));
  		
		 HttpEntity<Customer> request = new HttpEntity<Customer>(null,headers);
		 
		 ResponseEntity<Customer> responseEntityStr = restTemplate.
           exchange(serviceUrl+"/getcustomerbyid/"+appointment.getCustomerId(),HttpMethod.GET, request,
		  Customer.class); System.out.println(responseEntityStr); 
		  // Creating Object of objectMapper define in Jakson Api 
		  ObjectMapper Obj = new ObjectMapper();
		 String customerStr=Obj.writeValueAsString(responseEntityStr); 
		 System.out.println(customerStr);
		 String  appointmentStr=Obj.writeValueAsString(appointment);
		 
  	     return appointmentStr+"===>"+customerStr;
  	}

	 **/
  	//delete the Appointment
  	

  		public void deleteAppointmentById(long appointmentId)
  		{
  		     this.appointmentRepo.deleteById(appointmentId);
  		}
  	
  		//update the Appointment
  		public Appointment updateAppointment(Appointment appointment)
  		{
  			return this.appointmentRepo.save(appointment);
  		}

  		private long parseString(String response)
  		{
  			JSONParser parser = new JSONParser(); 
  			long customerId=0;
  		  	try {
  		  		 
  				// Put above JSON content to crunchify.txt file and change path location
  				Object obj = parser.parse(response);
  				JSONObject jsonObject = (JSONObject) obj;
  	 
  				// JsonFlattener: A Java utility used to FLATTEN nested JSON objects
  				String flattenedJson = JsonFlattener.flatten(jsonObject.toString());
  				System.out.println("\n=====Simple Flatten===== \n" + flattenedJson);
  	 
  				Map<String, Object> flattenedJsonMap = JsonFlattener.flattenAsMap(jsonObject.toString());
  			 	customerId=Long.parseLong(flattenedJsonMap.get("customerId").toString());       
  				//log("\n=====Flatten As Map=====\n" + flattenedJson);
  				// We are using Java8 forEach loop. More info: https://crunchify.com/?p=8047
  				//flattenedJsonMap.forEach((k, v) -> log(k + " : " + v));
  	 
  				// Unflatten it back to original JSON
  				String nestedJson = JsonUnflattener.unflatten(flattenedJson);
  				System.out.println("\n=====Unflatten it back to original JSON===== \n" + nestedJson);
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		 return customerId;

  		}
    
}
