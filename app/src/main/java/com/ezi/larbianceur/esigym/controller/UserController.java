package com.ezi.larbianceur.esigym.controller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.ezi.larbianceur.esigym.R;
import com.ezi.larbianceur.esigym.model.User;

/**
 * Created by Larbi Anceur on 19-May-18.
 */

public class UserController {

    public String checkInformation(User user){
        if(user.getName().equals( "" )||user.getUserName().equals( "" )||
                user.getEmail().equals( "" )||user.getPassword().equals( "" )||user.getConfirmPassword().equals( "" )){
            return "please check all  filed";

        } else if (!user.getPassword().equals( user.getConfirmPassword() )) {
            return "password Not a same";
        }else if(!isValidEmail( user.getEmail() )){
            return "your email is not valide";
        }
return "succes";
    }

    public  boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public String checkLogin(User user) {
        if(user.getEmail().length()<3 ||!isValidEmail( user.getEmail())){
            return " please check your email";
        }
        else if(user.getPassword().length()<5){
            return "please entre  password akbar men 5";
        }
        return "sucess";
    }

/*    public void readVideo(final VideoView videoView , final ImageButton imageButton , final String url){

        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!videoView.isPlaying()) {
                    Uri uri = Uri.parse( url );
                    videoView.setVideoURI( uri );
                    videoView.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            imageButton.setImageResource( R.drawable.ic_play );
                        }
                    } );
                }else {
                    videoView.pause();
                    imageButton.setImageResource( R.drawable.ic_play );
                }
                videoView.requestFocus();
                videoView.setOnPreparedListener( new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.setLooping( true );
                        videoView.start();
                        imageButton.setImageResource( R.drawable.ic_pause );
                    }
                } );
            }
        } );

    }*/

    public void loadImage(){

    }
}
