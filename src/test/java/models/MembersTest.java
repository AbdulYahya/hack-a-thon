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
        Members.clearAllMembers();
    }

    @Test
    public void newMember_instantiatesCorrectly_boolean() throws Exception {
        Members testMember = setupMember();
        assertTrue(testMember instanceof Members);
    }

    // Getter Methods
    @Test
    public void getStringFirstName_instantiatesWithFirstName_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals("Samwise", testMember.getStringFirstName());
    }

    @Test
    public void getStringLastName_instantiatesWithLastName_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals("Gamgee", testMember.getStringLastName());
    }

    @Test
    public void getStringShortDesc_instantiatesWithShortDesc_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals("New membe", testMember.getStringShortDesc());
    }

    @Test
    public void getIntAge_instantiatesWithAge_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals(19, testMember.getIntAge());
    }
}