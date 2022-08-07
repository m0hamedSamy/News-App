package com.example.onlinepractice.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.room.Room
import com.example.onlinepractice.R
import com.example.onlinepractice.database.UserDatabase
import com.example.onlinepractice.homePage.HomePageFragment
import com.example.onlinepractice.register.RegisterFragment


class LoginFragment : Fragment(R.layout.fragment_login){

    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var btnLogin: Button? = null
    var btnRegister: Button? = null
    var shared: SharedPreferences? = null
    var database: UserDatabase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etEmail = view.findViewById(R.id.et_loginEmail)
        etPassword = view.findViewById(R.id.et_loginPassword)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)
        shared = this.requireActivity().getSharedPreferences("user.prf", Context.MODE_PRIVATE)
        database = Room.databaseBuilder(this.requireContext(), UserDatabase::class.java, "users.db")
            .allowMainThreadQueries()
            .build()

        loadEmailFromSharedPref()

        btnLogin?.setOnClickListener {

            logUsers()
            storeEmailInSharedPref()

            if(isValidLogin()){
                if(isAuthenticatedUser()){
                    navigateToHomePage()
                }else{
                    warnUserForBadAuthentication()
                }
            }else{
                warnUserForBadLogin()
            }

        }

        btnRegister?.setOnClickListener {
            navigateToRegister()
        }



    }

    private fun isValidLogin(): Boolean {
        val email = etEmail?.text.toString()
        val password = etPassword?.text.toString()
        return !(email.isEmpty() || password.isEmpty())
    }

    private fun isAuthenticatedUser(): Boolean {
        val email = etEmail?.text.toString()
        val password = etPassword?.text.toString()
        val passwordInDatabase = database?.userDao()?.getUserPasswordByEmail(email)
        return password == passwordInDatabase
    }

    private fun warnUserForBadLogin() {
        //TODO("show that there are fields missing with red font")
    }

    private fun warnUserForBadAuthentication() {
        //TODO("show that either the email or password is wrong")
    }

    private fun navigateToHomePage() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HomePageFragment>(R.id.main_fragment_container)
        }
    }

    private fun navigateToRegister() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RegisterFragment>(R.id.main_fragment_container)
            addToBackStack("Register")
        }
    }

    private fun storeEmailInSharedPref(){
        val email = etEmail?.text.toString()
        shared?.edit()
            ?.putString("user email", email)
            ?.apply()
    }

    private fun loadEmailFromSharedPref(){
        etEmail?.setText(shared?.getString("user email", null))
    }

    private fun logUsers() {
        val users = database?.userDao()?.getAllUsers()
        Log.d("###", users.toString())
    }
}