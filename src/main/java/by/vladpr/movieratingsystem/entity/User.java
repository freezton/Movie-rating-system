package by.vladpr.movieratingsystem.entity;

public class User {

    private int id;
    private String username;
    private String password;
    private int roleId;
    private int status;
    private boolean isBanned;

    public User() {
    }

    public User(int id, String username, String password, int roleId, int status, boolean isBanned) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
        this.isBanned = isBanned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", status=" + status +
                '}';
    }
}


