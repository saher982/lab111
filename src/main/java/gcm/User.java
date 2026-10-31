package gcm;

import java.util.regex.Pattern;

  // A system user, an instance is created only if both the username and the password are valid
  // otherwise the constructor throws an IllegalArgumentException with a matching message
class User {

     private static final int MAX_EMAIL_LENGTH = 50;
      private static final int MIN_PASSWORD_LENGTH = 8;
     private static final int MAX_PASSWORD_LENGTH = 12;

      // Part 1: letters, digits and . _ % + -
      // Part 2 (after @): starts with a letter/digit, then letters, digits, '.' or '-'
      // Part 3 (after '.'): at least two letters
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}$");

    // allowed password characters: letters, digits and the symbols on the number row
    private static final String SYMBOLS = "`~!@#$%^&*()-_=+";
      private static final Pattern PASSWORD_ALLOWED =
            Pattern.compile("^[A-Za-z0-9" + Pattern.quote(SYMBOLS) + "]+$");

      private final String name;
       private final String password;

      public User(String name, String password) {
        validateName(name);
         validatePassword(password);
          this.name = name;
           this.password = password;
      }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private static void validateName(String name) {
         if (name == null || name.length() > MAX_EMAIL_LENGTH) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
         }
        if (!EMAIL_PATTERN.matcher(name).matches()) {
             throw new IllegalArgumentException("Please enter a valid Email as username");
         }
      }

    private static void validatePassword(String password) {
         if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
             throw new IllegalArgumentException(
                     "Your password is too short, add more characters");
        }
         if (password.length() > MAX_PASSWORD_LENGTH) {
             throw new IllegalArgumentException("Your password is too long, try a shorter one");
          }

          boolean hasLetter = false, hasDigit = false, hasSymbol = false;
           for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
             else if (Character.isDigit(c)) hasDigit = true;
              else if (SYMBOLS.indexOf(c) >= 0) hasSymbol = true;
         }

        // illegal characters, or missing a letter or digit or symbol
        boolean legalChars = PASSWORD_ALLOWED.matcher(password).matches();
          if (!legalChars || !hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException("Please enter a valid password");
         }
     }

    @Override
     public String toString() {
         return name + " " + password;
    }
}