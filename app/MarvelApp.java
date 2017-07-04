package com.github.codingricky.marvel.app;

import java.util.*;

public class MarvelApp {

    public static void main(String[] args) {
        clearScreen();
        startingApplication();

        Scanner keyboard = new Scanner(System.in);
        while(true) {
            System.out.print("search> ");
            final String line = keyboard.nextLine();
            exitCondition(line);
            MarvelAppMethods.command_parser(line);
        }
    }

    /**
     * This prints the application information at the
     * beginning of the execution.
     */
    private static void startingApplication(){
        System.out.println("****************************\n* Marvel Super Heroes App  *\n****************************");
        System.out.println("This application lets you search for data in the Marvel official database.");
        System.out.println("Write ':quit' to exit.");
    }

    /**
     * This checks if the user wants to finish the execution.
     * Input:
     *    String line: user input line of words
     */
    private static void exitCondition(String line){
        if(line.equals(":quit")){
            System.out.println("Exiting application");
            System.out.println("****************************");
            clearScreen();
            System.exit(0);
        }
    }

    /**
     * Just for visualizing purposes, cleans the terminal at the
     * beginning of the execution.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}