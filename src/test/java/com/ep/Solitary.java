package com.ep;

import org.junit.Before;

public class Solitary {
    @Before
    public void setup() {
        FileWriterGateway.disallowAccess = true;
    }
}