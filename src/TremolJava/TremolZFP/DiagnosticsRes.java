package TremolZFP;
import java.util.Date;
public class DiagnosticsRes {
   /**
    *1 symbol for device type: 
    * - '1' - A Type 
    * - '2' - B Type
    */
    public OptionDeviceType OptionDeviceType;
    public OptionDeviceType getOptionDeviceType() {
       return OptionDeviceType;
    }
    protected void setOptionDeviceType(OptionDeviceType value) {
       OptionDeviceType = value;
    }

   /**
    *10 symbols for current SD index position of last sent receipt
    */
    public String SDIdxPos;
    public String getSDIdxPos() {
       return SDIdxPos;
    }
    protected void setSDIdxPos(String value) {
       SDIdxPos = value;
    }

   /**
    *19 symbols for number of last invoice according the CU
    */
    public String LastInvoiceCUNum;
    public String getLastInvoiceCUNum() {
       return LastInvoiceCUNum;
    }
    protected void setLastInvoiceCUNum(String value) {
       LastInvoiceCUNum = value;
    }

   /**
    *6 symbols for last invoice date in the DDMMYY format
    */
    public String LastInvoiceDate;
    public String getLastInvoiceDate() {
       return LastInvoiceDate;
    }
    protected void setLastInvoiceDate(String value) {
       LastInvoiceDate = value;
    }

   /**
    *6 symbols for last sent EOD in the DDMMYY format
    */
    public String LastEODDate;
    public String getLastEODDate() {
       return LastEODDate;
    }
    protected void setLastEODDate(String value) {
       LastEODDate = value;
    }

   /**
    *4 symbold for number of invoices sent for the current day
    */
    public String InvoicesSent;
    public String getInvoicesSent() {
       return InvoicesSent;
    }
    protected void setInvoicesSent(String value) {
       InvoicesSent = value;
    }
}
