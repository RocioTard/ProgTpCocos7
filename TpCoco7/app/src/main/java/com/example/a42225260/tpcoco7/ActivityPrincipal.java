package com.example.a42225260.tpcoco7;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import org.cocos2d.opengl.CCGLSurfaceView;


public class ActivityPrincipal extends Activity{

    CCGLSurfaceView VistaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_principal);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VistaPrincipal = new CCGLSurfaceView(this);
        setContentView(VistaPrincipal);
    }

    @Override
    protected void onStart(){
        super.onStart();
        clsJuego7 miGenialJuego;
        miGenialJuego = new clsJuego7(VistaPrincipal);
        miGenialJuego.ComenzarJuego();

    }
}
