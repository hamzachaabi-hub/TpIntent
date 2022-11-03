package com.example.myapplicationintent

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    //declaration
    lateinit var secondNom : TextView
    lateinit var SecondDate : TextView
    lateinit var SecondMail : TextView
    lateinit var secondClasse : TextView
    lateinit var shareit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //cordination avec le layout
        secondNom = findViewById(R.id.secondNom)
        SecondDate = findViewById(R.id.SecondDate)
        SecondMail= findViewById(R.id.SecondMail)
        secondClasse= findViewById(R.id.secondClasse)
        shareit = findViewById(R.id.shareit)

        //recieving the data and naffchiweha
        val bundle = intent.extras
        if (bundle != null){
            secondNom.text = "Nom et Prenom = ${bundle.getString("Nom et Prenom")}"
            SecondDate.text = "Date de Naissance = ${bundle.getString("Date de Naissance")}"
            SecondMail.text = "mail = ${bundle.getString("mail")}"
            secondClasse.text = "classe = ${bundle.getString("classe")}"
        }

        shareit.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND)
        val title = resources.getString(R.string.app_name)
        val chooser = Intent.createChooser(intent, title)
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext,"DONE !! ",Toast.LENGTH_SHORT).show()
        }
    }
    }

}