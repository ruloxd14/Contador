package molina.raul.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var cuenta: Int = 0
    var click: String? = "Clicks"
    lateinit var tv_cuenta: TextView
    lateinit var et_click: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma: Button = findViewById(R.id.bMas)
        val btn_resta: Button = findViewById(R.id.bMenos)
        val btn_borrar: Button = findViewById(R.id.bBorrar)
        tv_cuenta = findViewById(R.id.tv_clicks)
        et_click = findViewById(R.id.et_click)

        btn_suma.setOnClickListener {
            cuenta++
            tv_cuenta.setText("$cuenta")
        }

        btn_resta.setOnClickListener {
            cuenta--
            tv_cuenta.setText("$cuenta")
        }

        btn_borrar.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Borrar",
                        DialogInterface.OnClickListener { dialog, id ->
                            cuenta = 0
                            tv_cuenta.setText("$cuenta")
                        })
                    setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                // Set other dialog properties
                builder?.setMessage("¿Seguro que desea borra la cuenta de " + click + " ?")
                    .setTitle("Si")

                // Create the AlertDialog
                builder.create()
            }
            alertDialog?.show()
        }


    }

    override fun onPause() {
        super.onPause()

        val sharedPref2 = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val editor = sharedPref2.edit()

        click = et_click.text.toString()

        editor.putString("Click", click)
        editor.putInt("contador", cuenta)
        editor.commit()

    }

    override fun onResume() {
        super.onResume()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        cuenta = sharedPref.getInt("contador",0)
        click = sharedPref.getString("Click","Clicks")
        tv_cuenta.setText("$cuenta")
        et_click.setText(click)


    }
}