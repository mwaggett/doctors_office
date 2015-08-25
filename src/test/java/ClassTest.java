import org.junit.*;
import static org.junit.Assert.*;
// import anything else needed

public class ClassTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Class.all().size());
  }

  // @Test
  // public void methodName_whatIsBeingTested_desiredResult() {
  //   Class instance = new Class();
  //   assertEquals(expectedValue, instance.methodName(param));
  // }

}
