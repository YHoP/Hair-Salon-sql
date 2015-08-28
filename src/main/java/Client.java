import java.util.*;
import org.sql2o.*;
import java.text.ParseException;

public class Client {
  private int id;
  private String first_name, last_name;
  private int stylist_id, service_id;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public int getServiceId() {
    return service_id;
  }

  public Client(String first_name, String last_name, int stylist_id, int service_id) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.stylist_id = stylist_id;
    this.service_id = service_id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getFirstName().equals(newClient.getFirstName()) &&
             this.getFirstName().equals(newClient.getFirstName()) &&
             this.getLastName().equals(newClient.getLastName()) &&
             this.getStylistId() == newClient.getStylistId() &&
             this.getServiceId() == newClient.getServiceId();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients ORDER BY first_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
 }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (first_name, last_name, stylist_id, service_id) VALUES (:first_name, :last_name, :stylist_id, :service_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("stylist_id", stylist_id)
        .addParameter("service_id", service_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public String getStylistName() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT first_name FROM stylists where id=:stylist_id";
      return (String) con.createQuery(sql)
        .addParameter("stylist_id", this.stylist_id)
        .executeAndFetchFirst(String.class);
    }
  }

  public String getServiceDescription() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT service FROM services where id=:service_id";
      return (String) con.createQuery(sql)
        .addParameter("service_id", this.service_id)
        .executeAndFetchFirst(String.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void update(String first_name, String last_name, int stylist_id, int service_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET first_name=:first_name, last_name=:last_name, stylist_id=:stylist_id, service_id=:service_id WHERE id=:id";
      con.createQuery(sql)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("stylist_id", stylist_id)
        .addParameter("service_id", service_id)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
