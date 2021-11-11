package controller;

import entities.Airport;
import entities.Flight;
import usecases.InteractDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.SearchResults;
import entities.Route;
import entities.BasicUser;

public class PlanFlight {
    private Flight selectedFlight;
    private final BasicUser user;

    public PlanFlight(BasicUser user){
        this.user = user;
    }

    public SearchResults EnterSearchRequirements(Date date, Airport departure, Airport destination ){
        InteractDatabase db = new InteractDatabase();
        ArrayList<Route> routes = db.getRoutes();
        return new SearchResults(routes);
    }

    public void selectFlight(Flight flightToBeSelected){
        this.selectedFlight = flightToBeSelected;
    }

    public Flight getSelectedFlight(){
        return this.selectedFlight;
    }

    public BasicUser getUser() {
        return this.user;
    }
}