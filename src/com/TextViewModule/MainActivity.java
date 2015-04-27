package com.TextViewModule;

import com.TextViewModule.View.TextViewLayout;
import com.TextViewModule.View.TextViewModule;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends Activity {
	private TextViewLayout layout;
	
	private TextViewModule last;
	private TextViewModule center;
	private TextViewModule running;
	private Handler handler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout = new TextViewLayout(this));
        
        last = layout.getLast();
        last.setYourText("abcdefghijklmnopqrstuvwxyz");
        
        center = layout.getCenter();
        center.setYourText("abcdefghijklmnopqrstuvwxyz");
        
        running = layout.getRunning();
        running.setYourText("abcdefghijklmnopqrstuvwxyz");
        
        handler = new Handler();
        handler.post(run);
    }
    
    private Runnable run = new Runnable() {
		public void run() {
			running.setXPosition(running.getXPosition() - 5);
			handler.postDelayed(run, 100);
		}
	};
}
