package Dao;

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
    public void getAllMembersByTeam() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteAll() {
    }
}