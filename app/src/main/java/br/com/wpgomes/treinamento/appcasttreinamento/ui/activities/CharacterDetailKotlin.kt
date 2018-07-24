package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.widget.Toolbar
import android.view.View

import br.com.wpgomes.treinamento.appcasttreinamento.R
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelImage
import br.com.wpgomes.treinamento.appcasttreinamento.service.MP3Player
import br.com.wpgomes.treinamento.appcasttreinamento.service.MP3Service
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailKotlin : BaseActivity() {

    var character: Character? = null

    private var mp3Player: MP3Player? = null


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mp3Player = (service as MP3Service.MP3Binder).service
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mp3Player = null
        }
    }

    private fun connectService() {
        val intent = Intent(this, MP3Service::class.java)
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    public override fun onStop() {
        super.onStop()
        this.unbindService(serviceConnection)
    }

    public override fun onStart() {
        super.onStart()
        connectService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        character = intent.getSerializableExtra("character") as Character
        //colocando título no topo da tela
        setTitle(character!!.getName() + " Kotlin Activity")

        Picasso.get().load(character!!.thumbnail.getImageUrl(MarvelImage.Size.DETAIL)).into(character_image)
        character_description.text = (character!!.getDescription().toString())

        play.setOnClickListener { mp3Player!!.play("http://52.23.170.89/wp-content/uploads/2017/09/james-bond.mp3") }

        stop.setOnClickListener { mp3Player!!.stop() }

    }

}