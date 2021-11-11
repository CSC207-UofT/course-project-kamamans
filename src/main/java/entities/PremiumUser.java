package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PremiumUser is responsible for implementing premium user features and actions which are defined in BaseUser
 */

public class PremiumUser implements BaseUser {
    private String classType;
    private Date renewalDate;
    private UserManager userManager;
    private String colorScheme;
    private List<Airport> favouriteAirports = new ArrayList<>();
    private double autoLogoutTimer;
    private Airport homeAirport;

    public PremiumUser(UserManager userManager) {
        this.userManager = userManager;
    }

    public String setClassType(String classType) {
        this.classType = classType;
        return "Class Type changed to " + classType + ".";
    }

    public String getClassType() { return this.classType; }

    public Date getRenewalDate() { return this.renewalDate; }

    public void setRenewalDate(Date renewalDate) { this.renewalDate = renewalDate; }

    public String getColorScheme() { return colorScheme; }

    public void setColorScheme(String colorScheme) { this.colorScheme = colorScheme; }

    public List<Airport> getFavouriteAirports() { return favouriteAirports; }

    public void addFavouriteAirport(Airport favouriteAirport) { this.favouriteAirports.add(favouriteAirport); }

    public void removeFavouriteAirport(Airport removedAirport) { this.favouriteAirports.remove(removedAirport); }

    public double getAutoLogoutTimer() { return autoLogoutTimer; }

    public void setAutoLogoutTimer(double autoLogoutTimer) { this.autoLogoutTimer = autoLogoutTimer; }

    public Airport getHomeAirport() { return homeAirport; }

    public void setHomeAirport(Airport homeAirport) { this.homeAirport = homeAirport; }

    public String upgradeUserType() {
        return "User Type is already Premium.";
    }

    /**
     * Downgrade PremiumUser to BasicUser by creating a new BasicUser and passing it to userManager
     * @return None
     */
    public String downgradeUserType() {
        this.userManager.changeUserType(new BasicUser(this.userManager));
        return "User Type downgraded to Basic.";
    }
}
