package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    lateinit var refBack: DatabaseReference
    lateinit var refInviEnvi: DatabaseReference
    lateinit var globalRef : DatabaseReference

    override fun onBackPressed() {
        refBack = FirebaseDatabase.getInstance().getReference("Usuarios")
        var userName= intent.getStringExtra("userName")

        refBack.child(userName).child("buscando_partida").setValue("no")

        val intent: Intent = Intent(this, SelectPlayerActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama)

        userList = mutableListOf()
        var userName= intent.getStringExtra("userName")

        ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        globalRef = FirebaseDatabase.getInstance().getReference("Partidas en vivo")


        listView= findViewById(R.id.listUsers)

        //hola mundo
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    userList.clear()
                    for(u in snapshot.children){
                        val user= u.getValue(User_fire::class.java)
                        if (user != null) {
                            user.userName= u.key.toString()
                        }
                        if (user != null) {
                            if(user.buscando_partida=="si" && user.userName != userName){
                                userList.add(user!!)
                            }
                        }

                    }

                    val adapter= User_adapter(applicationContext, R.layout.users, userList)
                    listView.adapter = adapter


                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        });

        val intentMemo2: Intent = Intent(this, GameMemoramaActivity::class.java).apply {
            putExtra("jugador2", userName)
            putExtra("jugadorOlo", userName)
        }
        globalRef.child("MatchTest").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user1= snapshot.child("user1").value
                intentMemo2.apply {
                    putExtra("jugador1", user1.toString())

                    if(user1 != null){
                      startActivity(intentMemo2)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        val intentMemo: Intent = Intent(this, GameMemoramaActivity::class.java).apply {
            putExtra("jugadorOlo", userName)
            putExtra("jugador1", userName)
        }
        refInviEnvi = FirebaseDatabase.getInstance().getReference("Usuarios")
        refInviEnvi.child(userName).child("invitaciones_recibidas").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var userNameInvita=""
                if(snapshot!!.exists()){

                    userNameInvita+= snapshot.value

                    if(userNameInvita != ""){
                        val mAlertDialog= AlertDialog.Builder(this@MemoramaActivity)
                        mAlertDialog.setTitle("Invitación de partida")
                        mAlertDialog.setMessage(userNameInvita+" te ha invitado a una partida ¿Aceptas?")
                        mAlertDialog.setPositiveButton("Si") GameMemoramaActivity@{ dialog, id ->


                            intentMemo.apply {
                                putExtra("jugador2", userNameInvita)
                            }

                            globalRef.child("MatchTest").child(userName).child("Puntuacion").setValue(0)

                            startActivity(intentMemo)
                            return@GameMemoramaActivity
                        }

                        mAlertDialog.setNegativeButton("No"){ dialog, id ->
                            refInviEnvi.child(userNameInvita).child("invitaciones_enviadas").setValue("")
                            refInviEnvi.child(userName).child("invitaciones_recibidas").setValue("")
                            dialog.dismiss()
                        }

                        mAlertDialog.show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })




            //Hola mundo
            val database = Firebase.database
            val myRef = database.getReference()
            var usuarios= database.getReference("users_waiting")

            //myRef.setValue("Hello "+userName.toString())
            myRef.child("Usuarios").child(userName).child("buscando_partida").setValue("si")
            myRef.child("Usuarios").child(userName).child("puntaje_por_partida").setValue(0)

        listView.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as User_fire

                var userNameInvi= itemValue.userName


                refInviEnvi = FirebaseDatabase.getInstance().getReference("Usuarios")

                refInviEnvi.child(userNameInvi).child("invitaciones_recibidas").setValue(userName)
                refInviEnvi.child(userName).child("invitaciones_enviadas").setValue(userNameInvi)

            }
        }



    }
}

private operator fun <T> MutableIterable<T>.get(i: Int) {

}


private fun <E> MutableList<E>.add(element: String) {

}
data class userInivita(var keyPrio: String, val username: String) {
    constructor() : this("", ""){

    }
}