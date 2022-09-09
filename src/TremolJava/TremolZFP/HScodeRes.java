package TremolZFP;
public class HScodeRes {
   /**
    *4 symbols for HS number in order in format ####
    */
    public Double HS_Number;
    public Double getHS_Number() {
       return HS_Number;
    }
    protected void setHS_Number(Double value) {
       HS_Number = value;
    }

   /**
    *10 symbols for HS code
    */
    public String HS_Code;
    public String getHS_Code() {
       return HS_Code;
    }
    protected void setHS_Code(String value) {
       HS_Code = value;
    }

   /**
    *20 symbols for name of HS group
    */
    public String HS_Name;
    public String getHS_Name() {
       return HS_Name;
    }
    protected void setHS_Name(String value) {
       HS_Name = value;
    }

   /**
    *1 symbol for parameter: 
    *- '1' - Exempted 
    *- '0' - Taxable
    */
    public OptionTaxable OptionTaxable;
    public OptionTaxable getOptionTaxable() {
       return OptionTaxable;
    }
    protected void setOptionTaxable(OptionTaxable value) {
       OptionTaxable = value;
    }

   /**
    *3 symbols for mesure unit of item's code
    */
    public String MesureUnit;
    public String getMesureUnit() {
       return MesureUnit;
    }
    protected void setMesureUnit(String value) {
       MesureUnit = value;
    }

   /**
    *(VAT rate) Value of VAT rate from 2 to 5 symbols with format ##.##
    */
    public Double VAT_Rate;
    public Double getVAT_Rate() {
       return VAT_Rate;
    }
    protected void setVAT_Rate(Double value) {
       VAT_Rate = value;
    }
}
