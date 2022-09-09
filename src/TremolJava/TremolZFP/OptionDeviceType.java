package TremolZFP;
    public enum OptionDeviceType {
        A_Type("1"),
        B_Type("2");

        private final String value;
        private OptionDeviceType(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
