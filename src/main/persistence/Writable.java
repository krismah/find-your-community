package persistence;

import org.json.JSONObject;

// based on JSON Demo from CPSC 210
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
