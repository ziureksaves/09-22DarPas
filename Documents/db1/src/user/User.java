package user;

public class User {
    int id;
    String username;
    String role;
    boolean isAdmin = false;

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        if (role.equals("administratorius")) {
            this.isAdmin = true;
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
