package br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.Mock;
import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.api.CharacterApi;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Character;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.adapters.CharacterAdapter;


public class CharacterFragment extends Fragment {

        private RecyclerView recyclerView;
        private CharacterAdapter characterAdapter;


        public static Fragment newInstance() {
            return new CharacterFragment();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_recycler, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            int collum = getResources().getInteger(R.integer.personagem_collum);

            GridLayoutManager layoutManager =
                    new GridLayoutManager(getActivity(), collum);

            recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(layoutManager);


            getCharacters();

        }

    private void getCharacters(){

        final CharacterApi characterApi = new CharacterApi(getActivity());
        characterApi.characters(new CharacterApi.OnCharactersListener() {
            @Override
            public void onCharacters(final List<Character> characters, int errorCode) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (characters != null) {
                            characterAdapter = new CharacterAdapter(getActivity(), characters, recyclerView);
                            recyclerView.setAdapter(characterAdapter);
                        }
                        else {
                            Toast.makeText(getActivity(), R.string.msg_error_generic,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
