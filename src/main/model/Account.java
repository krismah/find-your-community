package model;

// Represents a user with a profile, their list of profiles and the database
public class Account {
    private Profile userProfile;
    private ProfileList userList;
    private ProfileList database;

    // EFFECTS: creates an account with an initial Profile and two empty ProfileList, database and userList
    public Account() {
        userProfile = new Profile("Initial", "Science", 99, "Initial Message");
        userList = new ProfileList();
        database = new ProfileList();
    }


    // getters and setters
    public Profile getUserProfile() {
        return userProfile;
    }

    public ProfileList getUserList() {
        return userList;
    }

    public ProfileList getDatabase() {
        return database;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }

    public void setUserList(ProfileList userList) {
        this.userList = userList;
    }

    public void setDatabase(ProfileList database) {
        this.database = database;
    }


}
