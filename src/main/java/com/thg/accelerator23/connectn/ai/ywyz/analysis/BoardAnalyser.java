package com.thg.accelerator23.connectn.ai.ywyz.analysis;

import com.thg.accelerator23.connectn.ai.ywyz.model.Line;
import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardAnalyser {
  private Function<Position, Position> hMover = p -> new Position(p.getX() + 1, p.getY());
  private Function<Position, Position> vMover = p -> new Position(p.getX(), p.getY() + 1);
  private Function<Position, Position> diagUpRightMover = hMover.compose(vMover);
  private Function<Position, Position> diagUpLeftMover =
      p -> new Position(p.getX() - 1, p.getY() + 1);
  private Map<Function<Position, Position>, List<Position>> positionsByFunction;

  public BoardAnalyser(GameConfig config) {
    positionsByFunction = new HashMap<>();
    List<Position> leftEdge = IntStream.range(0, config.getHeight())
        .mapToObj(Integer::new)
        .map(i -> new Position(0, i))
        .collect(Collectors.toList());
    List<Position> bottomEdge = IntStream.range(0, config.getWidth())
        .mapToObj(Integer::new)
        .map(i -> new Position(i, 0))
        .collect(Collectors.toList());
    List<Position> rightEdge = leftEdge.stream()
        .map(p -> new Position(config.getWidth() - 1, p.getY()))
        .collect(Collectors.toList());

    List<Position> leftBottom = Stream.concat(leftEdge.stream(),
        bottomEdge.stream()).distinct().collect(Collectors.toList());
    List<Position> rightBottom = Stream.concat(rightEdge.stream(),
        bottomEdge.stream()).distinct().collect(Collectors.toList());

    positionsByFunction.put(hMover, leftEdge);
    positionsByFunction.put(vMover, bottomEdge);
    positionsByFunction.put(diagUpRightMover, leftBottom);
    positionsByFunction.put(diagUpLeftMover, rightBottom);
  }

  public GameState calculateGameState(Board board) {
    List<Line> lines = getLines(board);
    Map<Counter, Integer> bestRunByColour = new HashMap<>();
    for (Line line : lines) {
      Map<Counter, Integer> bestRunInLine = getBestRunByColour(line);
      bestRunByColour = maxMap(bestRunInLine, bestRunByColour);
    }
    boolean boardFull = isBoardFull(board);
    return new GameState(bestRunByColour, board.getConfig(), boardFull);
  }

  private Map<Counter, Integer> maxMap(Map<Counter, Integer> map1, Map<Counter, Integer> map2) {
    HashMap<Counter, Integer> maxMap = new HashMap<>();
    for (Map.Entry<Counter, Integer> entry : map1.entrySet()) {
      maxMap.put(entry.getKey(), Math.max(entry.getValue(), map2.getOrDefault(entry.getKey(), 0)));
    }
    return maxMap;
  }

  private boolean isBoardFull(Board board) {
    return IntStream.range(0, board.getConfig().getWidth())
        .allMatch(
            i -> board.hasCounterAtPosition(new Position(i, board.getConfig().getHeight() - 1)));
  }

  private List<Line> getLines(Board board) {
    ArrayList<Line> lines = new ArrayList<>();
    for (Map.Entry<Function<Position, Position>, List<Position>> entry : positionsByFunction
        .entrySet()) {
      Function<Position, Position> function = entry.getKey();
      List<Position> startPositions = entry.getValue();
      lines.addAll(startPositions.stream().map(p -> new BoardLine(board, p, function))
          .collect(Collectors.toList()));
    }
    return lines;
  }

  private Map<Counter, Integer> getBestRunByColour(Line line) {
    HashMap<Counter, Integer> bestRunByColour = new HashMap<>();
    for (Counter c : Counter.values()) {
      bestRunByColour.put(c, 0);
    }
    Counter current = null;
    int currentRunLength = 0;
    while (line.hasNext()) {
      Counter next = line.next();
      if (current != next) {
        if (current != null) {
          if (Math.max(currentRunLength, 1) > bestRunByColour.get(current)) {
            bestRunByColour.put(current, Math.max(currentRunLength, 1));
          }
        }
        currentRunLength = 1;
        current = next;
      } else {
        currentRunLength++;
      }
    }
    if (current != null && Math.max(currentRunLength, 1) > bestRunByColour.get(current)) {
      bestRunByColour.put(current, Math.max(currentRunLength, 1));
    }
    return bestRunByColour;
  }
}
