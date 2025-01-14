import java.util.Random;
import java.util.Scanner;
public class NumberGuessing{
    private int number;
    public void guess(){
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        boolean match = false;
        Random random = new Random();
        int javanumber = random.nextInt(100) + 1;
        int numberofshots = 0;
        while(!match){
            int diff = Math.abs(number - javanumber);
            if(diff == 0){
                System.out.println("CONRATULATIONS ,uou did it :) ");
                System.out.println("you have reach the number after "+(numberofshots+1)+" shots.");
                match = true;
                sc.close();
                break;
            }
            else if(diff > 40){
                System.out.println("it's to cold :(");
            }
            else if(diff > 20){
                System.out.println("it start to be worm right here !");
            }
            else if(diff > 10){
                System.out.println("oh woow ,it's hot now");
            }
            else if(diff <= 5){
                System.out.println("well it's really really HOT here ,you are so close to reach your gool ;)");
            }
            System.out.print("try again : ");
            number = sc.nextInt();
            numberofshots++;
        }
    }
    
}