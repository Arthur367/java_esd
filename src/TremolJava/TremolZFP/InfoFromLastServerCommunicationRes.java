package TremolZFP;
public class InfoFromLastServerCommunicationRes {
   /**
    *1 symbol with value 
    *- 'R' - At send receipt 
    *- 'Z' - At send EOD
    */
    public OptionServerResponse OptionServerResponse;
    public OptionServerResponse getOptionServerResponse() {
       return OptionServerResponse;
    }
    protected void setOptionServerResponse(OptionServerResponse value) {
       OptionServerResponse = value;
    }

   /**
    *1 symbol with value 
    *- 'c' - Error Code 
    *- 'm' - Error Message 
    *- 's' - Status 
    *- 'e' - Exception Message
    */
    public OptionTransactionType OptionTransactionType;
    public OptionTransactionType getOptionTransactionType() {
       return OptionTransactionType;
    }
    protected void setOptionTransactionType(OptionTransactionType value) {
       OptionTransactionType = value;
    }

   /**
    *Up to 200 symbols for the message from the server
    */
    public String Message;
    public String getMessage() {
       return Message;
    }
    protected void setMessage(String value) {
       Message = value;
    }
}
