package silverhillapps.com.contactsmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import silverhillapps.com.contactsmanager.conf.Constants;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * This class intents to show the detail of the contact. Should be constructed with a fragment that would be in charge of the view but as this is a simple view, we can avoid
 * this step and give this role over the parent activity. The separation between fragment and activity is really useful in case the controller was used in different device's families like tablets and smartphones
 */

public class ContactDetailActivity extends Activity {

    private Contact contact;

    //UI Elements
    private TextView firstnameTextView;
    private TextView surnameTextView;
    private TextView addressTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;
    private TextView createdAtTextView;
    private TextView updatedAtTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // We retrieve the parameters from parent activity
        contact = getIntent().getExtras().getParcelable(Constants.PassingParamConstants.ACTIVITY_PASS_CONTACT);

        getActionBar().setTitle(contact.getFirst_name());

        getViews();
        fillUIInfo();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Private methods
    // UI managed methods
    private void fillUIInfo() {
        if(this.contact != null){
            this.firstnameTextView.setText(contact.getFirst_name());
            this.surnameTextView.setText(contact.getSurname());
            this.addressTextView.setText(contact.getAddress());
            this.phoneNumberTextView.setText(contact.getPhone_number());
            this.emailTextView.setText(contact.getEmail());
            this.createdAtTextView.setText(contact.getCreatedAt());
            this.updatedAtTextView.setText(contact.getUpdatedAt());
        }
    }

    private void getViews() {
        this.firstnameTextView = (TextView)findViewById(R.id.firstname_textView_detailView);
        this.surnameTextView = (TextView)findViewById(R.id.surname_textView_detailView);
        this.addressTextView = (TextView)findViewById(R.id.address_textView_detailView);
        this.phoneNumberTextView = (TextView)findViewById(R.id.phoneNumber_textView_detailView);
        this.emailTextView = (TextView)findViewById(R.id.email_textView_detailView);
        this.createdAtTextView = (TextView)findViewById(R.id.createdAt_textView_detailView);
        this.updatedAtTextView = (TextView)findViewById(R.id.updatedAt_textView_detailView);
    }
}
