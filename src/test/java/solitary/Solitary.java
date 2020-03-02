package solitary;

import com.ep.FileWriterGateway;
import org.junit.Before;

public class Solitary {
    @Before
    public void setup() {
        FileWriterGateway.disallowAccess = true;
    }
}