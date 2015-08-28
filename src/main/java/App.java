import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;

public class App {
  public static void main(String[] args) {

  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylists", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/stylists.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/new", (request, response) -> {
    HashMap<String,Object> model = new HashMap<String, Object>();
    String first_name = request.queryParams("first_name");
    String last_name = request.queryParams("last_name");
    Stylist newStylist = new Stylist(first_name, last_name);
    newStylist.save();
    model.put("stylists", Stylist.all());
    response.redirect("/stylists");
    return null;
  });

  get("/stylists/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist thisStylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", thisStylist);
    model.put("stylists", Stylist.all());
    model.put("services", Service.all());
    model.put("clients", thisStylist.getClients());
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/:id/newClient", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int stylist_id = Integer.parseInt(request.params(":id"));
    Stylist thisStylist = Stylist.find(stylist_id);
    String first_name = request.queryParams("first_name");
    String last_name = request.queryParams("last_name");
    int service_id = Integer.parseInt(request.queryParams("service_id"));
    Client newClient = new Client(first_name, last_name, stylist_id, service_id);
    newClient.save();
    model.put("stylist", thisStylist);
    model.put("services", Service.all());
    model.put("clients", thisStylist.getClients());
    response.redirect("/stylists/" + stylist_id);
    return null;
  });

  get("/stylists/:id/edit", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Stylist thisStylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", thisStylist);
    model.put("template", "templates/stylist_update.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int stylist_id = Integer.parseInt(request.params(":id"));
    Stylist thisStylist = Stylist.find(stylist_id);
    String first_name = request.queryParams("first_name");
    String last_name = request.queryParams("last_name");
    thisStylist.update(first_name, last_name);
    model.put("stylist", thisStylist);
    response.redirect("/stylists/" + stylist_id);
    return null;
  });

  get("/clients", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("clients", Client.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/clients/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Client thisClient = Client.find(Integer.parseInt(request.params(":id")));
    model.put("client", thisClient);
    model.put("stylists", Stylist.all());
    model.put("services", Service.all());
    model.put("template", "templates/client_update.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/clients/:id/edit", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int client_id = Integer.parseInt(request.params(":id"));
    Client thisClient = Client.find(client_id);
    model.put("client", thisClient);
    model.put("stylists", Stylist.all());
    model.put("services", Service.all());
    response.redirect("/clients/" + client_id);
    return null;
  });

  post("/clients/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int client_id = Integer.parseInt(request.params(":id"));
    Client thisClient = Client.find(client_id);
    String first_name = request.queryParams("first_name");
    String last_name = request.queryParams("last_name");
    int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
    int service_id = Integer.parseInt(request.queryParams("service_id"));
    thisClient.update(first_name, last_name, stylist_id, service_id);
    model.put("client", thisClient);
    model.put("stylists", Stylist.all());
    model.put("services", Service.all());
    response.redirect("/stylists/" + stylist_id);
    return null;
  });

  get("/clients/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    int client_id = Integer.parseInt(request.params(":id"));
    Client thisClient = Client.find(client_id);
    int stylist_id = thisClient.getStylistId();
    Stylist thisStylist = Stylist.find(stylist_id);
    thisClient.delete();
    model.put("stylist", thisStylist);
    model.put("services", Service.all());
    model.put("clients", thisStylist.getClients());
    response.redirect("/stylists/" + stylist_id);
    return null;
  });

  get("/services", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("services", Service.all());
    model.put("template", "templates/services.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/services/new", (request, response) -> {
    HashMap<String,Object> model = new HashMap<String, Object>();
    String service = request.queryParams("service");
    Service newService = new Service(service);
    newService.save();
    model.put("services", Service.all());
    response.redirect("/services");
    return null;
  });

  }
}
