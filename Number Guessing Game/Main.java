import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        System.out.print("Hi .and welcome to the first beginner friendly java project \nif you want to play a guessing game ,print yes in the terminal : ");
        Scanner sc = new Scanner(System.in);
        String chois = sc.nextLine();
        if (chois.equalsIgnoreCase("yes")) {
            System.out.println("excellent ,now to play you have to guess a number and if you match the number we are giving you win : ");
            NumberGuessing ng = new NumberGuessing();
            ng.guess();
        }
        else{
            System.out.println("okay let's skip to the next thing.");
        }
        sc.close();
    }
}
