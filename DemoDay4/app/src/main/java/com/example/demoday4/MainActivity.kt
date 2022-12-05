package com.example.demoday4

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.demoday4.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arr = arrayListOf<String>("Blue", "red", "yellow")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                Toast.makeText(
                    applicationContext,
                    cal.time.toString(), Toast.LENGTH_SHORT
                ).show()
            }
//        binding.button.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.apply {
//                setIcon(R.drawable.ic_launcher_background)
//                setTitle("Title dialog")
//                setMessage("Content of dialog")
//                setCancelable(false)
//                setPositiveButton("OK") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "OK", Toast.LENGTH_SHORT).show()
//                }
//                setNegativeButton("NO") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "NO", Toast.LENGTH_SHORT).show()
//                }
//                setNeutralButton("Maybe") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "Maybe", Toast.LENGTH_SHORT).show()
//                }
//                show()
//            }


//            DatePickerDialog(
//                this@MainActivity, dateSetListener,
//                cal.get(Calendar.YEAR),
//                cal.get(Calendar.MONTH),
//                cal.get(Calendar.DAY_OF_MONTH)
//            ).show()
//            withEditText(binding.root)
    }
}
//    fun withEditText(view: View) {
//        val builder = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        builder.setTitle("Login")
//        val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
//        builder.setView(dialogLayout)
//        builder.show()
//    }
