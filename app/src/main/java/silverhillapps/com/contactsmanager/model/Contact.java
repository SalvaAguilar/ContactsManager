package silverhillapps.com.contactsmanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class models one contact
 */
public class Contact implements Parcelable{

    private String first_name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;
    private int id;
    private String createdAt; //These both fields should be managed as dates or longs. For this example we evaluate them as Strings for simplicity because we don't need to work with their value.
    private String updatedAt;

    public Contact(String first_name, String surname, String address, String phone_number, String email, int id, String createdAt, String updatedAt) {
        this.first_name = first_name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Contact(){

    }

    /**
     * Constructor used by the CREATOR
     * @param source
     */
    public Contact(Parcel source) {
        this.id = source.readInt();
        this.first_name = source.readString();
        this.surname = source.readString();
        this.address = source.readString();
        this.phone_number = source.readString();
        this.email = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    //Parcelable methods

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(surname);
        dest.writeString(address);
        dest.writeString(phone_number);
        dest.writeString(email);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);

    }

    /**
     * This is the creator of the parcelable
     */
    public static final Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {

        public Contact createFromParcel(Parcel source) {

            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }


    };

    // overriding generic methods
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) o;
        return id==other.id;
    }



}
