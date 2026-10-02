package Model;


public class Account {

    private String username;
    private String password;
    private double balance;
    private String email;
    private int age;
    private String phoneNumber;


    public Account(String username, String password, int age,
                   String email, String phoneNumber) {

        // Username validation
        if (username == null) {
            throw new IllegalArgumentException("Please enter a username");
        }

        username = username.strip();

        if (username.length() < 5 || username.length() > 20) {
            throw new IllegalArgumentException(
                    "Username must be between 5 and 20 characters"
            );
        }

        if (!Character.isUpperCase(username.charAt(0))) {
            throw new IllegalArgumentException(
                    "Username must start with a capital letter"
            );
        }

        this.username = username;


        // Password validation
     validatePassword(password);

        this.password = password;

        this.email = email;
        if (age <18) throw new IllegalArgumentException("Account holder must be at least 18 years old");
        this.age = age;
        // Phone number validation
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Please enter a phone number");
        }

        phoneNumber = phoneNumber.strip();

        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException(
                    "Phone number must contain exactly 11 digits"
            );
        }

        boolean isNumeric = true;

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                isNumeric = false;
                break;
            }
        }

        if (!isNumeric) {
            throw new IllegalArgumentException(
                    "Phone number must contain numbers only"
            );
        }

        boolean validPrefix =
                phoneNumber.startsWith("010")
                        || phoneNumber.startsWith("011")
                        || phoneNumber.startsWith("012")
                        || phoneNumber.startsWith("015");

        if (!validPrefix) {
            throw new IllegalArgumentException(
                    "Phone number must start with 010, 011, 012, or 015"
            );
        }

        this.phoneNumber = phoneNumber;
    }


    // Used for login
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void validatePassword(String password) {

        if (password == null) {
            throw new IllegalArgumentException("Please enter a password");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters"
            );
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {

            if (Character.isUpperCase(password.charAt(i))) {
                hasUpper = true;
            }

            if (Character.isLowerCase(password.charAt(i))) {
                hasLower = true;
            }

            if (Character.isDigit(password.charAt(i))) {
                hasDigit = true;
            }

            if (hasUpper && hasLower && hasDigit) {
                break;
            }
        }

        if (!(hasUpper && hasLower && hasDigit)) {
            throw new IllegalArgumentException(
                    "Password must contain at least one uppercase letter, " +
                            "one lowercase letter, and one digit"
            );
        }
    }
}