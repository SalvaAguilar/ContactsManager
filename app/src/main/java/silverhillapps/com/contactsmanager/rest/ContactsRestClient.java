package silverhillapps.com.contactsmanager.rest;

import com.loopj.android.http.*;

import silverhillapps.com.contactsmanager.conf.Constants;

/**
 * Base class for performing http calls.
 * A better approximation can be done using the path library for asynchronous loads + Square Otto as event bus for ui updating
 */
public class ContactsRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(Constants.RestApiConstants.CONTACTS_API_BASE_URL+url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.RestApiConstants.CONTACTS_API_BASE_URL + relativeUrl;
    }
}
