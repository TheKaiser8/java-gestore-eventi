package org.lessons.java.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            throw new IllegalArgumentException("ERRORE: il titolo è obbligatorio, non può essere vuoto.");
        }
        // date
        if (!date.isBefore(LocalDate.now())) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("ERRORE: la data dell'evento non può essere passata.");
        }
        // totalPlaces
        if (totalPlaces > 0) {
            this.totalPlaces = totalPlaces;
        } else {
            throw new IllegalArgumentException("ERRORE: il numero di posti totali deve essere positivo.");
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
            throw new IllegalArgumentException("ERRORE: il titolo è obbligatorio, non può essere vuoto.");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (!date.isBefore(LocalDate.now())) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("ERRORE: la data dell'evento non può essere passata.");
        }
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public int getReservedPlaces() {
        return reservedPlaces;
    }

    // METHODS
    public void booking() throws IllegalStateException {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalStateException("ERRORE: non è possibile effettuare la prenotazione perché l'evento è già passato.");
        }
        if (calcAvailablePlaces() <= 0) {
            throw new IllegalStateException("ERRORE: non è possibile effettuare la prenotazione perché i posti sono esauriti");
        }
        reservedPlaces++;
    }

    public void cancelBooking() throws IllegalStateException {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalStateException("ERRORE: non è possibile disdire la prenotazione di un evento già passato.");
        }
        if (reservedPlaces == 0) {
            throw new IllegalStateException("ERRORE: non è possibile disdire perché non risulta alcuna prenotazione.");
        }
        reservedPlaces--;
    }

    public int calcAvailablePlaces() {
        return totalPlaces - reservedPlaces;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter) + " - " + title;
    }
}
