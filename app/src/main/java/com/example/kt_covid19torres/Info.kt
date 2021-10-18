package com.example.kt_covid19torres

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_info.*

class Info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        toolbar.setOnClickListener { finish() }

        val arrayBoletim = this.intent.getParcelableExtra<Boletim>("Boletim")

        valSuspects.text = arrayBoletim?.suspeitos.toString()
        valConfirmed.text = arrayBoletim?.confirmados.toString()
        valDiscarded.text = arrayBoletim?.descartados.toString()
        valMonitoring.text = arrayBoletim?.monitoramento.toString()
        valCured.text = arrayBoletim?.mortes.toString()
        valSusHomeIsolation.text = arrayBoletim?.curados.toString()
        valHosIsolation.text = arrayBoletim?.sDomiciliar.toString()
        valConfHomeIsolation.text = arrayBoletim?.sHospitalar.toString()
        valDeaths.text = arrayBoletim?.Chospitalar.toString()
        infoDate.text = arrayBoletim?.data
        infoHour.text = arrayBoletim?.hora

        println(arrayBoletim.toString())
    }
}