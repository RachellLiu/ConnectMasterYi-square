package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.LinkedList;
import java.util.List;

public class MinimaxState {
    private Board board;
    private int lastMove;
    private int utilityValue;

    public MinimaxState(Board board, int lastMove, int utilityValue) {
        this.board = board;
        this.lastMove = lastMove;
        this.utilityValue = utilityValue;
    }
    public LinkedList<MinimaxState> getChildren(Counter counter) {
        LinkedList<MinimaxState> children = new LinkedList<>();
        int col = 0;
        System.out.println("Children:\n");
        while (col < 10) {
            try {
                Board newBoard = new Board(board, col, counter);
                MinimaxState child = new MinimaxState(newBoard, col, utilityValue);
                children.add(child);
                System.out.println(child.getLastMove());
            } catch (InvalidMoveException e) {
                break;
            } finally {
                col++;
            }
        }
        return children;
    }

    public Board getBoard() {
        return board;
    }

    public int getLastMove() {
        return lastMove;
    }

    public boolean isEmpty() {
        for (int i=0; i < board.getConfig().getWidth(); i++){
            for (int j=0; j < board.getConfig().getHeight(); j++){
                if (!board.hasCounterAtPosition(new Position(i, j))){
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
