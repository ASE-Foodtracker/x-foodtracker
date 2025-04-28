package de.jmf.adapters.menus;

public class PrintMenus{
    public void printHomeScreen(){
        System.out.println("Welcome to your favorite fitness app");
        System.out.println("Would you like to:");
        System.out.println("1 - Log into your account");
        System.out.println("2 - Create a new account");
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1 - user details");
        System.out.println("2 - Gymplan Menu");
        System.out.println("3 - track your weight");
        System.out.println("4 - meal management");
        System.out.println("5 - save");
        System.out.println("6 - log out");
        System.out.println("0 - exit");
    }

    public void printSavingMenu(){
        System.out.println();
        System.out.println("Saving");
        System.out.println("1 - all");
        System.out.println("2 - user details");
        System.out.println("3 - weight progress");
        System.out.println("4 - meals");
        System.out.println("0 - back");
    }

    public void printMealManagementMenu(){
        System.out.println();
        System.out.println("Meal Management");
        System.out.println("1 - add meal");
        System.out.println("2 - remove meal");
        System.out.println("3 - show meals");
        System.out.println("0 - back");
    }
}