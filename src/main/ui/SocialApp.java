package ui;

import model.Profile;
import model.ProfileList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Profile application (based on TellerApp and Json Demo from CPSC 210)
public class SocialApp {
    private Profile user;
    private ProfileList userList;
    private ProfileList database;
    private Scanner input;
    private boolean profileCreated;
    private List<String> faculties;
    private static final String JSON_STORE_USER_LIST = "./data/appstate.json";
    private JsonReader jsonReaderUserList;
    private JsonWriter jsonWriterUserList;


    // EFFECTS: runs the social application
    public SocialApp() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriterUserList = new JsonWriter(JSON_STORE);
        jsonReaderUserList = new JsonReader(JSON_STORE);
        runSocialApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSocialApp() {
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
            viewProfilesMenu(userList);
        } else if (command.equals("d")) {
            viewProfilesMenu(database);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // EFFECTS: initializes database of profiles and faculty lists
    private void initialize() {
        Profile sampleUser1 = new Profile("Tim", "Land and Food Systems", 99, "Hello!");
        Profile sampleUser2 = new Profile("Karen", "Science", 33, "I love bacteria!");
        Profile sampleUser3 = new Profile("Nathan", "Arts", 49, "Howdy!");
        database = new ProfileList();
        database.addProfile(sampleUser1);
        database.addProfile(sampleUser2);
        database.addProfile(sampleUser3);
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
        System.out.println("\tl : View Your Personal List of Profiles");
        System.out.println("\td : View All Profiles");
        System.out.println("\tq : Quit Application");
    }

    // EFFECTS: creates a user's profile
    private void createUserProfile() {
        if (!profileCreated) {
            System.out.println("What is your name?");
            String name = input.next();

            System.out.println("What faculty are you in? Select a number:");
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

    // EFFECTS: prints a given list of profiles
    private void viewListOfProfiles(ProfileList profileList) {
        if (profileList.getList().size() > 0) {
            for (int i = 1; i <= profileList.getList().size(); i++) {
                System.out.println("PROFILE " + i + ":");
                System.out.println("\tName: " + profileList.getList().get(i - 1).getName());
                System.out.println("\tFaculty: " + profileList.getList().get(i - 1).getFaculty());
                System.out.println("\tRoute: " + profileList.getList().get(i - 1).getRoute());
                System.out.println("\tMessage: " + profileList.getList().get(i - 1).getMessage());
            }
        } else {
            System.out.println("There are no profiles!");
        }
    }

    // EFFECTS: shows user a given list and prompts to make an action to either sort or add/remove
    private void viewProfilesMenu(ProfileList profileList) {
        viewListOfProfiles(profileList);

        if (profileList == database) {
            System.out.println("act : add a profile to your list OR sort the list by commute route or faculty.");
            System.out.println("Press any key to return to the main menu.");
            String choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("act")) {
                profileDatabaseActions();
            }
        } else if (profileList == userList) {
            System.out.println("act : remove a profile from the list OR sort the list by commute route or faculty.");
            System.out.println("Press any key to return to the main menu.");
            String choice = input.next();
            choice = choice.toLowerCase();
            if (choice.equals("act")) {
                profileListActions();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the list by faculty or commute route OR removes a profile from the list
    private void profileListActions() {
        System.out.println("Select an action:");
        System.out.println("\ts : Sort by Faculty or Commute Route");
        System.out.println("\tr : Remove a Profile");

        String choice = input.next();
        if (choice.equals("s")) {
            System.out.println("f : Faculty");
            System.out.println("r : Commute Route");
            choice = input.next();
            if (choice.equals("f")) {
                System.out.println("Enter a faculty:");
                printFaculties();
                printProfiles(userList.sortProfileByFaculty(faculties.get(input.nextInt() - 1)));
            } else if (choice.equals("r")) {
                System.out.println("Enter a bus route:");
                printProfiles(userList.sortProfileByRoute(input.nextInt() - 1));
            }
        } else if (choice.equals("r")) {
            System.out.println("Enter which profile number you would like to remove:");
            viewListOfProfiles(userList);
            userList.removeProfile(userList.getList().get(input.nextInt() - 1));
            System.out.println("Profile removed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: sorts the list by faculty or commute route OR adds a profile to user's list from database
    private void profileDatabaseActions() {
        System.out.println("Select an action:");
        System.out.println("\ts : Sort by Faculty or Commute Route");
        System.out.println("\ta : Add a Profile to Your List");

        String choice = input.next();
        if (choice.equals("s")) {
            System.out.println("f : Faculty");
            System.out.println("r : Commute Route");
            choice = input.next();
            if (choice.equals("f")) {
                System.out.println("Enter a faculty:");
                printFaculties();
                printProfiles(database.sortProfileByFaculty(faculties.get(input.nextInt() - 1)));
            } else if (choice.equals("r")) {
                System.out.println("Enter a bus route:");
                printProfiles(database.sortProfileByRoute(input.nextInt()));
            }
        } else if (choice.equals("a")) {
            System.out.println("Enter the profile number you would like to add:");
            viewListOfProfiles(database);
            userList.addProfile(database.getList().get(input.nextInt() - 1));
            System.out.println("Profile added!");
        }
    }

    // EFFECTS: prints a list of profiles
    private void printProfiles(List<Profile> profiles) {
        for (int i = 1; i <= profiles.size(); i++) {
            System.out.println("PROFILE " + i);
            System.out.println("\tName: " + profiles.get(i - 1).getName());
            System.out.println("\tFaculty: " + profiles.get(i - 1).getFaculty());
            System.out.println("\tRoute: " + profiles.get(i - 1).getRoute());
            System.out.println("\tMessage: " + profiles.get(i - 1).getMessage());
        }
    }




}
