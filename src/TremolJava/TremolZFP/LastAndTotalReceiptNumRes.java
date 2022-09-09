package TremolZFP;
public class LastAndTotalReceiptNumRes {
   /**
    *19 symbols for the last number of invoice according the middleware, CU, 
    *internal invoice counter
    */
    public String LastCUInvoiceNum;
    public String getLastCUInvoiceNum() {
       return LastCUInvoiceNum;
    }
    protected void setLastCUInvoiceNum(String value) {
       LastCUInvoiceNum = value;
    }

   /**
    *7 symbols for last receipt number in format #######
    */
    public Double LastReceiptNum;
    public Double getLastReceiptNum() {
       return LastReceiptNum;
    }
    protected void setLastReceiptNum(Double value) {
       LastReceiptNum = value;
    }
}
