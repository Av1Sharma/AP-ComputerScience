import java.util.*;

/* 
 * Author: Avi Sharma
 * Date: 10-14-23
 * 
 * Programming Assigment 3- Nim
 * Due 10/15/2023
 * 
 * Program Description: Nim Game
 * 
 * Honor Code: <replace this text with school honor code - one or two sentences>
 * 
 */

public class NimSmith{ 
   //Variables
   private static int pile = (int)(Math.random()*91+10);
   private static boolean computerMove = false;
   private static boolean humanMove = true;
   private static boolean smartMode;
   
   public static void main(String args[]){
      Scanner scnr = new Scanner(System.in);
      int n;
      System.out.println("You have " + pile + " elements to start");
      System.out.println("=====================================================");
      
      if((int)(Math.random()*2) == 1){
         computerMove = true;
      } else {
         humanMove = false;
      }
      
      if((int)(Math.random()*2) == 1){
         smartMode = true;
         System.out.println("Coin toss result for computer mode: smart mode");
         System.out.println("=====================================================");
      } else {
         System.out.println("Coin toss result for computer mode: stupid mode");
         System.out.println("=====================================================");
      }
      
      while (pile > 0) //main while loop for code
      {
         if(computerMove){
            pile = computerMove(pile, smartMode);
            if (pile == 1) {
               System.out.println("Computer won!");
               break;
            }
            pile = humanMove(pile, scnr);
            if (pile == 1) {
               System.out.println("You won!");
               break;
            }
         } else {
            pile = humanMove(pile, scnr);
            if (pile == 1) {
               System.out.println("You won!");
               break;
            }
            pile = computerMove(pile, smartMode);
            if (pile == 1) {
               System.out.println("Computer won!");
               break;
            }
         }
      
      }
   }
   
   public static int stupidMoveMarbleNum(int pile){
      return (int)(Math.random()*pile/2+1);
   }
   
   public static int smartMoveMarbleNum(int pile){
      if(pile == 3|| pile == 7|| pile == 15|| pile == 31|| pile == 63){
         return (int)(Math.random()*pile/2+1);
      } else if (pile == 2){
         return 1;
      } else
      {
         return pile- (int) (Math.pow(2,calculateN(pile))-1);
      }
     
   }
   
   public static int calculateN(int pile){ //If-Statements to determine power of N marbles we need to take away
      if(pile > 63){
         return 6;
      }
      if(pile > 31){
         return 5;
      }
      if(pile > 15){
         return 4;
      }
      if(pile > 7){
         return 3;
      }
      if(pile > 3){
         return 2;
      } else {
         return 0;
      }
      
   }
   
   public static int humanMove(int pile, Scanner scnr) {
      System.out.println("What's your move?");
      int marblesToTake = scnr.nextInt();
      
      if (marblesToTake > pile / 2 || marblesToTake < 1) {
         System.out.println(marblesToTake + " is not a legal number. You can only take a number from 1 and " + pile / 2 + "!");
         return humanMove(pile, scnr); // Prompt the user again
      } 
         
      pile = pile - marblesToTake;
      System.out.println("Now there are " + pile + " left");
      return pile; // Return the updated pile value
   }

   public static int computerMove(int pile, boolean smartMode) {
      int computerMarblesToTake;
      
      if (smartMode) { // Determine how many marbles to take
         computerMarblesToTake = smartMoveMarbleNum(pile);
      } else {
         computerMarblesToTake = stupidMoveMarbleNum(pile);
      }
      
      System.out.println("Computer takes " + computerMarblesToTake);
      pile = pile - computerMarblesToTake;
      System.out.println("Now there are " + pile + " left");
      System.out.println("=====================================================");
      return pile; // Return the updated pile value
   }
   
   public static boolean computerMove() {
      return computerMove;
   }
   
   public static boolean isSmart() {
      return smartMode;
   }
   
}

  
