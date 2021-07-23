package com.example.mediaworksdemo;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class VideoDetailActivity extends AppCompatActivity {

    String mockUrl = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";
    boolean startPlay;
//    private LottieAnimationView heart;
    private ImageView videoPause;
    private ImageView like;
    private ImageView likered;
    private SurfaceView surfaceView;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private SeekBar seekBar;
    private TextView textdate;
    private TextView textuser1;
    private TextView textuser2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        String videoUrl=getIntent().getStringExtra("videoUrl");
        String username=getIntent().getStringExtra("username");
        String updateTime=getIntent().getStringExtra("updateTime");
        System.out.println("play video: "+videoUrl);
        surfaceView = findViewById(R.id.surfaceView);
        videoPause = findViewById(R.id.imageView);
        videoPause.bringToFront();
        heart = findViewById(R.id.heart);
        like=findViewById(R.id.like);
        likered=findViewById(R.id.likered);
        textuser1=findViewById(R.id.textView1);
        textdate=findViewById(R.id.textView3);
        textuser2=findViewById(R.id.textView4);
        textuser1.setText("@"+username);
        textuser2.setText("视频原声@"+username);
        textdate.setText("·"+updateTime);
        player = new MediaPlayer();
        try {
            player.setDataSource(this,Uri.parse(videoUrl));
            holder = surfaceView.getHolder();
            holder.setFormat(PixelFormat.TRANSPARENT);
            holder.addCallback(new PlayerCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // 自动播放
                    player.start();
                    player.setLooping(true);
                    startPlay = true;
                    handler.postDelayed(runnable, 0);
                }
            });
            player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    System.out.println(percent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        heart.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(animation.getAnimatedFraction() == 1f){
                    if(heart.getVisibility() == View.VISIBLE){
                        heart.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        seekBar=findViewById(R.id.mediaSeekBar);
        seekBar.setMax(player.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    player.seekTo(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        surfaceView.setOnTouchListener(new MyClick(new MyClick.MyClickCallBack() {
            @Override
            public void oneClick() {
                if(startPlay) {
                    if (player.isPlaying()) {
                        player.pause();
                        videoPause.setVisibility(View.VISIBLE);
                    } else {
                        player.start();
                        videoPause.setVisibility(View.INVISIBLE);
                    }
                }
            }
            @Override
            public void doubleClick() {
                if(startPlay) {
                    heart.playAnimation();
                    heart.setVisibility(View.VISIBLE);
                    like.setVisibility(View.INVISIBLE);
                    likered.setVisibility(View.VISIBLE);
                }
            }
        }));
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            player.release();
        }
        if(handler!=null){
            handler.removeCallbacks(runnable);
        }
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            if (player.isPlaying()) {
                int current = player.getCurrentPosition();
                seekBar.setProgress(current);
            }
            handler.postDelayed(runnable, 500);
        }
    };
}
