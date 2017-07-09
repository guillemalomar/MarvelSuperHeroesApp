package com.github.codingricky.marvel.app;

import java.util.*;
import java.io.*;

import com.github.codingricky.marvel.model.Result;
import com.github.codingricky.marvel.model.Comic;
import com.github.codingricky.marvel.model.Creator;
import com.github.codingricky.marvel.parameter.CreatorOrderBy;
import com.github.codingricky.marvel.parameter.CreatorParameters;
import com.github.codingricky.marvel.parameter.CreatorParametersBuilder;
import com.github.codingricky.marvel.RestClient;

public class MarvelAppMethods {

    private static void help_message(){
        System.out.println("List of available commands:");
        System.out.println("    -List: Show all creators.");
        System.out.println("        -X: filter");
        System.out.println("        -Y: sort");
        System.out.println("    -Add note: add a note to a specific creator.");
        System.out.println("        -id: Creator id");
        System.out.println("        -note: text to assign");
        System.out.println("    -Edit note: updates a note from a specific creator.");
        System.out.println("        -id: Creator id");
        System.out.println("        -note: new note to assign");
        System.out.println("    -Delete note: deletes the note from a specific creator.");
        System.out.println("        -id: Creator id");
        System.out.println("        -note: text to delete");
        System.out.println("    -Compare: Show two creators.");
        System.out.println("        -id1: Creator id");
        System.out.println("        -id2: Creator id");
        System.out.println("    -Test: runs tests on each of the commands.");
        System.out.println("        -(optional)Test to run (if not specified, all tests will be run)");
    }

    private static void list_filter_order(String filter, String sort){
        System.out.println("Sort   " + sort);
        CreatorParametersBuilder creatorParametersBuilder = new CreatorParametersBuilder(1);
        CreatorParameters creatorParameters = creatorParametersBuilder.create();
        if (sort.equals("A")){
            creatorParameters.addOrderBy(CreatorOrderBy.FIRST_NAME_ASC);
        } else if (sort.equals("D")){
            creatorParameters.addOrderBy(CreatorOrderBy.FIRST_NAME_DESC);
        }
        creatorParameters.setFirstName(filter);
        get_comics_creators(creatorParameters);
    }

    private static void list_filter(String filter){
        CreatorParametersBuilder creatorParametersBuilder = new CreatorParametersBuilder(1);
        CreatorParameters creatorParameters = creatorParametersBuilder.create();
        creatorParameters.setFirstName(filter);
        get_comics_creators(creatorParameters);
    }

    private static void get_comics_creators(CreatorParameters creatorParameters){
        try{
            Result<Creator> creator_res = restClient.getComicsCreators(creatorParameters);
            List<Creator> creators = creator_res.getData().getResults();
            for(Creator my_creator : creators){
                System.out.println(my_creator.getFullName());
            }
        } catch (IOException e){
            System.out.println("Exception: " + e);
        }
    }

    private static String publicKey = "b61d873ba41348e45c1b61130de3b32e";
    private static String privateKey = "62ce3b44558643b76533043889d18d2a7c8dfaec";
    private static RestClient restClient = new RestClient(privateKey, publicKey);

    public static void command_parser(String line){

        String[] splitted = line.split(";|,|\\:|-|\\.|\\ ");
        switch (splitted[0]) {
            case "Help":
                help_message();
            case "List":
                System.out.println("Listing creators");
                if (splitted.length > 1){
                    String filter = splitted[1];
                    System.out.println("Filter " + filter);
                    if (splitted.length > 2){
                        String sort = splitted[2];
                        list_filter_order(filter, sort);
                    } else {
                        list_filter(filter);
                    }
                } else {
                    CreatorParametersBuilder creatorParametersBuilder = new CreatorParametersBuilder(1);
                    CreatorParameters creatorParameters = creatorParametersBuilder.create();
                    get_comics_creators(creatorParameters);
                }
            case "Add":
                if (splitted.length == 4 && splitted[1].equals("note")){
                    String id = splitted[2];
                    String note = splitted[3];
                } else {
                    System.out.println("Incorrect 'Add note' call");
                    System.out.println("It should be 'Add note id note'");
                }
            case "Edit":
                if (splitted.length == 4 && splitted[1].equals("note")){
                    String id = splitted[2];
                    String note = splitted[3];
                } else {
                    System.out.println("Incorrect 'Edit note' call");
                    System.out.println("It should be 'Edit note id note'");
                }
            case "Delete":
            case "Compare":
            case "Test":
            case "comics":
                System.out.println("Searching for all comics");
                try{
                    final Result<Comic> comic_res = restClient.getComics();
                    List<Comic> comics = comic_res.getData().getResults();

                    for(Comic my_comic : comics){
                        System.out.println(my_comic.getTitle());
                    }
                } catch (IOException e){
                    System.out.println("Error retrieving comic:" + e);
                }
        }
    }
}
