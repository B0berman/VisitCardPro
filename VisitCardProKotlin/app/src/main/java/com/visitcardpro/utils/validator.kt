package utils

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    val regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(email)
    return matcher.matches()
}

fun isPasswordValid(password: String): Boolean {

    //(?=.*[0-9]) Au moins un chiffre
    //(?=.*[a-z]) Au moins une lettre minuscule
    //(?=.*[A-Z]) Au moins une lettre maj
    //(?=.*[@#$%^&+=]) Au moins un carac spé
    //(?=\\S+$) Pas d'espace
    //.{8,} Au moins 8 caracs

    val regex = "^(?=\\S+$).{8,}$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(password)
    return matcher.matches()
}