package persistence;

import model.Account;
import model.Profile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Based on JSON Demo from CPSC 210
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Account acc = new Account();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // all good! pass!
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            Account acc = new Account();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            acc = reader.read();
            checkProfile("Initial", "Science", 99, "Initial Message", acc.getUserProfile());
            assertEquals(0, acc.getUserList().getList().size());
            assertEquals(0, acc.getDatabase().getList().size());
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try {
            Account acc = new Account();
            Profile testProfile1 = new Profile("Kris", "Science", 99, "Hello there!");
            Profile testProfile2 = new Profile("Trisha", "Sauder School of Business",
                    49, "Sauder!");
            Profile testProfile3 = new Profile("Josh", "Arts", 33, "Writing!");
            acc.setUserProfile(testProfile1);
            acc.getUserList().addProfile(testProfile1);
            acc.getDatabase().addProfile(testProfile2);
            acc.getDatabase().addProfile(testProfile3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            acc = reader.read();
            checkProfile("Kris", "Science", 99, "Hello there!", acc.getUserProfile());
            assertEquals(1, acc.getUserList().getList().size());
            checkProfile("Kris", "Science", 99, "Hello there!",
                    acc.getUserList().getList().get(0));
            assertEquals(2, acc.getDatabase().getList().size());
            checkProfile("Trisha", "Sauder School of Business",  49, "Sauder!",
                    acc.getDatabase().getList().get(0));
            checkProfile("Josh", "Arts", 33, "Writing!",
                    acc.getDatabase().getList().get(1));

        } catch (IOException e) {
            fail("Exception shouldn't have been thrown!");
        }
    }
}
