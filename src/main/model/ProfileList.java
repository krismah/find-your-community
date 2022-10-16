package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user's list of profiles
public class ProfileList implements ProfileOperations {
    private List<Profile> profiles;

    // EFFECTS: creates an empty list where user can add profiles to this list
    public ProfileList() {
        profiles = new ArrayList<Profile>();

    }

    @Override
    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    @Override
    public void removeProfile(Profile profile) {
        profiles.remove(profile);
    }

    @Override
    public ArrayList<Profile> sortProfileByFaculty(String faculty) {
        ArrayList<Profile> sorted = new ArrayList<Profile>();
        for (Profile profile: profiles) {
            if (profile.getFaculty().equals(faculty)) {
                sorted.add(profile);
            }
        }
        return sorted;
    }

    public ArrayList<Profile> sortProfileByRoute(int route) {
        ArrayList<Profile> sorted = new ArrayList<Profile>();
        for (Profile profile: profiles) {
            if (profile.getRoute() == route) {
                sorted.add(profile);
            }
        }
        return sorted;
    }

    public List<Profile> getList() {
        return profiles;
    }
}
