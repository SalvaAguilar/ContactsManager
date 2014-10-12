package silverhillapps.com.contactsmanager.manager;

import java.util.List;

import silverhillapps.com.contactsmanager.loader.RepositoryReceiver;
import silverhillapps.com.contactsmanager.model.Contact;

/**
 * This interface defines the skeleton for a generic manager
 */
public interface ManagerInterface {

    public List<Contact> getContactList(RepositoryReceiver receiver);
    public void getResults(Object result, int code);
}
