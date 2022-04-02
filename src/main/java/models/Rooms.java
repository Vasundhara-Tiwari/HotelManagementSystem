package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Vasundhara Tiwari
 * On 12-03-2022 at 14:42
 */

@Entity
public class Rooms {

    @Id
    private int number;
    private String type;
    private int price;
    private boolean available;

    public Rooms() {
    }

    public Rooms(int number, String type, int price, boolean available) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}