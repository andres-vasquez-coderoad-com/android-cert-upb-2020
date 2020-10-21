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
            case Constants.ERROR_REGISTER:
                return context.getString(R.string.error_register);
            case Constants.ERROR_REGISTER_EMAIL_ALREADY_EXISTS:
                return context.getString(R.string.error_register_email_already_exist);
            default:
                return context.getString(R.string.error_unknown);
        }
    }
}
