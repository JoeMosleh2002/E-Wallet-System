package Model;

public class Account {
    private String username;
    private  String password;
    private double balance;
    private String email;
    private int age;
    private String phoneNumber;

    public Account(String username, String password, int age, String email,String PhoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phoneNumber = PhoneNumber;
        this.email = email;
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
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
