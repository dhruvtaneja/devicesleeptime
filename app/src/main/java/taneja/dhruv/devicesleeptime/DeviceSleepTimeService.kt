package taneja.dhruv.devicesleeptime

import android.graphics.drawable.Icon
import android.provider.Settings
import android.service.quicksettings.TileService

/**
 * Created by dhruvtaneja on 24/12/17.
 */
class DeviceSleepTimeService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        if (Settings.System.canWrite(applicationContext)) {
            val timeout = Settings.System.getLong(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, 15000)
            val index = TimeoutUtils.getTimeoutIndex(timeout)
            updateTile(index)
        }
    }

    override fun onClick() {
        super.onClick()
        if (!Settings.System.canWrite(this)) {
            SleepTimeOptionsActivity.startActivity(this)
        } else {
            val timeout = Settings.System.getLong(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, 15000)
            val index = TimeoutUtils.getTimeoutIndex(timeout)
            val nextIndex = (index + 1) % TimeoutUtils.timeouts.size
            val updatedTimeout = TimeoutUtils.timeouts.get(nextIndex)
            Settings.System.putLong(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, updatedTimeout)
            updateTile(nextIndex)
        }
    }

    private fun updateTile(index: Int) {
        val timeouts = resources.getStringArray(R.array.timeouts)
        if (index < 0 || index > timeouts.size - 1) {
            return
        }
        val tile = qsTile
        with(tile) {
            label = timeouts[index]
            icon = Icon.createWithResource(applicationContext, getIconId(index))
            updateTile()
        }
    }

    private fun getIconId(index: Int): Int {
        return when (index) {
            0 -> R.drawable.ic_combined_shape
            1 -> R.drawable.ic_combined_shape_2
            2 -> R.drawable.ic_combined_shape_3
            3 -> R.drawable.ic_combined_shape_4
            4 -> R.drawable.ic_combined_shape_5
            5 -> R.drawable.ic_combined_shape_6
            else -> R.drawable.ic_combined_shape
        }
    }
}