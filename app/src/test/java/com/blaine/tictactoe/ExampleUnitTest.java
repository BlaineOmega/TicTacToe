package com.blaine.tictactoe;

import com.blaine.tictactoe.models.Player;
import com.blaine.tictactoe.utilities.ScoringUtility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void playerOneWinsVertical(){
        int[][] board =  {
                {1, 2, 0},
                {1, 2, 0},
                {1, 0, 0}};
        assertFalse(ScoringUtility.checkHorizontalForWinner(board, 1));
        assertTrue(ScoringUtility.checkVerticalForWinner(board, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board, 1));

        int[][] board2 =  {
                {0, 1, 0},
                {2, 1, 0},
                {2, 1, 0}};

        assertFalse(ScoringUtility.checkHorizontalForWinner(board2,1));
        assertTrue(ScoringUtility.checkVerticalForWinner(board2, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board2, 1));

        int[][] board3 =  {
                {0, 0, 1},
                {0, 0, 1},
                {2, 2, 1}};

        assertFalse(ScoringUtility.checkHorizontalForWinner(board3, 1));
        assertTrue(ScoringUtility.checkVerticalForWinner(board3, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board3, 1));
    }

    @Test
    public void playerOneWinsHorizontal(){
        int[][] board =  {
                {1, 1, 1},
                {0, 2, 0},
                {2, 0, 0}};
        assertTrue(ScoringUtility.checkHorizontalForWinner(board, 1));
        assertFalse(ScoringUtility.checkVerticalForWinner(board, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board, 1));

        int[][] board2 =  {
                {0, 0, 2},
                {1, 1, 1},
                {2, 0, 0}};

        assertTrue(ScoringUtility.checkHorizontalForWinner(board2, 1));
        assertFalse(ScoringUtility.checkVerticalForWinner(board2, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board2, 1));

        int[][] board3 =  {
                {0, 0, 2},
                {0, 0, 2},
                {1, 1, 1}};

        assertTrue(ScoringUtility.checkHorizontalForWinner(board3, 1));
        assertFalse(ScoringUtility.checkVerticalForWinner(board3, 1));
        assertFalse(ScoringUtility.checkDiagonalForWinner(board3, 1));
    }

    @Test
    public void playerOneWinsDiagonal(){
        int[][] board =  {
                {0, 2, 1},
                {0, 1, 0},
                {1, 0, 2}};
        assertTrue(ScoringUtility.checkDiagonalForWinner(board, 1));

        int[][] board1 =  {
                {1, 2, 2},
                {0, 1, 0},
                {0, 0, 1}};
        assertTrue(ScoringUtility.checkDiagonalForWinner(board1, 1));

        int[][] board2 =  {
                {1, 2, 2, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {2, 0, 0, 1}};
        assertTrue(ScoringUtility.checkDiagonalForWinner(board2, 1));

        int[][] board3 =  {
                {0, 2, 2, 1},
                {0, 2, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 0, 0}};
        assertTrue(ScoringUtility.checkDiagonalForWinner(board3, 1));
    }
}