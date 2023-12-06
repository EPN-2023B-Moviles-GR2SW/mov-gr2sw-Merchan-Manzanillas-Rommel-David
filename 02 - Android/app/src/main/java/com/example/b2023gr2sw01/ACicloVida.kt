package com.example.b2023gr2sw01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    var textoGlobal=""

    fun mostratSnackbar(texto:String){
        textoGlobal += texto
        val snack = Snackbar.make(findViewById(R.id.cl_ciclo_vida),
            textoGlobal, Snackbar.LENGTH_LONG)
        snack.show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostratSnackbar("Me pican los cocos")
        mostratSnackbar("OnCreate")
    }

    override fun onStart(){
        super.onStart()
        mostratSnackbar("onStart")
    }

    override fun onResume(){
        super.onResume()
        mostratSnackbar("onResume")
    }

    override fun onRestart(){
        super.onRestart()
        mostratSnackbar("onResume")
    }

    override fun onPause(){
        super.onPause()
        mostratSnackbar("onPause")
    }

    override fun onStop(){
        super.onStop()
        mostratSnackbar("onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        mostratSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outSate: Bundle){
        outSate.run {
            putString("textoGuardado", textoGlobal)
        }
    super.onSaveInstanceState(outSate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)

        val textoRecuperado:String? = savedInstanceState
            .getString("textoGuardado")

        if (textoRecuperado!=null){
            mostratSnackbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }

}
