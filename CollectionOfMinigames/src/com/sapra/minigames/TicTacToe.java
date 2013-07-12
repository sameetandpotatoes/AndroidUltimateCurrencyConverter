package com.sapra.minigames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class TicTacToe extends Activity
{
    DrawViewTicTacToe view;
    protected static int numMoves = 0;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        view = new DrawViewTicTacToe(this);
        setContentView(view);
        
        HelloOnTouchListener l = new HelloOnTouchListener();
        view.setOnTouchListener(l); 
    }
}
class HelloOnTouchListener implements OnTouchListener
{
	public boolean onTouch(View v, MotionEvent event)
	{
		if (userTurn())
		{
			//if (TicTacToeDirections.getPiece().equals("X"))
				//DrawViewTicTacToe.draw("X");
			//else
				//DrawViewTicTacToe.draw("O");
		}
		return true;
	}
	private boolean userTurn()
	{
		return (TicTacToe.numMoves % 2 == 0);
	}
}