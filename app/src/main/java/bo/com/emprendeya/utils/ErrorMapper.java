package bo.com.emprendeya.utils;

import android.content.Context;

import bo.com.emprendeya.R;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        switch (errorCode) {
            case Constants.ERROR_EMPTY_VALUES:
                return context.getString(R.string.error_fill_values);
            case Constants.ERROR_NO_CONNECTION:
                return context.getString(R.string.error_no_connection);
            case Constants.ERROR_LOGIN:
                return context.getString(R.string.error_incorrect_login);
            case Constants.ERROR_INVALID_EMAIL:
                return context.getString(R.string.error_valid_email);
            default:
                return context.getString(R.string.error_unknown);
        }
    }
}
