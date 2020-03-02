package com.ep;

import org.junit.Test;

import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PidWriterTest extends Solitary {
    @Test
    public void writePid() throws Exception {
        RuntimeMXBean bean =
                mock(RuntimeMXBean.class);
        when(bean.getName()).thenReturn("12@X");
        PidWriter.writePid(
                "/tmp/sample.pid", bean);
        assertEquals(
                "12",
                Files.readAllLines(
                        Paths.get("/tmp/sample.pid"),
                        Charset.defaultCharset()).get(0));
    }
}
