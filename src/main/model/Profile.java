package model;

// Represents a profile with a name, faculty, commute route and short message
public class Profile {
    private String name;
    private String faculty;
    private int route;
    private String message;

    // EFFECTS: creates a profile with a name, faculty, commute route and short message
    public Profile(String name, String faculty, int route, String message) {
        this.name = name;
        this.faculty = faculty;
        this.route = route;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getRoute() {
        return route;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String newName) {
       name = newName;
    }

    public void setFaculty(String newFaculty) {
        faculty = newFaculty;
    }

    public void setRoute(int newRoute) {
        route = newRoute;
    }

    public void setMessage(String newMessage) {
        message = newMessage;
    }

}
