import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsMain {
    private final static Path path = Paths.get("src","contacts.txt");

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

    private static List<String> addContact(){
        Input addNum = new Input();
        List<String> newContact = new ArrayList<>();
        String userInput;
        userInput = addNum.getString("Enter a contact name:");
        String newNumber;
        newNumber = addNum.getString("Enter a phone number");
        newContact.add(userInput + " |   " + newNumber);
        try {
            Files.write(path, newContact, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writeLines(newContact);
    }

    private static List<String> deleteContact(){
        Input deleteName = new Input();
        List<String> contacts = readLines();
        List <String> updatedContacts = new ArrayList<>();
        String userInput;
        userInput = deleteName.getString("Enter a name to delete:");
        for (String contact : readLines()) {
            if (!contact.toLowerCase().contains(userInput.toLowerCase())){
                updatedContacts.add(contact);
            }
        }
        return writeLines(updatedContacts);
    }



    public static void main(String[] args) {

        Input input = new Input();

        String userChoice = "yes";
        while (!userChoice.equals("5")) {
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");

            userChoice = input.getString("Enter an option (1,2,3,4, or 5)");
            List<String> contacts = readLines();
            switch (userChoice) {
                case "1":
                    for (String contact : contacts) {
                        System.out.println(contact);
                    }
                    break;
                case "2":
                    addContact();
                    break;
                case "3":
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
