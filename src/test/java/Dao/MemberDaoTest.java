package Dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class MemberDaoTest {
    private TeamDao teamDao;
    private MemberDao memberDao;
    private Connection con;

    private Member setupNew() {
        return new Member("Test", "Test", "Testing member", 23, 0);
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
    public void add_instantiatesMemberWithId() throws Exception {
        Member member = setupNew();
        int originalId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalId, member.getId());
    }

    @Test
    public void getAll_returnsAllMembers() throws Exception {
        Member member = setupNew();
        Member otherMember = setupNew();
        memberDao.add(member);
        memberDao.add(otherMember);
        assertEquals(2, memberDao.getAll().size());
        assertTrue(memberDao.getAll().contains(member));
        assertTrue(memberDao.getAll().contains(otherMember));
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