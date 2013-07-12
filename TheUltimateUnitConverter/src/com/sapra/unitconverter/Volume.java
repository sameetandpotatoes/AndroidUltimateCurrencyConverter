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

public class Volume extends Activity
{
	private EditText text;
	private Toast toast;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		text = (EditText) findViewById(R.id.editText1);
		
		Spinner from = (Spinner) findViewById(R.id.fromSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.volume_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		from.setAdapter(adapter);
		from.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Spinner to = (Spinner) findViewById(R.id.toSpinner);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.volume_array, android.R.layout.simple_spinner_item);
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
				if (from.equals("Pints")) sameInput = inputValue + " pints";
				else if (from.equals("Quarts")) sameInput = inputValue + " quarts";
				else if (from.equals("Gallons")) sameInput = inputValue + " gallons";
				else if (from.equals("Fluid ounces")) sameInput = inputValue + " fluid ounces";
				else sameInput = inputValue + " milliliters";
				
				display.setText(sameInput);
			}
			else
			{
				if (from.equals("Pints"))
				{
					if (to.equals("Quarts")) 
					{
						if (fraction)display.setText(decimalToFraction(convertPintsToQuarts(inputValue)) + " quarts");
						else display.setText(convertPintsToQuarts(inputValue) + " quarts");
					}
					else if (to.equals("Gallons"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToGallons(inputValue)) + " gallons");
						else display.setText(convertPintsToGallons(inputValue) + " gallons");
					}
					else if (to.equals("Fluid ounces"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToFlOz(inputValue)) + " fluid ounces");
						else display.setText(convertPintsToFlOz(inputValue) + " fluid ounces");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToMilliliters(inputValue)) + " milliliters");
						else display.setText(convertPintsToMilliliters(inputValue) + " milliliters");
					}
				}	
				else if (from.equals("Quarts"))
				{
					if (to.equals("Pints")) 
					{
						if (fraction)display.setText(decimalToFraction(convertQuartsToPints(inputValue)) + " pints");
						else display.setText(convertQuartsToPints(inputValue) + " pints");
					}
					else if (to.equals("Gallons"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToGallons(convertQuartsToPints(inputValue))) + " gallons");
						else display.setText(convertPintsToGallons(convertQuartsToPints(inputValue)) + " gallons");
					}
					else if (to.equals("Fluid ounces"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToFlOz(convertQuartsToPints(inputValue))) + " fluid ounces");
						else display.setText(convertPintsToFlOz(convertQuartsToPints(inputValue)) + " fluid ounces");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToMilliliters(convertQuartsToPints(inputValue))) + " milliliters");
						else display.setText(convertPintsToMilliliters(convertQuartsToPints(inputValue)) + " milliliters");
					}
				}
				else if (from.equals("Gallons"))
				{
					if (to.equals("Quarts")) 
					{
						if (fraction)display.setText(decimalToFraction(convertPintsToQuarts(convertGallonsToPints(inputValue))) + " quarts");
						else display.setText(convertPintsToQuarts(convertGallonsToPints(inputValue)) + " quarts");
					}
					else if (to.equals("Pints"))
					{
						if (fraction) display.setText(decimalToFraction(convertGallonsToPints(inputValue)) + " pints");
						else display.setText(convertGallonsToPints(inputValue) + " pints");
					}
					else if (to.equals("Fluid ounces"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToFlOz(convertGallonsToPints(inputValue))) + " fluid ounces");
						else display.setText(convertPintsToFlOz(convertGallonsToPints(inputValue)) + " fluid ounces");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToMilliliters(convertGallonsToPints(inputValue))) + " milliliters");
						else display.setText(convertPintsToMilliliters(convertGallonsToPints(inputValue)) + " milliliters");
					}
				}
				else if (from.equals("Fluid ounces"))
				{
					if (to.equals("Quarts")) 
					{
						if (fraction)display.setText(decimalToFraction(convertPintsToQuarts(convertFlOzToPints(inputValue))) + " quarts");
						else display.setText(convertPintsToQuarts(convertFlOzToPints(inputValue)) + " quarts");
					}
					else if (to.equals("Gallons"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToGallons(convertFlOzToPints(inputValue))) + " gallons");
						else display.setText(convertPintsToGallons(convertFlOzToPints(inputValue)) + " gallons");
					}
					else if (to.equals("Pints"))
					{
						if (fraction) display.setText(decimalToFraction(convertFlOzToPints(inputValue)) + " pints");
						else display.setText(convertFlOzToPints(inputValue) + " pints");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToMilliliters(convertFlOzToPints(inputValue))) + " milliliters");
						else display.setText(convertPintsToMilliliters(convertFlOzToPints(inputValue)) + " milliliters");
					}
				}
				else 
				{
					if (to.equals("Quarts")) 
					{
						if (fraction)display.setText(decimalToFraction(convertPintsToQuarts(convertMillilitersToPints(inputValue))) + " quarts");
						else display.setText(convertPintsToQuarts(convertMillilitersToPints(inputValue)) + " quarts");
					}
					else if (to.equals("Gallons"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToGallons(convertMillilitersToPints(inputValue))) + " gallons");
						else display.setText(convertPintsToGallons(convertMillilitersToPints(inputValue)) + " gallons");
					}
					else if (to.equals("Fluid ounces"))
					{
						if (fraction) display.setText(decimalToFraction(convertPintsToFlOz(convertMillilitersToPints(inputValue))) + " fluid ounces");
						else display.setText(convertPintsToFlOz(convertMillilitersToPints(inputValue)) + " fluid ounces");
					}
					else
					{
						if (fraction) display.setText(decimalToFraction(convertMillilitersToPints(inputValue)) + " pints");
						else display.setText(convertMillilitersToPints(inputValue) + " pints");
					}
				}
			}	
			break;
		}
	}
	private float convertPintsToQuarts(float inputValue) {
		
		String ans = Float.toString((float) (inputValue * 0.5));
		return checkDecimal(ans);
	}
	private float convertPintsToGallons(float inputValue) {
		
		String ans = Float.toString((float) (inputValue * 0.125));
		return checkDecimal(ans);
	}
	private float convertPintsToFlOz(float inputValue) {
		
		String ans = Float.toString(inputValue * 16);
		return checkDecimal(ans);
	}
	private float convertPintsToMilliliters(float inputValue) {
		
		String ans = Float.toString((float) (inputValue * 473.176473));
		return checkDecimal(ans);
	}
	private float convertQuartsToPints(float inputValue) {
		
		String ans = Float.toString(inputValue * 2);
		return checkDecimal(ans);
	}
	private float convertGallonsToPints(float inputValue) {
		
		String ans = Float.toString(inputValue * 8);
		return checkDecimal(ans);
	}
	private float convertFlOzToPints(float inputValue) {
		
		String ans = Float.toString((float) (inputValue * 0.0625));
		return checkDecimal(ans);
	}
	private float convertMillilitersToPints(float inputValue) {
		
		String ans = Float.toString((float) (inputValue * 0.00211337642));
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