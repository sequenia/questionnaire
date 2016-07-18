package com.sequenia.sibgurmanquestionnaire.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by anton on 25.04.16.
 */
public class PermissionsChecker {

    private final Context context;

    public PermissionsChecker(Context context) {
        this.context = context;
    }

    /**
     * @param permissions разрешения
     * @return true, если хотя бы одно разрешение отклонено
     */
    public boolean permissionsDenied(String... permissions) {
        for (String permission : permissions) {
            if (permissionDenied(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true, если для всех отклоненных разрешений нажато - больше не показывать
     */
    public boolean clickedNeverAskAgain(Activity activity, String... permissions) {
        for (String permission : permissions) {
            // Если отклонено, но не нажато БОЛЬШЕ НЕ ПОКАЗЫВАТЬ, нужно вернуть false
            boolean denied = permissionDenied(permission);              // Отклонено ли
            boolean never = clickedNeverAskAgain(activity, permission); // Нажато ли - больше не показывать
            if(denied && !never) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param permission разрешение
     * @return true, если разрешение отклонено
     */
    private boolean permissionDenied(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    // Если БОЛЬШЕ НЕ ПОКАЗЫВАТЬ не нажимали
    private boolean clickedNeverAskAgain(Activity activity, String permission) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }
}

