package com.ezi.larbianceur.esigym;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;



public class videoMotivation extends AppCompatActivity {

    private Button btnonce, btncontinuously, btnstop, btnplay;
    ImageButton imgBtn1, imgBtn2;
    private VideoView video;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;
    boolean pause=false;
    int stopPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_motivation);

        progressBar = (ProgressBar) findViewById(R.id.progrss);
        //   btnonce = (Button) findViewById(R.id.btnonce);
        // btncontinuously = (Button) findViewById(R.id.btnconti);
        // btnstop = (Button) findViewById(R.id.btnstop);
        //btnplay = (Button) findViewById(R.id.btnplay);
        video = (VideoView) findViewById(R.id.video);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(video);
        String uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4"; //update package name
        uri = Uri.parse(uriPath);

        imgBtn1 =(ImageButton)findViewById(R.id.btnplay);
        imgBtn2 =(ImageButton)findViewById(R.id.btnstop);

        imgBtn1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if ( pause )
                                           {
                                               video.seekTo(stopPosition);
                                               pause=false;
                                               video.start();
                                           }
                                           else{
                                               progressBar.setVisibility(View.VISIBLE);
                                               video.setMediaController(mediacontroller);
                                               video.setVideoURI(uri);
                                               video.requestFocus();
                                               video.start();}
                                       }
                                   }


        );

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopPosition = video.getCurrentPosition();
                pause=true;
                video.pause();


            }});

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                video.stopPlayback();

            }
        });


      /*  btnstop.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                video.pause();
                pause=true;
            }
        });



        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if ( pause )
               {
                   video.resume();
                   pause=false;
               }
               else{
                   progressBar.setVisibility(View.VISIBLE);
                video.setMediaController(mediacontroller);
                video.setVideoURI(uri);
                video.requestFocus();
                video.start();}
            }
        });

*/



        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility( View.GONE);
            }
        });

    }
}

