package com.TextViewModule.View;

import android.content.Context;
import android.widget.RelativeLayout;

import com.TextViewModule.Module.WH;

public class TextViewLayout extends RelativeLayout {
	private Context context;
	private TextViewModule last;
	private TextViewModule center;
	private TextViewModule running;
	private WH WH;

	public TextViewLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		WH = new WH(context);
		setView();
	}

	private void setView() {
		{
			last = new TextViewModule(context);
			last.setId(getRandomId());
			LayoutParams lParams = new LayoutParams(WH.getW(25),
					LayoutParams.WRAP_CONTENT);
			last.setLayoutParams(lParams);
			last.setMode(last.DOT_LAST);
			this.addView(last);
		}
		{
			center = new TextViewModule(context);
			center.setId(getRandomId());
			LayoutParams lParams = new LayoutParams(WH.getW(25),
					LayoutParams.WRAP_CONTENT);
			lParams.addRule(RIGHT_OF, last.getId());
			center.setLayoutParams(lParams);
			center.setMode(center.DOT_CENTER);
			this.addView(center);
		}
		{
			running = new TextViewModule(context);
			running.setId(getRandomId());
			LayoutParams lParams = new LayoutParams(WH.getW(25),
					LayoutParams.WRAP_CONTENT);
			lParams.addRule(RIGHT_OF, center.getId());
			running.setLayoutParams(lParams);
			running.setMode(running.RUNNING_TEXT);
			this.addView(running);
		}
	}

	// 取得亂數ID
	protected int getRandomId() {
		return (int) (Math.random() * 1000000);
	}

	public TextViewModule getLast(){
		return this.last;
	}
	
	public TextViewModule getCenter(){
		return this.center;
	}
	
	public TextViewModule getRunning() {
		return this.running;
	}
}
