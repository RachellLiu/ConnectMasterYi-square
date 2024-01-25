package com.thg.accelerator23.connectn.ai.ywyz.analysis;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;
import com.thg.accelerator23.connectn.ai.ywyz.model.Line;

import java.util.Objects;
import java.util.function.Function;

public class BoardLine implements Line {
  private Board board;
  private Position currentPosition;
  private Function<Position, Position> movementFunction;

  public BoardLine(Board board, Position currentPosition,
                   Function<Position, Position> movementFunction) {
    this.board = board;
    this.currentPosition = currentPosition;
    this.movementFunction = movementFunction;
  }

  @Override
  public boolean hasNext() {
    return board.isWithinBoard(currentPosition);
  }

  @Override
  public Counter next() {
    Counter counterAtPosition = board.getCounterAtPosition(currentPosition);
    currentPosition = movementFunction.apply(currentPosition);
    return counterAtPosition;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardLine boardLine = (BoardLine) o;
    return Objects.equals(board, boardLine.board) &&
        Objects.equals(currentPosition, boardLine.currentPosition) &&
        Objects.equals(movementFunction, boardLine.movementFunction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(board, currentPosition, movementFunction);
  }
}
