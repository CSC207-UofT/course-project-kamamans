package entities;

import java.util.Date;

/**
 * BaseUser defines common methods with different implementations depending on Basic or Premium user status.
 */

public interface BaseUser {
    abstract String setClassType(String classType);

    abstract String upgradeUserType();
    abstract String downgradeUserType();
}
