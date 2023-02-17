import java.util.Scanner;

public class stockSimulator {
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String email, user, pass = "";

        System.out.println("Welcome to the paper trading simulator");
        System.out.println("Please enter your email, username, and password:");

        System.out.print("Email: ");
        email = scan.next();
        System.out.print("Username: ");
        user = scan.next();
        System.out.print("Password: ");
        pass = scan.next();

        System.out.println("Creating Account...");

        userAccount NewUser = new userAccount(email, user, pass);
        System.out.println("buying power: $"+NewUser.getBuyingPower());
        System.out.println("Account balance: $"+NewUser.getAccountBalance());

    }


}
