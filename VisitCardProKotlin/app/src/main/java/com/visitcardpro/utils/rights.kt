package utils

import android.os.Environment
import android.util.Base64
import com.visitcardpro.api.Client

fun isExternalStorageWritable(): Boolean {
    val state = Environment.getExternalStorageState()
    return Environment.MEDIA_MOUNTED == state
}

fun generateAuthorization(data: String): String {
    val encodedBytes: ByteArray = Base64.encode(data.toByteArray(), Base64.NO_WRAP or Base64.URL_SAFE)
    return "Basic " + String(encodedBytes)
}

fun getAccessToken(): String = Client.auth.accessToken