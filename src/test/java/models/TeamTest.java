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
        assertEquals(3, Team.getAll().size());
    }

    @Test
    public void getAll_getAllContainsAllTeams_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertTrue(Team.getAll().contains(testTeam));
        assertFalse(Team.getAll().contains(otherTestTeam));
    }
}