package api;


import Utils.Verify;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;

public class CommonApiTest {
    public ObjectMapper mapper;
    public Verify verify;

    @BeforeClass(alwaysRun = true)
    public synchronized void setupBeforeClass() {
        verify = new Verify();
        mapper = new ObjectMapper();
    }
}