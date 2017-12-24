package taneja.dhruv.devicesleeptime

import java.util.concurrent.TimeUnit

/**
 * Created by dhruvtaneja on 24/12/17.
 */

object TimeoutUtils {
    val timeouts = listOf(
            TimeUnit.SECONDS.toMillis(15),
            TimeUnit.SECONDS.toMillis(30),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(10),
            TimeUnit.MINUTES.toMillis(30)
    )

    fun getTimeoutIndex(timeout: Long) = timeouts.indexOf(timeout)
}