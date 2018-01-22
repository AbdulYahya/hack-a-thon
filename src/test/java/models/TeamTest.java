package models;

import Dao.TeamDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class TeamTest {
    String connectionString = "jdbc:mem:testing;INIT=RUNSCRIPT FROM 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    TeamDao teamDao = new TeamDao(sql2o);

    private Team setupTeam() {
        return new Team("NeverBrokeAgain", "Young NBA");
    }

    private Member dummyMember() {
        return new Member("Dummy", "Dummy", "Testing purposes", 99);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        teamDao.deleteAll();
    }

    @Test
    public void newTeam_instantiatesCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        assertTrue(testTeam instanceof Team);
    }

    @Test
    public void deleteAllTeams_deletesAllTeams_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        teamDao.deleteAll();
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void deleteTeam_deletesASpecificTeam_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        teamDao.deleteById(testTeam.getId());
        assertEquals(1, teamDao.getAll().size());
        assertEquals(teamDao.getAll().get(0).getId(), 2);
    }

    @Test
    public void findById_returnsCorrectTeam_boolean() throws Exception {
        Team testTeam = setupTeam();
        assertEquals(1, teamDao.findById(testTeam.getId()).getId());
    }

    @Test
    public void findById_returnsCorrectTeamWhenMoreThanOneTeamExists_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertEquals(2, teamDao.findById(otherTestTeam.getId()).getId());
    }

    @Test
    public void findById_returnsCorrectTeamMember_boolean() throws Exception {
        Team testTeam = setupTeam();
        Member testMember = dummyMember();
        Member otherTestMember = dummyMember();
//        testTeam.addMember(testMember);
//        testTeam.addMember(otherTestMember);
        assertTrue(teamDao.getAllMembersByTeam(testTeam.getId()).contains(testMember));
        assertTrue(teamDao.getAllMembersByTeam(testTeam.getId()).contains(otherTestMember));
    }

    // Getter Methods
    @Test
    public void getAll_getAllReturnsTeamsCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void getAll_getAllContainsAllTeams_boolean() throws Exception {
        Team testTeam = setupTeam();
        Team otherTestTeam = setupTeam();
        assertTrue(teamDao.getAll().contains(testTeam));
        assertTrue(teamDao.getAll().contains(otherTestTeam));
    }

    @Test
    public void getAllMembers_getAllMembersReturnsMembersCorrectly_boolean() throws Exception {
        Team testTeam = setupTeam();
        Member testMember =  dummyMember();
//        testTeam.addMember(testMember);
        assertTrue(teamDao.getAllMembersByTeam(testTeam.getId()).contains(testMember));
    }

    @Test
    public void getStringName_getStringNameReturnsName_String() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("NeverBrokeAgain", testTeam.getName());
    }

    @Test
    public void getStringDescription_getStringDescriptionReturnsDesc_String() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("Young NBA", testTeam.getDescription());
    }

    @Test
    public void getId_getIdReturnsTeamId_int() throws Exception {
        teamDao.deleteAll(); // Works without this. will continue to test.
        Team testTeam = setupTeam();
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void getTeamSize_getTeamSizeReturnsNumberOfMembersInATeam_int() throws Exception {
        Team testTeam = setupTeam();
        Member testMember = dummyMember();
        Member otherTestMember = dummyMember();
        Member otherOtherTestMember = dummyMember();
//        testTeam.addMember(testMember);
//        testTeam.addMember(otherTestMember);
//        testTeam.addMember(otherOtherTestMember);
//        assertEquals(3, testTeam.getTeamSize());
    }

    // Setter Methods
    @Test
    public void setStringName_setsNewStringName_string() throws Exception {
        Team testTeam = setupTeam();
        testTeam.setName("Young Test");
        assertEquals("Young Test", testTeam.getName());
    }

    @Test
    public void setStringDescription_setsNewStringDescription_string() throws Exception {
        Team testTeam = setupTeam();
        testTeam.setDescription("Nothing");
        assertEquals("Nothing", testTeam.getDescription());
    }

    @Test
    public void addMember_addsNewMember_List() throws Exception {
        Team testTeam = setupTeam();
        Member member = new Member("Samwise", "Gamgee", "Nothing", 22);
//        testTeam.addMember(member);
        assertTrue(teamDao.getAllMembersByTeam(testTeam.getId()).contains(member));

    }
}