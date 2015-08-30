import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class ServiceTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Service.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfServiceAretheSame() {
    Service firstService = new Service("Clipper Cut");
    Service secondService = new Service("Clipper Cut");
    assertTrue(firstService.equals(secondService));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Service myService = new Service("Classic Blowout");
    myService.save();
    assertTrue(Service.all().get(0).equals(myService));
  }

  @Test
  public void find_findServiceInDatabase_true() {
    Service myService = new Service("Classic Blowout");
    myService.save();
    Service savedService = Service.find(myService.getId());
    assertTrue(myService.equals(savedService));
  }

  @Test
  public void count_returnCorrectClientCountFromDatabase_IntCount() {
    Service myService = new Service("Classic Haircut");
    myService.save();
    Stylist myStylist = new Stylist("Mia", "Green");
    myService.save();
    Client firstClient = new Client("Dave", "Minion", myStylist.getId(), myService.getId());
    firstClient.save();
    Client secondClient = new Client("Phil", "Minion", myStylist.getId(), myService.getId());
    secondClient.save();
    assertEquals(2, myService.count());
  }

  @Test
  public void update_returnUpdatedServiceFromDatabase_true() {
    Service myService = new Service("Color-Permanent");
    myService.save();
    int id = myService.getId();
    myService.update("Clipper Cut");
    assertEquals("Clipper Cut", Service.all().get(0).getService());
  }

    @Test
    public void delete_deleteServiceFromDatabase_true() {
      Service firstService = new Service("Clipper Cut");
      firstService.save();
      Service secondService = new Service("Special Occasion Style");
      secondService.save();
      secondService.delete();
      assertTrue(!Service.all().contains(secondService));
    }

}
