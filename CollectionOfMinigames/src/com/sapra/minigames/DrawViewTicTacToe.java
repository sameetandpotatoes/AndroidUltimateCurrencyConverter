package com.sapra.minigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.EditText;

public class DrawViewTicTacToe extends View
{
	private EditText text;
    Paint paint = new Paint();
    private Canvas canvas;
    private int zone;
    public DrawViewTicTacToe(Context context)
    {
        super(context);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        text = (EditText) findViewById(R.id.editText1);
    }
    public void onDraw(Canvas canvas)
    {
    	this.canvas = canvas;
    	this.canvas.drawLine(150, 50, 150, 550, paint);
        this.canvas.drawLine(275, 50, 275, 550, paint);
        this.canvas.drawLine(25, 200, 400, 200, paint);
        this.canvas.drawLine(25, 375, 400, 375, paint);
        
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setStrokeJoin(Paint.Join.MITER);
        String inputValue = TicTacToeDirections.getName();
        this.canvas.drawText(inputValue, 0, 575, paint);
    }
    public void draw(String piece)
    {
    	zone = getZone();
    }
    private int getZone()
    {
    	return 0;
    }
}
