import java.util.Scanner;

/* 
 * Author: AVI SHARMA
 * Date: 9/24/2023
 * 
 * Programming Assigment RFOS 
 * Due 9/24
 * 
 * Program Description: Menu Ordering System 
 * 
 * Honor Code: <replace this text with the honor code - one or two sentences>
 * 
 */



public class RFOS {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      double subTotal = 0;
      double tip = 0;
      
      // Menu items and prices
      String[] menuDishes = {
         "Chicken Curry", "Shrimp Curry", "Naan", "Chai", "Gulab Jamun",
         "Lamb Biriyani", "Veg Biriyani", "Roti", "Chana Masala", "Kheer"
         };
      double[] menuPrices = {10.69, 14.25, 3.59, 2.45, 7.24, 15.59, 11.27, 10.35, 9.45, 4.99};
   
      // Display the menu
      System.out.println("\t\tMr. Sharma's Indian Food Menu");
      System.out.printf("%9s %28s\n", "Item", "Price (in dollars)");
      for (int i = 0; i < menuDishes.length; i++) {
         int length = 20 - menuDishes[i].length();
         if( i == 9){
            System.out.printf((i + 1) + "   %s%" + length + ".2f\n", menuDishes[i], menuPrices[i]);
            break;
         }
      
         System.out.printf((i + 1) + "    %s%" + length + ".2f\n", menuDishes[i], menuPrices[i]);
         
      }
      
   
      // Input the number of dishes to order
      System.out.print("How many different dishes would you like to order today? ");
      int numDishes = scnr.nextInt();
   
      
   
      // Order dishes
      int z = 1;
      while (z <= numDishes) {
         System.out.print("Enter dish " + z + " [1-10]: ");
         int dishNumber = scnr.nextInt();
         System.out.print("How many servings of dish " + dishNumber + " would you like to order? ");
         int servings = scnr.nextInt();
         subTotal += menuPrices[dishNumber - 1] * servings;
         z++;
      }
   
      // Input tax percentage
      System.out.print("Enter the tax %: ");
      double taxRate = scnr.nextDouble();
   
      // Calculate tax
      double tax = (subTotal * taxRate) / 100;
   
      // Input tip
      System.out.print("Do you want to add tip? ['y' - yes or 'n' - no] ");
      char addTip = scnr.next().charAt(0);
   
      
      switch (addTip) {
         case 'y':
            System.out.print("Enter tip % [0-100]: ");
            double tipRate = scnr.nextDouble();
            tip = (subTotal * tipRate) / 100;
            break;
         default:
            System.out.println("No tip added.");
            tip = 0.0;
            break;
      }
      
      
   
      // Calculate total amount
      double total = subTotal + tax + tip;
   
      // Display the receipt
      System.out.printf("Price: $ %.2f \n", subTotal);
      System.out.printf("Tax (" + taxRate + "%%): $%.2f \n", tax);
      System.out.printf("Tip: $%.2f\n", tip);
      System.out.println("− − − − − − − − −−");
      System.out.printf("Total Amount: $ %.2f\n",total);
      System.out.println("Your order has been placed and will be delivered soon! \nThank You for Ordering from Mr. Sharma's Kitchen!");
   
      
   }
}


