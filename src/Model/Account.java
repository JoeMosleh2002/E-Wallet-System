package Model;

public class Account {
    private String username;
    private  String password;
    private double balance;
    private String getEmail;

    public Account(String username, String password, String getEmail, int age, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.getEmail = getEmail;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Account(String regUsername, String regPassword, int age, String email, String phoneNumber) {
    }

    public String getGetEmail() {
        return getEmail;
    }

    public void setGetEmail(String getEmail) {
        this.getEmail = getEmail;
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

    private int age;
    private String phoneNumber;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    private  String email;

    public Account(String username, String password, String getEmail, double balance, int age, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.getEmail = getEmail;
        this.balance = balance;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
