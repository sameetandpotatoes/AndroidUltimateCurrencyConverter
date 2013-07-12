package com.sapra.minigames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CollectionOfMinigamesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Button tic = (Button) findViewById(R.id.tic);
		Button connect = (Button) findViewById(R.id.connect);
		Button reaction = (Button) findViewById(R.id.reaction);
		tic.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(CollectionOfMinigamesActivity.this, TicTacToeDirections.class);
				startActivity(intent);		  
			}
		});
		
		connect.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent(CollectionOfMinigamesActivity.this, Connect4.class);
				startActivity(intent);		  
			}
		});
		
		reaction.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent(CollectionOfMinigamesActivity.this, ReactionTime.class);
				startActivity(intent);		  
			}
			});	
    }
}