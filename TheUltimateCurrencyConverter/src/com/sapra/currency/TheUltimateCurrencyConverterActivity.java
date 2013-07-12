package com.sapra.currency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TheUltimateCurrencyConverterActivity extends Activity
{
	private Spinner fromSpinner;
	private Spinner toSpinner;
	private ArrayAdapter arrayFrom;
	private ArrayAdapter arrayTo;
	private int startLoc, endLoc;
    private TextView tv;
    private EditText text;
	private String from;
	private String to;
	private String[] currency_array;
	private SortedMap<String, Double> currency_map;
	private ListView lv;
	private static ArrayList<String> arrayList = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setSpinners();
        tv = (TextView) findViewById(R.id.textView4);
        lv = (ListView) findViewById(R.id.listView);
        currency_array = this.getResources().getStringArray(R.array.currency_array);
        DownloadWebPageTask ct = new DownloadWebPageTask();
        ct.execute(new String[] { "https://raw.github.com/currencybot/open-exchange-rates/master/latest.json" });
        addImageListener();
        addListViewListener();
    }
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.putStringArrayList("arrayList", arrayList);
    	savedInstanceState.putString("from", from);
    	savedInstanceState.putString("to", to);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
    	super.onRestoreInstanceState(savedInstanceState);
    	arrayList = savedInstanceState.getStringArrayList("arrayList");
    	from = savedInstanceState.getString("from");
    	to = savedInstanceState.getString("to");
    }
    private void addListViewListener()
    {
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				String favorite = arg0.getItemAtPosition(arg2).toString();
				String from = favorite.substring(0, favorite.indexOf("to") - 1);
				String to = favorite.substring(favorite.indexOf("to") + 3);
				fromSpinner.setSelection(arrayFrom.getPosition(from));
				toSpinner.setSelection(arrayTo.getPosition(to));
			}
		});
	}
	private void addImageListener()
    {
		Button b = (Button) findViewById(R.id.imageButtonSelector);
		b.setOnClickListener(new OnClickListener()
		{
			public void onClick(View button)
			{
				if (noDuplicates())
				{
					if (button.isSelected())
						button.setSelected(false);
					else
					{
						button.setSelected(true);
						addFavorite();
					}
				}
			}
			private void addFavorite()
			{
				arrayList.add(getFavorite());
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TheUltimateCurrencyConverterActivity.this, android.R.layout.simple_list_item_1, arrayList);
				lv.setAdapter(arrayAdapter);
			}	
		});		
	}
	private boolean noDuplicates()
	{
		String favorite = getFavorite();
		for (String s : arrayList)
			if (favorite.equals(s))
				return false;
		return true;
	}
	private String getFavorite()
	{
		return from + " to " + to;
	}
	
    public void switchSpinners(View view)
    {
    	String temp = from;
    	from = to;
    	to = temp;
    	int fromPosition = arrayFrom.getPosition(from);
    	int toPosition = arrayTo.getPosition(to);
    	fromSpinner.setSelection(toPosition);
    	toSpinner.setSelection(fromPosition);
    	myClickHandler(view);
    }
    public void myClickHandler(View view)
	{
		displayUnit();
		boolean errorMessage = false; 
		double input = 0;
		try
		{
			input = Double.parseDouble(text.getText().toString());
		}
		catch (NumberFormatException e) {errorMessage = true;}
		finally
		{
			if (errorMessage || text.getText().toString().length() == 0)
			{
				Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show(); return;
			}
		}
		Button display = (Button) findViewById(R.id.ans);
		display.setEnabled(true);
		if (from.equals(to))
			display.setText(input + " " + from + "s = " + input + " " +  to + "s");
		else
		{
			double inputUnit = currency_map.get(currency_array[startLoc].substring(0, 3));
			double usValue = input/inputUnit;
			double ans = usValue * currency_map.get(currency_array[endLoc].substring(0, 3));
			if (to.substring(to.length() - 1).equals("s"))
				display.setText(input + " " + from + "s = " + checkDecimal(ans) + " " + to.substring(5));
			else
				display.setText(input + " " + from + "s = " + checkDecimal(ans) + " " + to.substring(5) + "s");
		}
	}	
    private void displayUnit()
    {    	
    	for (int i = 0; i < currency_array.length; i++)
		{
			if (currency_array[i].equals(from))
				startLoc = i;
			if (currency_array[i].equals(to))
				endLoc = i;
		}
		double inputUnit = currency_map.get(currency_array[startLoc].substring(0, 3));
		double usValue = 1.0/inputUnit;
		double ans = usValue * currency_map.get(currency_array[endLoc].substring(0, 3));
		ans = checkDecimal(ans);
		tv.setText("1 " + from.substring(0, 3) + " = " + ans + "  " + to.substring(0, 3) + "s");
	}
	private double checkDecimal(double ans1)
	{
    	String ans = Double.toString(ans1);
		if (ans.length() > 8)
			return Double.parseDouble(ans.substring(0, 7));
		else 
			return Double.parseDouble(ans);
	}
    public class FromListener implements OnItemSelectedListener
	{
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
	    {
	    	String oldFrom = from;
	    	from = parent.getItemAtPosition(pos).toString();
	    	if(oldFrom != null && from != null)
	    	{
	    		Button b = (Button) findViewById(R.id.imageButtonSelector);
	    		//if (noDuplicates())
	    		//	b.setBackgroundResource(R.drawable.empty);
	    	}
	    }
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
	public class ToListener implements OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
		{
			String oldTo = to;
			to = arg0.getItemAtPosition(arg2).toString();
			if (oldTo != null && to != null)
			{
				Button b = (Button) findViewById(R.id.imageButtonSelector);
				//if (noDuplicates())
	    		//	b.setBackgroundResource(R.drawable.empty);
			}	
		}
		public void onNothingSelected(AdapterView<?> arg0)
		{
		}
	}
	public void setSpinners()
	{
		  text = (EditText) findViewById(R.id.editText1);  
	      fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
	      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
	      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      fromSpinner.setAdapter(adapter);
	      fromSpinner.setOnItemSelectedListener(new FromListener());
	      arrayFrom = (ArrayAdapter) fromSpinner.getAdapter();
	      toSpinner = (Spinner) findViewById(R.id.toSpinner);
	      ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
	      adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      toSpinner.setAdapter(adapter1);
	      toSpinner.setOnItemSelectedListener(new ToListener());
	      arrayTo = (ArrayAdapter) toSpinner.getAdapter();
	}
	private class DownloadWebPageTask extends AsyncTask<String, Void, SortedMap<String, Double>>
	{
		 protected SortedMap<String, Double> doInBackground(String... urls) {
			    SortedMap<String, Double> result = new TreeMap<String, Double>();

			    HttpClient client = new DefaultHttpClient();
			    for (String url : urls) {
			      try
			      {
			        HttpGet request = new HttpGet(url);
			        HttpResponse response = client.execute(request);
			        String jsonString = EntityUtils.toString(response.getEntity());

			        JSONObject json = new JSONObject(jsonString);
			        JSONObject rates = json.getJSONObject("rates");
			        Iterator<String> currencyCodes = rates.keys();
			        while (currencyCodes.hasNext())
			        {
			          String currencyCode = currencyCodes.next();
			          double rate = rates.getDouble(currencyCode);
			          result.put(currencyCode, rate);
			        }
			      } catch (Exception ex)
			      {
			    	  //Log.e("tag", "Unable to download updated rates from " + url);
			      }
			    }
			    return result;
			  }
		 protected void onPostExecute(SortedMap<String, Double> result)
		 { 
			 currency_map = result;
		 }
	}
}