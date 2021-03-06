package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class GameMemoramaActivity : AppCompatActivity() {
    lateinit var jugador1Text: TextView
    lateinit var jugador2Text: TextView
    lateinit var refInviEnvi: DatabaseReference
    lateinit var refTablero: DatabaseReference
    var refCarta: DatabaseReference = FirebaseDatabase.getInstance().getReference("Carta_jugada")
    var refBoton: DatabaseReference = FirebaseDatabase.getInstance().getReference("Boton_apretado")
    lateinit var imgRandom: Image


    lateinit var btnPressed: ImageButton
    lateinit var imagePressed: Image

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

    val cards2 = mutableListOf<Int>()
    val btnImg2= mutableListOf<Int>()

    //FUNCION TABLERO 2
    fun rellenar2(dataImage: Int?, dataButton: Int?){

            if(dataImage != null){
                cards2.add(dataImage)
            }

            if(dataButton != null){
                btnImg2.add(dataButton)

            }

        if(cards2.size==8 && btnImg2.size== 16){
            val btn1= findViewById<ImageButton>(btnImg2[0])
            btn1.setOnClickListener{ v ->
                isFav = !isFav;
                if (isFav){
                    btn1.setImageResource(cards2[0]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[0])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn1.id)
                }
                else{
                    btn1.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn1.id)
                }
            }

            val btn2= findViewById<ImageButton>(btnImg2[1])
            btn2.setOnClickListener{ v ->
                isFav2 = !isFav2;
                if (isFav2){
                    btn2.setImageResource(cards2[0]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[0])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn2.id)
                }
                else{
                    btn2.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn2.id)
                }
            }

            val btn3= findViewById<ImageButton>(btnImg2[2])
            btn3.setOnClickListener{ v ->
                isFav3 = !isFav3;
                if (isFav3){
                    btn3.setImageResource(cards2[1]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[1])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn3.id)
                }
                else{
                    btn3.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn3.id)
                }
            }

            val btn4= findViewById<ImageButton>(btnImg2[3])
            btn4.setOnClickListener{ v ->
                isFav4 = !isFav4;
                if (isFav4){
                    btn4.setImageResource(cards2[1]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[1])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn4.id)
                }
                else{
                    btn4.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn4.id)
                }
            }

            val btn5= findViewById<ImageButton>(btnImg2[4])
            btn5.setOnClickListener{ v ->
                isFav5 = !isFav5;
                if (isFav5){
                    btn5.setImageResource(cards2[2]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[2])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn5.id)
                }
                else{
                    btn5.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn5.id)
                }
            }

            val btn6= findViewById<ImageButton>(btnImg2[5])
            btn6.setOnClickListener{ v ->
                isFav6 = !isFav6;
                if (isFav6){
                    btn6.setImageResource(cards2[2]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[2])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn6.id)
                }
                else{
                    btn6.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn6.id)
                }
            }

            val btn7= findViewById<ImageButton>(btnImg2[6])
            btn7.setOnClickListener{ v ->
                isFav7 = !isFav7;
                if (isFav7){
                    btn7.setImageResource(cards2[3]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[3])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn7.id)
                }
                else{
                    btn7.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn7.id)
                }
            }

            val btn8= findViewById<ImageButton>(btnImg2[7])
            btn8.setOnClickListener{ v ->
                isFav8 = !isFav8;
                if (isFav8){
                    btn8.setImageResource(cards2[3]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[3])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn8.id)
                }
                else{
                    btn8.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn8.id)
                }
            }

            val btn9= findViewById<ImageButton>(btnImg2[8])
            btn9.setOnClickListener{ v ->
                isFav9 = !isFav9;
                if (isFav9){
                    btn9.setImageResource(cards2[4]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[4])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn9.id)
                }
                else{
                    btn9.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn9.id)
                }
            }

            val btn10= findViewById<ImageButton>(btnImg2[9])
            btn10.setOnClickListener{ v ->
                isFav10 = !isFav10;
                if (isFav10){
                    btn10.setImageResource(cards2[4]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[4])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn10.id)
                }
                else{
                    btn10.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn10.id)
                }
            }

            val btn11= findViewById<ImageButton>(btnImg2[10])
            btn11.setOnClickListener{ v ->
                isFav11 = !isFav11;
                if (isFav11){
                    btn11.setImageResource(cards2[5]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[5])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn11.id)
                }
                else{
                    btn11.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn11.id)
                }
            }

            val btn12= findViewById<ImageButton>(btnImg2[11])
            btn12.setOnClickListener{ v ->
                isFav12 = !isFav12;
                if (isFav12){
                    btn12.setImageResource(cards2[5]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[5])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn12.id)
                }
                else{
                    btn12.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn12.id)
                }
            }

            val btn13= findViewById<ImageButton>(btnImg2[12])
            btn13.setOnClickListener{ v ->
                isFav13 = !isFav13;
                if (isFav13){
                    btn13.setImageResource(cards2[6]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[6])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn13.id)
                }
                else{
                    btn13.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn13.id)
                }
            }

            val btn14= findViewById<ImageButton>(btnImg2[13])
            btn14.setOnClickListener{ v ->
                isFav14 = !isFav14;
                if (isFav14){
                    btn14.setImageResource(cards2[6]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[6])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn14.id)
                }
                else{
                    btn14.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn14.id)
                }
            }

            val btn15= findViewById<ImageButton>(btnImg2[14])
            btn15.setOnClickListener{ v ->
                isFav15 = !isFav15;
                if (isFav15){
                    btn15.setImageResource(cards2[7]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[7])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn15.id)
                }
                else{
                    btn15.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn15.id)
                }
            }

            val btn16= findViewById<ImageButton>(btnImg2[15])
            btn16.setOnClickListener{ v ->
                isFav16 = !isFav16;
                if (isFav16){
                    btn16.setImageResource(cards2[7]);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(cards2[7])
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn16.id)
                }
                else{
                    btn16.setImageResource(R.drawable.fondo);
                    refCarta= FirebaseDatabase.getInstance().getReference("Carta_jugada")
                    refCarta.setValue(R.drawable.fondo)
                    refBoton= FirebaseDatabase.getInstance().getReference("Boton_apretado")
                    refBoton.setValue(btn16.id)
                }
            }
        }

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_memorama)
        btnPressed = findViewById(R.id.boton00)

        refCarta.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!= null){
                    var imgInt = (snapshot.value as Long).toInt()
                    btnPressed.setImageResource(imgInt)
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        refBoton.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value!= null) {
                    var btnInt = (snapshot.value as Long).toInt()
                    btnPressed = findViewById<ImageButton>(btnInt)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

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

        refTablero = FirebaseDatabase.getInstance().getReference("Tablero")






        refTablero.child("imagenes").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var imgInt = (snapshot.value as Long).toInt()

                rellenar2(imgInt, null)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        refTablero.child("botones").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var btnInt = (snapshot.value as Long).toInt()

                rellenar2(null, btnInt)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



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
                    btn1.setImageResource(cards[0])
                    refCarta.setValue(cards[0])
                    refBoton.setValue(btn1.id)
                }
                else{
                    btn1.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn1.id)
                }
            }

            val btn2= findViewById<ImageButton>(btnImg[1])
            btn2.setOnClickListener{ v ->
                isFav2 = !isFav2;
                if (isFav2){
                    btn2.setImageResource(cards[0]);
                    refCarta.setValue(cards[0])
                    refBoton.setValue(btn2.id)
                }
                else{
                    btn2.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn2.id)
                }
            }

            val btn3= findViewById<ImageButton>(btnImg[2])
            btn3.setOnClickListener{ v ->
                isFav3 = !isFav3;
                if (isFav3){
                    btn3.setImageResource(cards[2]);
                    refCarta.setValue(cards[2])
                    refBoton.setValue(btn3.id)
                }
                else{
                    btn3.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn3.id)
                }
            }

            val btn4= findViewById<ImageButton>(btnImg[3])
            btn4.setOnClickListener{ v ->
                isFav4 = !isFav4;
                if (isFav4){
                    btn4.setImageResource(cards[1]);
                    refCarta.setValue(cards[1])
                    refBoton.setValue(btn4.id)
                }
                else{
                    btn4.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn4.id)
                }
            }

            val btn5= findViewById<ImageButton>(btnImg[4])

            btn5.setOnClickListener{ v ->
                isFav5 = !isFav5;
                if (isFav5){
                    btn5.setImageResource(cards[2]);
                    refCarta.setValue(cards[2])
                    refBoton.setValue(btn5.id)
                }
                else{
                    btn5.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn5.id)
                }
            }

            val btn6= findViewById<ImageButton>(btnImg[5])
            btn6.setOnClickListener{ v ->
                isFav6 = !isFav6;
                if (isFav6){
                    btn6.setImageResource(cards[2]);
                    refCarta.setValue(cards[2])
                    refBoton.setValue(btn6.id)
                }
                else{
                    btn6.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn6.id)
                }
            }

            val btn7= findViewById<ImageButton>(btnImg[6])
            btn7.setOnClickListener{ v ->
                isFav7 = !isFav7;
                if (isFav7){
                    btn7.setImageResource(cards[3]);
                    refCarta.setValue(cards[3])
                    refBoton.setValue(btn7.id)
                }
                else{
                    btn7.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn7.id)
                }
            }

            val btn8= findViewById<ImageButton>(btnImg[7])
            btn8.setOnClickListener{ v ->
                isFav8 = !isFav8;
                if (isFav8){
                    btn8.setImageResource(cards[3]);
                    refCarta.setValue(cards[3])
                    refBoton.setValue(btn8.id)
                }
                else{
                    btn8.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn8.id)
                }
            }

            val btn9= findViewById<ImageButton>(btnImg[8])
            btn9.setOnClickListener{ v ->
                isFav9 = !isFav9;
                if (isFav9){
                    btn9.setImageResource(cards[4]);
                    refCarta.setValue(cards[4])
                    refBoton.setValue(btn9.id)
                }
                else{
                    btn9.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn9.id)
                }
            }

            val btn10= findViewById<ImageButton>(btnImg[9])
            btn10.setOnClickListener{ v ->
                isFav10 = !isFav10;
                if (isFav10){
                    btn10.setImageResource(cards[4]);
                    refCarta.setValue(cards[4])
                    refBoton.setValue(btn10.id)
                }
                else{
                    btn10.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn10.id)
                }
            }

            val btn11= findViewById<ImageButton>(btnImg[10])
            btn11.setOnClickListener{ v ->
                isFav11 = !isFav11;
                if (isFav11){
                    btn11.setImageResource(cards[5]);
                    refCarta.setValue(cards[5])
                    refBoton.setValue(btn11.id)
                }
                else{
                    btn11.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn11.id)
                }
            }

            val btn12= findViewById<ImageButton>(btnImg[11])
            btn12.setOnClickListener{ v ->
                isFav12 = !isFav12;
                if (isFav12){
                    btn12.setImageResource(cards[5]);
                    refCarta.setValue(cards[5])
                    refBoton.setValue(btn12.id)
                }
                else{
                    btn12.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn12.id)
                }
            }

            val btn13= findViewById<ImageButton>(btnImg[12])
            btn13.setOnClickListener{ v ->
                isFav13 = !isFav13;
                if (isFav13){
                    btn13.setImageResource(cards[6]);
                    refCarta.setValue(cards[6])
                    refBoton.setValue(btn13.id)
                }
                else{
                    btn13.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn13.id)
                }
            }

            val btn14= findViewById<ImageButton>(btnImg[13])
            btn14.setOnClickListener{ v ->
                isFav14 = !isFav14;
                if (isFav14){
                    btn14.setImageResource(cards[6]);
                    refCarta.setValue(cards[6])
                    refBoton.setValue(btn14.id)
                }
                else{
                    btn14.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn14.id)
                }
            }

            val btn15= findViewById<ImageButton>(btnImg[14])
            btn15.setOnClickListener{ v ->
                isFav15 = !isFav15;
                if (isFav15){
                    btn15.setImageResource(cards[7]);
                    refCarta.setValue(cards[7])
                    refBoton.setValue(btn15.id)
                }
                else{
                    btn15.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn15.id)
                }
            }

            val btn16= findViewById<ImageButton>(btnImg[15])
            btn16.setOnClickListener{ v ->
                isFav16 = !isFav16;
                if (isFav16){
                    btn16.setImageResource(cards[7]);
                    refCarta.setValue(cards[7])
                    refBoton.setValue(btn16.id)
                }
                else{
                    btn16.setImageResource(R.drawable.fondo);
                    refCarta.setValue(R.drawable.fondo)
                    refBoton.setValue(btn16.id)
                }
            }

            for(i in 0..7){
                refTablero.child("imagenes").child("img" + i).setValue(cards[i])
            }

            for(j in 0..15){
                refTablero.child("botones").child("btn" + j).setValue(btnImg[j])
            }
        }



    }
}
