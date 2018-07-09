package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character;

public class CharacterDetailActivity extends BaseActivity {

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

        ImageView characterImage = (ImageView) findViewById(R.id.character_image);
        TextView characterDescription = (TextView) findViewById(R.id.character_description);

        Picasso.get().load(character.getThumbnailUrl()).into(characterImage);
        characterDescription.setText(character.getDescription());


    }

    //pegando o menu na toolbar (topo da tela)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.character_detail, menu);

        //criando a intent e validando tudo, sem precidar de actios send
        ShareCompat.IntentBuilder intent = ShareCompat.IntentBuilder.from(this).
                setText(character.getDescription()).setType("text/plain");
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider
                (menu.findItem(R.id.action_share));
        actionProvider.setShareIntent(intent.getIntent());

        return super.onCreateOptionsMenu(menu);
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

