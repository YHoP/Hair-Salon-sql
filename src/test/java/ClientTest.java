import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientAretheSame() {
    Client firstClient = new Client("Bob", "Minion", 1, 1);
    Client secondClient = new Client("Bob", "Minion", 1, 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("Stuart", "Minion", 1, 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void find_findClientInDatabase_true() {
    Client myClient = new Client("Kevin", "Minion", 1, 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void getStylistName_retrievesStylistFirstNameFromDatabase_String() {
    Stylist myStylist = new Stylist("Dave", "Minion");
    myStylist.save();
    Service myService = new Service("Clipper Cut");
    myService.save();
    Client myClient = new Client("Dave", "Minion", myStylist.getId(), myService.getId());
    myClient.save();
    assertEquals(myStylist.getFirstName(), myClient.getStylistName());
  }

  @Test
  public void getServiceDescription_retrievesServiceDescriptionFromDatabase_String() {
    Stylist myStylist = new Stylist("Dave", "Minion");
    myStylist.save();
    Service myService = new Service("Clipper Cut");
    myService.save();
    Client myClient = new Client("Dave", "Minion", myStylist.getId(), myService.getId());
    myClient.save();
    assertEquals(myService.getService(), myClient.getServiceDescription());
  }

  @Test
  public void update_returnUpdatedNameFromDatabase_true() {
    Client myClient = new Client("Mark", "Minion", 1, 1);
    myClient.save();
    int id = myClient.getId();
    myClient.update("Phil", "Minion", 1, 1);
    assertEquals("Phil", Client.all().get(0).getFirstName());
  }

  @Test
  public void delete_deleteClientFromDatabase_true() {
    Client firstClient = new Client("Mark", "Minion", 1, 1);
    firstClient.save();
    Client secondClient = new Client("Bob", "Minion", 1, 1);
    secondClient.save();
    firstClient.delete();
    assertTrue(!Client.all().contains(firstClient));
  }

}
