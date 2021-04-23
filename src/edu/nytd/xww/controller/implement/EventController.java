package edu.nytd.xww.controller.implement;

import edu.nytd.xww.controller.PanelControl;
import edu.nytd.xww.gui.implement.BorderFrame;
import edu.nytd.xww.message.Message;

/**
 * @author Yanyu
 */
public class EventController implements PanelControl {
    public static boolean sendMessage(Message message){
        // TODO 发送信息
        return true;
    }

    public static void acceptMessage(){
        var message = new Message("1","2");
        setMessage(message);
    }

    public static void setMessage(Message message){
        if (message != null){
            BorderFrame.update(message);
        }else {
            throw new NullPointerException();
        }
    }

}
