package TremolZFP;
    public enum OptionReadEJStorage {
        Reading_to_PC("J0"),
        Reading_to_PC_for_JSON("JY");

        private final String value;
        private OptionReadEJStorage(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
