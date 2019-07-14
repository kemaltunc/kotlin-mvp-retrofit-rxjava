package com.tunc.kotlinmvpretro.Activities.SignupActivity.Presenter

interface ISingPresenter {
    fun onSign(username: String, email: String, password: String)
}