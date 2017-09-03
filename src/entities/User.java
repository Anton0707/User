package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Created by admin on 8/28/17.
 */
@Data
@NoArgsConstructor
public class User {
    private long id;
    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private String role;
    private String email;
    private java.sql.Date dateOfBirth;

    public User(long id, String lastName, String firstName,
                String password,String login,
              String email,  Date dateOfBirth){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String lastName, String firstName, String password,
                String login, String email, Date dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
