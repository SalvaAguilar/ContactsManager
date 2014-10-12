package silverhillapps.com.contactsmanager.manager;

import android.os.Handler;
import android.provider.ContactsContract;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.ObjectGraph;
import silverhillapps.com.contactsmanager.conf.Constants;
import silverhillapps.com.contactsmanager.db.DatabaseHandler;
import silverhillapps.com.contactsmanager.loader.ContactRepository;
import silverhillapps.com.contactsmanager.loader.ContactRepositoryModule;
import silverhillapps.com.contactsmanager.loader.RepositoryReceiver;
import silverhillapps.com.contactsmanager.model.Contact;
import silverhillapps.com.contactsmanager.utils.ConnectionUtils;

/**
 * This class is in charge of the data loading, separating the data loading completely from the view controller.
 */
public class LoaderManagerFacade implements ManagerInterface{
    @Inject DatabaseHandler dbHandler;
    @Inject ContactRepository repository;
    private Handler handler = new Handler();


    /**
     * This method retrieves the contact list
     * @param receiver
     * @return
     */
    @Override
    public List<Contact> getContactList(RepositoryReceiver receiver) {

        ObjectGraph graph = ObjectGraph.create(ContactRepositoryModule.class);
        graph.inject(this);

        //we try first to load them from the database
        List<Contact> contactList = dbHandler.getAllContacts();

        //We fire the network load if the connection is available. Another conditions can be set here, for example, the assess of the results stored in the database...
        if(ConnectionUtils.isNetworkAvailable()) {
            repository.getContactList(receiver, this); //This method performs the calculation if there is any connection in the device
        }

        return contactList;
    }

    /**
     * Method in charge of collecting the results from the low level repository. It manages the persistence of the data obtained.
     * @param result
     * @param code
     */
    @Override
    public void getResults(final Object result, int code) {
        Runnable r = null;
        switch(code){
            case Constants.PassingParamConstants.REST_RETURN_LIST_CONTACTS:
                r = new Runnable() {
                    @Override
                    public void run() {
                        List<Contact> contacts = (List<Contact>) result;
                        dbHandler.insertAllContacts(contacts);
                    }
                };

                break;
        }
        if(r != null) {
            handler.post(r); // we run the persistence runnable in background. The main activity should check the state of this handler to ensure its termination before closing the app in the onPause or onStop method.
        }
    }


}


