import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class UsersApp {

    private static final String DEFAULT_FILE = "users.txt";

    public static void main(String[] args) {
        // arrayList of type User
         ArrayList<User> users = new ArrayList<>();

        // read the file line by line
        try (Scanner reader = new Scanner(openInput(args))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                  if (line.isEmpty()) {
                    continue; // ignore blank lines
                }

                // username and password are separated by one space or more
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    System.err.println(line + " -> Line must have a username and a password");
                    continue;
                }

                try {
                    users.add(new User(parts[0], parts[1]));
                } catch (IllegalArgumentException e) {
                    System.err.println(line + " -> " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            return;
        }

        // print the valid users sorted by username
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        for (User u : users) {
            // "\n"  so the output file is identical on Windows too
            System.out.print(u + "\n");
        }
        System.out.flush();
    }

    /** picks the input source: argument file then users.txt then a standard input */
    private static InputStream openInput(String[] args) throws FileNotFoundException {
         if (args.length > 0) {
            return new FileInputStream(new File(args[0]));
            }
         File defaultFile = new File(DEFAULT_FILE);
           if (defaultFile.exists()) {
             return new FileInputStream(defaultFile);
        }
        return System.in;
      }
 }
