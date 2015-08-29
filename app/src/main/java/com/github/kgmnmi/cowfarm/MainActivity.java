package com.github.kgmnmi.cowfarm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class MainActivity extends Activity implements View.OnClickListener {

//    public class Mammal {
//        public void eat(Object o) {
//            System.out.println("eat object");
//        }
//    }
//
//    public class Cow extends Mammal {
//        public void sleep(){
//            System.out.println("sleeping");
//        }
//        public void main() {
//            this.eat("unk");
//            this.sleep();
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView CowImage = (ImageView) this.findViewById(R.id.cow);
        CowImage.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaPlayer bgm = MediaPlayer.create(this, R.raw.game_maoudamashii_5_village10);
        bgm.setLooping(true);
        bgm.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Log.d("", "onClick");
        ImageView img = (ImageView) findViewById(R.id.cow);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.motion);
        animation.setFillAfter(false);   //終了後を保持
        img.startAnimation(animation);
    }
}
