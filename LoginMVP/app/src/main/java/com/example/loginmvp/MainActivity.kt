package com.example.loginmvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginmvp.Presenter.ILoginPresenter
import com.example.loginmvp.Presenter.LoginPresenter
import com.example.loginmvp.Storage.AppPreferences
import com.example.loginmvp.View.ILoginView
import com.example.loginmvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ILoginView {

    lateinit var binding: ActivityMainBinding
    internal lateinit var loginPresenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.init(this)
        if (AppPreferences.isLogin) {
            Intent(this, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnLogin.setOnClickListener {
                loginPresenter.onLogin(
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }
            loginPresenter = LoginPresenter(this)

        }

    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        if (binding.checkLogin.isChecked) AppPreferences.isLogin = true
        else AppPreferences.isLogin = false
    }

    override fun onLoginFail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}