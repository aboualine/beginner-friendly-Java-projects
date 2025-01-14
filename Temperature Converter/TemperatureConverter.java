import java.util.Scanner;

public class TemperatureConverter {
    private double degree ;
    public void convertedegree(){
        Scanner sc = new Scanner(System.in);
        System.out.print("chouse the type of the temperature between (Celsius, Fahrenheit, and Kelvin) : ");
        String type = sc.nextLine().toLowerCase();
        boolean correcte = false;
        while (!correcte) {
            System.out.println("now enter the degree you want to convert : ");
            if (sc.hasNextDouble()) {
                degree = sc.nextDouble();
                correcte = true;
                switch (type) {
                    case "celsius":
                        double K = degree + 273.15;
                        double F = ((9*degree)/5) + 32;
                        System.out.println("the "+degree+" Celsius is "+K+" in Kelvin ,and "+F+" in Fahrenheit.");
                        
                    break;
                    case "fahrenheit":
                        double C = ((5*(degree-32))/9);
                        K = ((5*(degree+459.67))/9);
                        System.out.println("the "+degree+" Fahrenheit is "+K+" in Kelvin ,and "+C+" in Celsius.");
                    break;
                    case "kelvin":
                        C = degree - 273.15;
                        F = ((9*(degree - 273))/5);
                        System.out.println("the "+degree+" Kelvin is "+F+" in Fahrenheit ,and "+C+" in Celsius.");
                    break;
                    default:
                        System.out.println("the type is undefined !you need to chouse just between (Celsius, Fahrenheit, and Kelvin) : ");
                        correcte = false;
                        sc.nextLine();
                        System.out.print("Choose the type of temperature (Celsius, Fahrenheit, Kelvin): ");
                        type = sc.nextLine().toLowerCase();
                    break;
                }
            }
            else{
                System.out.println("Invalid input! Please enter a valid number for the degree.");
                sc.next();
            }
        }
        sc.close();
    }
}
