package za.co.jaredfishy.mslights.application.util;

public class OutputFormatter {

    public static String formatOutput(String output){
        output = output.replaceAll("\r\n", " ");
        return output;
    }
}
