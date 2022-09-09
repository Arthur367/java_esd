package TremolZFP;
    public enum OptionAuthenticationType {
        CHAP("2"),
        None("0"),
        PAP("1"),
        PAP_or_CHAP("3");

        private final String value;
        private OptionAuthenticationType(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
