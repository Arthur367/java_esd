package TremolZFP;
    public enum OptionServerResponse {
        At_send_EOD("Z"),
        At_send_receipt("R");

        private final String value;
        private OptionServerResponse(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
