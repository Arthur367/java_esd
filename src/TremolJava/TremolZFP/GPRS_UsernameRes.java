package TremolZFP;
public class GPRS_UsernameRes {
   /**
    *Up to 3 symbols for the GPRS username length
    */
    public Double GprsUserNameLength;
    public Double getGprsUserNameLength() {
       return GprsUserNameLength;
    }
    protected void setGprsUserNameLength(Double value) {
       GprsUserNameLength = value;
    }

   /**
    *Up to 100 symbols for the device's GPRS username
    */
    public String Username;
    public String getUsername() {
       return Username;
    }
    protected void setUsername(String value) {
       Username = value;
    }
}
