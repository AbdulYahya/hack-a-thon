import models.*;

import java.util.HashMap;
import java.util.List;
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
           Team newTeam = new Team(teamName, teamDesc);
           model.put("team", newTeam);
           response.redirect("/teams"); // After user submits form - redirect to /teams
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show all teams
        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = Team.getAll();
            model.put("teams", teams);
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "teams.hbs"));
        });
        // get: show an individual team
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team foundTeam = Team.findById(teamId);
            model.put("team", foundTeam);
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
        // get: show a form to update a team
        get("/teams/:id/update", (request, response) -> {
           Map<String, Object> model = new HashMap<>();
           int updateTeamId = Integer.parseInt(request.params("id"));
           Team updateTeam = Team.findById(updateTeamId);
           model.put("updateTeam", updateTeam);
           return new HandlebarsTemplateEngine().render(new ModelAndView(model, "form.hbs"));
        });
        //  post: process a form to update a team
        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int updateTeamId = Integer.parseInt(request.params("id"));
            Team updateTeam = Team.findById(updateTeamId);
            String teamName = request.queryParams("teamName");
            String teamDesc = request.queryParams("teamDesc");
            updateTeam.setStringName(teamName);
            updateTeam.setStringDescription(teamDesc);
            model.put("updateTeam", updateTeam);
            response.redirect("/teams"); // After user submits form - redirect to /teams
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "team.hbs"));
        });
    }
}
