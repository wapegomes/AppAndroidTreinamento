package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character;

public class CharacterDetailActivity extends AppCompatActivity {

    private Character character;

    //no oncreate se definie o layout da activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setando o layout
        setContentView(R.layout.activity_character_detail);

        //inserindo toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //mesmo comportament da action bar, mas continua customizavel
        setSupportActionBar(toolbar);
        //seta de voltar para a home - recria a pilha - volta para a parent a activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //intent são mensagens entre telas, é possível enviar e receber informações
        character = (Character) getIntent().getSerializableExtra("character");
        //colocando título no topo da tela
        setTitle(character.getName());

        ImageView characterImage = (ImageView)findViewById(R.id.character_image_icon);
        TextView characterDescription = (TextView) findViewById(R.id.character_description);

        Picasso.get().load(character.getThumbnailUrl()).resize(50, 50)
                .centerCrop()
                .into(characterImage);
        characterDescription.setText(character.getDescription());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //validando se o usuario clicou no botao share
        if (item.getItemId() == R.id.action_share) {
            share();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //metodo para compartilhar contéudo
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, character.getDescription());
        intent.putExtra(Intent.EXTRA_TITLE, character.getName());
        //informando que tipo de arquivo pode ser commpartilhado, neste cado todos tipos
        intent.setType("*/*");
        //verificando se o usuario tem apps para compartilhar
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}