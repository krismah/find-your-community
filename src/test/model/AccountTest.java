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
    }
}