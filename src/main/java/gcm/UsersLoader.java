package gcm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


  // reads the users file  and builds a list of the valid users only

public class UsersLoader {


      // reads the file line by line
     // A line is added to the list only if User accepts it, invalid lines are skipped

    // @param fileName path of the users file
    // @return ArrayList of the valid users
    // @throws FileNotFoundException if the file doesn't exist

    public static ArrayList<User> loadValidUsers(String fileName) throws FileNotFoundException {
        ArrayList<User> users = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    continue; // not a "username password" line
                }

                try {
                    users.add(new User(parts[0], parts[1]));
                } catch (IllegalArgumentException e) {
                    // invalid user, same messages as lab 1
                    System.err.println(line + " -> " + e.getMessage());
                }
            }
        }
        return users;
    }
}
