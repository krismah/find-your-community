package ui.gui;

import model.Account;
import model.Event;
import model.EventLog;
import model.Profile;
import model.ProfileList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

// Represents the graphical user interface
// Partially inspired by CPSC 210 Traffic Light Lecture Lab
public class GUI {
    private Account acc;
    private Profile user;
    private ProfileList userList;
    private ProfileList database;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int HEIGHT = 500;
    private static final int WIDTH = 800;
    private JFrame frame;
    private JPanel saveAndLoad;
    private JList graphicalDatabase;
    private JPanel userGraphicalDatabase;
    private JList graphicalUserList;
    private JPanel userGraphicalUserList;
    private JButton removeProfile;

    // EFFECTS: constructs the graphical user interface
    public GUI() {
        frame = new JFrame("Find Your Community!");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog log = EventLog.getInstance();
                printLog(log);
                System.exit(0);
            }
        });
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        initialize();
        initializeSaveAndLoadButtons();
        initializeDatabase();
        initializeUserList();

        frame.add(userGraphicalDatabase, BorderLayout.WEST);
        frame.add(userGraphicalUserList, BorderLayout.EAST);
        frame.add(saveAndLoad, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

    }

    // EFFECTS: initializes account, database and things related to persistence
    private void initialize() {
        Profile sampleUser1 = new Profile("Tim", "Land and Food Systems", 99, "Hello!");
        Profile sampleUser2 = new Profile("Karen", "Science", 33, "I love bacteria!");
        Profile sampleUser3 = new Profile("Nathan", "Arts", 49, "Howdy!");
        Profile sampleUser4 = new Profile("Marie", "Science", 99, "Chemistry-fanatic!");
        Profile sampleUser5 = new Profile("Tyler", "Sauder School of Business", 49, "Business!");
        acc = new Account();
        database = acc.getDatabase();
        database.addProfile(sampleUser1);
        database.addProfile(sampleUser2);
        database.addProfile(sampleUser3);
        database.addProfile(sampleUser4);
        database.addProfile(sampleUser5);
        userList = acc.getUserList();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // EFFECTS: initializes the saving and loading buttons and features
    private void initializeSaveAndLoadButtons() {
        saveAndLoad = new JPanel(new GridLayout(2, 1));
        JButton saveButton = new JButton("Save the Application");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAccount();
            }
        });

        JButton loadButton = new JButton("Load the Application");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAccount();
            }
        });

        saveAndLoad.add(saveButton);
        saveAndLoad.add(loadButton);
    }

    // EFFECTS: initializes the graphical representation of database
    private void initializeDatabase() {
        DefaultListModel listModel = printProfiles(acc.getDatabase());

        graphicalDatabase = new JList(listModel);
        graphicalDatabase.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    return;
                }
            }
        });
        graphicalDatabase.setSelectionMode(SINGLE_SELECTION);
        graphicalDatabase.setSelectedIndex(0);
        graphicalDatabase.setVisibleRowCount(3);
        createDatabaseButtons();
    }

    // EFFECTS: creates buttons related to database
    private void createDatabaseButtons() {
        JLabel databaseLabel = new JLabel("Profile Database" + " (Name, Faculty, Route, and Message)");
        JButton addProfile = new JButton("Add Profile to Your List");
        addProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfileAction();
            }
        });

        JButton sortDatabaseByRoute = new JButton("Sort Database by Route");
        sortDatabaseByRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortProfilesAction(database);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addProfile);
        buttonPanel.add(sortDatabaseByRoute);

        userGraphicalDatabase = new JPanel(new BorderLayout());
        userGraphicalDatabase.add(databaseLabel, BorderLayout.NORTH);
        userGraphicalDatabase.add(graphicalDatabase, BorderLayout.CENTER);
        userGraphicalDatabase.add(buttonPanel, BorderLayout.SOUTH);
        userGraphicalDatabase.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    // EFFECTS: initializes the graphical representation of userList
    private void initializeUserList() {
        DefaultListModel listModel = printProfiles(acc.getUserList());

        graphicalUserList = new JList(listModel);
        graphicalUserList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (graphicalUserList.getSelectedIndex() == -1) {
                        removeProfile.setEnabled(false);
                    } else {
                        removeProfile.setEnabled(true);
                    }
                }
            }
        });
        graphicalUserList.setSelectionMode(SINGLE_SELECTION);
        graphicalUserList.setSelectedIndex(0);
        graphicalUserList.setVisibleRowCount(3);
        createUserListButtons();
    }

    // EFFECTS: creates buttons related to userList
    private void createUserListButtons() {
        JLabel userListLabel = new JLabel("Your List of Profiles");
        removeProfile = new JButton("Remove Profile");
        removeProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProfileAction();
            }
        });

        JButton sortUserListByRoute = new JButton("Sort List by Route");
        sortUserListByRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortProfilesAction(userList);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeProfile);
        buttonPanel.add(sortUserListByRoute);

        userGraphicalUserList = new JPanel(new BorderLayout());
        userGraphicalUserList.add(userListLabel, BorderLayout.NORTH);
        userGraphicalUserList.add(graphicalUserList, BorderLayout.CENTER);
        userGraphicalUserList.add(buttonPanel, BorderLayout.SOUTH);
        userGraphicalUserList.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    // EFFECTS: adds a given profile
    private void addProfileAction() {
        userList.addProfile(database.getList().get(graphicalDatabase.getSelectedIndex()));
        DefaultListModel list = printProfiles(acc.getUserList());

        graphicalUserList.setModel(list);
    }

    // EFFECTS: removes a given profile
    private void removeProfileAction() {
        userList.removeProfile(userList.getList().get(graphicalUserList.getSelectedIndex()));
        DefaultListModel list = printProfiles(acc.getUserList());

        graphicalUserList.setModel(list);
    }

    // EFFECTS: sorts the list by a given condition
    private void sortProfilesAction(ProfileList profileList) {
        JPanel sortPanel = new JPanel();
        JLabel label = new JLabel("Enter a route:");
        JTextField sortCondition = new JTextField(2);
        JButton button = new JButton("Confirm");

        sortPanel.add(label);
        sortPanel.add(sortCondition);
        sortPanel.add(button);

        JFrame userSortFrame = new JFrame();
        userSortFrame.add(sortPanel);

        userSortFrame.setSize(100, 150);
        userSortFrame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSortFrame.dispose();
                int response = Integer.parseInt(sortCondition.getText());

                DefaultListModel list = printProfiles(profileList.sortProfileByRoute(response));

                JList sortedList = new JList(list);
                sortedFrame(sortedList);
            }
        });
    }

    // EFFECTS: creates a frame to show the sorted list
    private void sortedFrame(JList list) {
        JFrame sortedFrame = new JFrame();
        JLabel label = new JLabel("The following profiles have the same route:");
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(label, BorderLayout.NORTH);
        panel.add(list, BorderLayout.CENTER);

        sortedFrame.add(panel);
        sortedFrame.pack();
        sortedFrame.setVisible(true);

    }

    // EFFECTS: creates a list that represents each profile
    private DefaultListModel printProfiles(ProfileList profileList) {
        DefaultListModel list = new DefaultListModel();

        for (Profile profile : profileList.getList()) {
            list.addElement(profile.getName() + " || " + profile.getFaculty()
                    + " || " + profile.getRoute() + " || " + profile.getMessage());
        }
        return list;
    }

    // EFFECTS: saves the account to file
    private void saveAccount() {
        try {
            acc.setDatabase(database);
            acc.setUserList(userList);
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
            confirmSaveOrLoad("save");
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + JSON_STORE);
        }
    }

    // EFFECTS: loads the account from file
    private void loadAccount() {
        try {
            acc = jsonReader.read();
            user = acc.getUserProfile();
            userList = acc.getUserList();
            database = acc.getDatabase();
            reloadProfileList(database);
            reloadProfileList(userList);
            confirmSaveOrLoad("load");
            System.out.println("Successfully loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from " + JSON_STORE);
        }
    }

    // EFFECTS: loads the graphical representation of lists from file
    private void reloadProfileList(ProfileList profileList) {
        DefaultListModel list = printProfiles(profileList);

        if (profileList.equals(database)) {
            graphicalDatabase.setModel(list);
        } else {
            graphicalUserList.setModel(list);
        }
    }

    // EFFECTS: opens a new window confirming saving or loading was successful
    private void confirmSaveOrLoad(String state) {
        String sep = System.getProperty("file.separator");
        ImageIcon checkmark = new ImageIcon(System.getProperty("user.dir") + sep + "data" + sep + "images"
                + sep + "checkmark.png");
        JFrame confirmationFrame = new JFrame();

        JLabel label = new JLabel(checkmark);
        JLabel confirmation;

        if (state.equals("save")) {
            confirmation = new JLabel("Successfully saved to ./data/account.json");
        } else {
            confirmation = new JLabel("Successfully loaded from ./data/account.json");
        }

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(confirmation);

        confirmationFrame.add(panel);
        confirmationFrame.pack();
        confirmationFrame.setVisible(true);
    }

    // EFFECTS: prints the EventLog
    private void printLog(EventLog log) {
        for (Event event : log) {
            System.out.println(event.toString());
        }
    }
}
