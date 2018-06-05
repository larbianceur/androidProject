package com.ezi.larbianceur.esigym.motivation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezi.larbianceur.esigym.R;

public class SpeechFragment extends Fragment {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageView imageView;
    TextView textView1;
    TextView textView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.speech_fragment, container, false );
        button1= (Button)view.findViewById( R.id.b1 );
        button2= (Button)view.findViewById( R.id.b2 );
        button3= (Button)view.findViewById( R.id.b3 );
        button4= (Button)view.findViewById( R.id.b4 );
        button5= (Button)view.findViewById( R.id.b5 );
        button6= (Button)view.findViewById( R.id.b6 );

        textView1 =(TextView)view.findViewById( R.id.txt1 );
        textView2 =(TextView)view.findViewById( R.id.txt2 );

        imageView=(ImageView)view.findViewById( R.id.img );

        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setText( "TIME FOR WAR\n" +
                        "Today. Right now you are going to war.\n" +
                        "You are going into war with your opponent\n" +
                        "You are going to war with yourself.\n" +
                        "You are not scared… You are prepared\n" +
                        "You are not weak… You are a machine. A Freak.\n" +
                        "Are you focused?!?\u2028….\n" +
                        "\n" +
                        "I AM FOCUSED\n" +
                        "I AM FOCUSED\n" +
                        "I AM FOCUSED\n" +
                        "\n" +
                        "Yes you are. You are focused and you will not lose sight of that.\n" +
                        "Not today, not tomorrow, next week or next year.\n" +
                        "Repeat after me.\n" +
                        "Today is my day.\n" +
                        "No one will get in the way of my dreams,\n" +
                        "of my growth or my desire to be the VERY BEST in my chosen field.\n" +
                        "Yes i said the best.\n" +
                        "No one has the right to take that mantle from me.\n" +
                        "I will sacrifice until i reach the very top.\n" +
                        "No matter how hard it gets.\n" +
                        "No matter how many times life beats me down.\n" +
                        "I will get up EVERYTIME!\n" +
                        "I will fight tooth and nail.\n" );

                imageView.setImageResource( R.drawable.text1 );

                textView2.setText(" Chris Ross" );
            }
        } );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setText( "Saying you want it bad WILL NOT get you results, \u2028but SHOWING you want it bad WILL!\n" +
                        "TALKING about it WILL NOT get you results, \u2028DOING IT WILL!\n" +
                        "\n" +
                        "You see in life you have a choice. You can either be a spectator, or you can be a participant.\n" +
                        "Spectators like to talk. Participants are all about ACTION\n" +
                        "\n" +
                        "You see, everyone talks a good game don’t they?! \u2028But what separates the successful from the unknown? It’s ACTION.\n" +
                        "The successful, the champions of life, they TAKE MASSIVE ACTION\n" +
                        "They do not hesitate, they do not procrastinate.\n" +
                        "They do not fear failure. \u2028THEY FEAR BEING AVERAGE.\n" +
                        "\n" +
                        "Dreams can come true, but dreams need ACTION.\n" +
                        "But there is a difference between a dream and a fairytale, there is a difference in a dream and a good idea\n" +
                        "\n" +
                        "You see, a dream will keep coming back, a dream will not go away.\n" +
                        "I don’t care how many years, how many months, how many days, that dream will keep coming back\n" +
                        "\n" +
                        "Whatever you dream you gotta have ACTION, and if not it’s ALWAYS going to stay a fairytale!\n" +
                        "In fact it will stalk you, it will harass you, until you give in and say: “It is now time for me to LAUNCH, it is time for me to step out, it’s time for me to JUMP and GO FOR IT!”\n" +
                        "\n" +
                        "Saying you want it bad WILL NOT get you results, \u2028but SHOWING you want it bad WILL!\n" +
                        "TALKING about it WILL NOT get you results, \u2028DOING IT WILL!\n" +
                        "\n" );

                imageView.setImageResource( R.drawable.text1 );

                textView2.setText(" Chris Ross" );
            }
        } );


        return view;}
    }
