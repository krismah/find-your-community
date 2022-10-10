package model;

// Represents operations that can be performed on collections containing profiles
public interface ProfileOperations {

    // MODIFIES: this
    // EFFECTS: adds a given profile
    void addProfile(Profile profile);

    // MODIFIES: this
    // EFFECTS: removes a profile
    void removeProfile(Profile profile);

    // MODIFIES: this
    // EFFECTS: sorts profiles based on a given category
    void sortProfile(String category);
}
