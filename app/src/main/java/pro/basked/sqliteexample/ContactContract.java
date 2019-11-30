package pro.basked.sqliteexample;

public final class ContactContract {
    private ContactContract() {
    }
    //fixme 1 acrion) Create contract CLASS
    // - cpetify table name, id, fields
    public static class ContactEntry {
        public static final String TABLE_NAME = "contact_info";
        public static final String CONTACT_ID = "contract_id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
    }
}
