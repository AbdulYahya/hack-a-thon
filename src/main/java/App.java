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
         *       get: show all team
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
    }
}
