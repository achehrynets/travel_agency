package db.entity;

import java.util.Comparator;

public class User extends Entity implements Comparator<User>{

    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String phone;
    private String middleName;
    private String passportId;
    private String internationalPassportId;
    private boolean blocked;
    private Integer roleId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String maddleName) {
        this.middleName = maddleName;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInternationalPassportId() {
        return internationalPassportId;
    }

    public void setInternationalPassportId(String internationalPassportId) {
        this.internationalPassportId = internationalPassportId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int compare(User o1, User o2) {
        return Boolean.compare(o1.isBlocked(), o2.isBlocked());
    }

    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportId='" + passportId + '\'' +
                ", internationalPassportId='" + internationalPassportId + '\'' +
                ", blocked=" + blocked +
                ", roleId=" + roleId +
                '}';
    }
}
