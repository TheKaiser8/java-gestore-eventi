package org.lessons.java.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event {
    // FIELDS
    private LocalTime time;
    private BigDecimal price;

    // CONSTRUCTOR

    public Concert(String title, LocalDate date, int totalPlaces, LocalTime time, BigDecimal price) {
        super(title, date, totalPlaces);
        this.time = time;
        this.price = price;
    }

    // GETTERS & SETTERS
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // METHODS
    public String getFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(getDate(), time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public String getFormattedPrice() {
        return price.setScale(2) + "€";
    }

    @Override
    public String toString() {
        return getFormattedDateTime() + " - " + getTitle() + " - " + getFormattedPrice();
    }
}
