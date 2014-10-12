package silverhillapps.com.contactsmanager.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import silverhillapps.com.contactsmanager.conf.Constants;
import silverhillapps.com.contactsmanager.loader.ContactRepository;
import silverhillapps.com.contactsmanager.loader.RepositoryReceiver;
import silverhillapps.com.contactsmanager.manager.ManagerInterface;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * This is the rest loader. it generates the http calls to rest api for any of the methods needed in the app.
 * It uses http-request library.
 * @author salva
 *
 */
public class RestContactRepository extends ContactRepository{

    private Gson g;

    /**
     * This method obtains the contact list through the rest endpoint
     * @param receiver
     * @param manager
     */
    @Override
    public void getContactList(final RepositoryReceiver receiver, final ManagerInterface manager) {

          ContactsRestClient.get(Constants.RestApiConstants.CONTACTS_LIST_METHOD, null,
                  new AsyncHttpResponseHandler() {
                      @Override
                      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                          String str = new String(responseBody);

                          List<Contact> contactList = parseJsonContacts(str);

                          if(manager!=null){ //we can run the rest client without needing an intermediate manager
                              manager.getResults(contactList, Constants.PassingParamConstants.REST_RETURN_LIST_CONTACTS);
                          }

                          receiver.getResults(contactList, Constants.PassingParamConstants.REST_RETURN_LIST_CONTACTS);
                      }

                      @Override
                      public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                          //TODO manage the error in the connection
                      }

                  }
          );
          }

    /**
     * This method parses the http response in order to create the list of model objects.
     * @param response
     * @return
     */
    private List<Contact> parseJsonContacts(String response) {
        g = new Gson(); //TODO dagger - could be injected by dagger also.
        List<Contact> contactList = new ArrayList<Contact>();
        contactList = g.fromJson(response, new TypeToken<List<Contact>>(){}.getType());
        return contactList;
    }
}
