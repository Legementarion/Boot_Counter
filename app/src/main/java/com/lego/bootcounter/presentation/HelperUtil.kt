package com.lego.bootcounter.presentation

import android.content.Context
import com.lego.bootcounter.R
import com.lego.bootcounter.domain.models.CounterModel


/**
 * 1. If no boot events were detected within the app’s lifetime - the body text should be = “No boots detected”
2. If only 1 boot event was detected, then the text must be = “The boot was detected with the timestamp =
${timestamp_of_the_boot_event}”. “timestamp_of_the_boot_event” event milliseconds since Unix Epoch.
Reference of such here.
3. If multiple events were detected, then the text should be = “Last boots time delta =
${time_between_2_last_boot_events}”. “Time_between_2_last_boot_events” must be the delta between
last and pre-last boot events.
UI
This app has only 1 text view.
It shows different text depending on the information:
1. If no boot events were triggered within the app’s
 */
fun showMessage(context: Context, counter: CounterModel): String {
    with(context) {
        return when (counter.count) {
            0 -> {
                getString(R.string.no_boot)
            }

            1 -> {
                getString(R.string.boot_detected, counter.lastBootTime.toString())
            }

            else -> {
                getString(R.string.last_boot, counter.lastBootTime.toString())
            }
        }
    }
}