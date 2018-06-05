package com.ezi.larbianceur.esigym.workout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;

import android.widget.VideoView;

import com.ezi.larbianceur.esigym.BottomNavigationViewHelper;
import com.ezi.larbianceur.esigym.LoginActivity;
import com.ezi.larbianceur.esigym.MainActivity;
import com.ezi.larbianceur.esigym.motivation.MotivationActivity;
import com.ezi.larbianceur.esigym.NutritionActivity;
import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.suppliment.SupplimentActivity;

public class VideoWorkout extends AppCompatActivity {

    private VideoView video;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressBar;
    Toolbar toolbar;
    int id;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_video );

        prefs = getSharedPreferences( "MyData", Context.MODE_PRIVATE );

        boolean isLogin = prefs.getBoolean( "isConnect", false );



        if (!isLogin) {
            startActivity( new Intent( this, LoginActivity.class ) );
        }

        id = prefs.getInt( "id", 0 );

        if (id == 0) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean( "isConnect", false );
            editor.commit();
            startActivity( new Intent( this, LoginActivity.class ) );
        }


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(VideoWorkout.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_meals:
                        Intent intent4 = new Intent(VideoWorkout.this, NutritionActivity.class);
                        startActivity(intent4);
                        break;

                    case R.id.ic_motivation:
                        Intent intent3 = new Intent(VideoWorkout.this, MotivationActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_suppliment:
                        Intent intent2 = new Intent(VideoWorkout.this, SupplimentActivity.class);
                        startActivity(intent2);

                        break;

                    case R.id.ic_workout:
                        break;
                }
                return false;
            }
        } );


        toolbar = (Toolbar)findViewById( R.id.action_bar );
        toolbar.setTitle( "WorkOut Exemple" );
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar) findViewById( R.id.progrss );


        video = (VideoView) findViewById( R.id.video );

        String uriPath = "http://192.168.1.3:8080/video/video.mp4"; //update package name
        uri = Uri.parse( uriPath );
        mediacontroller = new MediaController( this );
        mediacontroller.setAnchorView( video );
        video.setMediaController( mediacontroller );
        video.setVideoURI( uri );
        progressBar.setVisibility( View.VISIBLE );


        video.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar.setVisibility( View.GONE );
                video.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        /*
                         * add media controller
                         */
                        mediacontroller =new MediaController( VideoWorkout.this );
                        video.setMediaController(mediacontroller);
                        /*
                         * and set its position on screen
                         */
                        mediacontroller.setAnchorView(video);
                    }
                });
            }
        } );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected( item );
    }
}

