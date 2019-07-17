package com.tunc.kotlinmvpretro.Activities.LoginActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tunc.kotlinmvpretro.Activities.LoginActivity.Model.User
import com.tunc.kotlinmvpretro.Activities.LoginActivity.Presenter.ILoginPresenter
import com.tunc.kotlinmvpretro.Activities.LoginActivity.Presenter.LoginPresenter
import com.tunc.kotlinmvpretro.R
import com.tunc.kotlinmvpretro.Activities.LoginActivity.View.ILoginView
import com.tunc.kotlinmvpretro.Activities.MainActivity.MainActivity
import com.tunc.kotlinmvpretro.Activities.SignupActivity.SignupActivity
import com.tunc.kotlinmvpretro.Retrofit.CreateRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), ILoginView {


    internal lateinit var loginPresenter: ILoginPresenter

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginPresenter = LoginPresenter(this)

        btn_login.setOnClickListener {
            loginPresenter.onLogin(ed_email.text.toString(), ed_password.text.toString())
        }


        tv_signup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

    }

    override fun onLoginSuccess(user: User) {
        val repository = CreateRepositoryProvider.provideCreateRepository()

        val params = HashMap<String, String>()
        params["email"] = user.email
        params["password"] = user.password

        compositeDisposable.add(
            repository.login(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        if (result.success) {
                            val myId = result.id
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("id", myId)
                            startActivity(intent)
                        } else
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show()
                    }, { error ->
                        error.printStackTrace()
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                )

        )
    }

    override fun onLoginError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
