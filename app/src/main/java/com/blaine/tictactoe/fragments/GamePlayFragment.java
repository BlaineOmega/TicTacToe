package com.blaine.tictactoe.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.blaine.tictactoe.R;
import com.blaine.tictactoe.utilities.ScoringUtility;

/**
 * Created by blaineanderson on 3/16/17.
 */

public class GamePlayFragment extends Fragment {

    private final String PLAYER_1 = "PLAYER1";
    private final String PLAYER_2 = "PLAYER2";
    private final String ROWS = "ROWS";

    String player1;
    String player2;

    int player1Score = 0;
    int player2Score = 0;
    int drawScore = 0;

    TextView player1ScoreView;
    TextView player2ScoreView;
    TableLayout table;

    int boardSize;
    int moves = 0;
    int[][] gameBoard;
    Context context;

    TextView playerTurn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_board_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        if(b != null){
            player1 = b.getString(PLAYER_1);
            player2 = b.getString(PLAYER_2);
            boardSize = b.getInt(ROWS);
        }

        context = getActivity().getApplicationContext();
        gameBoard = new int[boardSize][boardSize];
    }

    @Override
    public void onStart() {
        super.onStart();

        setupGameBoard();

        TextView player1Label = (TextView) getActivity().findViewById(R.id.player1);
        TextView player2Label = (TextView) getActivity().findViewById(R.id.player2);

        player1ScoreView = (TextView) getActivity().findViewById(R.id.player1score);
        player2ScoreView = (TextView) getActivity().findViewById(R.id.player2score);

        playerTurn = (TextView) getActivity().findViewById(R.id.p_turn);


        playerTurn.setText("It's " + player1 + "'s turn!");

        player1Label.setText(player1);
        player2Label.setText(player2);
    }

    private void setupGameBoard(){
        table = (TableLayout) getActivity().findViewById(R.id.game_board);
        TableRow row;

        for(int ii = 0; ii < boardSize;ii++){
            final int x = ii;
            row = new TableRow(getActivity().getApplicationContext());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            row.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));

            for(int jj=0; jj < boardSize; jj++){
                final int y = jj;
                final Button tile = new Button(context);
                tile.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));

                LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(3,3,3,3);

                if(ii == 0){
                    lp.topMargin = 0;
                }
                if(jj == 0){
                    lp.leftMargin = 0;
                }
                if(jj == boardSize -1){
                    lp.rightMargin = 0;
                }
                if(ii == boardSize - 1){
                    lp.bottomMargin = 0;
                }


                lp.weight = 1;
                tile.setLayoutParams(lp);

                tile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String player = String.valueOf(getPlayerMove());
                        tile.setText(getPlayerMarker());
                        tile.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
                        moves++;
                        tile.setClickable(false);
                        updateBoard(x, y, player);

                        if(ScoringUtility.checkBoardForWinner(gameBoard, Integer.valueOf(player))){
                            moves--;
                            playerTurn.setText(getPlayerName() + " Wins!");
                            buildEndOfGameAlert(getPlayerName(), getPlayerMove()).show();
                        }else if(moves == boardSize * boardSize){
                            playerTurn.setText(R.string.draw);
                            buildEndOfGameAlert("Cat's Game", 0).show();

                        }else{
                            playerTurn.setText("It's " + getPlayerName() + "'s turn.");
                        }
                    }
                });

                row.addView(tile);
            }

            table.addView(row);
        }
    }

    private AlertDialog buildEndOfGameAlert(String player, final int playerId){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              resetGame(playerId);
            }
        });

        builder.setNegativeButton(R.string.end_game, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        builder.setTitle(player + " Wins!")
                .setMessage(R.string.end_game_message);

        return builder.create();
    }

    private void resetGame(int player){
        playerTurn = (TextView) getActivity().findViewById(R.id.p_turn);
        playerTurn.setText("It's " + player1 + "'s turn!");


        if(player == 1){
            //player1Score++;
            player1ScoreView.setText(String.valueOf(++player1Score));
        }else if(player == 2){
            //player2Score++;
            player2ScoreView.setText(String.valueOf(++player2Score));

        }else{
            TextView draw = (TextView) getActivity().findViewById(R.id.drawScore);
            draw.setText(String.valueOf(++drawScore));
        }

        gameBoard = new int[boardSize][boardSize];
        moves = 0;

        resetGameBoard();
        setupGameBoard();
    }

    private void resetGameBoard(){

        table.removeAllViews();
    }

    private int getPlayerMove(){
        if(moves % 2 == 0){
            return 1;
        }
        return 2;
    }

    private String getPlayerMarker(){
        if(moves % 2 == 0){
            return "X";
        }
        return "O";
    }

    private String getPlayerName(){
        if(moves % 2 == 0){
            return player1;
        }
        return player2;
    }

    private void updateBoard(int x, int y, String player ){
        this.gameBoard[x][y] = Integer.valueOf(player);
    }

}
