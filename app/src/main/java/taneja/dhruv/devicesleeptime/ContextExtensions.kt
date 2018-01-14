package taneja.dhruv.devicesleeptime

import android.content.Context
import android.provider.Settings

/**
 * Created by dhruvtaneja on 14/01/18.
 */


fun Context.getSystemSettingsLong(setting: String, default: Long) =
        Settings.System.getLong(contentResolver, setting, default)

fun Context.updateSystemSettingsLong(setting: String, value: Long) {
    Settings.System.putLong(contentResolver, setting, value)
}
