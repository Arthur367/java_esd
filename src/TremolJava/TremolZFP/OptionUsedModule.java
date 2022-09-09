package TremolZFP;
    public enum OptionUsedModule {
        LAN_module("1"),
        WiFi_module("2");

        private final String value;
        private OptionUsedModule(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
