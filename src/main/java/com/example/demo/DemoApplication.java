package com.example.demo;

import controller.PlanFlight;
import entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import controller.UserController;
import usecases.AirportReadWriter;
import usecases.LoginHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Class to set up application for future use.
 * Consists of multiple functions that tie together database (springboot application) and hmtl/js code
 */

@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
public class DemoApplication {
    private LoginHandler us = new LoginHandler();
    private UserController uc = new UserController(us);
    private SearchResults sr;
    private Route selectedRoute;

    public void runDemo(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        try {
            return (String.valueOf(uc.login(username, password)));
        } catch (NullPointerException e) {
            return ("false");
        }
    }

    @GetMapping("/createAccount")
    public String createAccount(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                @RequestParam(value = "repeatPassword") String repeatPassword, @RequestParam(value = "email") String email,
                                @RequestParam(value = "phoneNumber") String phoneNumber) {

        return uc.createAccount(username, password, email, phoneNumber);
    }

    @GetMapping("/searchFlight")
    public String searchFlight(@RequestParam(value = "departure") String departure, @RequestParam(value = "destination") String destination,
                               @RequestParam(value = "date") String date) {
        if (departure.equals("")) {
            return ("Missing departure");
        }
        if (destination.equals("")) {
            return ("Missing destination");
        }
        PlanFlight planner = new PlanFlight(us.getCurrentUserUsername());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            return ("Invalid Date Format");
        }
        Airport departureAirport = AirportReadWriter.getAirportByName(departure);

        Airport destinationAirport = AirportReadWriter.getAirportByName(destination);
        if (departureAirport == null) {
            return ("Departure airport not found");
        }
        if (destinationAirport == null) {
            return ("Destination airport not found");
        }
        this.sr = PlanFlight.EnterSearchRequirements(cal, departureAirport, destinationAirport);

        return null;
    }

    @GetMapping("/selectFlight")
    public String selectFlight(@RequestParam(value = "id") String id) {
        for (Route r : this.sr.getPotentialRoutes()) {
            if (r.getRouteID() == Integer.parseInt(id)) {
                this.selectedRoute = r;
            }
        }
        System.out.println("selected flight");
        return ("true");
    }

    @GetMapping("/bookFlight")
    public String bookFlight(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
                             @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "email") String email,
                             @RequestParam(value = "dateOfBirth") String dateOfBirth) {

        uc.addRouteToHistory(this.selectedRoute);
        System.out.println("booked flight with id = " + this.selectedRoute.getRouteID());
        return "it worked!!!";
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

    @GetMapping("/UpdateAndSaveUserInformation")
    public String UpdateAndSaveUserSettings(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                            @RequestParam(value = "email") String email,
                                            @RequestParam(value = "phoneNumber") String phoneNumber) {
        System.out.println(password + email + phoneNumber);
        uc.setPassword(password);
        uc.setEmail(email);
        uc.setPhoneNumber(phoneNumber);
        uc.saveSettings();
        return ("true");
    }

    @GetMapping("/deleteRoute")
    public String viewRouteHistory(@RequestParam(value = "id") String id) {
        uc.removeRoutebyID(id);
        System.out.println("deleted route");
        return "removed route";
    }

	@GetMapping("/getPotentialFlights")
	public String getPotentialFlights() {
		System.out.println(this.sr.toString(uc.getCurrentUser()));
		return this.sr.toString(uc.getCurrentUser());
	}

	@GetMapping("/getPotentialFlightsByDuration")
	public String getPotentialFlightsByDuration() {
		sr.sortByDuration();
		System.out.println(this.sr.toString(uc.getCurrentUser()));
		return this.sr.toString(uc.getCurrentUser());
	}

	@GetMapping("/getPotentialFlightsByPrice")
	public String getPotentialFlightsByPrice() {
		sr.sortByPrice();
		System.out.println(this.sr.toString(uc.getCurrentUser()));
		return this.sr.toString(uc.getCurrentUser());
	}

	@GetMapping("/getSelectedFlight")
	public String getSelectedFlight() {
		System.out.println(this.selectedRoute.toString());
		return(this.selectedRoute.toString());
	}

	@GetMapping("/viewRouteHistory")
	public String viewRouteHistory() {
		System.out.println(uc.getRouteHistory());
		System.out.println(uc.getRouteHistory());
		return uc.getRouteHistory();
	}
}


            