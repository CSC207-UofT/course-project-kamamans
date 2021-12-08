package entities;

import java.util.Date;
import java.util.List;

/**
 * BaseUserSettings defines all methods across BasicUserSettings and PremiumUserSettings with different implementations
 * depending on user type.
 */

public interface BaseUserSettings {
    boolean setClassType(String classType);
    String getClassType();
    Date getRenewalDate();
    boolean setRenewalDate(Date renewalDate);
    String getColorScheme();
    boolean setColorScheme(String colorScheme);
    List<Airport> getFavouriteAirports();
    boolean addFavouriteAirport(Airport favAirport);
    boolean removeFavouriteAirport(Airport favAirport);
    int getAutoLogoutTimer();
    boolean setAutoLogoutTimer(int autoLogoutTimer);
    Airport getHomeAirport();
    boolean setHomeAirport(Airport homeAirport);
    String upgradeUserType();
    String downgradeUserType();
    void updateSettings();
}
