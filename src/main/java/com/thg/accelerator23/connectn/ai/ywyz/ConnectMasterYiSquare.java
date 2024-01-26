package com.thg.accelerator23.connectn.ai.ywyz;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;


public class ConnectMasterYiSquare extends Player {
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
    MinMax ai = new MinMax(5, getCounter());
    MinimaxState thisState = new MinimaxState(board, -1,0);
    if (thisState.isEmpty()){
      return 5;
    } else {
      return ai.decision(thisState);
    }
  }
}
