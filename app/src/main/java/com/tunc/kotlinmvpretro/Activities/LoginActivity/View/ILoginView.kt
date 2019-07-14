package com.tunc.kotlinmvpretro.Activities.LoginActivity.View

import com.tunc.kotlinmvpretro.Activities.LoginActivity.Model.User


interface ILoginView {
    fun onLoginSuccess(user: User)
    fun onLoginError(message: String)
}