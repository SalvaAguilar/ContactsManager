package silverhillapps.com.contactsmanager;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import silverhillapps.com.contactsmanager.manager.ManagerModule;

/**
 * Application class for wrapping global runtime variables
 *
 */
public class CMApplication extends Application {

    private static Context mContext; // Application context for assets loading


    public void onCreate() {
        super.onCreate();
        CMApplication.mContext = getApplicationContext();

    }

    /**
     * Method which returns the application context
     *
     * @return the application context
     */
    public static Context getAppContext() {
        return CMApplication.mContext;
    }

}