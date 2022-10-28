import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsMain {
    private final static Path path = Paths.get("src", "contacts.txt");

    private static List<String> writeLines(List<String> lines) {
        Path path = Paths.get("contacts.txt");
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    private static List<String> readLines() {
        List<String> contacts = new ArrayList<>();
        try {
            contacts = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static List<String> addContact() {
        Input addNum = new Input();
        List<String> newContact = new ArrayList<>();
        String newFirstName;
        newFirstName = addNum.getString("Enter your contact's first name:");
        String newLastName;
        newLastName = addNum.getString("Enter your contact's last name:");
        String newNumber;
        newNumber = addNum.getString("Enter a phone number: (###-###-####)");
        newContact.add("|  " + newFirstName + " " + newLastName + " |   " + newNumber + "         |");
            try {
                Files.write(path, newContact, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.printf("%nYour contact was added!%n%n");
        viewContacts();
        System.out.printf("%n");
        return writeLines(newContact);
    }

    public static void searchContact() {
        Input nameInput = new Input();
        String userInput;
        userInput = nameInput.getString("Enter the name to search:");
        List<String> allContacts = readLines();
        for (String contact : allContacts) {
            if (contact.toLowerCase().contains(userInput.toLowerCase())) {
                System.out.println(contact);
            }
        }
    }


    public static List<String> deleteContact() {
        Input deleteName = new Input();
        String userInput;
        List<String> allContacts = readLines();
        for (String contacts : allContacts) {
            System.out.printf(contacts + "%n");
        }
        System.out.printf("%n");
        userInput = deleteName.getString("Enter a name to delete from the list above:");
        for (Iterator<String> contact = allContacts.iterator(); contact.hasNext(); ) {
            String con = contact.next();
            if (con.toLowerCase().contains(userInput.toLowerCase())) {
                contact.remove();
                System.out.printf("%nThe contact has been removed!%n%n");
                for (String contacts : allContacts) {
                    System.out.println(contacts);
                }
            }
        }
            try {
                Files.write(path, allContacts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return readLines();
    }

    public static void viewContacts(){
        List<String> contacts = readLines();
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }

    public static void displayContacts() {
        Input input = new Input();
        String userChoice = "";
        while (!userChoice.equals("5")) {
            System.out.printf("%n1. View contacts%n");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");

            userChoice = input.getString("Enter an option (1,2,3,4, or 5)");
            switch (userChoice) {
                case "1":
                    viewContacts();
                    break;
                case "2":
                    addContact();
                    break;
                case "3":
                    searchContact();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("YOU DID NOT TYPE IN A NUMBER.");
                    break;
            }
        }
    }




}// end ContactsMain class
