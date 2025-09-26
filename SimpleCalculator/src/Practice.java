import java.util.Scanner;

public class Practice {
    public int sum(int num1 ,int num2){

        return num1+num2;
    }
    public int diff(int num1 ,int num2){

        return num1-num2;
    }
    public int mul(int num1 ,int num2){

        return num1*num2;
    }
    public float div(int num1 ,int num2){
        if(num2==0){

            return  0f ;
        }

        return (float) num1/num2;
    }

}

