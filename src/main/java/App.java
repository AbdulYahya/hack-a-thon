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
           new Team(teamName, teamDesc);
           model.put("teams", teamDao.getAll());
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
            Team team = teamDao.findById(teamId);
            String firstName = request.queryParams("memberFirstName");
            String lastName = request.queryParams("memberLastName");
            String shortDesc = request.queryParams("memberShortDesc");
            int age = Integer.parseInt(request.queryParams("memberAge"));
            Member member = new Member(firstName, lastName, shortDesc, age);
            memberDao.add(member);
            model.put("team", team);
            model.put("members", teamDao.getAllMembersByTeam(teamId));
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
            Team team = teamDao.findById(teamId);
            model.put("team", team);
            model.put("members", teamDao.getAllMembersByTeam(teamId));
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show an individual member
        get("/teams/:id/m/:userId", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           int userId = Integer.parseInt(request.params("userId").substring(2));
           model.put("team", teamDao.findById(teamId));
           model.put("member", Member.findById(userId));
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
            Team updateTeam = teamDao.findById(teamId);
            String teamName = request.queryParams("teamName");
            String teamDesc = request.queryParams("teamDesc");
            updateTeam.setName(teamName);
            updateTeam.setDescription(teamDesc);
            model.put("team", updateTeam);
            model.put("members", teamDao.getAllMembersByTeam(teamId));
            response.redirect("/teams/" + teamId);
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });

        // get: process a form to update a team member
        get("/teams/:id/m/:userId/update", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           int userId = Integer.parseInt(request.params("userId").substring(2));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
        });
    }
}
