package silverhillapps.com.contactsmanager.loader;

import silverhillapps.com.contactsmanager.manager.ManagerInterface;

/**
 * This abstract class determines the structure of a generic loader
 */
public abstract class ContactRepository {

    public abstract void getContactList(RepositoryReceiver receiver, ManagerInterface manager);
}
