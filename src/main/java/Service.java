import java.util.List;
import org.sql2o.*;

public class Service {
  private int id;
  private String service;

  public int getId() {
    return id;
  }

  public String getService() {
    return service;
  }

  public Service(String service) {
    this.service = service;
  }

  @Override
  public boolean equals(Object otherService) {
    if(!(otherService instanceof Service )) {
      return false;
    }
    else {
      Service newService = (Service) otherService;
      return this.getService().equals(newService.getService());
    }
  }

  public static List<Service> all() {
    String sql ="SELECT * FROM services ORDER BY service";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Service.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="INSERT INTO services (service) values (:service)";
      this.id = (int) con.createQuery(sql,true)
          .addParameter("service", this.service)
          .executeUpdate()
          .getKey();
    }
  }

  public static Service find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM services WHERE id=:id";
      Service service = con.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetchFirst(Service.class);
      return service;
    }
  }

  public int count() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM clients where service_id=:id";
      return (int) con.createQuery(sql)
                  .addParameter("id", id)
                  .executeAndFetchFirst(Integer.class);
    }
  }

  public void update(String service) {
    try(Connection con = DB.sql2o.open()) {
       String sql = "UPDATE services SET service=:service WHERE id=:id";
       con.createQuery(sql)
         .addParameter("service", service)
         .addParameter("id", this.id)
         .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM services WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  
}
