package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Vasundhara Tiwari
 * On 12-03-2022 at 14:35
 */
@Entity
public class Employee {

    @Id
    private int id;
    private String name;

    public Employee(){

    }

    public Employee(int id, String name) {
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