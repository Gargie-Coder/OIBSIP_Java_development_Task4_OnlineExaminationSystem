public class User {
    private String username;
    private String password;
    private String displayName;

    public User(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public boolean checkLogin(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}