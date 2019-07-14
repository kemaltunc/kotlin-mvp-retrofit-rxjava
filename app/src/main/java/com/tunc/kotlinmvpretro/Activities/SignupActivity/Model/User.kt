package com.tunc.kotlinmvpretro.Activities.SignupActivity


import android.text.TextUtils
import android.util.Patterns
import org.w3c.dom.Text
import java.util.regex.Pattern

data class User(val username: String, val email: String, val password: String) {

    fun isDataValid(): Int {
        return when {
            TextUtils.isEmpty(email) -> 0
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> 1
            password.length < 6 -> 2
            TextUtils.isEmpty(username) -> 3
            else -> 4
        }

    }

}
