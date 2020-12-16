package com.virtusa.banking.appointment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtusa.banking.appointment.models.Appointment;
import com.virtusa.banking.appointment.services.AppointmentService;

import net.minidev.json.JSONObject;
@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	//saving the Appointment details
	@CrossOrigin("*")
	@PostMapping("/appointments")
	public @ResponseBody ResponseEntity<?> addAppointment(@RequestBody Appointment appointment)
	{
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("message", "Appointment could not be made because customer id is invalid");
		
		if(this.appointmentService.saveAppointment(appointment)!=null)
			return ResponseEntity.ok(this.appointmentService.saveAppointment(appointment));
		else
	    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(jsonObject);
	}
	
	//retrieval 
	
	@CrossOrigin("*")
	@GetMapping("/appointments")
	public List<Appointment> findAllAppointments()
	{
		return this.appointmentService.getAllAppointments();
	}
	
	//retrieval  by id
	
		@CrossOrigin("*")
		@GetMapping("/appointments/{appointmentId}")
		public Appointment findAppointmentById(@PathVariable("appointmentId") long appointmentId)
		{
			return this.appointmentService.getAppointmentById(appointmentId);
		}
		
	/*
	 * @CrossOrigin("*")
	 * 
	 * @GetMapping("/appointments/{appointmentId}") public String
	 * findAppointmentCustomerById(@PathVariable("appointmentId") long
	 * appointmentId) throws JsonProcessingException { return
	 * this.appointmentService.getAppointmentCustomerById(appointmentId);
	 * 
	 * }
	 */
		
		
		@CrossOrigin("*")
		@DeleteMapping("/appointments/{id}")
		public boolean deleteAppointmentById(@PathVariable("id") long id)
		{
			this.appointmentService.deleteAppointmentById(id);
			boolean status=false;
			if(appointmentService.getAppointmentById(id)==null)
				status=true;
			return status;
		}
		
		
		@CrossOrigin("*")
		@PutMapping("/appointments")
		public @ResponseBody Appointment updateAppointment(@RequestBody Appointment appointment)
		{
			return appointmentService.updateAppointment(appointment);
		}
			
}
