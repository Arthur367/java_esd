package TremolZFP;
    public enum OptionInvoiceCopy {
        Reading("J0"),
        Storage_in_External_SD_card_memory("J4"),
        Storage_in_External_USB_Flash_memory("J2");

        private final String value;
        private OptionInvoiceCopy(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
