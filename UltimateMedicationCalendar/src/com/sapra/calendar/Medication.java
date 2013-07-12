package com.sapra.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

public class Medication extends Activity
{
	private String name;
	private String amount;
	private String time;
	private RadioButton date;
	private RadioGroup group;
	private Button save;
	private Button cancel;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication);  
        if (getIntent().hasExtra("name"))
        { 
        	save = (Button) findViewById(R.id.save);
        	save.setText("Save edits");
        	Bundle bun = getIntent().getExtras(); name = (String) bun.get("name"); amount = (String) bun.get("amount"); time = (String) bun.get("time");
        	
        	EditText et = (EditText) findViewById(R.id.name);
            et.setText(name);
            EditText et1 = (EditText) findViewById(R.id.quantity);
            et1.setText(amount);
        }
        addListenerOnButton();
    }
    private void ReturnToParent(int result)
    {
    	if (result == RESULT_OK)
    	{	
	       Bundle conData = new Bundle();
	       conData.putString("name", name);
	       conData.putString("time", time);
	       conData.putString("amount", amount);
	       conData.putString("repeat", date.toString());
	       Intent intent = new Intent();
	       intent.putExtras(conData);
	       setResult(RESULT_OK, intent);
    	}
    	else
    	{	
    		setResult(RESULT_CANCELED, new Intent());
    	}
    	finish();
    }
    public void addListenerOnButton()
    {
    	group = (RadioGroup) findViewById(R.id.group);
        save = (Button) findViewById(R.id.save);
     
    	save.setOnClickListener(new OnClickListener()
    	{
       		public void onClick(View v)
    		{
       			TimePicker tp = (TimePicker) findViewById(R.id.time);
       			tp.setIs24HourView(false);
    			// get selected radio button from radioGroup
    			int selectedId = group.getCheckedRadioButtonId();
    			// find the radiobutton by returned id
    		    date = (RadioButton) findViewById(selectedId);

    		    name = ((EditText) findViewById(R.id.name)).getText().toString();
    	        amount = ((EditText)findViewById(R.id.quantity)).getText().toString();
    	        
    	        int hour = tp.getCurrentHour();
    	        String minute = tp.getCurrentMinute().toString();
    	        if (Integer.parseInt(minute) < 10)
    	        	minute = "0" + minute;
    	        
    	        //if (hour > 12) hour -= 12;
    	        time = (hour + ":" + minute);
    	        
    	        if (name.equals("") || amount.equals(""))
    	        { displayError(); return; }
    	        
    	        else
    	        	ReturnToParent(RESULT_OK);
    		}
    	});
    	
    	cancel = (Button) findViewById(R.id.button1);
    	cancel.setOnClickListener(new OnClickListener()
    	{
    		public void onClick(View v)
    		{
    			ReturnToParent(RESULT_CANCELED);
    		}
    	});
    }
	protected void displayError()
	{
		Toast.makeText(this, "Please fill in all information.", Toast.LENGTH_LONG).show();
	}
}