package entities;

import java.util.Date;

public interface BaseUser {
    abstract String setClassType(String classType);

    abstract String upgradeUserType();
    abstract String downgradeUserType();
}
