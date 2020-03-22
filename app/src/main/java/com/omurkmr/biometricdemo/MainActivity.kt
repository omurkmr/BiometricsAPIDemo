package com.omurkmr.biometricdemo

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.security.KeyChain
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import android.content.SharedPreferences
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val biometricPromptManager by lazy { BiometricPromptManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {

        encryptButton.setOnClickListener {
            biometricPromptManager.encryptPrompt(
                data = secureET.text.toString().toByteArray(),
                failedAction = { "encrypt failed".showToast() },
                successAction = {
                    encrpytText.text = String(it)
                    "encrypt success".showToast()
                }
            )
        }

        decryptButton.setOnClickListener {
            biometricPromptManager.decryptPrompt(
                failedAction = { "decrypt failed".showToast() },
                successAction = {
                    decrpytText.text = String(it)
                    "decrypt success".showToast()
                }
            )
        }

    }

    private fun String.showToast() {
        Toast.makeText(applicationContext, this,Toast.LENGTH_SHORT).show()
    }

}
