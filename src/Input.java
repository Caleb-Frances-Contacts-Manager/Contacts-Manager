import java.util.Scanner;

public class Input {

    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
     }


