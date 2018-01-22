package Dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
}
