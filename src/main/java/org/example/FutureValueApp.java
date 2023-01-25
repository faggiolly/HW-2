package org.example;

import java.text.*;

public class FutureValueApp {

    public static void main(String[] args) {
        int row = 0;
        String calculations[][] = new String[10][4];

        // display a welcome message
        System.out.println("Welcome to the Future Value Calculator");
        System.out.println();

        // perform 1 or more calculations
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {

            // get the input from the user
            System.out.println("DATA ENTRY");
            double monthlyInvestment = Console.getDouble(
                    "Enter monthly investment: ", 0, 1000);
            double interestRate = Console.getDouble(
                    "Enter yearly interest rate: ", 0, 30);
            int years = Console.getInt(
                    "Enter number of years: ", 0, 100);

            // first split, years and interest calculation where added to the calculateFutureValue method
            double futureValue = calculateFutureValue(monthlyInvestment, interestRate, years); // first split

            // second split, Formatters were replaced with the setFormat method to avoid creating objects in the Main

            // third split, format the result as a single string and print results was added to a method
            printAsSingleString(monthlyInvestment, interestRate, years, futureValue);

            // fourth split, format the values and store them in the array was added to a method
            addResultsToArray(calculations, row, monthlyInvestment, interestRate, years, futureValue);

            // increment the row counter
            row++;

            // see if the user wants to continue
            choice = Console.getString("Continue? (y/n): ");
            System.out.println();
        }

        // fifth split, print the results was added to a method
        printResults(calculations, row);

    }

    public static double calculateFutureValue(double monthlyInvestment,
                                              double monthlyInterestRate, int years) {

        monthlyInterestRate = monthlyInterestRate / 12 / 100;
        int months = years * 12;

        double futureValue = 0;
        for (int i = 1; i <= months; i++) {
            futureValue
                    = (futureValue + monthlyInvestment)
                    * (1 + monthlyInterestRate);
        }
        return futureValue;
    }

    static public void printAsSingleString(double monthlyInvestment, double interestRate, double years, double futureValue) {

        String results
                = "Monthly investment:\t"
                + setFormat("currency").format(monthlyInvestment) + "\n"
                + "Yearly interest rate:\t"
                + setFormat("percent").format(interestRate / 100) + "\n"
                + "Number of years:\t"
                + years + "\n"
                + "Future value:\t\t"
                + setFormat("currency").format(futureValue) + "\n";

        System.out.println();
        System.out.println("FORMATTED RESULTS");
        System.out.println(results);
    }

    static public NumberFormat setFormat(String input) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        NumberFormat result = null;

        if ("currency".equalsIgnoreCase(input)) {
            result = NumberFormat.getCurrencyInstance();
        } else if ("percent".equalsIgnoreCase(input)) {
            result = NumberFormat.getPercentInstance();
            percent.setMinimumFractionDigits(1);
        } else {
            System.out.println("No Format Defined");
        }
        return result;
    }

    static public void addResultsToArray(String calculations[][], int row, double monthlyInvestment,
                                         double interestRate, int years, double futureValue) {
        calculations[row][0] = setFormat("currency").format(monthlyInvestment);
        calculations[row][1] = setFormat("percent").format(interestRate / 100);
        calculations[row][2] = Integer.toString(years);
        calculations[row][3] = setFormat("currency").format(futureValue);
    }

    static public void printResults(String calculations[][], int row) {
        System.out.println();
        System.out.println("Future Value Calculations");
        System.out.println();
        System.out.println("Inv/Mo.\tRate\tYears\tFuture Value");
        for (int i = 0; i < row; i++) {
            String message = "";
            for (int j = 0; j < 4; j++) {
                message += calculations[i][j] + "\t";
            }
            System.out.println(message);

        }

    }
}