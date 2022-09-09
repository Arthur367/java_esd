package TremolZFP;
public class HTTPS_ServerRes {
   /**
    *Up to 3 symbols for parameter length
    */
    public Double ParamLength;
    public Double getParamLength() {
       return ParamLength;
    }
    protected void setParamLength(Double value) {
       ParamLength = value;
    }

   /**
    *50 symbols for address
    */
    public String Address;
    public String getAddress() {
       return Address;
    }
    protected void setAddress(String value) {
       Address = value;
    }
}
