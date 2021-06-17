package com.cayiroglu.alsatkitapclone.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.cayiroglu.alsatkitapclone.R
import com.cayiroglu.alsatkitapclone.databinding.ActivityLoginBinding
import com.cayiroglu.alsatkitapclone.ui.category.CategoryActivity
import com.cayiroglu.alsatkitapclone.ui.products.ProductActivity
import com.cayiroglu.alsatkitapclone.util.Constants
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private var isShowPassword:Boolean = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnLogin.setOnClickListener(this@LoginActivity)
            imgPassword.setOnClickListener(this@LoginActivity)
        }
    }

    private fun showAndHidePassword(){
        binding.apply {
            if(isShowPassword){
                txtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                imgPassword.setImageDrawable(getDrawable(R.drawable.hide_password))
                isShowPassword = false
            }else{
                txtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imgPassword.setImageDrawable(getDrawable(R.drawable.show_password))
                isShowPassword = true
            }
        }
    }

    private fun signIn(){
        val email = binding.txtEMail.text.trim().toString()
        val password = binding.txtPassword.text.trim().toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        goToCategoryActivity()
                    }else{
                        Toast.makeText(applicationContext,resources.getString(R.string.login_failed_text),Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            Toast.makeText(applicationContext,resources.getString(R.string.login_null_text),Toast.LENGTH_LONG).show()
        }
    }

    private fun goToCategoryActivity(){
        val categoryIntent= Intent(this, CategoryActivity::class.java)
        startActivity(categoryIntent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin->
            {
                signIn()
            }
            R.id.imgPassword->
            {
                showAndHidePassword()
            }
        }
    }
}