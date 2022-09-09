package TremolZFP;
public class CurrentReceiptInfoRes {
   /**
    *1 symbol with value: 
    * - '0' - No 
    * - '1' - Yes
    */
    public OptionIsReceiptOpened OptionIsReceiptOpened;
    public OptionIsReceiptOpened getOptionIsReceiptOpened() {
       return OptionIsReceiptOpened;
    }
    protected void setOptionIsReceiptOpened(OptionIsReceiptOpened value) {
       OptionIsReceiptOpened = value;
    }

   /**
    *3 symbols for number of sales
    */
    public String SalesNumber;
    public String getSalesNumber() {
       return SalesNumber;
    }
    protected void setSalesNumber(String value) {
       SalesNumber = value;
    }

   /**
    *Up to 11 symbols for subtotal by VAT group A
    */
    public Double SubtotalAmountVATGA;
    public Double getSubtotalAmountVATGA() {
       return SubtotalAmountVATGA;
    }
    protected void setSubtotalAmountVATGA(Double value) {
       SubtotalAmountVATGA = value;
    }

   /**
    *Up to 11 symbols for subtotal by VAT group B
    */
    public Double SubtotalAmountVATGB;
    public Double getSubtotalAmountVATGB() {
       return SubtotalAmountVATGB;
    }
    protected void setSubtotalAmountVATGB(Double value) {
       SubtotalAmountVATGB = value;
    }

   /**
    *Up to 11 symbols for subtotal by VAT group C
    */
    public Double SubtotalAmountVATGC;
    public Double getSubtotalAmountVATGC() {
       return SubtotalAmountVATGC;
    }
    protected void setSubtotalAmountVATGC(Double value) {
       SubtotalAmountVATGC = value;
    }

   /**
    *Up to 11 symbols for subtotal by VAT group D
    */
    public Double SubtotalAmountVATGD;
    public Double getSubtotalAmountVATGD() {
       return SubtotalAmountVATGD;
    }
    protected void setSubtotalAmountVATGD(Double value) {
       SubtotalAmountVATGD = value;
    }

   /**
    *Up to 11 symbols for subtotal by VAT group E
    */
    public Double SubtotalAmountVATGE;
    public Double getSubtotalAmountVATGE() {
       return SubtotalAmountVATGE;
    }
    protected void setSubtotalAmountVATGE(Double value) {
       SubtotalAmountVATGE = value;
    }

   /**
    *(Format) 1 symbol with value: 
    * - '1' - Detailed 
    * - '0' - Brief
    */
    public OptionReceiptFormat OptionReceiptFormat;
    public OptionReceiptFormat getOptionReceiptFormat() {
       return OptionReceiptFormat;
    }
    protected void setOptionReceiptFormat(OptionReceiptFormat value) {
       OptionReceiptFormat = value;
    }

   /**
    *1 symbol with value: 
    * - '1' - invoice (client) receipt 
    * - '0' - standard receipt
    */
    public OptionClientReceipt OptionClientReceipt;
    public OptionClientReceipt getOptionClientReceipt() {
       return OptionClientReceipt;
    }
    protected void setOptionClientReceipt(OptionClientReceipt value) {
       OptionClientReceipt = value;
    }

   /**
    *1 symbol with value: 
    *- '0' - No 
    *- '1' - Yes
    */
    public OptionPowerDownInReceipt OptionPowerDownInReceipt;
    public OptionPowerDownInReceipt getOptionPowerDownInReceipt() {
       return OptionPowerDownInReceipt;
    }
    protected void setOptionPowerDownInReceipt(OptionPowerDownInReceipt value) {
       OptionPowerDownInReceipt = value;
    }

   /**
    *Up to 11 symbols
    */
    public Double Reserved5;
    public Double getReserved5() {
       return Reserved5;
    }
    protected void setReserved5(Double value) {
       Reserved5 = value;
    }
}
