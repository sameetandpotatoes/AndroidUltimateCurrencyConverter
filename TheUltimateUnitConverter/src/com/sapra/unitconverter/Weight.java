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

public class Weight extends Activity
{
	private EditText text;
	private Toast toast;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);
		text = (EditText) findViewById(R.id.editText1);
		
		Spinner from = (Spinner) findViewById(R.id.fromSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weight_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		from.setAdapter(adapter);
		from.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Spinner to = (Spinner) findViewById(R.id.toSpinner);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.weight_array, android.R.layout.simple_spinner_item);
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
				if (text.getText().length() == 0 || inputValue < 0 || errorMessage)
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
					if (from.equals("Pounds")) sameInput = inputValue + " pounds";
					else if (from.equals("Kilograms")) sameInput = inputValue  + " kilograms";
					else if (from.equals("Grams")) sameInput = inputValue + " grams";
					else sameInput = inputValue + " ounces";
					display.setText(sameInput);
				}
				else
				{	
					if(from.equals("Pounds"))
					{
						if (to.equals("Kilograms"))
						{	
							if (fraction) display.setText(decimalToFraction(convertPoundsToKilograms(inputValue)) + " kilograms");
							else display.setText(convertPoundsToKilograms(inputValue) + " kilograms");
						}
						else if (to.equals("Grams"))
						{
							if (fraction) display.setText(decimalToFraction(convertPoundsToGrams(inputValue)) + " grams");
							else display.setText(convertPoundsToGrams(inputValue) + " grams");
						}
						else
						{
							if (fraction) display.setText(decimalToFraction(convertPoundsToOunces(inputValue)) + " ounces");
							else display.setText(convertPoundsToOunces(inputValue) + " ounces");
						}
					}
					else if (from.equals("Kilograms"))
					{
						if (to.equals("Pounds"))
						{	
							if (fraction)display.setText(decimalToFraction(convertKilogramsToPounds(inputValue)) + " pounds");
							else display.setText(convertKilogramsToPounds(inputValue) + " pounds");
						}
						else if (to.equals("Grams"))
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToGrams(convertKilogramsToPounds(inputValue))) + " grams");
							else display.setText(convertPoundsToGrams(convertKilogramsToPounds(inputValue)) + " grams");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToOunces(convertKilogramsToPounds(inputValue))) + " ounces");
							else display.setText(convertPoundsToOunces(convertKilogramsToPounds(inputValue)) + " ounces");
						}
					}
					else if (from.equals("Grams"))
					{
						if (to.equals("Pounds"))
						{	
							if (fraction)display.setText(decimalToFraction(convertGramsToPounds(inputValue)) + " pounds");
							else display.setText(convertGramsToPounds(inputValue) + " pounds");
						}
						else if (to.equals("Kilograms"))
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToKilograms(convertGramsToPounds(inputValue))) + " kilograms");
							else display.setText(convertPoundsToKilograms(convertGramsToPounds(inputValue)) + " kilograms");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToOunces(convertGramsToPounds(inputValue))) + " ounces");
							else display.setText(convertPoundsToOunces(convertGramsToPounds(inputValue)) + " ounces");
						}
					}
					else
					{
						if (to.equals("Pounds"))
						{	
							if (fraction)display.setText(decimalToFraction(convertOuncesToPounds(inputValue)) + " pounds");
							else display.setText(convertOuncesToPounds(inputValue) + " pounds");
						}
						else if (to.equals("Kilograms"))
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToKilograms(convertOuncesToPounds(inputValue))) + " kilograms");
							else display.setText(convertPoundsToKilograms(convertOuncesToPounds(inputValue)) + " kilograms");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertPoundsToGrams(convertOuncesToPounds(inputValue))) + " ounces");
							else display.setText(convertPoundsToGrams(convertOuncesToPounds(inputValue)) + " ounces");
						}
					}
				}
				break;
		}
	}
	private float convertOuncesToPounds(float inputValue) {
		String ans = Float.toString((float) (inputValue * 0.0625));
		return checkDecimal(ans);
	}
	private float convertPoundsToOunces(float inputValue)
	{
		String ans = Float.toString(inputValue * 16);
		return (checkDecimal(ans));
	}
	private float convertGramsToPounds(float inputValue) {
		String ans = Float.toString((float) (inputValue * 0.00220462262));
		return checkDecimal(ans);
	}
	private float convertPoundsToGrams(float inputValue)
	{
		String ans = Float.toString((float) (inputValue * 453.59237));
		return checkDecimal(ans);
	}
	private float convertPoundsToKilograms(float inputValue)
	{
		String ans = Float.toString((float) (inputValue * 0.45359237));
		return checkDecimal(ans);
	}
	private float convertKilogramsToPounds(float inputValue)
	{
		String ans = Float.toString((float) ((float) inputValue * 2.20462262185));
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