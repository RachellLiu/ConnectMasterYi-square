package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;

import java.util.LinkedList;

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
}
