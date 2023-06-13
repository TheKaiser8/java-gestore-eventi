package org.lessons.java.handler;

import java.time.LocalDate;

public class Event {
    // FIELDS
    private String title;
    private LocalDate date;
    private int totalPlaces;
    private int reservedPlaces;

    // CONSTRUCTOR
    public Event(String title, LocalDate date, int totalPlaces) throws IllegalArgumentException {
        // title
        if (title != null && !title.isBlank()) this.title = title;
        else {
            throw new IllegalArgumentException("Il titolo è obbligatorio, non può essere vuoto.");
        }
        // date
        if (!date.isBefore(LocalDate.now())) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("ERRORE: La data dell'evento non può essere passata.");
        }
        // totalPlaces
        if (totalPlaces > 0) {
            this.totalPlaces = totalPlaces;
        } else {
            throw new IllegalArgumentException("ERRORE: Il numero di posti totali deve essere positivo.");
        }
        // reservedPlaces
        this.reservedPlaces = 0;
    }

    // GETTERS & SETTERS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank()) this.title = title;
        else {
            throw new IllegalArgumentException("Il titolo è obbligatorio, non può essere vuoto.");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (!date.isBefore(LocalDate.now())) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("ERRORE: La data dell'evento non può essere passata.");
        }
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public int getReservedPlaces() {
        return reservedPlaces;
    }

    // METHODS
}
