package silverhillapps.com.contactsmanager.manager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import silverhillapps.com.contactsmanager.ContactListActivity;
import silverhillapps.com.contactsmanager.db.DatabaseHandler;
import silverhillapps.com.contactsmanager.loader.ContactRepositoryModule;

/**
 * This class defines the module for Manager class injection
 */

@Module(
        injects = ContactListActivity.class,
        includes = {
                ContactRepositoryModule.class
        }
)
public class ManagerModule {

    @Provides @Singleton ManagerInterface provideManager() {
        return new LoaderManagerFacade();
    }
}
