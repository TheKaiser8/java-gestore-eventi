package org.lessons.java.handler;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Aggiungo evento
        System.out.println("Benvenuto nel portale di gestione dei tuoi eventi!");
        System.out.println("Inserisci le informazioni basilari del tuo evento");

        // Titolo
        String titleInput;
        boolean validTitleInput = false;
        do {
            System.out.print("Titolo: ");
            titleInput = scan.nextLine();
            if (!titleInput.isBlank()) validTitleInput = true;
            else System.out.println("ERRORE: il titolo è obbligatorio, non può essere vuoto.");
        } while (!validTitleInput);

        // Data
        LocalDate dateInput = null;
        boolean validInputDate = false;
        do {
            try {
                System.out.print("Data (yyyy-mm-dd): ");
                dateInput = LocalDate.parse(scan.nextLine());
                validInputDate = true;
            } catch (Exception e) {
                System.out.println("La data dev'essere espressa nel formato corretto: yyyy-mm-dd");
            }
        } while (!validInputDate);

        // Posti totali
        int totalPlacesInput = 0;
        boolean validTotalPlaces = false;
        do {
            System.out.print("Numero posti totali: ");
            String totalPlacesStr = scan.nextLine();
            try {
                totalPlacesInput = Integer.parseInt(totalPlacesStr);
                if (totalPlacesInput > 0) {
                    validTotalPlaces = true;
                } else {
                    System.out.println("ERRORE: il numero di posti totali deve essere positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERRORE: Inserisci un numero valido per i posti totali.");
            }
        } while (!validTotalPlaces);

        System.out.println("--------------------------------------"); // linea di demarcazione per rendere più leggibile l'output

        // instanzio un nuovo evento
        Event event1 = null;
        try {
            event1 = new Event(titleInput, dateInput, totalPlacesInput);

            // output verifica evento aggiunto
            System.out.println("Riepilogo informazioni evento: ");
            System.out.println("Stringa data evento + titolo: " + event1);
            System.out.println("Titolo: " + event1.getTitle());
            System.out.println("Data: " + event1.getFormattedDate());
            System.out.println("Numero posti totali: " + event1.getTotalPlaces());
            System.out.println("Numero posti prenotati: " + event1.getReservedPlaces());
            System.out.println("Numero posti disponibili: " + event1.calcAvailablePlaces());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------------"); // linea di demarcazione per rendere più leggibile l'output

        // effettuo una prenotazione se l'evento è stato istanziato correttamente
        if (event1 != null) {
            // chiedo all'utente se vuole effettuare prenotazioni
            System.out.print("Vuoi effettuare una prenotazione? (\"s\" per confermare) ");
            String choice = scan.nextLine();
            boolean exitBooking = false;
            while (!exitBooking && event1.calcAvailablePlaces() > 0) {
                // se SI gli chiedo quanti posti vuole prenotare
                if (choice.equalsIgnoreCase("s")) {
                    try {
                        System.out.print("Quanti posti vuoi prenotare? ");
                        int numReservedPlaces = Integer.parseInt(scan.nextLine());

                        for (int i = 0; i < numReservedPlaces; i++) {
                            event1.booking();
                        }
                        System.out.println("Numero posti prenotati: " + event1.getReservedPlaces());
                        System.out.println("Numero posti disponibili: " + event1.calcAvailablePlaces());

                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    if (event1.calcAvailablePlaces() > 0) {
                        System.out.print("Vuoi effettuare un'altra prenotazione? (\"s\" per confermare) ");
                        choice = scan.nextLine();
                    }
                }
                // altrimenti esco dal sistema di prenotazione
                else {
                    System.out.println("Arrivederci");
                    exitBooking = true;
                }
            }
        }

        scan.close();
    }
}
