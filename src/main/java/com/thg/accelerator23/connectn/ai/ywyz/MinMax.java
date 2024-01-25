package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ywyz.analysis.GameState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.thg.accelerator23.connectn.ai.ywyz.UtilityFunction.utilityFunction;

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

    public CalculatedMove min(MinimaxState minimaxState, int depth) {
        Random random = new Random();
        BoardAnalyser boardAnalyser = new BoardAnalyser(new GameConfig(10, 8, 4));
        GameState gameState = boardAnalyser.calculateGameState(minimaxState.getBoard());
        if (gameState.isEnd() || depth == maxDepth) {
            CalculatedMove baseMove = new CalculatedMove();
            baseMove = baseMove.thisMove(minimaxState.getLastMove(), utilityFunction(minimaxState.getBoard(), counter));
            System.out.println("final baseMoveFound:  "+ baseMove.getCol());
            return baseMove;
        } else {
            LinkedList<MinimaxState> children = minimaxState.getChildren(counter.getOther());
            CalculatedMove minPlayerMove = new CalculatedMove();
            minPlayerMove = minPlayerMove.moveToCompare(Integer.MAX_VALUE);
            for (int i = 0; i < children.size(); i++) {
                MinimaxState child = children.get(i);
                CalculatedMove nextMove = max(child, depth + 1);
                if (nextMove.getUtilityValue() <= minPlayerMove.getUtilityValue()) {
                    if (nextMove.getUtilityValue() == minPlayerMove.getUtilityValue()) {
                        if (random.nextInt(2) == 0) {
                            minPlayerMove.setCol(child.getLastMove());
                            minPlayerMove.setUtilityValue(nextMove.getUtilityValue());
                        }
                    } else {
                        minPlayerMove.setCol(child.getLastMove());
                        minPlayerMove.setUtilityValue(nextMove.getUtilityValue());
                    }
                }
            }
            System.out.println("minPlayerMoveFound:  "+ minPlayerMove.getCol());
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
            System.out.println("final baseMoveFound:  "+ baseMove.getCol());
            return baseMove;
        } else {
            LinkedList<MinimaxState> children = minimaxState.getChildren(counter);
            CalculatedMove maxPlayerMove = new CalculatedMove();
            maxPlayerMove = maxPlayerMove.moveToCompare(Integer.MIN_VALUE) ;
            for (int j = 0; j < children.size(); j++) {
                MinimaxState child = children.get(j);
                CalculatedMove nextMove = min(child, depth + 1);
                if (nextMove.getUtilityValue() >= maxPlayerMove.getUtilityValue()) {
                    if (nextMove.getUtilityValue() == maxPlayerMove.getUtilityValue()) {
                        if (random.nextInt(2) == 0) {
                            maxPlayerMove.setCol(child.getLastMove());
                            maxPlayerMove.setUtilityValue(nextMove.getUtilityValue());
                        }
                    } else {
                        maxPlayerMove.setCol(child.getLastMove());
                        maxPlayerMove.setUtilityValue(nextMove.getUtilityValue());
                    }
                }
            }
            System.out.println("maxPlayerMoveFound:  "+ maxPlayerMove.getCol());
            return maxPlayerMove;
        }
    }
}
