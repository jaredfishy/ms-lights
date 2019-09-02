package za.co.jaredfishy.mslights.application.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.light.Light;

import java.net.DatagramSocket;
import java.util.List;
import java.util.Map;

@Ignore
public class LightDiscoveryServiceTest {

    private static final int PORT = 7000;
    private DatagramSocket socketOut, socketIn;
    private LightDiscoveryService lightDiscoveryService;

    @Before
    public void setUp() {
        lightDiscoveryService = new LightDiscoveryService();

        // TODO: test Datagram Sockets correctly
//        socketOut = new DatagramSocket(PORT, addressOut);
//        socketIn = new DatagramSocket(PORT+1);
//        socketOut.send(new DatagramPacket(Payload, Payload.length, addressOut, PORT+1 ));
//        socketIn.receive(new DatagramPacket(bufferIn, bufferIn.length));
    }

    @Test
    public void testNic() throws Exception {
        lightDiscoveryService.getSocket();
    }

    @Test
    public void discover() {
        List<Light> lights = lightDiscoveryService.discover();
        Assert.assertNotNull(lights);
    }
}