package entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchQueries<T> {

    private List<Route<String>> potentialRoutes;

    public SearchQueries(List<Route<String>> potentialRoutes){
        this.potentialRoutes = potentialRoutes;
    }

    public List<Route<String>> getPotentialRoutes() {
        return potentialRoutes;
    }

    public void setPotentialRoutes(List<Route<String>> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public String getPrice(Route<String> route){
        return route.toString();
    }

    public void sortByPrice() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getPriceofFlights));
    }

    public void sortByDuration() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getTotalDuration));
    }
}
