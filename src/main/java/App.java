import models.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        /*
         *            CRUD
         *   Create Read Update Delete
         *
         *   Route Handler Order
         *       get: show new team form
         *       post: process new team form
         *       get: show all teams
         *       get: show an individual team
         *       get: show a form to update a team
         *       post: process a form to update a team
         *       get: delete an individual team
         *       get: delete all team
         * */

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
           model.put("teams", Team.getAll());
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show new member form
        get("/teams/:id/member/new", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           model.put("team", Team.findById(teamId));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "member-form.hbs"));
        });
        // post: process new member form
        post("/teams/:id/member/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team team = Team.findById(teamId);
            String firstName = request.queryParams("memberFirstName");
            String lastName = request.queryParams("memberLastName");
            String shortDesc = request.queryParams("memberShortDesc");
//            String addMember = request.queryParams("inlineRadioOptions");
            int age = Integer.parseInt(request.queryParams("memberAge"));
            Members member = new Members(firstName, lastName, shortDesc, age);
            team.addMember(member);
            model.put("team", team);
            model.put("members", team.getAllMembers());
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show all teams
        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("teams", Team.getAll());
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show an individual team
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team team = Team.findById(teamId);
            model.put("team", team);
            model.put("members", team.getAllMembers());
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show a form to update a team
        get("/teams/:id/update", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int teamId = Integer.parseInt(request.params("id"));
           model.put("team", Team.findById(teamId));
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        //  post: process a form to update a team
        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team updateTeam = Team.findById(teamId);
            String teamName = request.queryParams("teamName");
            String teamDesc = request.queryParams("teamDesc");
            updateTeam.setStringName(teamName);
            updateTeam.setStringDescription(teamDesc);
            model.put("team", updateTeam);
            model.put("members", updateTeam.getAllMembers());
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
    }
}
