package com.thg.accelerator23.connectn.ai.ywyz.analysis;

import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;

import java.util.Map;
import java.util.Objects;

public class GameState {
  private Map<Counter, Integer> maxInARowByCounter;
  private boolean win;
  private boolean draw;
  private Counter winner;

  public GameState(Counter winner) {
    this.winner = winner;
    this.win = true;
  }

  public GameState(Map<Counter, Integer> maxInARowByCounter, GameConfig config, boolean isFull) {
    this.maxInARowByCounter = maxInARowByCounter;
    calculateProperties(config, isFull);
  }

  private void calculateProperties(GameConfig config, boolean isFull) {
    win = maxInARowByCounter.entrySet().stream()
        .anyMatch(e -> e.getValue() >= config.getnInARowForWin());
    draw = !win && isFull;
    winner = win ? maxInARowByCounter.entrySet().stream()
        .filter(e -> e.getValue() >= config.getnInARowForWin()).findFirst().get().getKey() : null;

  }

  public boolean isEnd() {
    return win || draw;
  }

  public boolean isWin() {
    return win;
  }

  public boolean isDraw() {
    return draw;
  }

  public Counter getWinner() {
    return winner;
  }

  public Map<Counter, Integer> getMaxInARowByCounter() {
    return maxInARowByCounter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GameState gameState = (GameState) o;
    return win == gameState.win &&
        draw == gameState.draw &&
        Objects.equals(maxInARowByCounter, gameState.maxInARowByCounter) &&
        winner == gameState.winner;
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxInARowByCounter, win, draw, winner);
  }
}
