package com.tunc.kotlinmvpretro.Activities.SignupActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tunc.kotlinmvpretro.Activities.MainActivity.MainActivity
import com.tunc.kotlinmvpretro.Activities.SignupActivity.Presenter.SignPresenter
import com.tunc.kotlinmvpretro.Activities.SignupActivity.View.ISignView
import com.tunc.kotlinmvpretro.R
import com.tunc.kotlinmvpretro.Retrofit.Api
import com.tunc.kotlinmvpretro.Retrofit.CreateRepositoryProvider
import com.tunc.kotlinmvpretro.Retrofit.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_signup.*
import org.reactivestreams.Subscription

class SignupActivity : AppCompatActivity(), ISignView {

    internal lateinit var iSignPresenter: SignPresenter

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        iSignPresenter = SignPresenter(this)


        btn_signup.setOnClickListener {
            iSignPresenter.onSign(
                ed_username.text.toString(),
                ed_email.text.toString(),
                ed_password.text.toString()
            )
        }
    }

    override fun onLoginSuccess(user: User) {
        val repository = CreateRepositoryProvider.provideCreateRepository()

        val params = HashMap<String, String>()
        params["name"] = user.username
        params["email"] = user.email
        params["password"] = user.password

        compositeDisposable.add(
            repository.createUsers(params)
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
                            Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT).show();
                    }, { error ->
                        error.printStackTrace()
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                )

        )


    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onLoginError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show();
    }

}
