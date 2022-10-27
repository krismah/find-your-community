package model;

import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
    }

    @Test
    void testConstructor() {
        assertEquals("Initial", testAccount.getUserProfile().getName());
        assertEquals("Science", testAccount.getUserProfile().getFaculty());
        assertEquals(99, testAccount.getUserProfile().getRoute());
        assertEquals("Initial Message", testAccount.getUserProfile().getMessage());
        assertEquals(0, testAccount.getDatabase().getList().size());
        assertEquals(0, testAccount.getUserList().getList().size());
        assertFalse(testAccount.getProfileCreated());
    }

    @Test
    void testSetUserList() {
        Profile testProfile1 = new Profile("Test", "Faculty", 1, "Message");
        ProfileList testProfileList = new ProfileList();
        testProfileList.addProfile(testProfile1);
        testAccount.setUserList(testProfileList);
        assertEquals(1, testAccount.getUserList().getList().size());
        assertEquals(testProfileList, testAccount.getUserList());
    }

    @Test
    void testSetDatabase() {
        Profile testProfile1 = new Profile("Test", "Faculty", 1, "Message");
        ProfileList testProfileList = new ProfileList();
        testProfileList.addProfile(testProfile1);
        testAccount.setDatabase(testProfileList);
        assertEquals(1, testAccount.getDatabase().getList().size());
        assertEquals(testProfileList, testAccount.getDatabase());
    }

}