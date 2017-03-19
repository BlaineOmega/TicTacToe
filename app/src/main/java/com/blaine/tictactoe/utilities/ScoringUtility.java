package com.blaine.tictactoe.utilities;

import com.blaine.tictactoe.models.Player;

/**
 * Created by blaineanderson on 3/14/17.
 */

public class ScoringUtility {


    public static boolean checkBoardForWinner(int[][]board, int player){

        return checkHorizontalForWinner(board, player) ||
                checkVerticalForWinner(board, player) ||
                checkDiagonalForWinner(board, player);
    }

    public static boolean checkVerticalForWinner(int[][]board, int player){
        int horIndex = 0;
        int verIndex = 0;
        int boardSize = board.length-1;
        boolean winner = false;

        for(horIndex= 0;horIndex <= boardSize; horIndex++){
            for(verIndex = 0; verIndex <=boardSize; verIndex++){
                int value = board[verIndex][horIndex];
                if(value == player){
                    winner = true;
                }else{
                    winner = false;
                    break;
                }
            }

            if(winner){
                return true;
            }
        }
        return winner;
    }

    public static boolean checkHorizontalForWinner(int[][]board, int player){
        int horIndex = 0;
        int verIndex = 0;
        int boardSize = board.length-1;
        boolean winner = false;

        for(verIndex= 0;verIndex <= boardSize; verIndex++){
            for(horIndex = 0; horIndex <=boardSize; horIndex++){
                int value = board[verIndex][horIndex];
                if(value == player){
                    winner = true;
                }else{
                    winner = false;
                    break;
                }
            }

            if(winner){
                return true;
            }
        }
        return winner;
    }

    public static boolean checkDiagonalForWinner(int[][]board, int player){
        return checkDiagonalFromLeft(board, player) || checkDiagonalFromRight(board, player);
    }

    private static boolean checkDiagonalFromLeft(int[][]board, int player){
        int index = 0;
        int boardSize = board.length-1;
        boolean winner = false;

        //check diagonal from top left
        do{
            int value = board[index][index];
            if(value == player){
                winner = true;
            }else{
                winner = false;
                break;
            }
            index++;
        }while(index <= boardSize);

        return winner;
    }

    private static boolean checkDiagonalFromRight(int[][]board, int player){
        int boardSize = board.length-1;

        int rightIndex = boardSize;
        int leftIndex = 0;
        boolean winner = false;

        //check diagonal from top left
        do{
            int valueRight = board[rightIndex][leftIndex];
            int valueLeft = board[leftIndex][rightIndex];
            if((valueLeft == player) && (valueRight == player)){
                winner = true;
            }else{
                winner = false;
                break;
            }
            rightIndex--;
            leftIndex++;
        }while(leftIndex<=rightIndex);

        return winner;
    }
}
