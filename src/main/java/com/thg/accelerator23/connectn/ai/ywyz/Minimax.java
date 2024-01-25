package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Position;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Minimax {

    private final Board gameBoard;
    private final GameState gameState;

    public Minimax (Board gameBoard, GameState gameState){
        this.gameBoard = gameBoard;
        this.gameState = gameState;
    }

    private int minimaxAlgo(int depth, Counter currentCounter) {
        List<Integer> choices = legalMoves();
        Counter winner = gameState.getWinner();

        if (winner == Counter.O) {
            return 1;
        } else if (winner == Counter.X){
            return -1;
        // if it is a draw or it has reached a depth of 0
        } else if (choices.size() == 0 || depth == 0){
            return 0;
        }

        int[] scores = new int[choices.size()];

        for (int i=0; i < choices.size(); i++){
            int position = choices.get(i);
            if (currentCounter == Counter.X){
                gameBoard.
            }
        }

    }

    private int getMax(int[] scores){
        int max = Integer.MIN_VALUE;
        for (int value: scores){
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private int getMin (int[] scores){
        int min = Integer.MAX_VALUE;
        for (int value: scores){
            if (value < min){
                min = value;
            }
        }
        return min;
    }

    // return the list of column choices that aren't full yet
    private ArrayList<Integer> legalMoves() {
        ArrayList<Integer> choices = new ArrayList<>();
        // find out the columns that aren't filled
        for (int i = 0; i < gameBoard.getConfig().getWidth(); i++){
            if (!isColumnFull(gameBoard, i)){
                choices.add(i);
            }
        }
        return choices;
    }

    private boolean isColumnFull(Board board, int columnIndex){
        return IntStream.range(0, board.getConfig().getHeight())
                .allMatch(row -> board.hasCounterAtPosition(new Position(columnIndex, row)));
    }

    private void placeCounter(Counter counter, int columnIndex) throws InvalidMoveException {
        if (!gameBoard.isWithinBoard(new Position(columnIndex, 0))){
            throw new InvalidMoveException("Outside of board");
        }

        int columnIndex = gameBoard.
    }

    private void undoCounterPlacement(int columnIndex) {

    }
}
