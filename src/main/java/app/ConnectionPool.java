package app;

public class ConnectionPool{

    private String masterUrl="jdbc:mysql://localhost:3306/people";// link to masterDB
    private String user="root";
    private String password="root";

    private String secondUrl="jdbc:mysql://localhost:3306/people1";//link to reserveDB

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String masterUser) {
        this.user = masterUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondUrl() {
        return secondUrl;
    }
}
