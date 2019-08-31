package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandMessageCreatorTest {

    @Before
    public void setup() {
        CommandMessageCreator.setMessageId(182);
    }

    @Test
    public void testSetPowerCommand() {
        Command command = new SetPowerCommand(true, CommandEffect.SMOOTH, 500);
        String actual = CommandMessageCreator.getMessage(command);
        String expected = "{\"method\":\"set_power\",\"id\":182,\"params\":[\"on\",\"smooth\",500]}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetRGBCommand() {
        Command command = new SetRGBCommand(255, CommandEffect.SMOOTH, 500);
        CommandMessageCreator.setMessageId(182);
        String actual = CommandMessageCreator.getMessage(command);
        String expected = "{\"method\":\"set_rgb\",\"id\":182,\"params\":[255,\"smooth\",500]}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartFlowCommand() {
        List<FlowExpression> flowExpressions = new ArrayList<>();
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(255, 0, 0), 100));
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(0, 255, 0), 100));
        flowExpressions.add(new FlowExpression(5000, 1, RGBCalculator.getColor(0, 0, 255), 100));

        Command command = new StartFlowCommand(0, FlowAction.STAY, flowExpressions);
        String actual = CommandMessageCreator.getMessage(command);
        String expected = "{\"method\":\"start_cf\",\"id\":182,\"params\":[0,1,\"5000,1,16711680,100,5000,1,65280,100,5000,1,255,100\"]}";
        Assert.assertEquals(expected, actual);
    }

}