package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Vasundhara Tiwari
 * On 12-03-2022 at 14:39
 */

@Entity
public class Guest {

    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    
    public Guest() {
    }

    public Guest(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}