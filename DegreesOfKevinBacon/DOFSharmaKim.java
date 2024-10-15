/************************************************************
*
* Programming Assignment: Degrees of Kevin Bacon
*
* file: KevinBaconDegreesSharmaKim.java
*
*
* Author: Avi Sharma, Joshua Kim
*
* Description: A program to calculate an actor's connection to Kevin Bacon. A higher number means farther connection.
* Note: This version is limited to a bacon number of 2.
*
* Honor Code: PWCS HONOR CODE-  Students are to neither give nor receive assistance (written, orally, or otherwise) on tests, examinations, final evaluations, or class assignments that are to be graded as the work of a single individual. Cheating includes the giving or receiving of a computer file, program, part of a program, or other computer-based information without specific teacher direction or approval. Cheating encompasses any violation of rules where the violation involves dishonesty.
*
* *********************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DOFSharmaKim {

   private static final String file = "list.txt"; 
    
   private ArrayList<String> actors;
   private ArrayList<String> movies;
   private ArrayList<Integer> years;

   public DOFSharmaKim() {
      actors = new ArrayList<>();
      movies = new ArrayList<>();
      years = new ArrayList<>();
      loadFromFile();
   }

   private void loadFromFile() {
      try {
         File inputFile = new File("list.txt");
         Scanner scanner = new Scanner(inputFile);
         while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String[] actorsMoviesYears = row.split(" ");
            if (actorsMoviesYears.length >= 3) {
               String name = actorsMoviesYears[0] + " " + actorsMoviesYears[1];
               String movie = "";
               for (int i = 2; i < actorsMoviesYears.length - 1; i++) {
                  movie += actorsMoviesYears[i] + " ";
               }
               movie = movie.trim(); 
               int year = Integer.parseInt(actorsMoviesYears[actorsMoviesYears.length - 1]);
            
               actors.add(name);
               movies.add(movie);
               years.add(year);
            }
         }
      } catch (FileNotFoundException e) {
         System.out.println("An error occurred: File not found.");
         e.printStackTrace();
      }
   }   
   private void saveToFile() {
      try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
         for (int i = 0; i < actors.size(); i++) {
            writer.println(actors.get(i) + " " + movies.get(i) + " " + years.get(i));
         }
      } catch (IOException e) {
         System.err.println("Error writing to file: " + e.getMessage());
      }
   }
   private int calculateBaconNumber(String actorName) {
      int actorIndex = actors.indexOf(actorName);
    
      if (actorIndex == -1) {
         System.out.println(actorName + " not found in the database.");
         return -1;
      }
    
      ArrayList<String> actorMovies = new ArrayList<>();
      for (int i = 0; i < actors.size(); i++) {
         if (actors.get(i).equalsIgnoreCase(actorName)) {
            actorMovies.add(movies.get(i));
         }
      }
    
      for (int i = 0; i < actors.size(); i++) {
         if (actors.get(i).equalsIgnoreCase("Kevin Bacon") && actorMovies.contains(movies.get(i))) {
            return 1; 
         }
      }
    
      return 2;
   }




   private void addEntry(String actor, String movie, int year) {
      actors.add(actor);
      movies.add(movie);
      years.add(year);
      System.out.println("Entry added successfully: " + actor + ", " + movie + ", " + year);
      saveToFile(); 
   }

   private void deleteEntry(String actor, String movie) {
      String entryToDelete = actor + " " + movie; 
      for (int i = 0; i < actors.size(); i++) {
         String currentEntry = actors.get(i) + " " + movies.get(i); 
         if (currentEntry.equalsIgnoreCase(entryToDelete)) { 
            actors.remove(i);
            movies.remove(i);
            years.remove(i);
            System.out.println("Entry deleted successfully: " + actor + ", " + movie);
            return;
         }
      }
      System.out.println("Entry not found: " + actor + ", " + movie);
   }




   public void run() {
      Scanner scanner = new Scanner(System.in);
      boolean running = true;
   
      while (running) {
         System.out.println("Select Options:");
         System.out.println("1. Calculate an actor’s Bacon Number");
         System.out.println("2. Add an entry to the list");
         System.out.println("3. Delete an entry from the list");
         System.out.println("4. Quit");
      
         int choice = scanner.nextInt();
         scanner.nextLine(); 
      
         switch (choice) {
            case 1:
               System.out.println("Enter actor's name:");
               String actorName = scanner.nextLine();
               int baconNumber = calculateBaconNumber(actorName);
               System.out.println(actorName + "'s Bacon Number is: " + baconNumber);
               break;
            case 2:
               System.out.println("Enter actor's name:");
               String actor = scanner.nextLine();
               System.out.println("Enter movie:");
               String movie = scanner.nextLine();
               System.out.println("Enter year:");
               int year = scanner.nextInt();
               addEntry(actor, movie, year);
               saveToFile();
               break;
            case 3:
               System.out.println("Enter actor's name:");
               String actorToDelete = scanner.nextLine();
               System.out.println("Enter movie to delete:");
               String movieToDelete = scanner.nextLine();
               deleteEntry(actorToDelete, movieToDelete);
               saveToFile();
               break;
         
            case 4:
               running = false;
               break;
            default:
               System.out.println("Invalid choice! Please enter a number between 1 and 4.");
         }
      }
   
      scanner.close();
      saveToFile(); 
   }

   public static void main(String[] args) {
      DOFSharmaKim program = new DOFSharmaKim();
      program.run();
   }
}
