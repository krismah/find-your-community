package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Represents all profiles that exist
public class ProfileDatabase implements ProfileOperations {
    private Set<Profile> profiles;

    // EFFECTS: creates an empty database to be filled with all profiles
    public ProfileDatabase() {
        profiles = new HashSet<Profile>();
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
    public HashSet<Profile> sortProfileByFaculty(String faculty) {
        HashSet<Profile> sorted = new HashSet<Profile>();
        for (Profile profile: profiles) {
            if (profile.getFaculty().equals(faculty)) {
                sorted.add(profile);
            }
        }
        return sorted;
    }

    @Override
    public HashSet<Profile> sortProfileByRoute(int route) {
        HashSet<Profile> sorted = new HashSet<Profile>();
        for (Profile profile: profiles) {
            if (profile.getRoute() == route) {
                sorted.add(profile);
            }
        }
        return sorted;
    }

    public Set<Profile> getSet() {
        return profiles;
    }
}
