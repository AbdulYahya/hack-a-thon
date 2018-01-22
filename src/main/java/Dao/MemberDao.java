package Dao;

import models.Member;
import org.sql2o.Sql2o;

public class MemberDao implements iMemberDao {
    private final Sql2o sql2o;

    public MemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {

    }
}
