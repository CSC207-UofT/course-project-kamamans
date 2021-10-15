package controller;

import entities.Airport;
import entities.Flight;
import usecases.InteractDatabase;

import java.util.ArrayList;
import java.util.Date;
import entities.SearchQueries;
import entities.Route;
public class PlanFlight {

    private Flight selectedFlight;


    public SearchQueries<Route> EnterSearchRequirements(Date date, Airport departure, Airport destination ){
        InteractDatabase db = new InteractDatabase();
        ArrayList<Route> routes = db.routeByParameters( departure, destination, date);
        return new SearchQueries<>(routes);
    }
    public void selectFlight(Flight flightToBeSelected){
        this.selectedFlight = flightToBeSelected;
    }
    public Flight getSelectedFlight(){
        return this.selectedFlight;
    }

}