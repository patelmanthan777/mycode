///*
// * Copyright (C) 2014 kince
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.administrator.myapplication.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.Align;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.LayerDrawable;
//import android.text.TextPaint;
//import android.util.AttributeSet;
//import android.widget.ProgressBar;
//
//import com.kince.saundprogressbar.R;
//
///**
// * An enhanced version of the ProgressBar which provides greater control over
// * how the progress bar is drawn and displayed. The motivation is to allow us to
// * customize the appearance of the progress bar more freely. We can now display
// * a rounded cap at the end of the progress bar and optionally show an overlay.
// * We can also present a progress indicator to show the percentage completed or
// * a more customized value by implementing the Formatter interface.
// *
// * @author kince
// *进度显示的也是圆弧的progress
// */
//public class SaundProgressBar extends ProgressBar {
//	private int m_offset = 5;
//	private TextPaint m_textPaint;
//	private Formatter m_formatter;
//
//	public SaundProgressBar(Context context) {
//		this(context, null);
//	}
//
//	public SaundProgressBar(Context context, AttributeSet attrs) {
//		this(context, attrs, 0);
//	}
//
//	public SaundProgressBar(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//
//		// create a default progress bar indicator text paint used for drawing
//		// the
//		// text on to the canvas
//		m_textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
//		m_textPaint.density = getResources().getDisplayMetrics().density;
//
//		m_textPaint.setColor(Color.WHITE);
//		m_textPaint.setTextAlign(Align.CENTER);
//		m_textPaint.setTextSize(10);
//		m_textPaint.setFakeBoldText(true);
//
//		// get the styleable attributes as defined in the xml
//		TypedArray a = context.obtainStyledAttributes(attrs,
//				R.styleable.SaundProgressBar, defStyle, 0);
//
//		if (a != null) {
//			m_textPaint.setTextSize(a.getDimension(
//					R.styleable.SaundProgressBar_textSize, 10));
//			m_textPaint.setColor(a.getColor(
//					R.styleable.SaundProgressBar_textColor, Color.WHITE));
//
//			int alignIndex = (a.getInt(R.styleable.SaundProgressBar_textAlign,
//					1));
//			if (alignIndex == 0) {
//				m_textPaint.setTextAlign(Align.LEFT);
//			} else if (alignIndex == 1) {
//				m_textPaint.setTextAlign(Align.CENTER);
//			} else if (alignIndex == 2) {
//				m_textPaint.setTextAlign(Align.RIGHT);
//			}
//
//			int textStyle = (a
//					.getInt(R.styleable.SaundProgressBar_textStyle, 1));
//			if (textStyle == 0) {
//				m_textPaint.setTextSkewX(0.0f);
//				m_textPaint.setFakeBoldText(false);
//			} else if (textStyle == 1) {
//				m_textPaint.setTextSkewX(0.0f);
//				m_textPaint.setFakeBoldText(true);
//			} else if (textStyle == 2) {
//				m_textPaint.setTextSkewX(-0.25f);
//				m_textPaint.setFakeBoldText(false);
//			}
//
//
//			m_offset = (int) a.getDimension(
//					R.styleable.SaundProgressBar_offset, 0);
//
//			a.recycle();
//		}
//	}
//
//	/**
//	 * The text formatter is used for customizing the presentation of the text
//	 * displayed in the progress indicator. The default text format is X% where
//	 * X is [0,100]. To use the formatter you must provide an object which
//	 * implements the {@linkplain SaundProgressBar.Formatter} interface.
//	 *
//	 * @param formatter
//	 */
//	public void setTextFormatter(Formatter formatter) {
//		m_formatter = formatter;
//	}
//
//	/**
//	 * The additional offset is for tweaking the position of the indicator.
//	 *
//	 * @param offset
//	 */
//	public void setOffset(int offset) {
//		m_offset = offset;
//	}
//
//	/**
//	 * Set the text color
//	 *
//	 * @param color
//	 */
//	public void setTextColor(int color) {
//		m_textPaint.setColor(color);
//	}
//
//	/**
//	 * Set the text size.
//	 *
//	 * @param size
//	 */
//	public void setTextSize(float size) {
//		m_textPaint.setTextSize(size);
//	}
//
//	/**
//	 * Set the text bold.
//	 *
//	 * @param bold
//	 */
//	public void setTextBold(boolean bold) {
//		m_textPaint.setFakeBoldText(true);
//	}
//
//	/**
//	 * Set the alignment of the text.
//	 *
//	 * @param align
//	 */
//	public void setTextAlign(Align align) {
//		m_textPaint.setTextAlign(align);
//	}
//
//	/**
//	 * Set the paint object used to draw the text on to the canvas.
//	 *
//	 * @param paint
//	 */
//	public void setPaint(TextPaint paint) {
//		m_textPaint = paint;
//	}
//
//	@Override
//	protected synchronized void onDraw(Canvas canvas) {
//		Drawable progressDrawable = getProgressDrawable();
//
//		// If we have an indicator then we'll need to adjust the drawable bounds
//		// for
//		// the progress bar and its layers (if the drawable is a layer
//		// drawable).
//		// This will ensure the progress bar gets drawn in the correct position
//
//		// update the size of the progress bar and overlay
//		updateProgressBar();
//
//		super.onDraw(canvas);
//
//		// Draw the indicator to match the far right position of the progress
//		// bar
//	}
//
//	@Override
//	public synchronized void setProgress(int progress) {
//		super.setProgress(progress);
//
//		// the setProgress super will not change the details of the progress bar
//		// anymore so we need to force an update to redraw the progress bar
//		invalidate();
//	}
//
//	private float getScale(int progress) {
//		float scale = getMax() > 0 ? (float) progress / (float) getMax() : 0;
//
//		return scale;
//	}
//
//	/**
//	 * Instead of using clipping regions to uncover the progress bar as the
//	 * progress increases we increase the drawable regions for the progress bar
//	 * and pattern overlay. Doing this gives us greater control and allows us to
//	 * show the rounded cap on the progress bar.
//	 */
//	private void updateProgressBar() {
//		Drawable progressDrawable = getProgressDrawable();
//
//		if (progressDrawable != null
//				&& progressDrawable instanceof LayerDrawable) {
//			LayerDrawable d = (LayerDrawable) progressDrawable;
//
//			final float scale = getScale(getProgress());
//
//			// get the progress bar and update it's size
//			Drawable progressBar = d.findDrawableByLayerId(R.id.progress);
//
//			final int width = d.getBounds().right - d.getBounds().left;
//
//			if (progressBar != null) {
//				Rect progressBarBounds = progressBar.getBounds();
//				progressBarBounds.right = progressBarBounds.left
//						+ (int) (width * scale + 0.5f);
//				progressBar.setBounds(progressBarBounds);
//			}
//
//			// get the pattern overlay
//			Drawable patternOverlay = d.findDrawableByLayerId(R.id.pattern);
//
//			if (patternOverlay != null) {
//				if (progressBar != null) {
//					// we want our pattern overlay to sit inside the bounds of
//					// our progress bar
//					Rect patternOverlayBounds = progressBar.copyBounds();
//					final int left = patternOverlayBounds.left;
//					final int right = patternOverlayBounds.right;
//
//					patternOverlayBounds.left = (left + 1 > right) ? left
//							: left + 1;
//					patternOverlayBounds.right = (right > 0) ? right - 1
//							: right;
//					patternOverlay.setBounds(patternOverlayBounds);
//				} else {
//					// we don't have a progress bar so just treat this like the
//					// progress bar
//					Rect patternOverlayBounds = patternOverlay.getBounds();
//					patternOverlayBounds.right = patternOverlayBounds.left
//							+ (int) (width * scale + 0.5f);
//					patternOverlay.setBounds(patternOverlayBounds);
//				}
//			}
//		}
//	}
//
//	/**
//	 * You must implement this interface if you wish to present a custom
//	 * formatted text to be used by the Progress Indicator. The default format
//	 * is X% where X [0,100]
//	 *
//	 * @author jsaund
//	 *
//	 */
//	public interface Formatter {
//		public String getText(int progress);
//	}
//}
