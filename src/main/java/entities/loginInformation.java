package entities;

import java.util.HashMap;

public class loginInformation {
    private HashMap<String, String> info = new HashMap<>();

    public void setUsername(String u){
        info.put("username", u);
    }

    public void setPassword(String p){
        info.put("password", p);
    }

    public String getUsername(){
        return info.get("username");
    }

    public String getPassword(){
        return info.get("password");
    }

}
