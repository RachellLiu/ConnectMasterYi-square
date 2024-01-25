package com.thg.accelerator23.connectn.ai.ywyz.minimax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;


public class ConnectMasterYiSquare extends Player {

  boolean isEmpty = true;
  public ConnectMasterYiSquare(Counter counter) {
    //TODO: fill in your name here
    super(counter, ConnectMasterYiSquare.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
//    int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
//    return randomNum;
    MinMax ai = new MinMax(4, getCounter());
    MinimaxState thisState = new MinimaxState(board, 1,0);
    return ai.decision(thisState);
  }

  public boolean isEmptyBoard(Board board){
    for (int i = 0; i < board.getConfig().getWidth(); i++){
      for (int j = 0; j < board.getConfig().getHeight(); j++){
        if (board.getCounterAtPosition(new Position(i, j)) == null ){
          return true;
        }
      }
    }
    return false;
  }
}
