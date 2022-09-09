package TremolZFP;
public class CloseReceiptRes {
   /**
    *19 symbols for CU invoice number
    */
    public String InvoiceNum;
    public String getInvoiceNum() {
       return InvoiceNum;
    }
    protected void setInvoiceNum(String value) {
       InvoiceNum = value;
    }

   /**
    *128 symbols for QR code
    */
    public String QRcode;
    public String getQRcode() {
       return QRcode;
    }
    protected void setQRcode(String value) {
       QRcode = value;
    }
}
