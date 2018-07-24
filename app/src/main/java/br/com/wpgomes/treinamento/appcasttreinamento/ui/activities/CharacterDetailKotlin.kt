package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView

import br.com.wpgomes.treinamento.appcasttreinamento.R
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailKotlin : BaseActivity() {

    var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        character = intent.getSerializableExtra("character") as Character
        //colocando título no topo da tela
        setTitle(character!!.getName() + " Kotlin Activity")

        val characterDescription = findViewById<View>(R.id.character_description) as TextView

        Picasso.get().load(character!!.thumbnail.getImageUrl(MarvelImage.Size.DETAIL)).into(character_image)
        characterDescription.setText(character!!.getDescription())
    }


}