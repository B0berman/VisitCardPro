package utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.IOException

const val LANGUAGE_PREF = "lang_pref"

@Throws(IOException::class)
fun savePreferences(id: String, param: String, ctx: Context) {
    val sharedPref = ctx.getSharedPreferences("shared_pref", MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(id, param)
    editor.commit()
}

@Throws(IOException::class, ClassNotFoundException::class)
fun getPreferences(ctx: Context, pref: String): String? {
    val sharedPref = ctx.getSharedPreferences("shared_pref", MODE_PRIVATE)
    val toget = sharedPref.getString(pref, "default")

    return if (toget!!.compareTo("default") == 0) {
        null
    } else
        toget
}