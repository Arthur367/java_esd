package TremolZFP;
    public enum OptionReportStorage {
        Storage_in_External_SD_card_memory("J4"),
        Storage_in_External_SD_card_memory_for_JSON("JX"),
        Storage_in_External_USB_Flash_memory("J2"),
        Storage_in_External_USB_Flash_memory_for_JSON("Jx");

        private final String value;
        private OptionReportStorage(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
