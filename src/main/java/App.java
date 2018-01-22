import Dao.MemberDao;
import Dao.TeamDao;
import models.*;

import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/abduls-hack-a-thon.db;INIT=RUNSCRIPT FROM 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        TeamDao teamDao = new TeamDao(sql2o);
        MemberDao memberDao = new MemberDao(sql2o);

        // Root Route
        get("/", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
        });
        // get: show new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "form.hbs"));
        });
        // post: process new team form
        post("/teams/new", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           String teamName = request.queryParams("teamName");
           String teamDesc = request.queryParams("teamDesc");
           Team team = new Team(teamName, teamDesc);
           teamDao.add(team);
           model.put("teams", teamDao.getAll());
           response.redirect("/teams");
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: delete all teams
        get("/teams/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteAll();
            teamDao.deleteAll();
            response.redirect("/teams");
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show new member form
        get("/teams/:id/m/new", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           model.put("team", teamDao.findById(teamId));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "member-form.hbs"));
        });
        // post: process new member form
        post("/teams/:id/m/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            String firstName = request.queryParams("memberFirstName");
            String lastName = request.queryParams("memberLastName");
            String description = request.queryParams("memberShortDesc");
            int age = Integer.parseInt(request.queryParams("memberAge"));
            Member member = new Member(firstName, lastName, description, age, teamId);
            memberDao.add(member);
            response.redirect("/teams/" + teamId);
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show all teams
        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("teams", teamDao.getAll());
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show an individual team
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            model.put("team", teamDao.findById(teamId));
            model.put("members", teamDao.getAllMembersByTeam(teamId));
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show an individual member
        get("/teams/:id/m/:userId", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           int userId = Integer.parseInt(request.params("userId").replaceAll("[^0-9]", ""));
           model.put("team", teamDao.findById(teamId));
           model.put("member", memberDao.findById(userId));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "member.hbs"));
        });
        // get: show a form to update a team
        get("/teams/:id/update", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           model.put("team", teamDao.findById(teamId));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        //  post: process a form to update a team
        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            String teamName = request.queryParams("teamName");
            String teamDesc = request.queryParams("teamDesc");
            teamDao.update(teamId, teamName, teamDesc);
            model.put("team", teamDao.findById(teamId));
            model.put("members", teamDao.getAllMembersByTeam(teamId));
            response.redirect("/teams/" + teamId);
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // post: process form to update a team member
        post("/teams/:id/m/:userId/update", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           int userId = Integer.parseInt(request.params("userId").replaceAll("[^0-9]", ""));
           String memberFirstName = request.queryParams("memberFirstName");
           String memberLastName = request.queryParams("memberLastName");
           String memberDesc = request.queryParams("memberDesc");
           int memberAge = Integer.parseInt(request.queryParams("memberAge"));
           memberDao.update(userId, memberFirstName, memberLastName, memberDesc, memberAge, teamId);
           model.put("team", teamDao.findById(teamId));
           model.put("member", memberDao.findById(userId));
           response.redirect("/teams/" + teamId + "/m/" + memberDao.findById(userId).getFirstName() + userId);
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "member.hbs"));
        });
        // get: delete a team
        get("/teams/:id/delete", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           teamDao.deleteById(teamId);
           response.redirect("/teams");
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: delete a member
        get("/teams/:id/m/:userId/delete", (request, response) -> {
          Map<String, Object> model = new HashMap<>();
          int teamId = Integer.parseInt(request.params("id"));
          int userId = Integer.parseInt(request.params("userId").replaceAll("[^0-9]", ""));
          memberDao.deleteById(memberDao.findById(userId).getId());
          response.redirect("/teams/" + teamId);
          return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
    }
}
