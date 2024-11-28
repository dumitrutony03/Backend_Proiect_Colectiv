package proiectcolectiv.dto;

import java.io.Serializable;

public class UserDataDto implements Serializable {
    public String userName;
    public String password;
    public String role;
    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
