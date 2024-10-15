import java.util.*;
/*
* Author: Avi Sharma
* Date: 12/2/23
*
* Programming Assigment PA4
* Due 11/27
*
* Program Description: school social network
*
* Honor Code:
*
*/

public class SocialNet {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalPeople = 0;
        System.out.println("What is the total number of friends in the network: ");

        
        while (true) {
            if (scanner.hasNextInt()) {
                totalPeople = scanner.nextInt();
                if (totalPeople > 0) {
                    break;  
                } else {
                    System.out.println("You need to have at least one friend in the network. Please enter again:");
                }
            } else {
                String invalidInput = scanner.next();
                System.out.println(invalidInput + "is an incorrect input! Please enter an integer.");
            }
        }

        

        String[] names = new String[totalPeople];
        for (int i = 0; i < totalPeople; i++) {
            System.out.println("Enter name: ");
            names[i] = scanner.next();
        }

        int[][] arrayOfFriends = new int[totalPeople][totalPeople];

        for (int i = 0; i < totalPeople; i++) {
            for (int j = 0; j < totalPeople; j++) {
                if (i != j && Math.abs(names[i].charAt(0) - names[j].charAt(0)) <= 12) {
                    arrayOfFriends[i][j] = 1;
                }
            }
        }

        
        System.out.print("\t");
        for (String name : names) {
            System.out.print(name + "\t");
        }
        System.out.println();
        for (int i = 0; i < totalPeople; i++) {
            System.out.print(names[i] + "\t");
            for (int j = 0; j < totalPeople; j++) {
                System.out.print(arrayOfFriends[i][j] + "\t");
            }
            System.out.println();
        }

        
        System.out.println("\nTotal Friends Count:");
        for (int i = 0; i < totalPeople; i++) {
            int totalFriends = 0;
            for (int j = 0; j < totalPeople; j++) {
                totalFriends += arrayOfFriends[i][j];
            }
            System.out.println(names[i] + "\t" + totalFriends);
        }

        
    }
}
