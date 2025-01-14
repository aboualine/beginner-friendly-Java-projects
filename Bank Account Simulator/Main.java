import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("Hi! and welcom to the Bank Account Simple Simulator\nhere you can Creating a new account ,Depositing & Withdrawing money ,Checking the account balance\nand also see all the accounts we have in our bank .");
        System.out.print("if you want to start as a new client write \"yes\" : ");
        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        BankAccountSimulator bas = new BankAccountSimulator();
        if (reponse.equalsIgnoreCase("yes")) {
            bas.addAccount();
        }
        else{
            System.out.println("have a good day then sir!");
        }
        System.out.print("if you are already a client and you want to deposit money write \"yes\" : ");
        reponse = sc.nextLine();
        if (reponse.equalsIgnoreCase("yes")) {
            bas.deposit();
        }
        else{
            System.out.println("have a good day then sir!");
        }
        System.out.print("if you are already a client and you want to withdraw money write \"yes\" : ");
        reponse = sc.nextLine();
        if (reponse.equalsIgnoreCase("yes")) {
            bas.withdraw();
        }
        else{
            System.out.println("have a good day then sir!");
        }
        System.out.print("if you are already a client and you want to check your balance write \"yes\" : ");
        reponse = sc.nextLine();
        if (reponse.equalsIgnoreCase("yes")) {
            bas.checkBalance();
        }
        else{
            System.out.println("have a good day then sir!");
        }
        bas.clients();
        sc.close();
    }
}
