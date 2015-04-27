package com.TextViewModule.Module;

import android.graphics.Paint;

public class PaintText extends Paint {
	private String text;

	public PaintText(String text) {
		this.text = text;
		init();
	}

	private void init() {
		measureText(text);
		setFlags(Paint.ANTI_ALIAS_FLAG);
	}

	public float getWidth() {
		return measureText(text);
	}

	public float getHeight() {
		return Math.abs(ascent()) + descent();
	}

	public float getCurrent() {
		return Math.abs(ascent());
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
		init();
	}
	
	public float getVerticalCenter(){
		return getHeight()/2.0f;
	}
	
	public float getHorizontalCenter(){
		return getWidth()/2.0f;
	}
	public void setTextSize(float textSize) {
		super.setTextSize(textSize);
		init();
	}
}
