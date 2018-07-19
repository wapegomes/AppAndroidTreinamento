package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.bd.SQLiteHelper;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character;
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelImage;
import br.com.wpgomes.treinamento.appcasttreinamento.service.MP3Player;
import br.com.wpgomes.treinamento.appcasttreinamento.service.MP3Service;
import br.com.wpgomes.treinamento.appcasttreinamento.util.Constants;


/**
 * Created by wgomes on 17/06/16.
 */

public class CharacterDetailActivity extends BaseActivity {

    private Character character;
    private MP3Player mp3Player;
    private SQLiteDatabase db;
    private List<Character> characterList = new ArrayList<>();


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mp3Player = ((MP3Service.MP3Binder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp3Player = null;
        }
    };

    private void connectService() {
        Intent intent = new Intent(this, MP3Service.class);
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.unbindService(serviceConnection);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectService();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = SQLiteHelper.getDatabase(this);

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

        Picasso.get().load(character.thumbnail.getImageUrl(MarvelImage.Size.DETAIL)).into(characterImage);
        characterDescription.setText(character.getDescription());

        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3Player.play("http://52.23.170.89/wp-content/uploads/2017/09/james-bond.mp3");
            }
        });

        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3Player.stop();
            }
        });


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


        //ShareCompat.configureMenuItem(menu, R.id.action_share, intent);
        return super.onCreateOptionsMenu(menu);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //validando se o usuario clicou no botao share
        if (item.getItemId() == R.id.action_favorite) {
            registerRemoveFavorite(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void registerRemoveFavorite(MenuItem item) {


        if (!db.isOpen()) {
            db = SQLiteHelper.getDatabase(this);
        }
        if (!recuperaDado().isEmpty()) {
            db.delete(Constants.CHARACTER_TABLE, "name=?", new String[]{character.getName()});
            item.setIcon(R.drawable.ic_action_not_like);

        } else {
            ContentValues dados = new ContentValues();
            dados.put("name", character.getName());
            dados.put("description", character.getDescription());
            dados.put("favorite", 1);

            db.insert(Constants.CHARACTER_TABLE, null, dados);
            item.setIcon(R.drawable.ic_action_liked);

        }

        db.close();
    }

    private List<Character> recuperaDado() {

        Cursor cursor = null;
        String where = "name=?";
        String[] colunas = new String[]{"id, name", "description", "favorite"};
        String argumentos[] = new String[]{character.getName()};
        cursor = db.query("character", colunas, where, argumentos, null, null, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            characterList.add(montaDadoCharacter(cursor));
        }

        return characterList;
    }

    private Character montaDadoCharacter(Cursor cursor) {
        Character caracter = new Character();
        caracter.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
        caracter.setName(cursor.getString(cursor.getColumnIndex("name")));
        caracter.setDescription(cursor.getString(cursor.getColumnIndex("description")));
//        caracter.setFavorite((long) cursor.getInt(cursor.getColumnIndex("favorite")));

        return caracter;
    }


}

