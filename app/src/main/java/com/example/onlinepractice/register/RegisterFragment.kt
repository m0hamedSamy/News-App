package com.example.onlinepractice.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.onlinepractice.R
import com.example.onlinepractice.database.User
import com.example.onlinepractice.database.UserDatabase


class RegisterFragment : Fragment(R.layout.fragment_register){

    var etName: EditText? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var btnRegister: Button? = null
    var database: UserDatabase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etName = view.findViewById(R.id.et_registerName)
        etEmail = view.findViewById(R.id.et_registerEmail)
        etPassword = view.findViewById(R.id.et_registerPassword)
        btnRegister = view.findViewById(R.id.btn_registerSave)
        database = Room.databaseBuilder(this.requireContext(), UserDatabase::class.java, "users.db")
            .allowMainThreadQueries()
            .build()

        btnRegister?.setOnClickListener {
            //if(isValidRegistration())
            //else
            insertUser()
            logUsers()
            navigateToLogin()
        }
    }


    private fun isValidRegistration(): Boolean {
        TODO()
    }

    private fun insertUser(){
        //TODO those fields must be non-nullable for valid registration
        val name = etName?.text.toString()
        val email = etEmail?.text.toString()
        val password = etPassword?.text.toString()

        val user = User(name = name, email = email, password = password)
        database?.userDao()?.insertUser(user)
    }

    private fun logUsers() {
        val users = database?.userDao()?.getAllUsers()
        Log.d("###", users.toString())
    }

    private fun navigateToLogin(){
        parentFragmentManager.popBackStack()
    }
}