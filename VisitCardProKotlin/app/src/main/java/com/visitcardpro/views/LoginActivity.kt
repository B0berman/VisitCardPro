package com.visitcardpro.views

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.visitcardpro.R
import com.visitcardpro.viewmodels.LoginViewModel
import com.visitcardpro.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = LoginViewModel()
    private lateinit var mPasswordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.loginActivity = this
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        }
        mPasswordView = findViewById(R.id.password_login)
        viewModel.mLoginFormView = findViewById(R.id.login_form)
        viewModel.mProgressView = findViewById(R.id.login_progress)

        mPasswordView.setOnEditorActionListener(viewModel.getPasswordEditorActionListener())
    }
}

