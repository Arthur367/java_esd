package TremolZFP;
import java.util.Date;
public class SpecificMessageRes {
   /**
    *2 symbols for total number of messages
    */
    public String MessageNum;
    public String getMessageNum() {
       return MessageNum;
    }
    protected void setMessageNum(String value) {
       MessageNum = value;
    }

   /**
    *Date Time parameter
    */
    public Date DateTime;
    public Date getDateTime() {
       return DateTime;
    }
    protected void setDateTime(Date value) {
       DateTime = value;
    }

   /**
    *1 symbol for type
    */
    public String Type;
    public String getType() {
       return Type;
    }
    protected void setType(String value) {
       Type = value;
    }

   /**
    *3 symbols for code
    */
    public String Code;
    public String getCode() {
       return Code;
    }
    protected void setCode(String value) {
       Code = value;
    }

   /**
    *Up to 128 symbols for message text
    */
    public String MessageText;
    public String getMessageText() {
       return MessageText;
    }
    protected void setMessageText(String value) {
       MessageText = value;
    }
}
