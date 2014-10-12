package silverhillapps.com.contactsmanager.loader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import silverhillapps.com.contactsmanager.manager.LoaderManagerFacade;
import silverhillapps.com.contactsmanager.rest.RestContactRepository;

/**
 * Dagger Module for Manager injection
 */

@Module(injects = LoaderManagerFacade.class, complete = false, library = true)
public class ContactRepositoryModule {

    @Provides @Singleton ContactRepository providePump() {
        return new RestContactRepository();
    }
}
