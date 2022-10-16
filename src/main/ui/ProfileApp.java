package ui;

import model.Profile;
import model.ProfileList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Profile application (inspired by TellerApp)
public class ProfileApp {
    private Profile user;
    private ProfileList userList;
    private ProfileList database;
    private Scanner input;
    private boolean profileCreated;
    private List<String> faculties;

    // EFFECTS: run the profile application
    public ProfileApp() {
        runProfileApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProfileApp() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user commands
    private void processCommand(String command) {
        if (command.equals("c")) {
            createUserProfile();
        } else if (command.equals("l")) {
            viewListOfProfiles(userList);
        } else if (command.equals("d")) {
            viewListOfProfiles(database);
        } else if (command.equals("ap")) {
            profileListActions();
        } else if (command.equals("ad")) {
            profileDatabaseActions();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the database of profiles
    private void initialize() {
        Profile sampleUser1 = new Profile("Tim", "Land and Food Systems", 99, "Hello!");
        Profile sampleUser2 = new Profile("Karen", "Science", 33, "I love bacteria!");
        Profile sampleUser3 = new Profile("Nathan", "Arts", 49, "Howdy!");
        database = new ProfileList();
        database.addProfile(sampleUser1);
        database.addProfile(sampleUser2);
        database.addProfile(sampleUser3);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        userList = new ProfileList();
        faculties = new ArrayList<>();
        faculties.add("Applied Science");
        faculties.add("Arts");
        faculties.add("Sauder School of Business");
        faculties.add("Forestry");
        faculties.add("Land and Food Systems");
        faculties.add("Pharmaceutical Sciences");
        faculties.add("Science");

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome! Select from one of the following options:");
        System.out.println("\tc : Create Your Profile");
        System.out.println("\tl : View Your List of Profiles");
        System.out.println("\td : View All Profiles");
        System.out.println("\tq : Quit Application");
    }

    // EFFECTS: creates a user's profile
    private void createUserProfile() {
        if (!profileCreated) {
            System.out.println("What is your name?");
            String name = input.next();

            System.out.println("What faculty are you in?");
            printFaculties();
            int facultyNumber = input.nextInt();
            String faculty = faculties.get(facultyNumber - 1);

            System.out.println("What bus route do you take?");
            int route = input.nextInt();

            System.out.println("Please share a little bit about yourself!");
            String message = input.next();

            user = new Profile(name, faculty, route, message);
            profileCreated = true;
            database.addProfile(user);

        } else {
            System.out.println("You've already created a profile!");
            viewProfile();
        }
    }

    // EFFECTS: creates and prints a list of all the faculties
    private void printFaculties() {
        for (int i = 1; i <= faculties.size(); i++) {
            System.out.println(i + ". " + faculties.get(i - 1));
        }
    }

    // EFFECTS: shows user their created profile
    private void viewProfile() {
        System.out.println("\tName: " + user.getName());
        System.out.println("\tFaculty: " + user.getFaculty());
        System.out.println("\tRoute: " + user.getRoute());
        System.out.println("\t" + user.getMessage());

    }

    // EFFECTS: shows a list of profiles
    private void viewListOfProfiles(ProfileList profileList) {
        if (profileList.getList().size() == 0) {
            System.out.println("There are no profiles!");
        }
        for (int i = 1; i <= profileList.getList().size(); i++) {
            System.out.println("PROFILE " + i + ":");
            System.out.println("\tName: " + profileList.getList().get(i - 1).getName());
            System.out.println("\tFaculty: " + profileList.getList().get(i - 1).getFaculty());
            System.out.println("\tRoute: " + profileList.getList().get(i - 1).getRoute());
            System.out.println("\tMessage: " + profileList.getList().get(i - 1).getMessage());
        }
        if (profileList == userList) {
            System.out.println("ap: remove a profile from the list OR sort the list by commute route or faculty.");
        } else if (profileList == database) {
            System.out.println("ad: add a profile to your list OR sort the list by commute route or faculty.");
        }

    }

    // MODIFIES: this
    // EFFECTS: sorts the list by faculty or commute route OR removes a profile from the list
    private void profileListActions() {
        System.out.println("Select an action:");
        System.out.println("s: Sort by Faculty or Commute Route");
        System.out.println("r: Remove a Profile");

        String choice = input.next();
        if (choice.equals("s")) {
            System.out.println("f: Faculty");
            System.out.println("r: Commute Route");
            choice = input.next();
            if (choice.equals("f")) {
                printFaculties();
                userList.sortProfileByFaculty(faculties.get(input.nextInt() - 1));
            } else if (choice.equals("r")) {
                userList.sortProfileByRoute(input.nextInt() - 1);
            }
        } else if (choice.equals("r")) {
            System.out.println("Enter which profile number you would like to remove:");
            viewListOfProfiles(userList);
            userList.removeProfile(userList.getList().get(input.nextInt() - 1));
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the list by faculty or commute route OR adds a profile to user's list from database
    private void profileDatabaseActions() {
        System.out.println("Select an action:");
        System.out.println("s: Sort by Faculty or Commute Route");
        System.out.println("a: Add a Profile to Your List");

        String choice = input.next();
        if (choice.equals("s")) {
            System.out.println("f: Faculty");
            System.out.println("r: Commute Route");
            choice = input.next();
            if (choice.equals("f")) {
                printFaculties();
                database.sortProfileByFaculty(faculties.get(input.nextInt() - 1));
            } else if (choice.equals("r")) {
                database.sortProfileByRoute(input.nextInt());
            }
        } else if (choice.equals("a")) {
            System.out.println("Enter the profile number you would like to add:");
            viewListOfProfiles(database);
            userList.addProfile(database.getList().get(input.nextInt() - 1));
        }
    }


}
