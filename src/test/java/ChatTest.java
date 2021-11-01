import com.sun.org.apache.xpath.internal.operations.NotEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;


public class ChatTest {
    private BufferedReader in = null;

    @Before
    public void setup() throws IOException {
        in = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/data.txt")));
    }

    @After
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
