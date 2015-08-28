import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String first_name, last_name;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public Stylist(String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist )) {
      return false;
    }
    else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getId() == newStylist.getId() &&
             this.getFirstName().equals(newStylist.getFirstName()) &&
             this.getLastName().equals(newStylist.getLastName());
    }
  }

  public static List<Stylist> all() {
    String sql ="SELECT * FROM stylists ORDER BY first_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO stylists (first_name, last_name) VALUES (:first_name, :last_name)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("first_name", this.first_name)
      .addParameter("last_name", this.last_name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id";
      return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Client.class);
    }
  }

  public int count() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM clients where stylist_id=:id";
      return (int) con.createQuery(sql)
                  .addParameter("id", id)
                  .executeAndFetchFirst(Integer.class);
    }
  }

  public void update(String first_name, String last_name) {
    try(Connection con = DB.sql2o.open()) {
       String sql = "UPDATE stylists SET first_name=:first_name, last_name=:last_name WHERE id=:id";
       con.createQuery(sql)
         .addParameter("first_name", first_name)
         .addParameter("last_name", last_name)
         .addParameter("id", this.id)
         .executeUpdate();
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

}
