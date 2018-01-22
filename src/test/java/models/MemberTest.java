package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
    private Member setupMember() {
        return new Member("Samwise", "Gamgee", "New member", 20);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Member.deleteAllMembers();
    }

    @Test
    public void newMember_instantiatesCorrectly_boolean() throws Exception {
        Member testMember = setupMember();
        assertTrue(testMember instanceof Member);
    }

    @Test
    public void deleteAllMember_deletesAllMember_boolean() throws Exception {
        Member testMember = setupMember();
        Member otherTestMember = setupMember();
        Member.deleteAllMembers();
        assertEquals(0, Member.getAll().size());
    }

    @Test
    public void deleteMember_deletesASpecificMember_boolean() throws Exception {
        Member testMember = setupMember();
        Member otherTestMember = setupMember();
        testMember.deleteMember();
        assertEquals(1, Member.getAll().size());
        assertEquals(Member.getAll().get(0).getId(), 2);
    }

    @Test
    public void findById_returnsCorrectMember_boolean() throws Exception {
        Member testMember = setupMember();
        assertEquals(1, Member.findById(testMember.getId()).getId());
    }

    @Test
    public void findById_returnsCorrectTeamWhenMoreThanOneTeamExists_boolean() throws Exception {
        Member testMember = setupMember();
        Member otherTestMember = setupMember();
        assertEquals(2, Member.findById(otherTestMember.getId()).getId());
    }

    // Getter Methods
    @Test
    public void getAll_getAllReturnsMemberCorrectly_boolean() throws Exception {
        Member testMember = setupMember();
        Member otherTestMember = setupMember();
        assertEquals(2, Member.getAll().size());
    }

    @Test
    public void getAll_getAllContainsAllMember_boolean() throws Exception {
        Member testMember = setupMember();
        Member otherTestMember = setupMember();
        assertTrue(Member.getAll().contains(testMember));
        assertTrue(Member.getAll().contains(otherTestMember));
    }

    @Test
    public void getStringFirstName_instantiatesWithFirstName_boolean() throws Exception {
        Member testMember = setupMember();
        assertEquals("Samwise", testMember.getStringFirstName());
    }

    @Test
    public void getStringLastName_instantiatesWithLastName_boolean() throws Exception {
        Member testMember = setupMember();
        assertEquals("Gamgee", testMember.getStringLastName());
    }

    @Test
    public void getStringShortDesc_instantiatesWithShortDesc_boolean() throws Exception {
        Member testMember = setupMember();
        assertEquals("New member", testMember.getStringShortDesc());
    }

    @Test
    public void getIntAge_instantiatesWithAge_boolean() throws Exception {
        Member testMember = setupMember();
        assertEquals(20, testMember.getIntAge());
    }

    @Test
    public void getId_getIdReturnsMemberId_int() throws Exception {
        Member.deleteAllMembers(); // Works without. Will continue to test
        Member testMember = setupMember();
        assertEquals(1, testMember.getId());
    }

    @Test
    public void getMemberInitials_getsMemberInitials_String() throws Exception {
        Member testMember = setupMember();
        assertEquals("SG", testMember.getStringBuilderMemberInitials().toString());
    }
    // Setter Methods
    @Test
    public void setStringFirstName_setsNewringFirstName_String() throws Exception {
        Member testMember = setupMember();
        testMember.setStringFirstName("Young");
        assertEquals("Young", testMember.getStringFirstName());
    }

    @Test
    public void setStringLastName_setsNewStringLastName_String() throws Exception {
        Member testMember = setupMember();
        testMember.setStringLastName("NBA");
        assertEquals("NBA", testMember.getStringLastName());
    }

    @Test
    public void setStringShortDesc_setsNewStringShortDesc_String() throws Exception {
        Member testMember = setupMember();
        testMember.setStringShortDesc("Never Broke Again");
        assertEquals("Never Broke Again", testMember.getStringShortDesc());
    }
}