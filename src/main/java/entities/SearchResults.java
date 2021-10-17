package entities;

import java.util.Comparator;
import java.util.List;

public class SearchResults {

    private List<Route<Airport>> potentialRoutes;

    public SearchResults(List<Route<Airport>> potentialRoutes){
        this.potentialRoutes = potentialRoutes;
    }

    public List<Route<Airport>> getPotentialRoutes() {
        return potentialRoutes;
    }

    public void setPotentialRoutes(List<Route<Airport>> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public String getPrice(Route<Airport> route){
        return route.toString();
    }

    public void sortByPrice() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getPriceofFlights));
    }

    public void sortByDuration() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getTotalDuration));
    }
}
