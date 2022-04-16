package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vasundhara Tiwari
 * On 12-04-2022 at 01:16
 */

@Entity
public class BookingDetails {

    @Id
    private long bookingDate;
    private int number;
    private String email;
    private String name;
    private String checkInDate;
    private String checkOutDate;

    public BookingDetails() {
    }

    public BookingDetails(int number, String name, String email, String checkInDate, String checkOutDate) {
        this.number = number;
        this.email = email;
        this.name = name;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingDate = System.currentTimeMillis();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(long bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}