package netty;

import org.junit.Test;

public class CoreTest {

    @Test
    public void timeServerTest() throws Exception {
        new TimeServer().bind(8080);
    }
    
    @Test
    public void timeClientTest() throws Exception {
        new TimeClient().connect(8080, "127.0.0.1");
    }
    
}