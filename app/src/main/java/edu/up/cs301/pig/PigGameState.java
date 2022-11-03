package edu.up.cs301.pig;

public class PigGameState extends edu.up.cs301.game.infoMsg.GameState{
    private int turnID;
    private int player0Score;
    private int player1Score;
    private int runTotal;
    private int currVal;
    private String msg;

    public PigGameState(){
        turnID = 0;
        player0Score = player1Score = 0;
        runTotal = 0;
        currVal = 0;
        msg = "";
    }

    void copyState(PigGameState toCopy){

        turnID = toCopy.turnID;
        player0Score = toCopy.player0Score;
        player1Score = toCopy.player1Score;
        runTotal = toCopy.runTotal;
        currVal = toCopy.currVal;
        msg = toCopy.msg;
    }

    int getTurnID(){
        return turnID;
    }

    int getPlayer0Score(){
        return player0Score;
    }
    int getPlayer1Score(){
        return player1Score;
    }

    int getRunTotal(){
        return runTotal;
    }

    int getCurrVal(){
        return currVal;
    }

    void setTurnID(int x){
        turnID = x;
    }


    void setPlayer0Score(int score){
        player0Score = score;
    }
    void setPlayer1Score(int score){
        player1Score = score;
    }

    void setRunTotal(int x){
        runTotal = x;
    }

    void setCurrVal(int x){
        currVal = x;
    }
    void setMsg(String s) { msg = s; }
    String getMsg(){ return msg; }
}
