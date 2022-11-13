package ui.gui;

import model.Account;
import model.Profile;
import model.ProfileList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

// Represents the graphical user interface
public class GUI {
    private Account acc;
    private Profile user;
    private ProfileList userList;
    private ProfileList database;
    private List<String> faculties;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int HEIGHT = 5000;
    private static final int WIDTH = 5000;
    private JFrame frame;
    private JPanel saveAndLoad;
    private JList graphicalDatabase;
    private JPanel userGraphicalDatabase;
    private JList graphicalUserList;
    private JPanel userGraphicalUserList;

    public GUI() {
        frame = new JFrame("Find Your Community!");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        initialize();
        initializeSaveAndLoadButtons();
        initializeDatabase();
        initializeUserList();

        frame.add(saveAndLoad, BorderLayout.SOUTH);
        frame.add(userGraphicalDatabase, BorderLayout.WEST);
        frame.add(userGraphicalUserList, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);

    }

    // EFFECTS: initializes account, database and things related to persistence
    private void initialize() {
        Profile sampleUser1 = new Profile("Tim", "Land and Food Systems", 99, "Hello!");
        Profile sampleUser2 = new Profile("Karen", "Science", 33, "I love bacteria!");
        Profile sampleUser3 = new Profile("Nathan", "Arts", 49, "Howdy!");
        Profile sampleUser4 = new Profile("Marie", "Science", 99, "Chemistry-fanatic!");
        Profile sampleUser5 = new Profile("Tyler", "Sauder School of Business", 49, "Money money money!");
        acc = new Account();
        database = acc.getDatabase();
        database.addProfile(sampleUser1);
        database.addProfile(sampleUser2);
        database.addProfile(sampleUser3);
        database.addProfile(sampleUser4);
        database.addProfile(sampleUser5);
        userList = acc.getUserList();
    }

    // EFFECTS: initializes the saving and loading buttons and features
    private void initializeSaveAndLoadButtons() {
        saveAndLoad = new JPanel(new GridLayout(2, 1));
        JButton saveButton = new JButton("Save the Application");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("save button pushed!");
            }
        });

        JButton loadButton = new JButton("Load the Application");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("load button pushed!");
            }
        });

        saveAndLoad.add(saveButton);
        saveAndLoad.add(loadButton);
    }

    // EFFECTS: initializes the graphical representation of database
    private void initializeDatabase() {
        DefaultListModel listModel = new DefaultListModel();

        for (Profile profile : acc.getDatabase().getList()) {
            listModel.addElement(profile.getName() + " || " + profile.getFaculty()
                    + " || " + profile.getRoute() + " || " + profile.getMessage());
        }

        graphicalDatabase = new JList(listModel);
        graphicalDatabase.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (graphicalDatabase.getSelectedIndex() == -1) {
                        System.out.println("case 1");
                    } else {
                        System.out.println("case 2");
                    }
                }
            }
        });
        graphicalDatabase.setSelectionMode(SINGLE_SELECTION);
        graphicalDatabase.setSelectedIndex(0);
        graphicalDatabase.setVisibleRowCount(3);
        createDatabaseButtons();
    }

    private void createDatabaseButtons() {
        JLabel databaseLabel = new JLabel("Profile Database" + " (Name, Faculty, Route, and Message)");
        JButton addProfile = new JButton("Add Profile to Your List");
        addProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfileAction();
            }
        });

        JButton sortProfiles = new JButton("Sort Database by Route");
        sortProfiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("sort profiles button");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addProfile);
        buttonPanel.add(sortProfiles);

        userGraphicalDatabase = new JPanel(new BorderLayout());
        userGraphicalDatabase.add(databaseLabel, BorderLayout.NORTH);
        userGraphicalDatabase.add(graphicalDatabase, BorderLayout.CENTER);
        userGraphicalDatabase.add(buttonPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: initializes the graphical representation of userlist
    private void initializeUserList() {
        DefaultListModel listModel = new DefaultListModel();

        for (Profile profile : acc.getUserList().getList()) {
            listModel.addElement(profile.getName() + " || " + profile.getFaculty()
                    + " || " + profile.getRoute() + " || " + profile.getMessage());
        }
        graphicalUserList = new JList(listModel);
        graphicalUserList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (graphicalUserList.getSelectedIndex() == -1) {
                        System.out.println("case 1");
                    } else {
                        System.out.println("case 2");
                    }
                }
            }
        });
        graphicalUserList.setSelectionMode(SINGLE_SELECTION);
        graphicalUserList.setSelectedIndex(0);
        graphicalUserList.setVisibleRowCount(3);
        createUserListButtons();
    }

    private void createUserListButtons() {
        JLabel userListLabel = new JLabel("Your List of Profiles");
        JButton removeProfile = new JButton("Remove Profile");
        removeProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProfileAction();
            }
        });

        JButton sortProfiles = new JButton("Sort List by Route");
        sortProfiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("sort profiles button");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeProfile);
        buttonPanel.add(sortProfiles);

        userGraphicalUserList = new JPanel(new BorderLayout());
        userGraphicalUserList.add(userListLabel, BorderLayout.NORTH);
        userGraphicalUserList.add(graphicalUserList, BorderLayout.CENTER);
        userGraphicalUserList.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addProfileAction() {
        userList.addProfile(database.getList().get(graphicalDatabase.getSelectedIndex()));
        DefaultListModel list = new DefaultListModel();

        for (Profile profile : acc.getUserList().getList()) {
            list.addElement(profile.getName() + " || " + profile.getFaculty()
                    + " || " + profile.getRoute() + " || " + profile.getMessage());
        }

        graphicalUserList.setModel(list);
    }

    private void removeProfileAction() {
        userList.removeProfile(userList.getList().get(graphicalUserList.getSelectedIndex()));
        DefaultListModel list = new DefaultListModel();

        for (Profile profile : acc.getUserList().getList()) {
            list.addElement(profile.getName() + " || " + profile.getFaculty()
                    + " || " + profile.getRoute() + " || " + profile.getMessage());
        }

        graphicalUserList.setModel(list);
    }
}
