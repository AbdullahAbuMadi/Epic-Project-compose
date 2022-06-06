package com.abumadi.epicprojectcompose.compose

import java.util.regex.Pattern
import android.util.Patterns

object Validation {

     val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{4,}" +  //at least 4 characters
                "$"
    )

    fun validateEmail(email: String?): Boolean {
        val emailInput = email?.trim { it <= ' ' }
        return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
    }

    fun validatePassword(password: String?): Boolean {
        val passwordInput = password?.trim { it <= ' ' }
        return PASSWORD_PATTERN.matcher(passwordInput).matches()
    }
}