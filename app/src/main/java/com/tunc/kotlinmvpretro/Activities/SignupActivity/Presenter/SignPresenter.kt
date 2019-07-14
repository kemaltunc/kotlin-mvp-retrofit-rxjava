package com.tunc.kotlinmvpretro.Activities.SignupActivity.Presenter


import com.tunc.kotlinmvpretro.Activities.SignupActivity.User
import com.tunc.kotlinmvpretro.Activities.SignupActivity.View.ISignView

class SignPresenter(private var iSignView: ISignView) : ISingPresenter {
    override fun onSign(username: String, email: String, password: String) {
        val user = User(username, email, password)

        when (user.isDataValid()) {
            0 -> {
                iSignView.onLoginError("Email must not be null")
            }
            1 -> {
                iSignView.onLoginError("Wrong email adress")
            }
            2 -> {
                iSignView.onLoginError("Password must be greater than 6")
            }
            3 -> {
                iSignView.onLoginError("Username must be greater than 6")
            }
            4 -> {
                iSignView.onLoginSuccess(user)
            }
        }
    }

}