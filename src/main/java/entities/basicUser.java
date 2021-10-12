package entities;

public class basicUser extends loginInformation{
    private String ID;
    private String email;
    private String phoneNumber;
    private String classType;

    public basicUser(){

    }

    public basicUser(String id, String email, String phoneNumber, String c){
        this.ID = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


}
