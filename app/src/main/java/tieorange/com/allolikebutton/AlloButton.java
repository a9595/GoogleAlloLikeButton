package tieorange.com.allolikebutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import in.championswimmer.sfg.lib.SimpleFingerGestures;

/**
 * Created by tieorange on 08/09/16.
 */

public class AlloButton extends RelativeLayout {
  private static final String TAG = AlloButton.class.getCanonicalName();
  View mRootView;
  private AlloButton mImageButton;
  private SimpleFingerGestures mSwipeListener = new SimpleFingerGestures();
  private VerticalSeekBar mVerticalSeekBar;
  private TextView mPrivateYawn;
  private TextView mPublicYawn;

  public AlloButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public AlloButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mRootView = inflate(context, R.layout.allo_button_layout, this);
    //mImageButton = (AlloButton) findViewById(R.id.imageButton);
    //mSwipeListener.setOnFingerGestureListener(getSwipeListener());

    //initButton();

    mVerticalSeekBar = (VerticalSeekBar) findViewById(R.id.vertical_Seekbar);
    mPrivateYawn = (TextView) findViewById(R.id.privateYawn);
    mPublicYawn = (TextView) findViewById(R.id.publicYawn);
    initSeekBar();
  }

  private void initSeekBar() {
    mVerticalSeekBar.setProgress(0);
    //final int progressStep = 90;
    //mVerticalSeekBar.incrementProgressBy(progressStep);
    final int seekBarMax = 100;
    mVerticalSeekBar.setMax(seekBarMax);

    final Drawable drawableTransparent = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent);
    final Drawable drawableNormal = ContextCompat.getDrawable(getContext(), R.drawable.red_scrubber_progress);
    mVerticalSeekBar.setProgressDrawable(drawableTransparent);

    mVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /*progress = progress / progressStep;
        progress = progress * progressStep;
        */

        final int step2 = 10;
        int stepSize = step2;

        progress = (progress / stepSize) * stepSize;
        seekBar.setProgress(progress);
        //mPrivateYawn.setText(progress + "");

        if (progress >= seekBarMax) {
          mPublicYawn.setVisibility(VISIBLE);
        } else if (progress >= seekBarMax / 2) {
          mVerticalSeekBar.setProgressDrawable(drawableNormal);
          mPrivateYawn.setVisibility(VISIBLE);
        } else if (progress == 0) {
          mVerticalSeekBar.setProgressDrawable(drawableTransparent);
          mPublicYawn.setVisibility(GONE);
          mPrivateYawn.setVisibility(GONE);
        }
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

  @NonNull private SimpleFingerGestures.OnFingerGestureListener getSwipeListener() {
    return new SimpleFingerGestures.OnFingerGestureListener() {
      @Override public boolean onSwipeUp(int fingers, long duration, double distance) {
        Log.d(TAG, "onSwipeUp() called with: fingers = [" + fingers + "], duration = [" + duration + "], distance = [" + distance + "]");
        return true;
      }

      @Override public boolean onSwipeDown(int fingers, long duration, double distance) {
        Log.d(TAG, "onSwipeUp() called with: fingers = [" + fingers + "], duration = [" + duration + "], distance = [" + distance + "]");
        return false;
      }

      @Override public boolean onSwipeLeft(int i, long l, double v) {
        return false;
      }

      @Override public boolean onSwipeRight(int i, long l, double v) {
        return false;
      }

      @Override public boolean onPinch(int i, long l, double v) {
        return false;
      }

      @Override public boolean onUnpinch(int i, long l, double v) {
        return false;
      }

      @Override public boolean onDoubleTap(int i) {
        return false;
      }
    };
  }

  private void initButton() {
    mImageButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Log.d(TAG, "onClick() called with: v = [" + v + "]");
      }
    });
    //mImageButton.setOnTouchListener(mSwipeListener);
    mImageButton.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
      public void onSwipeTop() {
        Log.d(TAG, "onSwipeTop() called");
      }

      public void onSwipeRight() {
      }

      public void onSwipeLeft() {
      }

      public void onSwipeBottom() {
        Log.d(TAG, "onSwipeBottom() called");
      }
    });
  }
}
