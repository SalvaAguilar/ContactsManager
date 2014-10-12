package silverhillapps.com.contactsmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import silverhillapps.com.contactsmanager.adapter.ContactListAdapter;
import silverhillapps.com.contactsmanager.conf.Constants;
import silverhillapps.com.contactsmanager.loader.RepositoryReceiver;
import silverhillapps.com.contactsmanager.manager.ManagerInterface;
import silverhillapps.com.contactsmanager.manager.ManagerModule;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * This is the general Activity of the Application. it shows the list of all the contacts loaded from the Manager class.
 */
public class ContactListActivity extends Activity implements RepositoryReceiver, AdapterView.OnItemClickListener{

    private ContactListAdapter mAdapter;
    @Inject ManagerInterface manager;
    private List<Contact> mContacts;

    //UI elements
    private ListView listView;

    //The injection graph
    private ObjectGraph graph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        graph = ObjectGraph.create(new ManagerModule());
        graph.inject(this);

        this.listView = (ListView)findViewById(R.id.listView);

        mContacts = manager.getContactList(this);

        this.mAdapter = new ContactListAdapter(this, mContacts);
        this.listView.setAdapter(mAdapter);
        this.listView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_list, menu);
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


    //RepositorReceiver methods

    @Override
    public void getResults(Object result, int code) {

        switch(code){
            case Constants.PassingParamConstants.REST_RETURN_LIST_CONTACTS:
                List<Contact> newContacts = (List<Contact>) result;
                if(!compareContacts(newContacts)){
                    this.mContacts = newContacts;
                    this.mAdapter.setResults(mContacts);
                }
                break;
        }

    }

    private boolean compareContacts(List<Contact> newContacts) {
        boolean out = true;

        if(mContacts.size()!=newContacts.size()){
            out = false;
        }else{
            Contact one, two;
            for(int i = 0; i<mContacts.size(); i++){// we assume that they are in the same order
                one = mContacts.get(i);
                two = newContacts.get(i);
                if(!one.equals(two)){
                    out = false;
                    break;
                }
            }
        }

        return out;
    }

    @Override
    public void getError(int code) {
        //TODO manage the error with retries or other mechanisms. This could be done also in a low level in the manager code or in the rest connector.
    }

    // Item click methods
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contact c = mContacts.get(position);
        Intent detailActivityIntent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
        detailActivityIntent.putExtra(Constants.PassingParamConstants.ACTIVITY_PASS_CONTACT, c);

        startActivity(detailActivityIntent);
    }


}
