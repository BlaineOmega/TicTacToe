package com.blaine.tictactoe.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blaine.tictactoe.R;

/**
 * Created by blaineanderson on 3/14/17.
 */

public class GameSetupFragment extends Fragment {

    private final String PLAYER_1 = "PLAYER1";
    private final String PLAYER_2 = "PLAYER2";
    private final String ROWS = "ROWS";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.game_setup_fragment, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        Button startGame = (Button) getActivity().findViewById(R.id.start_game);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText player1 = (EditText) getActivity().findViewById(R.id.player1_name);
                final EditText player2 = (EditText) getActivity().findViewById(R.id.player2_name);
                final EditText num_rows = (EditText) getActivity().findViewById(R.id.num_rows);

                final String player1Name = player1.getText().toString();
                final String player2Name = player2.getText().toString();

                int r = 0;
                if(num_rows.getText().length() > 0){
                    r = Integer.valueOf(num_rows.getText().toString());
                }

                final int rows = r;

                //Verify the user input data
                if(rows < 3 || rows > 7){
                    Toast.makeText(getActivity().getApplicationContext(), "Please set row number between 3 and 7", Toast.LENGTH_LONG).show();
                }else {

                    if(!player1Name.equals("") &&
                            !player2Name.equals("")){

                        //Pass player names and game options to next fragment
                        Bundle b = new Bundle();
                        b.putString(PLAYER_1, player1Name);
                        b.putString(PLAYER_2, player2Name);
                        b.putInt(ROWS, rows);


                        //Start game
                        GamePlayFragment gamePlayFragment = new GamePlayFragment();
                        gamePlayFragment.setArguments(b);
                        getFragmentManager().beginTransaction()
                                .addToBackStack("Setup")
                                .replace(R.id.container, gamePlayFragment, "GamePlay")
                                .commit();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "Please set player names", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }


}

