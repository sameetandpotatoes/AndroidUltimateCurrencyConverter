package com.sapra.unitconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class BetterUnitConverterActivity extends Activity
{
	private EditText text;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Button temp = (Button) findViewById(R.id.temp);
		Button length = (Button) findViewById(R.id.length);
		Button time = (Button) findViewById(R.id.time);
		Button weight = (Button) findViewById(R.id.weight);
		Button about = (Button) findViewById(R.id.about);
		Button settings = (Button) findViewById(R.id.settings);
		Button volume = (Button) findViewById(R.id.volume);
		temp.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				 Intent intent = new Intent(BetterUnitConverterActivity.this, Temperature.class);
				 startActivity(intent);		  
			}
		});
		
		about.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				 Intent intent = new Intent(BetterUnitConverterActivity.this, About.class);
				 startActivity(intent);		  
			}
		});
		
		weight.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				 Intent intent = new Intent(BetterUnitConverterActivity.this, Weight.class);
				 startActivity(intent);		  
			}
			});
		
		time.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				 Intent intent = new Intent(BetterUnitConverterActivity.this, Time.class);
				 startActivity(intent);		  
			}
		});
		length.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(BetterUnitConverterActivity.this, Length.class);
				startActivity(intent);		  
			}
		});
		settings.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(BetterUnitConverterActivity.this, Settings.class);
				startActivity(intent);
			}
		});
		volume.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(BetterUnitConverterActivity.this, Volume.class);
				startActivity(intent);
			}
		});
	}
}