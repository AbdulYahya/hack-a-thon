package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    public Team setupTeam() {
        return new Team("NeverBrokeAgain", "Young NBA");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    @Test
    public void newTeam_instantiatesCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        assertTrue(testTeam instanceof Team);
    }

    // Getter Methods
    @Test
    public void getAll_getAllReturnsTeamsCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertEquals(2, Team.getAll().size());
    }

    @Test
    public void getAll_getAllContainsAllTeams_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertTrue(Team.getAll().contains(testTeam));
        assertTrue(Team.getAll().contains(otherTestTeam));
    }

    @Test
    public void getAllMembers_getAllMembersReturnsMembersCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        Members testMember = new Members("Sam", "Wise", "Nothing", 20);
        testTeam.getAllMembers().add(testMember);
        assertTrue(testTeam.getAllMembers().contains(testMember));
    }

    @Test
    public void getStringName_getStringNameReturnsName_String() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("NeveBrokeAgain", testTeam.getStringName());
    }
}