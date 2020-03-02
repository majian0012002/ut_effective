package com.ep;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterGateway extends FileWriter {
    public static boolean disallowAccess = false;

    public FileWriterGateway(String filename) throws IOException {
        super(filename);
        if (disallowAccess) {
            throw new RuntimeException("access disallowed");
        }
    }
}