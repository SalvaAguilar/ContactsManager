package silverhillapps.com.contactsmanager.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import silverhillapps.com.contactsmanager.CMApplication;

/**
 * Class with some util methods for evaluating the connectivity of the device
 */
public class ConnectionUtils {

    /**
     * This static method checks if the device has a valid internet connection.
     * @return
     */
    public static boolean isNetworkAvailable(){

            ConnectivityManager connectivityManager
                    = (ConnectivityManager) CMApplication.getAppContext().getSystemService(CMApplication.getAppContext().CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

