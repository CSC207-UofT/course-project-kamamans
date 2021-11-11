package entities;

import java.util.Comparator;
import java.util.List;

public class SearchResults {

    private List<Route> potentialRoutes;

    public SearchResults(List<Route> potentialRoutes){
        this.potentialRoutes = potentialRoutes;
    }

    public List<Route> getPotentialRoutes() {
        return potentialRoutes;
    }

    public void setPotentialRoutes(List<Route> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public String getPrice(Route route){
        return route.toString();
    }

    public void sortByPrice() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getPriceofFlights));
    }

    public void sortByDuration() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getTotalDuration));
    }
}
