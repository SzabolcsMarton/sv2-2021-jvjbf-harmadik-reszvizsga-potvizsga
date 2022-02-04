package contentsite;

public class User {

    private String userName;
    private int password;
    private boolean isPremiumMember;
    private boolean isLogIn;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = new ContentHelpers().hashPassword(userName, password);
    }

    public void upgradeForPremium() {
        this.isPremiumMember = true;
    }

    public void setLogIn(boolean value) {
        this.isLogIn = value;
    }

    public boolean isPremiumMember() {
        return isPremiumMember;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }
}
