package com.tunc.kotlinmvpretro.Activities.LoginActivity.Presenter

import com.tunc.kotlinmvpretro.Activities.LoginActivity.Model.User
import com.tunc.kotlinmvpretro.Activities.LoginActivity.View.ILoginView


class LoginPresenter(private var iLoginView: ILoginView) :
    ILoginPresenter {
    override fun onLogin(email: String, password: String) {
        val user = User(email, password)

        when (user.isDataValid()) {
            0 -> {
                iLoginView.onLoginError("Email must not be null")
            }
            1 -> {
                iLoginView.onLoginError("Wrong email adress")
            }
            2 -> {
                iLoginView.onLoginError("Password must be greater than 6")
            }
            3 -> {
                iLoginView.onLoginSuccess(user)
            }
        }

    }

}