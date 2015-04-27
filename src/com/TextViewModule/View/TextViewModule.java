package com.TextViewModule.View;

import com.TextViewModule.Module.PaintText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class TextViewModule extends View {
	public final int DOT_LAST = 0;
	public final int DOT_CENTER = 1;
	public final int RUNNING_TEXT = 2;
	private int mode = 0;
	private int allWidth;

	private String yourText = "yourText";
	private int yourTextSize = 100;
	private int yourTextColor = Color.BLACK;

	private PaintText paintText;
	private float yourTextWidth;
	private String[] letters;
	private int textLength = 0;
	private float textWholeLength;

	private float xPosition = 0 + getWidth();

	public TextViewModule(Context context) {
		super(context);
	}

	protected void onDraw(final Canvas canvas) {
		super.onDraw(canvas);
		this.allWidth = getWidth();
		getCalYourTextWidth();
		// 判斷使用何種模式
		if (mode == DOT_LAST) {
			// 判斷字長度是否超出範圍，有就顯示"..."在字的後面
			if (yourTextWidth > (this.allWidth - getDotTextWidth())) {
				letters = yourText.split("");
				textLength = (int) ((float) (this.allWidth - getDotTextWidth()) / getLetterWidth("W"));
				for (int i = 0; i < textLength; i++) {
					textWholeLength = 0;
					// 由於每個字占的寬度不同，所以要先算好"..."前字的長度
					for (int j = 0; j < i; j++) {
						textWholeLength = textWholeLength
								+ getLetterWidth(letters[j]);
					}
					canvas.drawText(letters[i], 0 + textWholeLength,
							0 + this.paintText.getCurrent(), paintText);

					textWholeLength = textWholeLength
							+ getLetterWidth(letters[i]);
				}
				// 最後印上"..."
				canvas.drawText("...", 0 + textWholeLength,
						0 + this.paintText.getCurrent(), paintText);
			} else {
				canvas.drawText(yourText, 0, 0 + this.paintText.getCurrent(),
						paintText);
			}
		} else if (mode == DOT_CENTER) {
			// 判斷字長度是否超出範圍，有就顯示"..."在字串的中間
			if (yourTextWidth > (this.allWidth - getDotTextWidth())) {
				letters = yourText.split("");
				textLength = (int) ((float) (this.allWidth - getDotTextWidth()) / getLetterWidth("W"));
				// 先印前半段文字，作法同"..."在後方
				for (int i = 0; i < textLength / 2; i++) {
					textWholeLength = 0;
					for (int j = 0; j < i; j++) {
						textWholeLength = textWholeLength
								+ getLetterWidth(letters[j]);
					}
					canvas.drawText(letters[i], 0 + textWholeLength,
							0 + this.paintText.getCurrent(), paintText);

					textWholeLength = textWholeLength
							+ getLetterWidth(letters[i]);
				}
				// 接著印上"..."
				canvas.drawText("...", 0 + textWholeLength,
						0 + this.paintText.getCurrent(), paintText);
				textWholeLength = textWholeLength + getLetterWidth("...");
				// 最後印後面的文字
				for (int i = textLength / 2; i < textLength; i++) {
					// 拿出最後幾個字先計算長度
					textWholeLength = textWholeLength
							+ getLetterWidth(letters[letters.length
									- (textLength - textLength / 2)
									+ (i - textLength / 2)]);
					// 依照計算的長度印上字
					canvas.drawText(letters[letters.length
							- (textLength - textLength / 2)
							+ (i - textLength / 2)], 0 + textWholeLength,
							0 + this.paintText.getCurrent(), paintText);
				}
			} else {
				canvas.drawText(yourText, 0, 0 + this.paintText.getCurrent(),
						paintText);
			}
		} else if (mode == RUNNING_TEXT) {
			canvas.drawText(yourText, xPosition, 0 + paintText.getCurrent(),
					paintText);
		} else {
			Log.e("Error", "Undefine mode");
		}
	}

	// 設定要何種模式 0 = "..."在後，1 = "..."在中央
	public void setMode(int mode) {
		this.mode = mode;
		invalidate();
	}

	// 設定字
	public void setYourText(String yourText) {
		this.yourText = yourText;
		invalidate();
	}

	// 設定字體大小
	public void setYourTextSize(int yourTextSize) {
		this.yourTextSize = yourTextSize;
		invalidate();
	}

	// 設定字體顏色
	public void setYourTextColor(int yourTextColor) {
		this.yourTextColor = yourTextColor;
		invalidate();
	}

	// 計算字長度
	private float getCalYourTextWidth() {
		if (this.paintText == null) {
			this.paintText = new PaintText(yourText);
		}
		this.paintText.setColor(yourTextColor);
		this.paintText.setTextSize(yourTextSize);
		return yourTextWidth = this.paintText.getWidth();
	}

	// 計算"..."長度
	private float getDotTextWidth() {
		PaintText pText = new PaintText("...");
		pText.setTextSize(yourTextSize);
		return pText.getWidth();
	}

	// 計算單一個字長度
	private float getLetterWidth(String letter) {
		PaintText pText = new PaintText(letter);
		pText.setTextSize(yourTextSize);
		return pText.getWidth();
	}

	// 取得目前跑馬燈位置
	public float getXPosition() {
		return this.xPosition;
	}

	// 設定跑馬燈的位置
	public void setXPosition(float xPosition) {
		this.xPosition = xPosition;
		if (xPosition < 0 - getCalYourTextWidth()) {
			this.xPosition = 0 + getWidth();
		}
		invalidate();
	}
}
