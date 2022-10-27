package persistence;

import model.Account;
import model.Profile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads an Account from JSON data stored in file
// (based on JSON Demo from CPSC 210)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        Account acc = new Account();
        addProfile(acc, jsonObject, "profile");
        addProfilesFromUserList(acc, jsonObject);
        addProfilesFromDatabase(acc, jsonObject);
        return acc;
    }

    // MODIFIES: acc
    // EFFECTS: parses userlist profiles from JSON object and adds them to account
    private void addProfilesFromUserList(Account acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userlist");
        String select = "userlist";
        for (Object json : jsonArray) {
            JSONObject nextProfile = (JSONObject) json;
            addProfile(acc, nextProfile, select);
        }
    }

    // MODIFIES: acc
    // EFFECTS: parses database profiles from JSON object and adds them to account
    private void addProfilesFromDatabase(Account acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("database");
        String select = "database";
        for (Object json : jsonArray) {
            JSONObject nextProfile = (JSONObject) json;
            addProfile(acc, nextProfile, select);
        }
    }

    // MODIFIES: acc
    // EFFECTS: parses profile from JSON object and adds it to account
    private void addProfile(Account acc, JSONObject jsonObject, String select) {
        int route = jsonObject.getInt("route");
        String name = jsonObject.getString("name");
        String message = jsonObject.getString("message");
        String faculty = jsonObject.getString("faculty");
        Profile profile = new Profile(name, faculty, route, message);
        if (select == "profile") {
            acc.setUserProfile(profile);
        }
        if (select == "userlist") {
            acc.getUserList().addProfile(profile);
        }
        if (select == "database") {
            acc.getDatabase().addProfile(profile);
        }
    }

}
