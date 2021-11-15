package entities;

/**
 * BaseUser defines common methods with different implementations depending on Basic or Premium user status.
 */

public interface BaseUser {
    String setClassType(String classType);

    String upgradeUserType();
    String downgradeUserType();
}
