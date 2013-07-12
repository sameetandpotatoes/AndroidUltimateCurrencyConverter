package com.sapra.unitconverter;

import java.util.ArrayList;

import com.sapra.unitconverter.Temperature.ItemSelectedListener;
import com.sapra.unitconverter.Temperature.MyOnItemSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Time extends Activity
{
	private EditText text;
	private Toast toast;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		text = (EditText) findViewById(R.id.editText1);
		
		Spinner from = (Spinner) findViewById(R.id.fromSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		from.setAdapter(adapter);
		from.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Spinner to = (Spinner) findViewById(R.id.toSpinner);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		to.setAdapter(adapter1);
		to.setOnItemSelectedListener(new ItemSelectedListener());
	}
	public void myClickHandler(View view)
	{
		switch (view.getId())
		{
			case R.id.button:
			boolean errorMessage = false; float inputValue = 0;
			try
			{
				inputValue = Float.parseFloat(text.getText().toString());
			}
			catch( NumberFormatException e)
			{
				errorMessage = true;
			}
			if (text.getText().length() == 0 || errorMessage)
			{
				Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
				return;
			}
			SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
			boolean fraction = sharedPrefs.getBoolean("checkboxPref", true);
			Button display = (Button) findViewById(R.id.ans);
			display.setEnabled(true);
			
			if (from.equals(to))
			{
				String sameInput;
				if (from.equals("Seconds")) sameInput = inputValue + " seconds";
				else if (from.equals("Minutes")) sameInput = inputValue + " minutes";
				else sameInput = inputValue + " hours";
				
				display.setText(sameInput);
			}
			else
			{
				if (from.equals("Seconds"))
				{
					if (to.equals("Minutes")) 
					{
						if (fraction)display.setText(decimalToFraction(convertHoursToMinutes(convertSecondsToHours(inputValue))) + " minutes");
						else display.setText(convertHoursToMinutes(convertSecondsToHours(inputValue)) + " minutes");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertSecondsToHours(inputValue)) + " hours");
						else display.setText(convertSecondsToHours(inputValue) + " hours");
					}
				}	
				else if(from.equals("Minutes"))
				{
					if (to.equals("Seconds"))
					{
						if (fraction)display.setText(decimalToFraction(convertHoursToSeconds(convertMinutesToHours(inputValue))) + " seconds");
						else display.setText(convertHoursToSeconds(convertMinutesToHours(inputValue)) + " seconds");
					}
					else
					{
						if (fraction)display.setText(decimalToFraction(convertMinutesToHours(inputValue)) + " hours");
						else display.setText(convertMinutesToHours(inputValue) + " hours");
					}
				}
				else
				{
					if (to.equals("Seconds"))
					{
						if (fraction)display.setText(decimalToFraction(convertHoursToSeconds(inputValue)) + " seconds");
						else display.setText(convertHoursToSeconds(inputValue) + " seconds");
					}
					else
					{
						if (fraction)display.setText(decimalToFraction(convertHoursToMinutes(inputValue)) + " seconds");
						else display.setText(convertHoursToMinutes(inputValue) + " seconds");
					}
				}
			}	
			break;
		}
	}
	private float convertHoursToMinutes (float inputValue)
	{
		String ans = Float.toString((float) inputValue * 60);
		return checkDecimal(ans);
	}
	private float convertHoursToSeconds (float inputValue)
	{ 
		String ans = Float.toString((float) inputValue * 3600);
		return checkDecimal(ans);
	}
	private float convertMinutesToHours (float inputValue)
	{ 
		String ans = Float.toString((float) ((float) inputValue/60.0));
		return checkDecimal(ans);
	}
	private float convertSecondsToHours (float inputValue)
	{ 
		String ans = Float.toString((float) ((float) inputValue/3600.0));
		return checkDecimal(ans);
	}
	private float checkDecimal(String ans)
	{
		boolean display = false;
		for (int i = 0; i < ans.length() - 1; i++) if (ans.substring(i, i + 1).equals("E")) display = true;
		if (!display && ans.length() > 8) return Float.parseFloat(ans.substring(0, 7));
		else if (!display) return Float.parseFloat(ans);
		return Float.parseFloat(ans);
	}
	private String decimalToFraction(float decimal)
	{
		int LIMIT = 12;
        int denominators[] = new int[LIMIT + 1];
        int numerator, denominator, temp;
        int MAX_GOODNESS = 100;
        ArrayList<String> str = new ArrayList<String>();
        int i = 0;
        while (i < LIMIT + 1)
        {
            denominators[i] = (int)decimal;
            decimal = (float) (1.0 / (decimal - denominators[i]));
            i++;
        }
        int last = 0;
        while (last < LIMIT)
        {         	  
            numerator = 1;
            denominator = 1;
            temp = 0;
            int current = last;
            while (current >= 0) {
                denominator = numerator;
                numerator = (numerator * denominators[current]) + temp;
                temp = denominator;
                current = current - 1;
            }
            last++;
                       
            int goodness = denominators[last];
            str.add((int)numerator + "/" + (int)denominator);                               
            // Exit early if we have reached our goodness criterion
            if (Math.abs(goodness) > MAX_GOODNESS) break;
        }   
        return str.get(last - 1);
	}
	public class MyOnItemSelectedListener implements OnItemSelectedListener
	{

	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
	    	from = parent.getItemAtPosition(pos).toString();
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
			to = arg0.getItemAtPosition(arg2).toString();
		}
		public void onNothingSelected(AdapterView<?> arg0)
		{
			
		}
	}
	public String from;
	public String to;
}