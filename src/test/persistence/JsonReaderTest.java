package persistence;

import model.Account;
import model.Profile;
import model.ProfileList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

// Based on JSON Demo from CPSC 210
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account acc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // all good! pass!
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account acc = reader.read();
            checkProfile("Initial", "Science", 99, "Initial Message", acc.getUserProfile());
            assertEquals(0, acc.getUserList().getList().size());
            assertEquals(0, acc.getDatabase().getList().size());
        } catch (IOException e) {
            fail("Didn't load from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Account acc = reader.read();
            checkProfile("Karen", "Science", 33, "I love bacteria!", acc.getUserProfile());
            ProfileList accUserList = acc.getUserList();
            ProfileList accDatabase = acc.getDatabase();
            assertEquals(1, accUserList.getList().size());
            checkProfile("Nathan", "Arts", 99, "Howdy there!", accUserList.getList().get(0));
            assertEquals(2, accDatabase.getList().size());
            checkProfile("Nathan", "Arts", 99, "Howdy there!", accDatabase.getList().get(0));
            checkProfile("Tim", "Land and Food Systems", 49, "Hello!",
                    accDatabase.getList().get(1));
        } catch (IOException e) {
            fail("Didn't load from file");
        }
    }

}

