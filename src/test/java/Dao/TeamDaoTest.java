package Dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class TeamDaoTest {
    private TeamDao teamDao;
    private MemberDao memberDao;
    private Connection con;

    public Team setupNew() {
        return new Team("Test", "Test Description");
    }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT FROM 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new TeamDao(sql2o);
        memberDao = new MemberDao(sql2o);

        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add_instantiatesTeamWithId() throws Exception {
        Team team = setupNew();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void getAll_returnsAllTeams() throws Exception {
        Team team = setupNew();
        Team otherTeam = setupNew();
        teamDao.add(team);
        teamDao.add(otherTeam);
        assertEquals(2, teamDao.getAll().size());
        assertTrue(teamDao.getAll().contains(team));
        assertTrue(teamDao.getAll().contains(otherTeam));
    }

    @Test
    public void getAll_returnsNothingIfNoTeamsExist() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeam_returnsAllMembersByTeamId() throws Exception {
        Team team = setupNew();
        teamDao.add(team);
        int teamId = team.getId();
        Member member = new Member("Samwise", "Gamgee", "Test", 20, teamId);
        Member otherMember = new Member("Young", "NBA", "Artist", 22, teamId);
        Member otherOtherMember = new Member("Doesn't", "Return", "Is not added to team", 0, teamId);
        memberDao.add(member);
        memberDao.add(otherMember);

        assertEquals(2, teamDao.getAllMembersByTeam(teamId).size());
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(member));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(otherMember));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(otherOtherMember));
    }

    @Test
    public void findById_existingTeamsFoundById() throws Exception {
        Team team = setupNew();
        Team otherTeam = setupNew();
        teamDao.add(team);
        teamDao.add(otherTeam);
        assertEquals(team, teamDao.findById(team.getId()));
    }

    @Test
    public void update_updatesSpecifiedTeam() throws Exception {
        Team team = setupNew();
        teamDao.add(team);
        teamDao.update(team.getId(), "Updated Team", "");
        assertNotEquals(team.getName(), teamDao.findById(team.getId()).getName());
    }

    @Test
    public void deleteById_deletesSpecifiedTeam() throws Exception {
        Team team = setupNew();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void deleteAll() {
    }
}