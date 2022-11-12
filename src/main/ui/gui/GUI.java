package ui.gui;

import model.Account;
import model.Profile;
import model.ProfileList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

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
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;
    private JFrame frame;
    private JPanel saveAndLoad;
    private JList graphicalDatabase;
    private JPanel userGraphicalDatabase;

    public GUI() {
        frame = new JFrame("Find Your Community!");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        initialize();
        initializeSaveAndLoadButtons();
        initializeDatabase();

        frame.add(saveAndLoad, BorderLayout.SOUTH);
        frame.add(userGraphicalDatabase);
        frame.pack();
        frame.setVisible(true);

    }

    // EFFECTS: initializes account, database and things related to persistence
    private void initialize() {
        Profile sampleUser1 = new Profile("Tim", "Land and Food Systems", 99, "Hello!");
        Profile sampleUser2 = new Profile("Karen", "Science", 33, "I love bacteria!");
        Profile sampleUser3 = new Profile("Nathan", "Arts", 49, "Howdy!");
        acc = new Account();
        database = acc.getDatabase();
        database.addProfile(sampleUser1);
        database.addProfile(sampleUser2);
        database.addProfile(sampleUser3);
        userList = acc.getUserList();
    }

    // EFFECTS: initializes the saving and loading buttons and features
    private void initializeSaveAndLoadButtons() {
        saveAndLoad = new JPanel(new GridLayout(2, 1));
        saveAndLoad.setBorder(BorderFactory.createLineBorder(Color.black));
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
            listModel.addElement(profile.getName() + " " + profile.getFaculty()
                    + "\n" + profile.getRoute() + "\n" + profile.getMessage());
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
        graphicalDatabase.setVisibleRowCount(5);

        createDatabaseButtons();
    }

    private void createDatabaseButtons() {
        JLabel databaseLabel = new JLabel("Profile Database");
        JButton addProfile = new JButton("Add Profile");
        addProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("slay");
            }
        });

        JButton sortProfiles = new JButton("Sort Profiles");
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
}
