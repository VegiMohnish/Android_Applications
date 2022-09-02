package com.example.mplayer;
import java.io.IOException;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button buttonPlayPause, buttonQuit;
    TextView textState;

    private int stateMediaPlayer;
    private final int stateMP_Error = 0;
    private final int stateMP_NotStarter = 1;
    private final int stateMP_Playing = 2;
    private final int stateMP_Pausing = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayPause = (Button)findViewById(R.id.playpause);
        buttonQuit = (Button)findViewById(R.id.quit);
        textState = (TextView)findViewById(R.id.state);

        buttonPlayPause.setOnClickListener(buttonPlayPauseOnClickListener);
        buttonQuit.setOnClickListener(buttonQuitOnClickListener);

        initMediaPlayer();
    }
    private void initMediaPlayer()
    {
        String PATH_TO_FILE = "/sdcard/sound.mp3";
        mediaPlayer = new  MediaPlayer();

        try {
            mediaPlayer.setDataSource(PATH_TO_FILE);
            mediaPlayer.prepare();
            Toast.makeText(this, PATH_TO_FILE, Toast.LENGTH_LONG).show();
            stateMediaPlayer = stateMP_NotStarter;
            textState.setText("- IDLE -");
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            stateMediaPlayer = stateMP_Error;
            textState.setText("- ERROR!!! -");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            stateMediaPlayer = stateMP_Error;
            textState.setText("- ERROR!!! -");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            stateMediaPlayer = stateMP_Error;
            textState.setText("- ERROR!!! -");
        }
    }

    Button.OnClickListener buttonPlayPauseOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch(stateMediaPlayer){
                case stateMP_Error:
                    break;
                case stateMP_NotStarter:
                    mediaPlayer.start();
                    buttonPlayPause.setText("Pause");
                    textState.setText("- PLAYING -");
                    stateMediaPlayer = stateMP_Playing;
                    break;
                case stateMP_Playing:
                    mediaPlayer.pause();
                    buttonPlayPause.setText("Play");
                    textState.setText("- PAUSING -");
                    stateMediaPlayer = stateMP_Pausing;
                    break;
                case stateMP_Pausing:
                    mediaPlayer.start();
                    buttonPlayPause.setText("Pause");
                    textState.setText("- PLAYING -");
                    stateMediaPlayer = stateMP_Playing;
                    break;
            }

        }
    };

    Button.OnClickListener buttonQuitOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mediaPlayer.stop();
            mediaPlayer.release();
            finish();
        }
    };
}