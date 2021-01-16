package za.co.jaredfishy.mslights.application.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedDateTime{

    public static FormattedDateTime now(){
        return new FormattedDateTime(LocalDateTime.now());
    }
    public static FormattedDateTime of(LocalDateTime localDateTime){
        return new FormattedDateTime(localDateTime);
    }

    private final LocalDateTime localDateTime;

    private FormattedDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(this.localDateTime).replace(" ","T");
    }

}
