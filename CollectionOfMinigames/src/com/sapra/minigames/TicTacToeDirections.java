package com.sapra.minigames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TicTacToeDirections extends Activity
{
	private static EditText text;
	protected static String difficulty;
	protected static String piece;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        Button back = (Button) findViewById(R.id.Back);
        Button start = (Button) findViewById(R.id.Start);
        back.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(TicTacToeDirections.this, CollectionOfMinigamesActivity.class);
				startActivity(intent);		  
			}
		});
        Spinner difficulty = (Spinner) findViewById(R.id.difficulty);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficulty_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		difficulty.setAdapter(adapter);
		difficulty.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Spinner piece = (Spinner) findViewById(R.id.piece);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.piece_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		piece.setAdapter(adapter1);
		piece.setOnItemSelectedListener(new ItemSelectedListener());
        text = (EditText) findViewById(R.id.editText1);
        
        start.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				if (text.getText().toString().length() == 0) 
					return;
				else
				{
					Intent intent = new Intent(TicTacToeDirections.this, TicTacToe.class);
					startActivity(intent);
				}
			}
		});
    }
    public static String getName()
    {
    	return text.getText().toString();
    }
    public static String getDifficulty() { return difficulty; }
    public static String getPiece() { return piece; }
    public class MyOnItemSelectedListener implements OnItemSelectedListener
	{

	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
	    	difficulty = parent.getItemAtPosition(pos).toString();
	    }
	    public void onNothingSelected(AdapterView parent)
	    {
	      // Do nothing.
	    }
	}
	public class ItemSelectedListener implements OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
		{
			piece = arg0.getItemAtPosition(arg2).toString();
		}
		public void onNothingSelected(AdapterView<?> arg0)
		{
			
		}
	}
}