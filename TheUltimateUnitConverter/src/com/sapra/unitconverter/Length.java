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

public class Length extends Activity
{
	private EditText text;
	private Toast toast;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.length);
		text = (EditText) findViewById(R.id.editText1);
		
		Spinner from = (Spinner) findViewById(R.id.fromSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.length_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		from.setAdapter(adapter);
		from.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		Spinner to = (Spinner) findViewById(R.id.toSpinner);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.length_array, android.R.layout.simple_spinner_item);
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
					if (from.equals("Centimeters")) sameInput = inputValue + " centimeters";
					else if (from.equals("Inches")) sameInput = inputValue + " inches";
					else if (from.equals("Feet")) sameInput = inputValue + " feet";
					else if (from.equals("Yards")) sameInput = inputValue + " yards";
					else if (from.equals("Meters")) sameInput = inputValue + " meters";
					else sameInput = inputValue + " kilometers";
					display.setText(sameInput);
				}
				else
				{
					if (from.equals("Centimeters"))
					{
						if (to.equals("Inches"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToInches(convertCentimetersToMeters(inputValue))) + " inches");
							else display.setText(convertMetersToInches(convertCentimetersToMeters(inputValue)) + " inches");
						}
						else if (to.equals("Feet"))
						{
							if (fraction) display.setText(decimalToFraction(convertMetersToFeet(convertCentimetersToMeters(inputValue))) + " feet");
							else display.setText(convertMetersToFeet(convertCentimetersToMeters(inputValue)) + " feet");
						}
						else if (to.equals("Yards"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToYards(convertCentimetersToMeters(inputValue))) + " yards");
							else display.setText(convertMetersToYards(convertCentimetersToMeters(inputValue)) + " yards");
						}
						else if (to.equals("Meters"))
						{
							if (fraction)display.setText(decimalToFraction(convertCentimetersToMeters(inputValue)) + " meters");
							else display.setText(convertCentimetersToMeters(inputValue) + " meters");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToKilometers(convertCentimetersToMeters(inputValue))) + " kilometers");
							else display.setText(convertMetersToKilometers(convertCentimetersToMeters(inputValue)) + " kilometers");
						}
					}	
					else if(from.equals("Inches"))
					{
						if (to.equals("Centimeters"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToCentimeters(convertInchesToMeters(inputValue))) + " centimeters");
							else display.setText(convertMetersToCentimeters(convertInchesToMeters(inputValue)) + " centimeters");
						}
						else if (to.equals("Feet"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToFeet(convertInchesToMeters(inputValue))) + " feet");
							else display.setText(convertMetersToFeet(convertInchesToMeters(inputValue)) + " feet");
						}
						else if (to.equals("Yards"))
						{
							if (fraction) display.setText(decimalToFraction(convertMetersToYards(convertInchesToMeters(inputValue))) + " yards");
							else display.setText(convertMetersToYards(convertInchesToMeters(inputValue)) + " yards");
						}
						else if (to.equals("Meters"))
						{
							if (fraction)display.setText(decimalToFraction(convertInchesToMeters(inputValue)) + " meters");
							else display.setText(convertInchesToMeters(inputValue) + " meters");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToKilometers(convertInchesToMeters(inputValue))) + " kilometers");
							else display.setText(convertMetersToKilometers(convertInchesToMeters(inputValue)) + " kilometers");
						}
					}
					else if (from.equals("Feet"))
					{
						if (to.equals("Inches"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToInches(convertFeetToMeters(inputValue))) + " inches");
							else display.setText(convertMetersToInches(convertFeetToMeters(inputValue)) + " inches");
						}
						else if (to.equals("Centimeters"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToCentimeters(convertFeetToMeters(inputValue))) + " centimeters");
							else display.setText(convertMetersToCentimeters(convertFeetToMeters(inputValue)) + " centimeters");
						}
						else if (to.equals("Yards"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToYards(convertFeetToMeters(inputValue))) + " yards");
							else display.setText(convertMetersToYards(convertFeetToMeters(inputValue)) + " yards");
						}
						else if (to.equals("Meters"))
						{
							if (fraction)display.setText(decimalToFraction(convertFeetToMeters(inputValue)) + " meters");
							else display.setText(convertFeetToMeters(inputValue) + " meters");
						}
						else if (to.equals("Kilometers"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToKilometers(convertFeetToMeters(inputValue))) + " kilometers");
							else display.setText(convertMetersToYards(convertFeetToMeters(inputValue)) + " kilometers");
						}
					}
					else if (from.equals("Yards"))
					{
						if (to.equals("Inches"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToInches(convertYardsToMeters(inputValue))) + " inches");
							else display.setText(convertMetersToInches(convertYardsToMeters(inputValue)) + " inches");
						}
						else if (to.equals("Centimeters"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToCentimeters(convertYardsToMeters(inputValue))) + " centimeters");
							else display.setText(convertMetersToCentimeters(convertYardsToMeters(inputValue)) + " centimeters");
						}
						else if (to.equals("Feet"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToFeet(convertYardsToMeters(inputValue))) + " feet");
							else display.setText(convertMetersToFeet(convertYardsToMeters(inputValue)) + " feet");
						}
						else if (to.equals("Meters"))
						{
							if (fraction)display.setText(decimalToFraction(convertCentimetersToMeters(inputValue)) + " meters");
							else display.setText(convertCentimetersToMeters(inputValue) + " meters");
						}
						else if (to.equals("Kilometers"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToKilometers(convertCentimetersToMeters(inputValue))) + " kilometers");
							else display.setText(convertMetersToKilometers(convertCentimetersToMeters(inputValue)) + " kilometers");
						}
					}
					else if (from.equals("Meters"))
					{
						if (to.equals("Inches"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToInches(inputValue)) + " inches");
							else display.setText(convertMetersToInches(inputValue) + " inches");
						}
						else if (to.equals("Feet"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToFeet(inputValue)) + " feet");
							else display.setText(convertMetersToFeet(inputValue) + " feet");
						}
						else if (to.equals("Yards"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToYards(inputValue)) + " yards");
							else display.setText(convertMetersToYards(inputValue) + " yards");
						}
						else
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToKilometers(inputValue)) + " kilometers");
							else display.setText(convertMetersToKilometers(inputValue) + " kilometers");
						}
					}
					else
					{
						if (to.equals("Inches"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToInches(convertKilometersToMeters(inputValue))) + " inches");
							else display.setText(convertMetersToInches(convertKilometersToMeters(inputValue)) + " inches");
						}
						else if (to.equals("Feet"))
						{
							if (fraction) display.setText(decimalToFraction(convertMetersToFeet(convertKilometersToMeters(inputValue))) + " feet");
							else display.setText(convertMetersToFeet(convertKilometersToMeters(inputValue)) + " feet");
						}
						else if (to.equals("Yards"))
						{
							if (fraction)display.setText(decimalToFraction(convertMetersToYards(convertKilometersToMeters(inputValue))) + " yards");
							else display.setText(convertMetersToYards(convertKilometersToMeters(inputValue)) + " yards");
						}
						else if (to.equals("Meters"))
						{
							if (fraction)display.setText(decimalToFraction(convertKilometersToMeters(inputValue)) + " meters");
							else display.setText(convertKilometersToMeters(inputValue) + " meters");
						}
					}
				}	
				break;
		}
	}
	private float convertMetersToKilometers(float inputValue)
	{
		String ans = Float.toString((float) ((float) inputValue/1000.0));
		return checkDecimal(ans);
	}
	private float convertKilometersToMeters(float inputValue)
	{
		
		String ans = Float.toString((float) ((float) inputValue * 1000.0));
		return checkDecimal(ans);
	}
	private float convertMetersToCentimeters(float inputValue)
	{
		String ans = Float.toString((float) ((float) inputValue * 100.0));
		return checkDecimal(ans);
	}
	private float convertMetersToYards(float inputValue) {return checkDecimal(Float.toString((float) (inputValue * 1.0936133))); }
	private float convertMetersToFeet(float inputValue) {return checkDecimal(Float.toString((float) (inputValue * 3.28083989501312)));}
	private float convertMetersToInches(float inputValue){return checkDecimal(Float.toString((float) (inputValue * 39.3700787)));}
	private float convertCentimetersToMeters(float inputValue){return checkDecimal(Float.toString((float) (inputValue * 0.01))); }
	private float convertFeetToMeters(float inputValue){return checkDecimal(Float.toString((float) (inputValue * 0.3048)));}
	private float convertYardsToMeters(float inputValue)
	{
		String ans = Float.toString((float) (inputValue * 0.9144));
		return checkDecimal(ans);
	}
	private float convertInchesToMeters(float inputValue){return checkDecimal(Float.toString((float) (inputValue * 0.0254)));}
	
	
	public float checkDecimal(String ans)
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