package za.co.jaredfishy.mslights.application.service;

import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.LightLocation;
import za.co.jaredfishy.mslights.application.domain.command.Command;
import za.co.jaredfishy.mslights.application.domain.command.CommandEffect;
import za.co.jaredfishy.mslights.application.domain.command.SetPowerCommand;
import za.co.jaredfishy.mslights.application.domain.command.SetRGBCommand;
import za.co.jaredfishy.mslights.application.util.RGBCalculator;

import java.util.List;

@Service
public class LightService {

    private LightConnectionHandler lightConnectionHandler;

    public LightService(LightConnectionHandler lightConnectionHandler) {
        this.lightConnectionHandler = lightConnectionHandler;
    }

    public void turnOn(List<LightLocation> lightLocations) {
        broadcastCommand(lightLocations,new SetPowerCommand(true, CommandEffect.SMOOTH, 500));
    }

    public void turnOff(List<LightLocation> lightLocations) {
        broadcastCommand(lightLocations,new SetPowerCommand(false, CommandEffect.SMOOTH, 500));
    }

    public void setColor(List<LightLocation> lightLocations, int red, int green, int blue) {
        int rgbValue = RGBCalculator.getColor(red, green, blue);
        broadcastCommand(lightLocations, new SetRGBCommand(rgbValue, CommandEffect.SMOOTH, 1000));
    }

    private void broadcastCommand(List<LightLocation> lightLocations, Command command) {
        for (LightLocation lightLocation : lightLocations) {
            LightConnection lightConnection = lightConnectionHandler.getConnection(lightLocation.getIp());
            lightConnection.send(command);
        }
    }

}
