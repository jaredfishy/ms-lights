package za.co.jaredfishy.mslights.application.domain.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.service.LightConnection;
import za.co.jaredfishy.mslights.application.util.RGBCalculator;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class LightCommandTest {

    private LightConnection lightConnection;

    @Before
    public void setUp() throws Exception {
        lightConnection = new LightConnection("192.168.0.101");
    }

    @Test
    public void testSwitchOn() {
        lightConnection.send(new SetPowerCommand(true, CommandEffect.SMOOTH, 1000));
    }

    @Test
    public void testSwitchOff() {
        lightConnection.send(new SetPowerCommand(false, CommandEffect.SUDDEN, 250));
    }


    @Test
    public void testSetRGB() {
        lightConnection.send(new SetRGBCommand(RGBCalculator.getColor(0, 0, 255), CommandEffect.SMOOTH, 250));
    }

    @Test
    public void testStartFlowCommand() {

        List<FlowExpression> flowExpressions = new ArrayList<>();
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(255, 0, 0), 100));
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(0, 255, 0), 100));
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(0, 0, 255), 100));

        Command command = new StartFlowCommand(0, FlowAction.STAY, flowExpressions);
        lightConnection.send(command);
    }

    @After
    public void after() {
        try {
            lightConnection.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
