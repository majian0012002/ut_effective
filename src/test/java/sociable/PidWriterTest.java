package sociable;

import com.ep.PidWriter;
import org.junit.Test;

import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PidWriterTest extends Sociable {
    @Test
    public void writePid() throws Exception {
        RuntimeMXBean bean =
                mock(RuntimeMXBean.class);
        when(bean.getName()).thenReturn("12@X");
        PidWriter.writePid(
                "/tmp/wewut/sample.pid", bean);
        assertEquals(
                "12",
                Files.readAllLines(
                        Paths.get("/tmp/wewut/sample.pid"),
                        Charset.defaultCharset()).get(0));
    }
}