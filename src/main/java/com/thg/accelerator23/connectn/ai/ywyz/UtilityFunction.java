package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.GameState;

public class UtilityFunction {
    public static int utilityFunction(Board board, Counter counter, int depth) {
        // Maximizing player plays counter
        // +100 if counter wins,
        // +10 if 3 counter in a row, -5 if 3 counter.getOther() in a row,
        // +4 if 2 counter in a row, -1 if 2 counter.getOther() in a row.
        BoardAnalyser boardAnalyser = new BoardAnalyser(new GameConfig(10, 8, 4));
        GameState state = boardAnalyser.calculateGameState(board);

        int maxPlayer = 0;
        int minPlayer = 0;
        if (state.isWin()) {
            if (state.getWinner().equals(counter)) {
//                maxPlayer += 100 - depth * 20;
                maxPlayer += 100;
            } else {
//                minPlayer += 1000 - depth * 200;
                minPlayer += 100;
            }
        }
        minPlayer += check3InARow(board, counter.getOther())*10 + check2InARow(board, counter.getOther())*4;
        maxPlayer += check3InARow(board, counter)*5 + check2InARow(board, counter);
        return maxPlayer - minPlayer;
    }

    public static int check3InARow(Board board, Counter counter) {
        int times = 0;
//        Counter[][] array = board.getCounterPlacements();
        //In column
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j, i + 2)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i+1][j] && board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i+2][j] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In row
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j+2, i)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i][j+1] && board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i][j+2] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In diagonal ascendent
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j + 2, i + 2)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i + 1][j + 1] && board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i + 2][j + 2] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In diagonal descendent
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j + 2, i - 2)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i - 1][j + 1] && board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i - 2][j + 2] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        return times;
    }
    public static boolean checkValid(int row, int col) {
        if ((row <= -1) || (col <= -1) || (row > 7) || (col > 9)) {
            return false;
        }
        return true;
    }

    public static int check2InARow(Board board, Counter counter) {
        int times = 0;
//        Counter[][] array = board.getCounterPlacements();
        //In a column
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j + 1, i)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i][j + 1] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In a row
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j, i-1)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i - 1][j] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In a diagonal ascendent
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid(j + 1, i+1)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i + 1][j + 1] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        //In a diagonal descendent
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkValid( j + 1, i - 1)) {
                    if (board.getCounterPlacements()[i][j] == board.getCounterPlacements()[i - 1][j + 1] && board.getCounterPlacements()[i][j] == counter) {
                        times++;
                    }
                }
            }
        }
        return times;
    }
}
