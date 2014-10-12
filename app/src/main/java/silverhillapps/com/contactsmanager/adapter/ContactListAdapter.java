package silverhillapps.com.contactsmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import silverhillapps.com.contactsmanager.R;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * Adapter class for representing the general list view. We use ArrayAdapter as the list is not enough complex to extend from BaseAdapter
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    private List<Contact> mContacts;   // The contact model.
    private LayoutInflater mInflater = null;


    public ContactListAdapter(Context context, List<Contact> objects) {
        super(context, R.layout.list_row_general, objects);

        this.mContext = context;
        this.mContacts = objects;
        mInflater = LayoutInflater.from(mContext);

    }


    static class ViewHolder {
        public TextView firstNameTextView;
        public TextView surnameTextView;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View rowPhotoView = convertView;

        // We reuse the views
        if (rowPhotoView == null) {

            rowPhotoView = mInflater.inflate(R.layout.list_row_general, null);

            holder = new ViewHolder();
            holder.firstNameTextView = (TextView) rowPhotoView.findViewById(R.id.firstname_textView_generalList);
            holder.surnameTextView = (TextView) rowPhotoView.findViewById(R.id.surname_textView_generalList);

            rowPhotoView.setTag(holder);
        }
        else{
            holder = (ViewHolder) rowPhotoView.getTag();
        }

        Contact j = (Contact) getItem(position);

        // UI update
        holder.firstNameTextView.setText(j.getFirst_name());
        holder.surnameTextView.setText(j.getSurname());

        return rowPhotoView;
    }


    /**
     * Method for communicating with the controller.
     * @param result
     */
    public void setResults(List<Contact> result) {
        this.clear();
        this.addAll(result);

        notifyDataSetChanged();

    }

}
