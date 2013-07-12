package com.sapra.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DigitalClock;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCalendar extends Activity
{
	private String day;
	private GridView gridview;
	private ArrayList<String> sundayItems = new ArrayList<String>();
	private ArrayList<String> mondayItems = new ArrayList<String>();
	private ArrayList<String> tuesdayItems = new ArrayList<String>();
	private ArrayList<String> wednesdayItems = new ArrayList<String>();
	private ArrayList<String> thursdayItems = new ArrayList<String>();
	private ArrayList<String> fridayItems = new ArrayList<String>();
	private ArrayList<String> saturdayItems = new ArrayList<String>();
	private int[] idArray = {R.id.Sunday, R.id.Monday, R.id.Tuesday, R.id.Wednesday, R.id.Thursday, R.id.Friday, R.id.Saturday};
	private TabHost tabs;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar); 
        setUpTabs();
    }
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putStringArrayList("sunday", sundayItems);
    	savedInstanceState.putStringArrayList("monday", mondayItems);
    	savedInstanceState.putStringArrayList("tuesday", tuesdayItems);
    	savedInstanceState.putStringArrayList("wednesday", wednesdayItems);
    	savedInstanceState.putStringArrayList("thursday", thursdayItems);
    	savedInstanceState.putStringArrayList("friday", fridayItems);
    	savedInstanceState.putStringArrayList("saturday", saturdayItems);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	  super.onRestoreInstanceState(savedInstanceState);
    	  sundayItems = savedInstanceState.getStringArrayList("sunday");
    	  mondayItems = savedInstanceState.getStringArrayList("monday");
    	  tuesdayItems = savedInstanceState.getStringArrayList("tuesday");
    	  wednesdayItems = savedInstanceState.getStringArrayList("wednesday");
    	  thursdayItems = savedInstanceState.getStringArrayList("thursday");
    	  fridayItems = savedInstanceState.getStringArrayList("friday");
    	  saturdayItems = savedInstanceState.getStringArrayList("saturday");
    }
    private void setUpTabs()
    {
    	tabs= (TabHost) findViewById(R.id.tabhost);  
        tabs.setup();
        
        TabSpec tab_one = tabs.newTabSpec("tab_one_btn_tab"); 
        tab_one.setContent(R.id.SundayTab);
        tab_one.setIndicator("Sunday"); 
        tabs.addTab(tab_one);
        
        TabSpec tab_two = tabs.newTabSpec("tab_two_btn_tab");
        tab_two.setContent(R.id.MondayTab);
        tab_two.setIndicator("Monday");
        tabs.addTab(tab_two);
        
        TabSpec tab_three = tabs.newTabSpec("tab_three_btn_tab");
        tab_three.setContent(R.id.TuesdayTab);
        tab_three.setIndicator("Tuesday");
        tabs.addTab(tab_three);
        
        TabSpec tab_four = tabs.newTabSpec("tab_four_btn_tab");
        tab_four.setContent(R.id.WednesdayTab);
        tab_four.setIndicator("Wednesday");
        tabs.addTab(tab_four);
        
        TabSpec tab_five = tabs.newTabSpec("tab_five_btn_tab");
        tab_five.setContent(R.id.ThursdayTab);
        tab_five.setIndicator("Thursday");
        tabs.addTab(tab_five);
        
        TabSpec tab_six = tabs.newTabSpec("tab_six_btn_tab");
        tab_six.setContent(R.id.FridayTab);
        tab_six.setIndicator("Friday");
        tabs.addTab(tab_six);
        
        TabSpec tab_seven = tabs.newTabSpec("tab_seven_btn_tab");
        tab_seven.setContent(R.id.SaturdayTab);
        tab_seven.setIndicator("Saturday");
        tabs.addTab(tab_seven);
	}
	public void viewDetails(View view)
    {
    	day = "";
    	int v = tabs.getCurrentTab();
    	switch (v)
    	{
    		case 0:
    			day = "Sunday"; break;
    		case 1:
    			day = "Monday"; break;
    		case 2: 
    			day = "Tuesday"; break;
    		case 3: 
    			day = "Wednesday"; break;
    		case 4: 
    			day = "Thursday"; break;
    		case 5:
    			day = "Friday"; break;
    		case 6: 
    			day = "Saturday"; break;
    	}
    	Intent intent = new Intent(this, Medication.class);
    	startActivityForResult(intent, R.id.secondary_finished);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	String name = "", time = "", amount = "", repeat = "";
         switch(requestCode)
         {
             case R.id.secondary_finished:
	         {
	        	 if (resultCode == RESULT_OK)
	             {
	            	Bundle res = data.getExtras();
	            	name = res.getString("name");
	            	time = res.getString("time");
	            	amount = res.getString("amount");
	            	repeat = res.getString("repeat");
	            	updateCalendar(name, time, amount, repeat);
	             }
	             else if (resultCode == RESULT_CANCELED)
	             {
	            	 
	             }
	         }	 
             break;
         }
    }
	private void updateCalendar(final String name, final String time, final String amount, final String repeat)
	{
		ArrayList<String> temp = new ArrayList<String>();
		
		int id;
		if (day.equals("Sunday"))
		{	id = R.id.Sunday; sundayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = sundayItems;}
		else if (day.equals("Monday"))
		{	id = R.id.Monday; mondayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = mondayItems;}
		else if (day.equals("Tuesday"))
		{	id = R.id.Tuesday; tuesdayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = tuesdayItems;}
		else if (day.equals("Wednesday"))
		{	id = R.id.Wednesday; wednesdayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = wednesdayItems;}
		else if (day.equals("Thursday"))
		{	id = R.id.Thursday; thursdayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = thursdayItems;}
		else if (day.equals("Friday"))
		{	id = R.id.Friday; fridayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = fridayItems;}
		else 
		{ 	id = R.id.Saturday; saturdayItems.add(name + "\n"+ amount + "pills\n at" + time); temp = saturdayItems;}
		
		
		if (repeat.equals("Daily"))
		{
			for (int i = 0; i < 7; i++)
			{
				gridview = (GridView) findViewById(idArray[i]);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
				displayMed(idArray[i], adapter);
			}
		}
		else if (repeat.equals("Three Times A Day"))
		{
			for (int i = 0; i < 3; i++)
			{
				//gridView = (GridView) findViewById()
				//displayMed()
			}
		}
		else if (repeat.equals("Every Other Day")) {}
		else if (repeat.equals("Weekly")) {}
		

		

		gridview.setOnItemClickListener(new OnItemClickListener()  
	    {  
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,  long arg3)
			{  
				 Bundle bun = new Bundle();
				 bun.putString("name", name);
				 bun.putString("amount", amount);
				 bun.putString("time", time);
				 
				 Intent intent = new Intent(ViewCalendar.this, Medication.class);
				 intent.putExtras(bun);
				 
				 startActivity(intent);		
			}
	    });
		
		new CountDownTimer(getNextTimeMilliseconds(), 1000)
		{
			TextView dc = (TextView) findViewById(R.id.digitalClock1);
		     public void onTick(long millisUntilFinished)
		     {
		         dc.setText("Time remaining " + millisUntilFinished / 1000);
		     }
		     public void onFinish() {
		         dc.setText("Please take your medication.");
		     }
		  }.start();
	}
	private void displayMed(int id, ArrayAdapter<String> adapter)
	{
		gridview.setAdapter(adapter);
	}
	private long getNextTimeMilliseconds()
	{
		switch(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
		{
			case 1:
			{
				for (int i = 1; i < 8; i++)
				{
					if (i < sundayItems.size() && sundayItems.get(i) != null)
					{
						int time = sundayItems.get(i).indexOf("at");
						int nextHour = Integer.parseInt(sundayItems.get(i).substring(time + 2, time + 3));
						int nextMin = Integer.parseInt(sundayItems.get(i).substring(time + 3));
						return ((long) nextHour * 3600000 * nextMin * 60000) - System.currentTimeMillis();
					}
				}
			}
		}
		return 0;
	}
}	