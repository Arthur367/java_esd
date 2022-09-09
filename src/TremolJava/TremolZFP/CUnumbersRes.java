package TremolZFP;
public class CUnumbersRes {
   /**
    *20 symbols for individual number of the CU
    */
    public String SerialNumber;
    public String getSerialNumber() {
       return SerialNumber;
    }
    protected void setSerialNumber(String value) {
       SerialNumber = value;
    }

   /**
    *11 symbols for pin number
    */
    public String PINnumber;
    public String getPINnumber() {
       return PINnumber;
    }
    protected void setPINnumber(String value) {
       PINnumber = value;
    }
}
