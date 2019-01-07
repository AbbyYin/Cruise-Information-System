package question_1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This interface can be implemented to read integer, string and date from the
 * user.
 *
 * @author yueyin2
 */
public interface ReadInput {

    /**
     * Scanner buffer.
     */
    Scanner readInput = new Scanner(System.in);

    /**
     * Passenger.
     */
    Passenger p = new Passenger();

    /**
     * This method reads the integer from users.
     *
     * @param title The name of the value.
     * @return User input.
     */
    public static int readInt(String title) {
        System.out.printf("\nPlease enter %s (Enter 0 if unknown): ", title);
        boolean again = false;
        int inputRead = 0;
        do {
            again = false;
            try {
                inputRead = readInput.nextInt(); //Read the input.
            } catch (InputMismatchException e) {
                System.out.printf("\nInvalid %s input. Please enter an integer. Try again: ", title);
                again = true;
                readInput.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Try again: ");
                again = true;
                readInput.next();
            }
        } while (again);
        return inputRead;
    }

    /**
     * This method reads user integer input. Designed for reading the report
     * number.
     *
     * @return An integer.
     */
    public static int readInt2() {
        boolean again = false;
        int inputRead = 0;
        do {
            again = false;
            try {
                inputRead = readInput.nextInt(); //Read the input.
                if (inputRead < 1 || inputRead > 5) {
                    System.out.println("Invalid input. Please enter an integer between 1 and 5. Try again: ");
                    again = true;
                    readInput.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer. Try again: ");
                again = true;
                readInput.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Try again: ");
                again = true;
                readInput.next();
            }
        } while (again);
        return inputRead;
    }

    /**
     * This method converts a String to an Integer.
     *
     * @param input String input.
     * @return An integer.
     */
    public static int readInt3(String input) {
        boolean again = false;
        int inputRead = 0;
        do {
            again = false;
            try {
                inputRead = Integer.parseInt(input); //Read the input.
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter an integer. Try again: ");
                again = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Try again: ");
                again = true;
            }
        } while (again);
        return inputRead;
    }

    /**
     * This method reads the string from users.
     *
     * @param title The name of the value.
     * @return User input.
     */
    public static String readString(String title) {
        System.out.printf("\nPlease enter %s (Enter 0 if unknown): ", title);
        readInput.next();
        boolean again = false;
        String inputRead = "";
        do {
            again = false;
            try {
                inputRead = readInput.nextLine();//Read the input.
            } catch (InputMismatchException e) {
                System.out.printf("\nInvalid %s input. Please enter a string. Try again: ", title);
                again = true;
                readInput.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Try again: ");
                again = true;
                readInput.next();
            }
        } while (again);
        return inputRead;
    }

    /**
     * This method reads the date from users.
     *
     * @param title The name of the value.
     * @return User input.
     */
    public static LocalDate readDate(String title) {
        System.out.printf("\nPlease enter %s (dd/mm/yyyy, Enter 01/01/1000 if unknown): ", title);
        boolean again = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputRead = LocalDate.parse("05/03/1996", formatter);
        String DoB = "";
        do {
            again = false;
            try {
                DoB = readInput.nextLine();//Read the input.
                inputRead = LocalDate.parse(DoB, formatter);
            } catch (InputMismatchException e) {
                System.out.printf("\nInvalid %s input. Please enter a String. Try again: ", title);
                again = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " Try again (dd/mm/yyyy): ");
                again = true;
            }
        } while (again);
        p.setPassengerDoB(inputRead);
        return inputRead;
    }

    /**
     * This method reads the date from users.
     *
     * @param title The name of the value.
     * @return User input.
     */
    public static LocalDate readDate2(String title) {
        System.out.printf("\nPlease enter %s (dd/mm/yyyy, Enter 01/01/1000 if unknown): ", title);
        boolean again = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputRead = LocalDate.parse("05/03/1996", formatter);
        String DoB = "";
        do {
            again = false;
            try {
                DoB = readInput.nextLine();//Read the input.
                inputRead = LocalDate.parse(DoB, formatter);
            } catch (InputMismatchException e) {
                System.out.printf("\nInvalid %s input. Please enter a String. Try again: ", title);
                again = true;
            } catch (Exception ex) {
                System.out.println("...");
                again = true;
            }
        } while (again);
        return inputRead;
    }

    /**
     * This method converts a string to a date.
     *
     * @param title The string.
     * @return A date.
     */
    public static LocalDate readDate3(String title) {
        boolean again = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputRead = LocalDate.parse("05/03/1996", formatter);
        String DoB = "";
        do {
            again = false;
            try {
                DoB = title;
                inputRead = LocalDate.parse(DoB, formatter);
            } catch (InputMismatchException e) {
                System.out.printf("\nInvalid %s input. Please enter a String. Try again: ", title);
                again = true;
            } catch (Exception ex) {
                System.out.println("Wrong type. Exit.");
                System.exit(0);
            }
        } while (again);
        return inputRead;
    }
}//End of class.
