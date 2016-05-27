package com.github.kgmnmi.cowfarm;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.media.AudioManager;

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


    MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "onCreate");
        setContentView(R.layout.activity_main);
        ImageView buttonCow = (ImageView) this.findViewById(R.id.button_cow);
        buttonCow.setOnClickListener(this);
        ImageButton buttonFeed = (ImageButton) this.findViewById(R.id.button_feed);
        buttonFeed.setOnClickListener(this);
        ImageButton buttonToilet = (ImageButton) this.findViewById(R.id.button_toilet);
        buttonToilet.setOnClickListener(this);
        ImageButton buttonShopping = (ImageButton) this.findViewById(R.id.button_shopping);
        buttonShopping.setOnClickListener(this);
        ImageView imageEaten = (ImageView) this.findViewById(R.id.eaten_feed);
        imageEaten.setVisibility(View.INVISIBLE); //非表示処理
        ImageView flushedToilet = (ImageView) this.findViewById(R.id.flushed_toilet);
        flushedToilet.setVisibility(View.INVISIBLE); //非表示処理
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm = MediaPlayer.create(this, R.raw.game_maoudamashii_5_village10);
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
        switch (v.getId()) {
            case R.id.button_cow:
                Log.d("", "onClick1");
                ImageView img = (ImageView) findViewById(R.id.button_cow);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.motion);
                animation.setFillAfter(false);   //終了後を保持
                img.startAnimation(animation);
                MediaPlayer se = MediaPlayer.create(this, R.raw.cowsynthetic011);
                se.start();
                break;

            case R.id.button_feed:
                Log.d("", "onClick2");
                final View unko = findViewById(R.id.flushed_toilet);
                View eaten = findViewById(R.id.eaten_feed);
                eaten.setVisibility(View.VISIBLE);
                ScaleAnimation scale = new ScaleAnimation(1, 0f, 1, 0f); //imgを1倍から0倍に縮小
                scale.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        unko.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                scale.setDuration(3000); //3000msかけてアニメーションする
                eaten.startAnimation(scale); //アニメーション適用
                eaten.setVisibility(View.INVISIBLE);
                break;

            case R.id.button_toilet:
                Log.d("", "onClick3");
                View clean = findViewById(R.id.flushed_toilet);
                AlphaAnimation alpha = new AlphaAnimation(1, 0.1f); //透明度を1から0.1に変化させる
                alpha.setDuration(4000); //3000msかけてアニメーションする
                clean.startAnimation(alpha); //アニメーション適用
                clean.setVisibility(View.INVISIBLE);
                break;

            case R.id.button_shopping:
                Log.d("", "onClick4");
                break;

            case R.id.eaten_feed:
                Log.d("", "onClick4");
                break;

            case R.id.flushed_toilet:
                Log.d("", "onClick5");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm.stop();
    }
}