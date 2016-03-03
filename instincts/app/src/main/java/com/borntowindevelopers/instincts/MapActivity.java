package com.borntowindevelopers.instincts;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MapActivity extends Activity {


    protected void onCreate(Bundle s){

        super.onCreate(s);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);

        View decorView = getWindow().getDecorView();
        int uiOptions =
             View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);

    }


}
