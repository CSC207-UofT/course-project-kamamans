package entities;

import java.util.ArrayList;
import java.util.List;

public class PremiumSettings {

    private String colorScheme;
    private List<Airport> favouriteAirports = new ArrayList<>();
    private double autoLogoutTimer;
    private Airport homeAirport;

    public PremiumSettings(){
        colorScheme = "default";
        autoLogoutTimer = 30;
    }

    public PremiumSettings(String colorScheme, List<Airport> favouriteAirports, double autoLogoutTimer, Airport homeAirport) {
        this.colorScheme = colorScheme;
        this.favouriteAirports = favouriteAirports;
        this.autoLogoutTimer = autoLogoutTimer;
        this.homeAirport = homeAirport;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public List<Airport> getFavouriteAirports() {
        return favouriteAirports;
    }

    public void addFavouriteAirport(Airport favouriteAirport) {
        this.favouriteAirports.add(favouriteAirport);
    }

    public void removeFavouriteAirport(Airport removedAirport) {
        this.favouriteAirports.remove(removedAirport);
    }

    public double getAutoLogoutTimer() {
        return autoLogoutTimer;
    }

    public void setAutoLogoutTimer(double autoLogoutTimer) {
        this.autoLogoutTimer = autoLogoutTimer;
    }

    public Airport getHomeAirport() {
        return homeAirport;
    }

    public void setHomeAirport(Airport homeAirport) {
        this.homeAirport = homeAirport;
    }

}
