package com.ezi.larbianceur.esigym.motivation;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.ezi.larbianceur.esigym.R;

public class VideoFragment extends Fragment {

    private Button btnonce, btncontinuously, btnstop, btnplay;
    ImageButton imgBtn1, imgBtn2;
    private VideoView video;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;
    boolean pause=false;
    int stopPosition;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.video_fragment, container, false );
        progressBar = (ProgressBar)view.findViewById(R.id.progrss);
        //   btnonce = (Button) findViewById(R.id.btnonce);
        // btncontinuously = (Button) findViewById(R.id.btnconti);
        // btnstop = (Button) findViewById(R.id.btnstop);
        //btnplay = (Button) findViewById(R.id.btnplay);
        video = (VideoView)view.findViewById(R.id.video);

        mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(video);
        String uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4"; //update package name
        uri = Uri.parse(uriPath);

        imgBtn1 =(ImageButton)view.findViewById(R.id.btnplay);
        imgBtn2 =(ImageButton)view.findViewById(R.id.btnstop);

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


    return view;}
    }
