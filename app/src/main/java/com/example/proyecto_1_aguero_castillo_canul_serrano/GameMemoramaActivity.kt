package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_game_memorama.*
import java.util.*


class GameMemoramaActivity : AppCompatActivity() {
    lateinit var jugador1Text: TextView
    lateinit var jugador2Text: TextView
    lateinit var refInviEnvi: DatabaseReference
    lateinit var refTablero: DatabaseReference
    lateinit var imgRandom: Image

    var isFav = false;
    var isFav2 = false;
    var isFav3 = false;
    var isFav4 = false;
    var isFav5 = false;
    var isFav6 = false;
    var isFav7 = false;
    var isFav8 = false;
    var isFav9 = false;
    var isFav10 = false;
    var isFav11 = false;
    var isFav12 = false;
    var isFav13 = false;
    var isFav14 = false;
    var isFav15 = false;
    var isFav16 = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_memorama)


        var jugador1= intent.getStringExtra("jugador1")
        var jugador2= intent.getStringExtra("jugador2")
        var jugadorOlo= intent.getStringExtra("jugadorOlo")

        jugador1Text= findViewById(R.id.jugador1)
        jugador2Text= findViewById(R.id.jugador2)

        jugador1Text.setText(jugador1)
        jugador2Text.setText(jugador2)

        refInviEnvi = FirebaseDatabase.getInstance().getReference("Usuarios")
        refInviEnvi.child(jugador2).child("invitaciones_enviadas").setValue("")
        refInviEnvi.child(jugador1).child("invitaciones_recibidas").setValue("")





        if(jugadorOlo== jugador1){
            refTablero = FirebaseDatabase.getInstance().getReference("Tablero")
            val cards = intArrayOf(
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7
            )
            //hola
            val btnImg= intArrayOf(
                R.id.boton00,
                R.id.boton01,
                R.id.boton02,
                R.id.boton03,
                R.id.boton04,
                R.id.boton05,
                R.id.boton06,
                R.id.boton07,
                R.id.boton08,
                R.id.boton09,
                R.id.boton10,
                R.id.boton11,
                R.id.boton12,
                R.id.boton13,
                R.id.boton14,
                R.id.boton15,
            )

            cards.shuffle()
            btnImg.shuffle()

            val btn1= findViewById<ImageButton>(btnImg[0])
            btn1.setOnClickListener{ v ->
                isFav = !isFav;
                if (isFav){
                    btn1.setImageResource(cards[0]);
                }
                else{
                    btn1.setImageResource(R.drawable.fondo);
                }
            }

            val btn2= findViewById<ImageButton>(btnImg[1])
            btn2.setOnClickListener{ v ->
                isFav2 = !isFav2;
                if (isFav2){
                    btn2.setImageResource(cards[0]);
                }
                else{
                    btn2.setImageResource(R.drawable.fondo);
                }
            }

            val btn3= findViewById<ImageButton>(btnImg[2])
            btn3.setOnClickListener{ v ->
                isFav3 = !isFav3;
                if (isFav3){
                    btn3.setImageResource(cards[1]);
                }
                else{
                    btn3.setImageResource(R.drawable.fondo);
                }
            }

            val btn4= findViewById<ImageButton>(btnImg[3])
            btn4.setOnClickListener{ v ->
                isFav4 = !isFav4;
                if (isFav4){
                    btn4.setImageResource(cards[1]);
                }
                else{
                    btn4.setImageResource(R.drawable.fondo);
                }
            }

            val btn5= findViewById<ImageButton>(btnImg[4])
            btn5.setOnClickListener{ v ->
                isFav5 = !isFav5;
                if (isFav5){
                    btn5.setImageResource(cards[2]);
                }
                else{
                    btn5.setImageResource(R.drawable.fondo);
                }
            }

            val btn6= findViewById<ImageButton>(btnImg[5])
            btn6.setOnClickListener{ v ->
                isFav6 = !isFav6;
                if (isFav6){
                    btn6.setImageResource(cards[2]);
                }
                else{
                    btn6.setImageResource(R.drawable.fondo);
                }
            }

            val btn7= findViewById<ImageButton>(btnImg[6])
            btn7.setOnClickListener{ v ->
                isFav7 = !isFav7;
                if (isFav7){
                    btn7.setImageResource(cards[3]);
                }
                else{
                    btn7.setImageResource(R.drawable.fondo);
                }
            }

            val btn8= findViewById<ImageButton>(btnImg[7])
            btn8.setOnClickListener{ v ->
                isFav8 = !isFav8;
                if (isFav8){
                    btn8.setImageResource(cards[3]);
                }
                else{
                    btn8.setImageResource(R.drawable.fondo);
                }
            }

            val btn9= findViewById<ImageButton>(btnImg[8])
            btn9.setOnClickListener{ v ->
                isFav9 = !isFav9;
                if (isFav9){
                    btn9.setImageResource(cards[4]);
                }
                else{
                    btn9.setImageResource(R.drawable.fondo);
                }
            }

            val btn10= findViewById<ImageButton>(btnImg[9])
            btn10.setOnClickListener{ v ->
                isFav10 = !isFav10;
                if (isFav10){
                    btn10.setImageResource(cards[4]);
                }
                else{
                    btn10.setImageResource(R.drawable.fondo);
                }
            }

            val btn11= findViewById<ImageButton>(btnImg[10])
            btn11.setOnClickListener{ v ->
                isFav11 = !isFav11;
                if (isFav11){
                    btn11.setImageResource(cards[5]);
                }
                else{
                    btn11.setImageResource(R.drawable.fondo);
                }
            }

            val btn12= findViewById<ImageButton>(btnImg[11])
            btn12.setOnClickListener{ v ->
                isFav12 = !isFav12;
                if (isFav12){
                    btn12.setImageResource(cards[5]);
                }
                else{
                    btn12.setImageResource(R.drawable.fondo);
                }
            }

            val btn13= findViewById<ImageButton>(btnImg[12])
            btn13.setOnClickListener{ v ->
                isFav13 = !isFav13;
                if (isFav13){
                    btn13.setImageResource(cards[6]);
                }
                else{
                    btn13.setImageResource(R.drawable.fondo);
                }
            }

            val btn14= findViewById<ImageButton>(btnImg[13])
            btn14.setOnClickListener{ v ->
                isFav14 = !isFav14;
                if (isFav14){
                    btn14.setImageResource(cards[6]);
                }
                else{
                    btn14.setImageResource(R.drawable.fondo);
                }
            }

            val btn15= findViewById<ImageButton>(btnImg[14])
            btn15.setOnClickListener{ v ->
                isFav15 = !isFav15;
                if (isFav15){
                    btn15.setImageResource(cards[7]);
                }
                else{
                    btn15.setImageResource(R.drawable.fondo);
                }
            }

            val btn16= findViewById<ImageButton>(btnImg[15])
            btn16.setOnClickListener{ v ->
                isFav16 = !isFav16;
                if (isFav16){
                    btn16.setImageResource(cards[7]);
                }
                else{
                    btn16.setImageResource(R.drawable.fondo);
                }
            }

            for(i in 0..7){
                refTablero.child("imagenes").child("img"+i).setValue(cards[i])
            }

            for(j in 0..15){
                refTablero.child("botones").child("btn"+j).setValue(btnImg[j])
            }
        }else if(jugadorOlo == jugador2){



            refTablero = FirebaseDatabase.getInstance().getReference("Tablero")

            val cards = mutableListOf<Int>()
            val btnImg= mutableListOf<Int>()






            refTablero.child("botones").addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot1: DataSnapshot) {
                    if(snapshot1!!.exists()){
                        for(btn in snapshot1.children){
                            val btnId= btn.value
                            btnImg.add(btnId as Int)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

            refTablero.child("imagenes").addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot2: DataSnapshot) {
                    if(snapshot2!!.exists()){
                        for(img in snapshot2.children){
                            val imgId= img.value
                            cards.add(imgId as Int)

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


            val btn1= findViewById<ImageButton>(btnImg[0])
            btn1.setOnClickListener{ v ->
                isFav = !isFav;
                if (isFav){
                    btn1.setImageResource(cards[0]);
                }
                else{
                    btn1.setImageResource(R.drawable.fondo);
                }
            }

            val btn2= findViewById<ImageButton>(btnImg[1])
            btn2.setOnClickListener{ v ->
                isFav2 = !isFav2;
                if (isFav2){
                    btn2.setImageResource(cards[0]);
                }
                else{
                    btn2.setImageResource(R.drawable.fondo);
                }
            }

            val btn3= findViewById<ImageButton>(btnImg[2])
            btn3.setOnClickListener{ v ->
                isFav3 = !isFav3;
                if (isFav3){
                    btn3.setImageResource(cards[1]);
                }
                else{
                    btn3.setImageResource(R.drawable.fondo);
                }
            }

            val btn4= findViewById<ImageButton>(btnImg[3])
            btn4.setOnClickListener{ v ->
                isFav4 = !isFav4;
                if (isFav4){
                    btn4.setImageResource(cards[1]);
                }
                else{
                    btn4.setImageResource(R.drawable.fondo);
                }
            }

            val btn5= findViewById<ImageButton>(btnImg[4])
            btn5.setOnClickListener{ v ->
                isFav5 = !isFav5;
                if (isFav5){
                    btn5.setImageResource(cards[2]);
                }
                else{
                    btn5.setImageResource(R.drawable.fondo);
                }
            }

            val btn6= findViewById<ImageButton>(btnImg[5])
            btn6.setOnClickListener{ v ->
                isFav6 = !isFav6;
                if (isFav6){
                    btn6.setImageResource(cards[2]);
                }
                else{
                    btn6.setImageResource(R.drawable.fondo);
                }
            }

            val btn7= findViewById<ImageButton>(btnImg[6])
            btn7.setOnClickListener{ v ->
                isFav7 = !isFav7;
                if (isFav7){
                    btn7.setImageResource(cards[3]);
                }
                else{
                    btn7.setImageResource(R.drawable.fondo);
                }
            }

            val btn8= findViewById<ImageButton>(btnImg[7])
            btn8.setOnClickListener{ v ->
                isFav8 = !isFav8;
                if (isFav8){
                    btn8.setImageResource(cards[3]);
                }
                else{
                    btn8.setImageResource(R.drawable.fondo);
                }
            }

            val btn9= findViewById<ImageButton>(btnImg[8])
            btn9.setOnClickListener{ v ->
                isFav9 = !isFav9;
                if (isFav9){
                    btn9.setImageResource(cards[4]);
                }
                else{
                    btn9.setImageResource(R.drawable.fondo);
                }
            }

            val btn10= findViewById<ImageButton>(btnImg[9])
            btn10.setOnClickListener{ v ->
                isFav10 = !isFav10;
                if (isFav10){
                    btn10.setImageResource(cards[4]);
                }
                else{
                    btn10.setImageResource(R.drawable.fondo);
                }
            }

            val btn11= findViewById<ImageButton>(btnImg[10])
            btn11.setOnClickListener{ v ->
                isFav11 = !isFav11;
                if (isFav11){
                    btn11.setImageResource(cards[5]);
                }
                else{
                    btn11.setImageResource(R.drawable.fondo);
                }
            }

            val btn12= findViewById<ImageButton>(btnImg[11])
            btn12.setOnClickListener{ v ->
                isFav12 = !isFav12;
                if (isFav12){
                    btn12.setImageResource(cards[5]);
                }
                else{
                    btn12.setImageResource(R.drawable.fondo);
                }
            }

            val btn13= findViewById<ImageButton>(btnImg[12])
            btn13.setOnClickListener{ v ->
                isFav13 = !isFav13;
                if (isFav13){
                    btn13.setImageResource(cards[6]);
                }
                else{
                    btn13.setImageResource(R.drawable.fondo);
                }
            }

            val btn14= findViewById<ImageButton>(btnImg[13])
            btn14.setOnClickListener{ v ->
                isFav14 = !isFav14;
                if (isFav14){
                    btn14.setImageResource(cards[6]);
                }
                else{
                    btn14.setImageResource(R.drawable.fondo);
                }
            }

            val btn15= findViewById<ImageButton>(btnImg[14])
            btn15.setOnClickListener{ v ->
                isFav15 = !isFav15;
                if (isFav15){
                    btn15.setImageResource(cards[7]);
                }
                else{
                    btn15.setImageResource(R.drawable.fondo);
                }
            }

            val btn16= findViewById<ImageButton>(btnImg[15])
            btn16.setOnClickListener{ v ->
                isFav16 = !isFav16;
                if (isFav16){
                    btn16.setImageResource(cards[7]);
                }
                else{
                    btn16.setImageResource(R.drawable.fondo);
                }
            }


        }



    }
}

