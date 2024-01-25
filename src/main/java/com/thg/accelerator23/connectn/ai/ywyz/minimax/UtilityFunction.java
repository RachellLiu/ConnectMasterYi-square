package com.thg.accelerator23.connectn.ai.ywyz.minimax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.GameState;

public class UtilityFunction {
    public static int utilityFunction(Board board, Counter counter) {
        // Maximizing player plays counter
        // +90 if counter wins,
        // +10 if 3 counter in a row, -5 if 3 counter.getOther() in a row,
        // +4 if 2 counter in a row, -1 if 2 counter.getOther() in a row.
        BoardAnalyser boardAnalyser = new BoardAnalyser(new GameConfig(10, 8, 4));
        GameState state = boardAnalyser.calculateGameState(board);
        int maxPlayer = 0;
        int minPlayer = 0;
        if (state.isWin()) {
            if (state.getWinner().equals(counter)) {
                maxPlayer += 90;
            } else {
                minPlayer += 90;
            }
        }
        return maxPlayer - minPlayer;
    }
}
