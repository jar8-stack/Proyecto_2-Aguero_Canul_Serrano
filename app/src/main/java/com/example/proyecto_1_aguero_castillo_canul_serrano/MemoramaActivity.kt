package com.example.proyecto_1_aguero_castillo_canul_serrano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.proyecto_1_aguero_castillo_canul_serrano.prueba
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_memorama.*

class MemoramaActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var refPerUser: DatabaseReference
    lateinit var userList: MutableList<User_fire>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama)

        userList= mutableListOf()

        ref= FirebaseDatabase.getInstance().getReference("Usuarios")

        listView= findViewById(R.id.listUsers)

        //hola mundo
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    for(u in snapshot.children){
                        val user= u.getValue(User_fire::class.java)
                        if (user != null) {
                            user.userName= u.key.toString()
                        }
                        userList.add(user!!)
                    }

                    val adapter= User_adapter(applicationContext, R.layout.users, userList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        });

        btnActivoPartida.setOnClickListener {
            var userName= intent.getStringExtra("userName")

            //Hola mundo
            val database = Firebase.database
            val myRef = database.getReference()
            var usuarios= database.getReference("users_waiting")

            //myRef.setValue("Hello "+userName.toString())
            myRef.child("Usuarios").child(userName).child("buscando_partida").setValue("si")
            myRef.child("Usuarios").child(userName).child("puntaje_por_partida").setValue(0)
        }

    }
}

private fun <E> MutableList<E>.add(element: String) {

}
