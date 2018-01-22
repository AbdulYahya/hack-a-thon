package Dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class TeamDao implements iTeamDao {
    private final Sql2o sql2o;

    public TeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Team team) {

    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public List<Member> getAllMembersByTeam(int teamId) {
        return null;
    }

    @Override
    public Team findById(int id) {
        return null;
    }

     @Override
    public void update(int id, String name, String description) {

     }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
