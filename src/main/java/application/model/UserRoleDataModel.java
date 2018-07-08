package application.model;

import application.data.model.Role;
import application.data.model.User;

public class UserRoleDataModel {
    private int id;
    private User user;
    private Role role;

    public UserRoleDataModel(int id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
