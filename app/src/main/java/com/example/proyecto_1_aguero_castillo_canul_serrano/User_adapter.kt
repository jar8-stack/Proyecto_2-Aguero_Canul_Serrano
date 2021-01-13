package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class User_adapter(val uCtx: Context,val layoutResId: Int,val userList: List<User_fire>)
    : ArrayAdapter<User_fire>(uCtx, layoutResId, userList) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater= LayoutInflater.from(uCtx)
            val view: View = layoutInflater.inflate(layoutResId, null)

            var user= userList[position]


            return view
        }
    }