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

    public Guest() {
    }

    public Guest(int id, String name) {
        this.id = id;
        this.name = name;
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
}