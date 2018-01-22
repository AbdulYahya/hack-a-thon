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
    public void findById_existingMembersFoundById() throws Exception {
        Member member = setupNew();
        Member otherMember = setupNew();
        memberDao.add(member);
        memberDao.add(otherMember);
        assertEquals(member, memberDao.findById(member.getId()));
    }

    @Test
    public void findById_returnsCorrectMemberWhenMoreThanOneMemberExists() throws Exception {
        Member member = setupNew();
        Member otherMember = setupNew();
        memberDao.add(member);
        memberDao.add(otherMember);
        assertEquals(2, memberDao.findById(otherMember.getId()).getId());
    }

    @Test
    public void update_updatesSpecifiedMember() throws Exception {
        Member member = setupNew();
        memberDao.add(member);
        memberDao.update(member.getId(), "Updated", "Updated", "Testing update method", 24, 1);
        assertNotEquals(member.getFirstName(), memberDao.findById(member.getId()).getFirstName());
        assertNotEquals(member.getLastName(), memberDao.findById(member.getId()).getLastName());
        assertNotEquals(member.getDescription(), memberDao.findById(member.getId()).getDescription());
    }

    @Test
    public void deleteById_deletesSpecifiedMember() throws Exception {
        Member member = setupNew();
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void deleteAll_deletesAllMembers() throws Exception {
        Member member = setupNew();
        Member otherMember = setupNew();
        memberDao.add(member);
        memberDao.add(otherMember);
        int memberSize = memberDao.getAll().size();
        memberDao.deleteAll();
        assertTrue(memberSize > 0 && memberSize > memberDao.getAll().size());
    }
}