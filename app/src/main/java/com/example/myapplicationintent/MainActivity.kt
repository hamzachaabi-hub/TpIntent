package com.example.myapplicationintent

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    //declaration des variables
    lateinit var etName: EditText
    lateinit var etDate: EditText
    lateinit var etMail: EditText
    lateinit var etclasse: EditText
    lateinit var btnSend: Button
    lateinit var main: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Concatination des varibales avec le layout
        etName = findViewById(R.id.etName)
        etDate = findViewById(R.id.etDate)
        etMail = findViewById(R.id.etMail)
        etclasse = findViewById(R.id.etclasse)
        main = findViewById(R.id.main)
        btnSend = findViewById(R.id.btnSend)


        //Calnder
        etDate.setOnClickListener {
            val c = Calendar.getInstance()
            //declaration
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            //Function
            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    etDate.setText(dat)
                }, year, month, day
            )
            //Date Picker
            datePickerDialog.show()
        }


        btnSend.setOnClickListener {
            val etName = etName.text.toString()
            val etDate = etDate.text.toString()
            val etMail = etMail.text.toString()
            val etclasse = etclasse.text.toString()
            //envoyer les data
            val bundle = Bundle()
            //declaation des data pour les sender ghadika
            bundle.putString("Nom et Prenom", etName)
            bundle.putString("Date de Naissance", etDate)
            bundle.putString("mail", etMail)
            bundle.putString("classe", etclasse)

            if (TextUtils.isEmpty(etName) || TextUtils.isEmpty(etDate) || TextUtils.isEmpty(etMail) || TextUtils.isEmpty(
                    etclasse
                )
            ) {
                Toast.makeText(applicationContext, "Input is empty !! ", Toast.LENGTH_SHORT).show()
                //snackbar Toast
                val snackbar = Snackbar.make(main, "Input is empty !!", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        val snackbar = Snackbar.make(main, "Message is restored", Snackbar.LENGTH_LONG)
                    }
                snackbar.show()
            } else {//Partie el Dialogue
                val builder = AlertDialog.Builder(this)
                //ell messaget elli besh yate3ou
                builder.setMessage("Do you want to proceed ?")
                builder.setTitle("Alert !")
                builder.setCancelable(false)
                //idha clika YES
                builder.setPositiveButton("Yes") {

                        dialog, which ->
                    //passage d'une activity a l'autre
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                //Idha Clika NO
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.cancel()
                }
                //el decelencheur mtea el dialoque
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }
}









