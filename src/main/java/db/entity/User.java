package db.entity;

public class User extends Entity{

    private String login;
    private String password;
    private String email;
    private String surname;
    private String name;
    private String middleName;
    private String passportId;
    private String internationalPassportId;
    private boolean blocked;
    private int roleId;

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

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportId='" + passportId + '\'' +
                ", internationalPassportId='" + internationalPassportId + '\'' +
                ", blocked=" + blocked +
                ", roleId=" + roleId +
                '}';
    }
}
