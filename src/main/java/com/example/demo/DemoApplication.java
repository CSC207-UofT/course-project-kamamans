
package com.example.demo;
import controller.PlanFlight;
import entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usecases.InteractDatabase;
import controller.UserController;
import usecases.LoginHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
public class DemoApplication {

	private LoginHandler us = new LoginHandler();
	private UserController uc = new UserController(us);
	private SearchResults sr;
	private Route selectedRoute;
	public void runDemo(String[] args)  {

		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/login")
	public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String  password) {
		try{
			return (String.valueOf(uc.login(username, password)));
		}catch (NullPointerException e){
			return("false");
		}


	}
	@GetMapping("/createAccount")
	public String createAccount(@RequestParam(value = "username") String username, @RequestParam(value = "password") String  password,
								@RequestParam(value = "repeatPassword") String  repeatPassword, @RequestParam(value = "email") String  email,
								@RequestParam(value = "phoneNumber") String  phoneNumber) {

		return uc.createAccount(username, password, email, phoneNumber);
	}
	@GetMapping("/searchFlight")
	public String searchFlight(@RequestParam(value = "departure") String departure, @RequestParam(value = "destination") String  destination,
								@RequestParam(value = "date") String  date)  {
		if(departure.equals("")){
			return("Missing departure");
		}
		if(destination.equals("")){
			return("Missing destination");
		}
		PlanFlight planner = new PlanFlight(us.getCurrentUserUsername());
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		try{
			cal.setTime(sdf.parse(date));
		} catch (ParseException  e ){
			return("Invalid Date Format");
		}
		try{

			Airport departureAirport = InteractDatabase.getAirportByName(departure);

			Airport destinationAirport = InteractDatabase.getAirportByName(destination);
			if(departureAirport == null){
				return("Departure airport not found");
			}
			if(destinationAirport == null){
				return("Destination airport not found");
			}
			this.sr = PlanFlight.EnterSearchRequirements(cal, departureAirport, destinationAirport);
			System.out.println(this.sr.routesToString());

			return null;
		} catch (IOException | ClassNotFoundException e) {
			return("Airport not found");
		}

	}
	@GetMapping("/getPotentialFlights")
	public String getPotentialFlights() {
		System.out.println(this.sr.routesToString().toString());
		return this.sr.routesToString().toString();
	}
	@GetMapping("/getPotentialFlightsByDuration")
	public String getPotentialFlightsByDuration() {
		sr.sortByDuration();
		System.out.println(this.sr.routesToString().toString());
		return this.sr.routesToString().toString();
	}
	@GetMapping("/getPotentialFlightsByPrice")
	public String getPotentialFlightsByPrice() {
		sr.sortByPrice();
		System.out.println(this.sr.routesToString().toString());
		return this.sr.routesToString().toString();
	}
	@GetMapping("/selectFlight")
	public String selectFlight(@RequestParam(value = "id") String id) {
		 this.selectedRoute = this.sr.getPotentialRoutes().get(Integer.parseInt(id));
		 for (Route r : this.sr.getPotentialRoutes()){
			 if (r.getRouteID() == Integer.parseInt(id)){
				 this.selectedRoute = r;
			 }
		 }
		 System.out.println("selected flight");
		return("true");
	}
	@GetMapping("/getSelectedFlight")
	public String getSelectedFlight() {
		System.out.println(this.selectedRoute.routeToString().toString());
		return(this.selectedRoute.routeToString().toString());
	}
	@GetMapping("/bookFlight")
	public String bookFlight(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
							 @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "email") String email,
							 @RequestParam(value = "dateOfBirth") String dateOfBirth) {
		System.out.println("booked flight");
		return "true";
	}
	@GetMapping("/getUserData")
	public String getUserData() {
		return uc.getUserDataJson();
	}
	@GetMapping("/getUserSettings")
	public String getUserSettings() {
		System.out.println(uc.getUserSettingsJson());
		return uc.getUserSettingsJson();
	}

}


            