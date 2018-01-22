package Dao;

import models.Member;

import java.util.List;

public interface iMemberDao {
    void add(Member member);

    List<Member> getAll();

    Member findById(int id);

    void update(int id, String firstName, String lastName, String description, int age, int teamId);

    void deleteById(int id);
    void deleteAll();
}
