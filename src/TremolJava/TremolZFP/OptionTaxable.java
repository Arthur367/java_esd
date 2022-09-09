package TremolZFP;
    public enum OptionTaxable {
        Exempted("1"),
        Taxable("0");

        private final String value;
        private OptionTaxable(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
