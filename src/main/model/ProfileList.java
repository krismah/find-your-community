package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of profiles
public class ProfileList implements ProfileOperations {

    // EFFECTS: creates an empty list where user can add profiles to this list
    public ProfileList() {

    }

    @Override
    public void addProfile(Profile profile) {

    }

    @Override
    public void removeProfile(Profile profile){

    }

    @Override
    public ArrayList<Profile> sortProfileByFaculty(String faculty) {
        return null; //stub
    }

    public ArrayList<Profile> sortProfileByRoute(int route) {
        return null; // stub
    }

    public ArrayList<Profile> getList() {
        return null; // stub
    }
}
