package Dao;

import models.Member;
import models.Team;

import java.util.List;

public interface iTeamDao {
    void add(Team team);

    List<Team> getAll();
    List<Member> getAllMembersByTeam(int teamId);

    Team findById(int id);

    void update(int id, String name, String description);

    void deleteById(int id);
    void deleteAll();
}
