import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Choose a number for operation:\n1-----Add two number\n2-----Sub\n3-----Mul\n4-----Div");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Practice practice = new Practice();
        int num1;
        int num2;
        switch (choice) {

            case 1:
                System.out.println("Enter the First number:");
                num1 = sc.nextInt();
                System.out.println("Second number:");
                num2 = sc.nextInt();


                System.out.println("The sum is:"+ practice.sum(num1,num2));
                break;

            case 2:
                System.out.println("Enter the First number:");
                num1 = sc.nextInt();
                System.out.println("Second number:");
                num2= sc.nextInt();


                System.out.println("The subtraction is:"+ practice.diff(num1,num2));
                break;

            case 3:
                System.out.println("Enter the First number:");
                num1 = sc.nextInt();
                System.out.println("Enter the Second number:");
                num2 = sc.nextInt();


                System.out.println("The multiplication is:"+ practice.mul(num1,num2));
                break;

            case 4:
                System.out.println("Enter the First number:");
                num1 = sc.nextInt();
                System.out.println("Enter the Second number:");
                num2 = sc.nextInt();

                System.out.println("The division is:"+ practice.div(num1,num2));
                break;
            default:
                System.out.println("Wrong choice");
        }


    }
}