package com.tunc.kotlinmvpretro.Activities.SignupActivity.View

import com.tunc.kotlinmvpretro.Activities.SignupActivity.User

interface ISignView {
    fun onLoginSuccess(user: User)
    fun onLoginError(message: String)
}