import java.util.Scanner;

public class SimpleCalculator{
    public double calcule(){
        Scanner sc = new Scanner(System.in);
        double result = 0 ;
        boolean continueCalculation = true;
        System.out.print("Enter the first number : ");
        double number1 = sc.nextDouble();
        while (continueCalculation) {
            System.out.print("Enter the second number : ");
            double number2 = sc.nextDouble();
            System.out.print("Enter the operator you want to apply (+, -, *, /): ");
            String operator = sc.next();
            switch (operator) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        System.out.println("Division by zero is not allowed!");
                        continue;
                    }
                    break;
                default:
                    System.out.println("Invalid operator! Please try again.");
                    continue;
            }
            System.out.println("Result: " + result);
            System.out.print("Do you want to perform another operation with the result? (yes/no): ");
            String choice = sc.next();

            if (choice.equalsIgnoreCase("yes")) {
                number1 = result;
            } else {
                continueCalculation = false;
            }
        }
        sc.close();
        System.out.println("Calculation finished. Final result: " + result);
        return result;
    }
}