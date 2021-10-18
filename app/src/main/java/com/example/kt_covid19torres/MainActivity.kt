package com.example.kt_covid19torres

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private var listaBoletim = arrayListOf<Boletim>()
    private var adapter = Adapter(listaBoletim)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        readJson(this)
        listData.layoutManager = LinearLayoutManager(applicationContext)
        listData.itemAnimator = DefaultItemAnimator()
        listData.adapter = adapter
    }
    fun readJson(context: Context) {
        var json: String? = null
        try {
            val inputStream: InputStream = context.assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
            var jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                var js = jsonArray.getJSONObject(i)
                val dia = formatarData(js.getString("boletim").substring(0, 10))

                var boletim = Boletim(
                    js.getString("Suspeitos").toInt(),
                    js.getString("Confirmados").toInt(),
                    js.getString("Descartados").toInt(),
                    js.getString("Monitoramento").toInt(),
                    js.getString("Curados").toInt(),
                    js.getString("Sdomiciliar").toInt(),
                    js.getString("Shopitalar").toInt(),
                    js.getString("Chospitalar").toInt(),
                    js.getString("mortes").toInt(),
                    dia,
                    js.getString("boletim").substring(11, 16)
                )
                listaBoletim.add(boletim)
            }
        } catch (e: IOException) {
            Log.e("Erro", "Impossivel ler JSON")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatarData(data: String): String {
        val diaString = data
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(diaString)
        var formattedDate = date.format(formatter)
        return formattedDate
    }
}