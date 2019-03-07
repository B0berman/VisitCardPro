package com.visitcardpro.views

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.visitcardpro.R
import com.visitcardpro.databinding.ActivityRegisterBinding
import com.visitcardpro.viewmodels.RegisterViewModel


class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel = RegisterViewModel(this)
    lateinit var mPasswordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel

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
        mPasswordView = findViewById(R.id.password_register)

        viewModel.mFormView = findViewById(R.id.register_form)
        viewModel.mProgressView = findViewById(R.id.register_progress)
        mPasswordView.setOnEditorActionListener(viewModel.getPasswordEditorActionListener())
    }
}

