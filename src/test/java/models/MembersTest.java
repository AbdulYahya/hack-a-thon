package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MembersTest {
    private Members setupMember() {
        return new Members("Samwise", "Gamgee", "New member", 20);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Members.deleteAllMembers();
    }

    @Test
    public void newMember_instantiatesCorrectly_boolean() throws Exception {
        Members testMember = setupMember();
        assertTrue(testMember instanceof Members);
    }

    @Test
    public void deleteAllMembers_deletesAllMembers_boolean() throws Exception {
        Members testMember = setupMember();
        Members otherTestMember = setupMember();
        Members.deleteAllMembers();
        assertEquals(0, Members.getAll().size());
    }

    @Test
    public void deleteMember_deletesASpecificMember_boolean() throws Exception {
        Members testMember = setupMember();
        Members otherTestMember = setupMember();
        testMember.deleteMember();
        assertEquals(1, Members.getAll().size());
        assertEquals(Members.getAll().get(0).getId(), 2);
    }

    @Test
    public void findById_returnsCorrectMember_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals(1, Members.findById(testMember.getId()).getId());
    }

    @Test
    public void findById_returnsCorrectTeamWhenMoreThanOneTeamExists_boolean() throws Exception {
        Members testMember = setupMember();
        Members otherTestMember = setupMember();
        assertEquals(2, Members.findById(otherTestMember.getId()).getId());
    }

    // Getter Methods
    @Test
    public void getAll_getAllReturnsMembersCorrectly_boolean() throws Exception {
        Members testMember = setupMember();
        Members otherTestMember = setupMember();
        assertEquals(2, Members.getAll().size());
    }

    @Test
    public void getAll_getAllContainsAllMembers_boolean() throws Exception {
        Members testMember = setupMember();
        Members otherTestMember = setupMember();
        assertTrue(Members.getAll().contains(testMember));
        assertTrue(Members.getAll().contains(otherTestMember));
    }

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
        assertEquals("New member", testMember.getStringShortDesc());
    }

    @Test
    public void getIntAge_instantiatesWithAge_boolean() throws Exception {
        Members testMember = setupMember();
        assertEquals(20, testMember.getIntAge());
    }

    @Test
    public void getId_getIdReturnsMemberId_int() throws Exception {
        Members.deleteAllMembers(); // Works without. Will continue to test
        Members testMember = setupMember();
        assertEquals(1, testMember.getId());
    }

    @Test
    public void getMemberInitials_getsMemberInitials_String() throws Exception {
        Members testMember = setupMember();
        assertEquals("SG", testMember.getStringBuilderMemberInitials().toString());
    }
    // Setter Methods
    @Test
    public void setStringFirstName_setsNewringFirstName_String() throws Exception {
        Members testMember = setupMember();
        testMember.setStringFirstName("Young");
        assertEquals("Young", testMember.getStringFirstName());
    }

    @Test
    public void setStringLastName_setsNewStringLastName_String() throws Exception {
        Members testMember = setupMember();
        testMember.setStringLastName("NBA");
        assertEquals("NBA", testMember.getStringLastName());
    }

    @Test
    public void setStringShortDesc_setsNewStringShortDesc_String() throws Exception {
        Members testMember = setupMember();
        testMember.setStringShortDesc("Never Broke Again");
        assertEquals("Never Broke Again", testMember.getStringShortDesc());
    }
}