package silverhillapps.com.contactsmanager.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import silverhillapps.com.contactsmanager.CMApplication;
import silverhillapps.com.contactsmanager.conf.Constants;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * Database handler class - We could user greenDao or other persistence library for the connection in case of more complex accessing
 *
 */
@Singleton
public class DatabaseHandler extends SQLiteOpenHelper {

    private final static String CREATE_NOTES_TABLE_QUERY = "CREATE TABLE " + Constants.DBConstants.TABLE_CONTACTS + "("
            + Constants.DBConstants.KEY_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + Constants.DBConstants.KEY_FIRST_NAME + " TEXT,"
            + Constants.DBConstants.KEY_SURNAME + " TEXT,"
            + Constants.DBConstants.KEY_ADDRESS + " TEXT,"
            + Constants.DBConstants.KEY_PHONE_NUMBER + " TEXT,"
            + Constants.DBConstants.KEY_EMAIL + " TEXT,"
            + Constants.DBConstants.KEY_CREATED_AT + " TEXT,"
            + Constants.DBConstants.KEY_UPDATED_AT + " TEXT "
            + ")";

    private String[] fields = { Constants.DBConstants.KEY_ID, Constants.DBConstants.KEY_FIRST_NAME, Constants.DBConstants.KEY_SURNAME, Constants.DBConstants.KEY_ADDRESS, Constants.DBConstants.KEY_PHONE_NUMBER, Constants.DBConstants.KEY_EMAIL, Constants.DBConstants.KEY_CREATED_AT, Constants.DBConstants.KEY_UPDATED_AT };

    @Inject
    public DatabaseHandler() {
        super(CMApplication.getAppContext(), Constants.DBConstants.DATABASE_NAME, null, Constants.DBConstants.DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_NOTES_TABLE_QUERY);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DBConstants.TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    /**
     * Getting All Contacts
     * @return the contact list
     */
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.DBConstants.TABLE_CONTACTS + " ORDER BY " + Constants.DBConstants.KEY_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contact = createContactFromCursor(cursor);

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    /**
     * Delete All Contacts
     */
    public void deleteAllContacts() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.DBConstants.TABLE_CONTACTS,null, null);

    }

    /**
     * insert All Contacts
     * @param contacts
     */
    public void insertAllContacts(List<Contact> contacts) {

        SQLiteDatabase db = this.getWritableDatabase();

        for(Contact c : contacts){
            ContentValues values = new ContentValues();
            values.put(Constants.DBConstants.KEY_ID,c.getId());
            values.put(Constants.DBConstants.KEY_FIRST_NAME,c.getFirst_name());
            values.put(Constants.DBConstants.KEY_SURNAME,c.getSurname());
            values.put(Constants.DBConstants.KEY_ADDRESS,c.getAddress());
            values.put(Constants.DBConstants.KEY_PHONE_NUMBER,c.getPhone_number());
            values.put(Constants.DBConstants.KEY_EMAIL,c.getEmail());
            values.put(Constants.DBConstants.KEY_CREATED_AT,c.getCreatedAt());
            values.put(Constants.DBConstants.KEY_UPDATED_AT,c.getUpdatedAt());
            db.insert(Constants.DBConstants.TABLE_CONTACTS,null,values);
        }

        db.close();

    }


    /**
     * Getting contacts Count
     * @return
     */
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + Constants.DBConstants.TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // PRivate methods

    // Auxiliar methods for constructing a new Contact object from cursor.
    private Contact createContactFromCursor(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getInt(0));
        contact.setFirst_name(cursor.getString(1));
        contact.setSurname(cursor.getString(2));
        contact.setAddress(cursor.getString(3));
        contact.setPhone_number(cursor.getString(4));
        contact.setEmail(cursor.getString(5));
        contact.setCreatedAt(cursor.getString(6));
        contact.setUpdatedAt(cursor.getString(7));

        return contact;
    }


}
