package TremolZFP;
public class GPRS_APNRes {
   /**
    *Up to 3 symbols for the APN length
    */
    public Double GprsAPNlength;
    public Double getGprsAPNlength() {
       return GprsAPNlength;
    }
    protected void setGprsAPNlength(Double value) {
       GprsAPNlength = value;
    }

   /**
    *(APN) Up to 100 symbols for the device's GPRS APN
    */
    public String APN;
    public String getAPN() {
       return APN;
    }
    protected void setAPN(String value) {
       APN = value;
    }
}
