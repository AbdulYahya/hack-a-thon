package Dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class MemberDao implements iMemberDao {
    private final Sql2o sql2o;

    public MemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (firstName, lastName, description, age, teamId) VALUES (:firstName, :lastName, :description, :age, :teamId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("firstName", member.getFirstName())
                    .addParameter("lastName", member.getLastName())
                    .addParameter("description", member.getDescription())
                    .addParameter("age", member.getAge())
                    .addParameter("teamId", member.getTeamId())
                    .addColumnMapping("FIRSTNAME", "firstName")
                    .addColumnMapping("LASTNAME", "lastName")
                    .addColumnMapping("DESCRIPTION", "description")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("TEAMID", "teamId")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public Member findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void update(int id, String firstName, String lastName, String description, int age, int teamId) {
        String sql = "UPDATE members SET firstName = :firstName, lastName = :lastName, description = :description, age = :age, teamId = :teamId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("firstName", firstName)
                    .addParameter("lastName", lastName)
                    .addParameter("description", description)
                    .addParameter("age", age)
                    .addParameter("teamId", teamId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM members WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {

    }
}
