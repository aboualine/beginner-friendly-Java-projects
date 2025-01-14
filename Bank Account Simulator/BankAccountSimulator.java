import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountSimulator {
    private ArrayList<String> accounts = new ArrayList<>();
    private int accountNumber;
    private static int accountCounter = 0;
    private String accountHolder;
    private double balance;
    public BankAccountSimulator() {
        accountNumber = ++accountCounter;
        accountHolder = " ";
        balance = 0;
    }
    public BankAccountSimulator(String accountHolder, double balance) {
        this.accountNumber = ++accountCounter;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public void setAccountHolder(String accountHolder){
        this.accountHolder = accountHolder;
    }
    public String getAccountHolder(){
        return accountHolder;
    }
    public void setBalance(double balance){
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative!");
        }    
    }
    public double getBalance(){
        return balance;
    }
    Scanner sc = new Scanner(System.in);
    File file = new File("clients.txt");
    
    public ArrayList<String> addAccount() throws IOException{
        System.out.println("to create an account ,write here your name : ");
        accountHolder = sc.nextLine();
        System.out.println("now add your balance here : ");
        balance = sc.nextDouble();
        sc.nextLine();
        String accountInfos = accountNumber+";"+accountHolder+";"+balance;
        accounts.add(accountInfos);
        if(!file.exists()){
            file.createNewFile();
            System.out.println("your file has been created successfully");
        }
        else{
            PrintWriter write = new PrintWriter(new FileWriter(file, true));
            for(int i = 0 ; i < accounts.size() ; i++){
                write.println(accounts.get(i));
            }
            write.close();
        }
        return accounts;
    }
    public void deposit() throws IOException{
        System.out.print("enter your name here to check in the database : ");
        accountHolder = sc.nextLine();
        boolean accountFound = false;
        for(int i = 0 ; i < accounts.size() ; i++){
            String[] details = accounts.get(i).split(";");
            if (accountHolder.equalsIgnoreCase(details[1])) {
                accountFound = true;
                System.out.print("Enter here the amount of money you want add to your account : ");
                double addmoney = sc.nextDouble();
                sc.nextLine();
                double amountIfNotPositive = Math.abs(addmoney);
                double currentBalance = Double.parseDouble(details[2]);
                double updatedBalance = currentBalance + amountIfNotPositive;
                accounts.set(i, details[0] + ";" + details[1] + ";" + updatedBalance);
                System.out.println("the deposit of "+amountIfNotPositive+" has been added to your balance successfully !now your balance is "+updatedBalance);
                break;
            }
            else if(!accountFound){
                handleAccountNotFound();
            }
        } 
    }
    public void withdraw() throws IOException{
        System.out.print("enter your name here to check in the database : ");
        accountHolder = sc.nextLine();
        boolean accountFound = false;
        for(int i = 0 ; i < accounts.size() ; i++){
            String[] details = accounts.get(i).split(";");
            if (accountHolder.equalsIgnoreCase(details[1])) {
                accountFound = true;
                double currentBalance = Double.parseDouble(details[2]);
                System.out.print("Enter here the amount of money you want withdraw from your account : ");
                double withdrawMoney = sc.nextDouble();
                sc.nextLine();
                double amountIfNegative = Math.abs(withdrawMoney);
                if (currentBalance >= amountIfNegative) {
                    double updatedBalance = currentBalance - amountIfNegative;
                    accounts.set(i, details[0] + ";" + details[1] + ";" + updatedBalance);
                    System.out.println("the amount of "+amountIfNegative+" has been withdrawed from your balance successfully !now your balance is "+updatedBalance);
                }
                else{
                    System.out.println("Insufficient balance! Your current balance is " + currentBalance);
                }
                break;
            }
            else if(!accountFound){
                handleAccountNotFound();
            }
        }
    }
    public void checkBalance() throws IOException{
        System.out.print("enter your name here to check in the database : ");
        accountHolder = sc.nextLine();
        boolean accountFound = false;
        for(int i = 0 ; i < accounts.size() ; i++){
            String[] details = accounts.get(i).split(";");
            if (accountHolder.equalsIgnoreCase(details[1])) {
                accountFound = true;
                double currentBalance = Double.parseDouble(details[2]);
                System.out.print("your balance is : "+currentBalance+"\n");
                break;
            }
            else if(!accountFound){
                handleAccountNotFound();
            }
        }
    }
    public void clients() throws IOException{
        System.out.println("all the accounts we have are : ");
        System.out.println("|   ID    |    NAME     |    BALANCE     |");
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String line = read.nextLine();
            String[] details = line.split(";");
            if (details.length == 3) {
                accountNumber = Integer.parseInt(details[0].trim());
                accountHolder = details[1].trim();
                balance = Double.parseDouble(details[2].trim());
                System.out.println("    "+accountNumber+"           "+accountHolder+"          "+balance);
            }
        }
        read.close();
    }
    private void handleAccountNotFound() throws IOException {
        System.out.print("The name you have entered is not a client of the bank! Do you want to create an account with that name (yes/no): ");
        String response = sc.nextLine();
    
        while (true) {
            if (response.equalsIgnoreCase("yes")) {
                addAccount();
                break;
            } else if (response.equalsIgnoreCase("no")) {
                System.out.println("You can open your bank account here whenever you want. Have a good day!");
                break;
            } else {
                System.out.print("You have to choose between (yes/no)! Enter here yes or no: ");
                response = sc.nextLine();
            }
        }
    }
}
