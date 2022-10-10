package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Represents operations that can be performed on collections containing profiles
public interface ProfileOperations {

    // REQUIRES: profile cannot already exist in this
    // MODIFIES: this
    // EFFECTS: adds a given profile
    void addProfile(Profile profile);

    // REQUIRES: profile must exist in this
    // MODIFIES: this
    // EFFECTS: removes a profile
    void removeProfile(Profile profile);

    // EFFECTS: creates a new sorted list of profiles based on faculty
    Collection<Profile> sortProfileByFaculty(String faculty);


    // EFFECTS: creates a new sorted list of profiles based on route
    Collection<Profile> sortProfileByRoute(int route);
}
