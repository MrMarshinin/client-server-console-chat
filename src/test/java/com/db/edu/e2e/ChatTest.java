package com.db.edu.e2e;

//import com.sun.org.apache.xpath.internal.operations.NotEquals;
import jdk.nashorn.internal.ir.annotations.Ignore;
//import org.junit.After;
//import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.verify;

@Disabled
public class ChatTest {
    private BufferedReader in = null;

    @BeforeEach
    public void setup() throws IOException {
        in = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/data.txt")));
    }

    @AfterEach
    public void teardown() throws IOException {
        if (in != null) {
            in.close();
        }
        in = null;
    }

    @Test
    public void testReadFromFile() throws IOException {
        String line = in.readLine();


    }

}
