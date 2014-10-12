package silverhillapps.com.contactsmanager.conf;

/**
 * Constants class
 */
public class Constants {

    /**
     * Class for storing the rest api constants
     */
    public static final class RestApiConstants{
        public static final String CONTACTS_API_BASE_URL = "http://fast-gorge.herokuapp.com/";
        public static final String CONTACTS_LIST_METHOD = "contacts";

    }

    /**
     * Class for storing the database constants
     */
    public static final class DBConstants{

        // Database Version
        public static final int DATABASE_VERSION = 1;
        // Database name
        public static final String DATABASE_NAME = "contactsManagerSilverhillApps";
        // Notes table
        public static final String TABLE_CONTACTS = "contacts";

        // Note Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_FIRST_NAME = "first_name";
        public static final String KEY_SURNAME = "surname";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_PHONE_NUMBER = "phone_number";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_CREATED_AT = "createdAt";
        public static final String KEY_UPDATED_AT = "updatedAt";

    }

    /**
     * Class for storing the passing parameters ids contacts
     */
    public static final class PassingParamConstants{

        public static final int REST_RETURN_LIST_CONTACTS = 1;

        public static final java.lang.String ACTIVITY_PASS_CONTACT = "activity_pass_contact";
    }

}
