package za.co.jaredfishy.mslights.application.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LightConnectionHandlerTest {

    private DummyLightConnectionCreator lightConnectionCreator;
    private LightConnectionHandler lightConnectionHandler;

    @Before
    public void setUp() throws Exception {
        lightConnectionCreator = new DummyLightConnectionCreator();
        lightConnectionHandler = new LightConnectionHandler(lightConnectionCreator);
        lightConnectionHandler.getConnection("1");
    }

    @Test
    public void testCreatingConnections() {
        Assert.assertEquals(1, lightConnectionCreator.getConnectionsCreated());
        LightConnection lightConnection = lightConnectionHandler.getConnection("2");
        Assert.assertNotNull(lightConnection);
        Assert.assertEquals(2, lightConnectionCreator.getConnectionsCreated());
    }


}