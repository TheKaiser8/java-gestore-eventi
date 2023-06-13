package org.lessons.java.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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

        // Implemento il sistema di prenotazione se l'evento è stato istanziato correttamente
        if (event1 != null) {
            // chiedo all'utente se vuole effettuare prenotazioni
            System.out.print("Vuoi effettuare una prenotazione? (\"s\" per confermare) ");
            String choice = scan.nextLine();
            boolean exitBooking = false;

            while (!exitBooking) {
                // se SI chiedo quanti posti vuole prenotare
                if (choice.equalsIgnoreCase("s")) {
                    try {
                        System.out.print("Quanti posti vuoi prenotare? ");
                        int numReservedPlaces = Integer.parseInt(scan.nextLine());

                        if (numReservedPlaces <= event1.calcAvailablePlaces()) {
                            for (int i = 0; i < numReservedPlaces; i++) {
                                event1.booking();
                            }
                            System.out.println("Hai prenotato " + numReservedPlaces + (numReservedPlaces == 1 ? " posto" : " posti") + "!");
                            System.out.println("Numero posti prenotati: " + event1.getReservedPlaces());
                            System.out.println("Numero posti disponibili: " + event1.calcAvailablePlaces());
                        } else {
                            System.out.println("ERRORE: Il numero di posti richiesti supera i posti disponibili.");
                        }
                        if (event1.calcAvailablePlaces() > 0) {
                            System.out.print("Vuoi effettuare un'altra prenotazione? (\"s\" per confermare) ");
                            choice = scan.nextLine();
                        } else {
                            System.out.println("Posti esauriti!!! Non è possibile effettuare ulteriori prenotazioni.");
                            exitBooking = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ERRORE: inserisci un numero valido.");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // altrimenti esco dal sistema di prenotazione
                else {
                    System.out.println("Grazie per aver utilizzato il nostro sistema di prenotazione!");
                    exitBooking = true;
                }
            }
        }

        System.out.println("--------------------------------------"); // linea di demarcazione per rendere più leggibile l'output

        // Implemento il sistema di disdetta se l'evento è stato istanziato correttamente e se risultano posti prenotati
        if (event1 != null && event1.getReservedPlaces() > 0) {
            System.out.print("Vuoi effettuare una disdetta? (\"s\" per confermare) ");
            String choice = scan.nextLine();
            boolean exitCancelBooking = false;

            while (!exitCancelBooking) {
                // se SI chiedo quanti posti vuole disdire
                if (choice.equalsIgnoreCase("s")) {
                    try {
                        System.out.print("Quanti posti vuoi disdire? ");
                        int numCancelledPlaces = Integer.parseInt(scan.nextLine());

                        if (numCancelledPlaces <= event1.getReservedPlaces()) {
                            for (int i = 0; i < numCancelledPlaces; i++) {
                                event1.cancelBooking();
                            }
                            System.out.println("Hai disdetto " + numCancelledPlaces + (numCancelledPlaces == 1 ? " posto" : " posti") + "!");
                            System.out.println("Numero posti prenotati: " + event1.getReservedPlaces());
                            System.out.println("Numero posti disponibili: " + event1.calcAvailablePlaces());
                        } else {
                            System.out.println("ERRORE: Il numero di posti da disdire supera i posti prenotati.");
                        }
                        if (event1.getReservedPlaces() > 0) {
                            System.out.print("Vuoi effettuare un'altra disdetta? (\"s\" per confermare) ");
                            choice = scan.nextLine();
                        } else {
                            System.out.println("Nessuna prenotazione trovata!!! Non è possibile effettuare ulteriori disdette.");
                            exitCancelBooking = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ERRORE: inserisci un numero valido.");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // altrimenti esco dal sistema di disdetta prenotazioni
                else {
                    System.out.println("Ci dispiace che tu abbia disdetto la tua prenotazione, ci auguriamo di rivederti presto!");
                    exitCancelBooking = true;
                }
            }
        }

        System.out.println("--------------------------------------"); // linea di demarcazione per rendere più leggibile l'output

        // Istanzio la classe concerto e stampo gli output per verifica
        Concert concert1 = new Concert("Concerto Live", LocalDate.of(2023, 9, 12), 100, LocalTime.of(21, 0), BigDecimal.valueOf(75));
        System.out.println("Titolo: " + concert1.getTitle());
        System.out.println("Data e ora: " + concert1.getFormattedDateTime());
        System.out.println("Prezzo: " + concert1.getFormattedPrice());
        System.out.println("Stringa formattata: " + concert1);
        scan.close();
    }
}
