package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * PremiumUserSettings is responsible for implementing premium user features and actions which are defined in BaseUserSettings
 */

public class PremiumUserSettings implements BaseUserSettings, Serializable {
    private String classType;
    private Calendar renewalDate;
    private final User user;
    private String colorScheme;
    private Airport favouriteAirport;
    private int autoLogoutTimer;
    private Airport homeAirport;

    public PremiumUserSettings(User user) {
        this.user = user;
        this.renewalDate = Calendar.getInstance();
        this.renewalDate.add(Calendar.YEAR, 1);
        this.colorScheme = "default";
        this.autoLogoutTimer = 60;

    }

    public boolean setClassType(String classType) {
        this.classType = classType;
        return true;
    }

    public String getClassType() { return classType; }

    public Calendar getRenewalDate() { return renewalDate; }

    public boolean setRenewalDate(Calendar renewalDate) {
        this.renewalDate = renewalDate;
        return true;
    }

    public String getColorScheme() { return colorScheme; }

    public boolean setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
        return true;
    }

    public Airport getFavouriteAirport() { return favouriteAirport; }

    public boolean setFavouriteAirport(Airport favouriteAirport) {
        this.favouriteAirport = favouriteAirport;
        return true;
    }

    public int getAutoLogoutTimer() { return autoLogoutTimer; }

    public boolean setAutoLogoutTimer(int autoLogoutTimer) {
        this.autoLogoutTimer = autoLogoutTimer;
        return true;
    }

    public Airport getHomeAirport() { return homeAirport; }

    public boolean setHomeAirport(Airport homeAirport) {
        this.homeAirport = homeAirport;
        return true;
    }

    public String upgradeUserType() {
        return "User Type is already Premium.";
    }

    /**
     * Downgrade PremiumUser to BasicUser by creating a new BasicUser and passing it to userManager
     * @return None
     */
    public String downgradeUserType() {
        this.user.changeUserType(new BasicUserSettings(this.user));
        return "User Type downgraded to Basic.";
    }

    /**
     * Update this user's settings given a HashMap of settings to be updated
     */
    public String updateSettings(Map<String, String> settingsHash) {
        return "true";
    }

    /**
     * Return this user's settings as a JSON parseable string
     */
    public String toJSONString() {
        return "{" +
                "\"userType\":" +
                "\"premium\"," +
                "\"Color_Scheme\":" +
                "\"" +
                this.colorScheme +
                "\"," +
                "\"Home_Airport\":" +
                "\"" +
                this.homeAirport.getCity() +
                "\"," +
                "\"Favourite_Airport\":" +
                "\"" +
                this.favouriteAirport.getCity() +
                "\"," +
                "\"Auto_Logout_Timer\":" +
                "\"" +
                this.autoLogoutTimer +
                "\"," +
                "\"Class_Type\":" +
                "\"" +
                this.classType +
                "\"," +
                "\"Renewal_Date\":" +
                "\"" +
                new SimpleDateFormat("MM-dd-yyyy").format(this.renewalDate) +
                "\"" +
                "}";
    }
}
