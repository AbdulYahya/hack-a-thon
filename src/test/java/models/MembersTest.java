package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MembersTest {
    public Members setupMember() {
        return new Members("Samwise", "Gamgee", "New member", 20);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void newMember_instantiatesCorrectly_boolean() throws Exception {
        Members testMember = setupMember();
        assertFalse(testMember instanceof Members);
    }
}