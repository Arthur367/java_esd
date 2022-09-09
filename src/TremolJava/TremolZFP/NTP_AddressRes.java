package TremolZFP;
public class NTP_AddressRes {
   /**
    *Up to 3 symbols for the address length
    */
    public Double AddressLen;
    public Double getAddressLen() {
       return AddressLen;
    }
    protected void setAddressLen(Double value) {
       AddressLen = value;
    }

   /**
    *(NTP Address)50 symbols for the device's NTP address
    */
    public String NTPAddress;
    public String getNTPAddress() {
       return NTPAddress;
    }
    protected void setNTPAddress(String value) {
       NTPAddress = value;
    }
}
