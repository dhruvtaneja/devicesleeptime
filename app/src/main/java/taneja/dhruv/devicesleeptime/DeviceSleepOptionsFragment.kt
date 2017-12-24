package taneja.dhruv.devicesleeptime


import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import kotlinx.android.synthetic.main.fragment_device_sleep_options.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class DeviceSleepOptionsFragment : Fragment(), View.OnClickListener {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PermissionFragment.
         */
        fun newInstance(): DeviceSleepOptionsFragment {
            return DeviceSleepOptionsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_device_sleep_options, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeTimeout()

        timeout15SecondsTv.setOnClickListener(this)
        timeout30SecondsTv.setOnClickListener(this)
        timeout1MinuteTv.setOnClickListener(this)
        timeout5MinuteTv.setOnClickListener(this)
        timeout10MinuteTv.setOnClickListener(this)
        timeout30MinuteTv.setOnClickListener(this)
    }

    private fun initializeTimeout() {
        when (getSystemTimeout()) {
            TimeUnit.SECONDS.toMillis(15) -> setRadioSelected(timeout15SecondsTv)
            TimeUnit.SECONDS.toMillis(30) -> setRadioSelected(timeout30SecondsTv)
            TimeUnit.MINUTES.toMillis(1) -> setRadioSelected(timeout1MinuteTv)
            TimeUnit.MINUTES.toMillis(5) -> setRadioSelected(timeout5MinuteTv)
            TimeUnit.MINUTES.toMillis(10) -> setRadioSelected(timeout10MinuteTv)
            TimeUnit.MINUTES.toMillis(30) -> setRadioSelected(timeout30MinuteTv)
        }
    }

    private fun setRadioSelected(textView: CheckedTextView) {
        textView.isSelected = true
    }

    private fun getSystemTimeout() = Settings.System.getLong(
            context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)

    private fun changeTimeoutTo(time: Long, unit: TimeUnit) {
        Settings.System.putLong(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT,
                unit.toMillis(time))
        activity.finish()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.timeout15SecondsTv -> changeTimeoutTo(15, TimeUnit.SECONDS)
            R.id.timeout30SecondsTv -> changeTimeoutTo(30, TimeUnit.SECONDS)
            R.id.timeout1MinuteTv -> changeTimeoutTo(1, TimeUnit.MINUTES)
            R.id.timeout5MinuteTv -> changeTimeoutTo(5, TimeUnit.MINUTES)
            R.id.timeout10MinuteTv -> changeTimeoutTo(10, TimeUnit.MINUTES)
            R.id.timeout30MinuteTv -> changeTimeoutTo(30, TimeUnit.MINUTES)
        }
    }
}
