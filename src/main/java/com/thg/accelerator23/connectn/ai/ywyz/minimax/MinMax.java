package com.thg.accelerator23.connectn.ai.ywyz.minimax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.thg.accelerator23.connectn.ai.ywyz.minimax.UtilityFunction.utilityFunction;

public class MinMax {
    int maxDepth;
    Counter counter;

    public MinMax(int maxDepth, Counter counter) {
        this.maxDepth = maxDepth;
        this.counter = counter;
    }

    public int decision(MinimaxState minimaxState) {
        return max(minimaxState, 0).getCol();
    }

    public int getLastMove(int decision){
        List<Integer> lastMove = new ArrayList<>();
        lastMove.add(decision);
        if (lastMove.size() == 1){
            return 1;
        } else {
            return lastMove.removeFirst();
        }
    }

    public CalculatedMove min(MinimaxState minimaxState, int depth) {
        Random random = new Random();
        BoardAnalyser boardAnalyser = new BoardAnalyser(new GameConfig(10, 8, 4));
        GameState gameState = boardAnalyser.calculateGameState(minimaxState.getBoard());
        if (gameState.isEnd() || depth == maxDepth) {
            CalculatedMove baseMove = new CalculatedMove();
            baseMove = baseMove.thisMove(minimaxState.getLastMove(), utilityFunction(minimaxState.getBoard(), counter));
            return baseMove;
        } else {
            LinkedList<MinimaxState> children = minimaxState.getChildren(counter);
            CalculatedMove minPlayerMove = new CalculatedMove();
            minPlayerMove = minPlayerMove.moveToCompare(Integer.MAX_VALUE) ;
            for (int i = 0; i < children.size(); i++) {
                MinimaxState child = children.get(i);
                CalculatedMove newMove = max(child, depth + 1);
                if (newMove.getUtilityValue() <= minPlayerMove.getUtilityValue()) {
                    if (newMove.getUtilityValue() == minPlayerMove.getUtilityValue()) {
                        if (random.nextInt(2) == 0) {
                            minPlayerMove.setCol(child.getLastMove());
                            minPlayerMove.setUtilityValue(newMove.getUtilityValue());
                        }
                    } else {
                        minPlayerMove.setCol(child.getLastMove());
                        minPlayerMove.setUtilityValue(newMove.getUtilityValue());
                    }
                }
            }
            return minPlayerMove;
        }
    }

    public CalculatedMove max(MinimaxState minimaxState, int depth) {
        Random random = new Random();
        BoardAnalyser boardAnalyser = new BoardAnalyser(new GameConfig(10, 8, 4));
        GameState gameState = boardAnalyser.calculateGameState(minimaxState.getBoard());
        if (gameState.isEnd() || depth == maxDepth) {
            CalculatedMove baseMove = new CalculatedMove();
            baseMove = baseMove.thisMove(minimaxState.getLastMove(), utilityFunction(minimaxState.getBoard(), counter));
            return baseMove;
        } else {
            LinkedList<MinimaxState> children = minimaxState.getChildren(counter);
            CalculatedMove maxPlayerMove = new CalculatedMove();
            maxPlayerMove = maxPlayerMove.moveToCompare(Integer.MIN_VALUE) ;
            for (int j = 0; j < children.size(); j++) {
                MinimaxState child = children.get(j);
                CalculatedMove newMove = min(child, depth + 1);
                if (newMove.getUtilityValue() >= maxPlayerMove.getUtilityValue()) {
                    if (newMove.getUtilityValue() == maxPlayerMove.getUtilityValue()) {
                        if (random.nextInt(2) == 0) {
                            maxPlayerMove.setCol(child.getLastMove());
                            maxPlayerMove.setUtilityValue(newMove.getUtilityValue());
                        }
                    } else {
                        maxPlayerMove.setCol(child.getLastMove());
                        maxPlayerMove.setUtilityValue(newMove.getUtilityValue());
                    }
                }
            }
            return maxPlayerMove;
        }
    }
}
