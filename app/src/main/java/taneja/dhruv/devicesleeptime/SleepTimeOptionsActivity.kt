package taneja.dhruv.devicesleeptime

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.service.quicksettings.TileService
import android.support.v4.app.FragmentTransaction

class SleepTimeOptionsActivity : AppCompatActivity() {

    companion object {
        val PERMISSION_REQUEST_CODE = 123

        @JvmStatic
        fun startActivity(service: TileService) =
                service.startActivityAndCollapse(Intent(service, SleepTimeOptionsActivity::class.java))
    }

    private lateinit var permissionFragment: PermissionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_time_options)

        if (Settings.System.canWrite(applicationContext)) {
            showOptionsFragment()
        } else {
            showPermissionFragment()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (Settings.System.canWrite(applicationContext)) {
                val handler = Handler()
                handler.post {
                    val fragment = supportFragmentManager.findFragmentByTag("permission_fragment")
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.remove(fragment)
                    ft.commit()
                    showOptionsFragment()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showPermissionFragment() {
        permissionFragment = PermissionFragment.newInstance()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragment_container, permissionFragment, "permission_fragment")
        ft.commit()
    }

    private fun showOptionsFragment() {
        val optionsFragment = DeviceSleepOptionsFragment.newInstance()
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragment_container, optionsFragment, "options_fragment")
        ft.commit()
    }
}
