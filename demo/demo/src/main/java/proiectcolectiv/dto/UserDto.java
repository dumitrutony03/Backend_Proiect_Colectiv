package proiectcolectiv.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    public String userName;
    public String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
