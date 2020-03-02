package com.ep;

import org.junit.Test;

import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PidWriterTest extends Solitary {
    @Test
    public void writePid() throws Exception {
        RuntimeMXBean bean =
                mock(RuntimeMXBean.class);
        when(bean.getName()).thenReturn("12@X");
        FileWriterGateway facade =
                mock(FileWriterGateway.class);
        PidWriter.writePid(facade, bean);
        verify(facade).write("12");
    }
}
