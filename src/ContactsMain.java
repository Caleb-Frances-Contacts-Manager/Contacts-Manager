import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsMain {
    private final static Path path = Paths.get("src","contacts.txt");

    private static void writeLines(List<String> lines) {
        Path path = Paths.get("contacts.txt");

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                    List<String> newContact = List.of("XXX | 956-337-1322", "tdrtrd | 54564-78678");
                            try {
                    Files.write(path, newContact, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                     e.printStackTrace();
                    }
                    writeLines(newContact);
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println("YOU LITERALLY DID NOT TYPE IN A NUMBER, IDIOT.");//default means it didn't match
                    // any of
                    // the switches so run this
                    break;
            }
        }
    }
}// end ContactsMain class
