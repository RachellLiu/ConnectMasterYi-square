package com.thg.accelerator23.connectn.ai.ywyz.minimax;

public class CalculatedMove {
    private int col;
    private int utilityValue;

    public CalculatedMove() {
        col = -1;
        utilityValue = 0;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getUtilityValue() {
        return utilityValue;
    }

    public void setUtilityValue(int utilityValue) {
        this.utilityValue = utilityValue;
    }

    public CalculatedMove moveDone(int col) {
        CalculatedMove move = new CalculatedMove();
        move.setCol(col);
        move.setUtilityValue(-1);
        return move;
    }
    public CalculatedMove thisMove(int col, int utilityValue) {
        CalculatedMove move = new CalculatedMove();
        move.setCol(col);
        move.setUtilityValue(utilityValue);
        return move;
    }

    public CalculatedMove moveToCompare(int value) {
        CalculatedMove move = new CalculatedMove();
        move.setCol(-1);
        move.setUtilityValue(value);
        return move;
    }


}
