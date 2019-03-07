package utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.view.View

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
fun showProgress(show: Boolean, view: View, progressView: View) {
    val shortAnimTime = 200

    view.visibility = if (show) View.GONE else View.VISIBLE
    view.animate().setDuration(shortAnimTime.toLong()).alpha(
        (if (show) 0 else 1).toFloat()
    ).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            view.visibility = if (show) View.GONE else View.VISIBLE
        }
    })

    progressView.visibility = if (show) View.VISIBLE else View.GONE
    progressView.animate().setDuration(shortAnimTime.toLong()).alpha(
        (if (show) 1 else 0).toFloat()
    ).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            progressView.visibility = if (show) View.VISIBLE else View.GONE
        }
    })
}