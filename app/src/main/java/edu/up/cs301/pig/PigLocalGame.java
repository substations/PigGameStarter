package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.pig.PigHumanPlayer;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState pigGameState;
    Random rand = new Random();
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == pigGameState.getTurnID()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        pigGameState.setMsg("");

        if(action instanceof PigHoldAction){

            if (pigGameState.getTurnID() == 0) {
                pigGameState.setPlayer0Score(pigGameState.getRunTotal() + pigGameState.getPlayer0Score());
            } else {
                pigGameState.setPlayer1Score(pigGameState.getRunTotal() + pigGameState.getPlayer1Score());
            }

            pigGameState.setMsg("" + playerNames[pigGameState.getTurnID()] + " gained " + pigGameState.getRunTotal() + " points");

            //change turn
            Log.d("Testing","switch");
            if(players.length > 1){
                if(pigGameState.getTurnID() == 0) {
                    pigGameState.setTurnID(1);
                } else {
                    pigGameState.setTurnID(0);
                }
            }

            //reset run total
            pigGameState.setRunTotal(0);
            return true;

        } else if(action instanceof PigRollAction){
            //roll dice
            pigGameState.setCurrVal(rand.nextInt(6) + 1);

            //if 1 reset and switch turn
            if(pigGameState.getCurrVal() == 1){
                pigGameState.setMsg("" + playerNames[pigGameState.getTurnID()] + " lost all their points :(");
                pigGameState.setRunTotal(0);

                //toggle
                //Log.d("Testing","switch");
                if(players.length > 1){
                    if(pigGameState.getTurnID() == 0){
                        pigGameState.setTurnID(1);
                    } else {
                        pigGameState.setTurnID(0);
                    }
                }

                pigGameState.setRunTotal(0);
                return true;
            }

            pigGameState.setRunTotal(pigGameState.getRunTotal() + pigGameState.getCurrVal());
            return true;
        }

        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        PigGameState copy = new PigGameState();
        copy.copyState(pigGameState);
        p.sendInfo(copy);

    }//sendUpdatedState

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if(pigGameState.getPlayer0Score() >= 50){
            return " " + playerNames[0] + " wins: " + pigGameState.getPlayer0Score();
        } else if(pigGameState.getPlayer1Score() >= 50){
            return " " + playerNames[1] + " wins: " + pigGameState.getPlayer1Score();
        }
        return null;
    }

}// class PigLocalGame
