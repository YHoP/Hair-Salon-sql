import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistAretheSame() {
    Stylist firstStylist = new Stylist("Bob", "Minion");
    Stylist secondStylist = new Stylist("Bob", "Minion");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Stuart", "Minion");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Kevin", "Minion");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesALlClientsFromDatabase_clientsList() {
    Stylist myStylist = new Stylist("Dave", "Minion");
    myStylist.save();
    Service myService = new Service("Clipper Cut");
    myService.save();
    Client firstClient = new Client("Dave", "Minion", myStylist.getId(), myService.getId());
    firstClient.save();
    Client secondClient = new Client("Jorge", "Minion", myStylist.getId(), myService.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void count_returnCorrectClientCountFromDatabase_IntCount() {
    Stylist myStylist = new Stylist("Dave", "Minion");
    myStylist.save();
    Service myService = new Service("Clipper Cut");
    myService.save();
    Client firstClient = new Client("Dave", "Minion", myStylist.getId(), myService.getId());
    firstClient.save();
    Client secondClient = new Client("Jorge", "Minion", myStylist.getId(), myService.getId());
    secondClient.save();
    assertEquals(2, myStylist.count());
  }

  @Test
  public void update_returnUpdatedNameFromDatabase_true() {
    Stylist myStylist = new Stylist("Mark", "Minion");
    myStylist.save();
    int id = myStylist.getId();
    myStylist.update("Phil", "Minion");
    assertEquals("Phil", Stylist.all().get(0).getFirstName());
  }

    @Test
    public void delete_deleteStylistFromDatabase_true() {
      Stylist firstStylist = new Stylist("Mark", "Minion");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Bob", "Minion");
      secondStylist.save();
      secondStylist.delete();
      assertTrue(!Stylist.all().contains(secondStylist));
    }

}
