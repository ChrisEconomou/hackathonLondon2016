package smartshopper.hackathon.london.myapplication.util;

/**
 * Created by christosoikonomou on 04/12/2016.
 */

public class MessageReceivedEvent {
    public String getMessage() {
        return message;
    }

    String message;

    public MessageReceivedEvent(String message){
        this.message=message;
    }

}
