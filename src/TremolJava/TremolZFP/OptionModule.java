package TremolZFP;
    public enum OptionModule {
        GSM("0"),
        LANWiFi("1");

        private final String value;
        private OptionModule(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
