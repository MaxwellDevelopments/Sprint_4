package ru.qa_scooter.business;

import ru.qa_scooter.constants.Color;
import ru.qa_scooter.constants.RentTime;

import java.time.LocalDate;

public class Order {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final LocalDate date;
    private final RentTime rentalPeriod;
    private final Color color;
    private final String comment;

    private String orderNumber;


    public Order() {
        this.firstName = null;
        this.orderNumber = null;
        this.comment = null;
        this.color = null;
        this.rentalPeriod = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.address = null;
        this.subwayStation = null;
        this.date = null;
    }

    public Order(String firstName, String lastName, String address,
                 String subwayStation, String phoneNumber, LocalDate date,
                 RentTime rentalPeriod, Color color, String comment, String orderNumber) {
        this(firstName, lastName, address, subwayStation, phoneNumber, date, rentalPeriod, color, comment);
        this.orderNumber = orderNumber;
    }



    public Order(String firstName, String lastName, String address,
                 String subwayStation, String phoneNumber,
                 LocalDate date, RentTime rentalPeriod, Color color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }


    public Order(String orderNumber) {
        this();
        this.orderNumber = orderNumber;
    }

    public static Order createFirstPageOrder(String firstName, String lastName, String address,
                                             String subwayStation, String phoneNumber) {
        return new Order(firstName, lastName, address, subwayStation, phoneNumber,
                null, null, null, null);

    }

    public static Order createSecondPageOrder(LocalDate rentDate, RentTime rentPeriod, Color color,
                                             String comment) {
        return new Order(null, null, null, null, null,
                rentDate, rentPeriod, color, comment);

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getSubwayStation() {
        return subwayStation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public RentTime getRentTime() {
        return rentalPeriod;
    }

    public Color getColor() {
        return color;
    }

    public String getComment() { return comment; }

    public String getOrderNumber() { return orderNumber; }

    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
}
