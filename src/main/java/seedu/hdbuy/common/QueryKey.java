package seedu.hdbuy.common;

public enum QueryKey {
    LOCATION {
        @Override public String toString() {
            return "location";
        }
    }, TYPE {
        @Override public String toString() {
            return "type";
        }
    }, LEASE_REMAINING {
        @Override public String toString() {
            return "lease_remaining";
        }
    }
}
