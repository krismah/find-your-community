package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of profiles
public class ProfileList {
    private List<Profile> profiles;

    // EFFECTS: creates an empty list, where profiles can be added either by the user or the program itself
    public ProfileList() {
        profiles = new ArrayList<>();

    }

    // REQUIRES: profile cannot already exist in this
    // MODIFIES: this
    // EFFECTS: adds a given profile
    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    // REQUIRES: profile must exist in this
    // MODIFIES: this
    // EFFECTS: removes a profile
    public void removeProfile(Profile profile) {
        profiles.remove(profile);
    }

    // EFFECTS: creates a new sorted list of profiles based on faculty
    public ProfileList sortProfileByFaculty(String faculty) {
        ProfileList sorted = new ProfileList();
        for (Profile profile: profiles) {
            if (profile.getFaculty().equals(faculty)) {
                sorted.addProfile(profile);
            }
        }
        return sorted;
    }

    // EFFECTS: creates a new sorted list of profiles based on route
    public ProfileList sortProfileByRoute(int route) {
        ProfileList sorted = new ProfileList();
        for (Profile profile: profiles) {
            if (profile.getRoute() == route) {
                sorted.addProfile(profile);
            }
        }
        return sorted;
    }

    public List<Profile> getList() {
        return profiles;
    }
}
