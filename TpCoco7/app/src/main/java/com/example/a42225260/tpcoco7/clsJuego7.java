package com.example.a42225260.tpcoco7;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCSize;

import java.util.Random;

/**
 * Created by 42225260 on 10/8/2017.
 */

public class clsJuego7 {

    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite Objeto1;
    Sprite Objeto2;
    Sprite banana;
    Sprite mono;
    int ImagenTocada=-1;

    public clsJuego7(CCGLSurfaceView VistaDelJuego){
        Log.d("Bob", "Comienza el construtor de la clase");
        _VistaDelJuego=VistaDelJuego;


    }
    public void ComenzarJuego(){
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo=Director.sharedDirector().displaySize();
        Log.d("Comenzar", "Pantalla del dispositivo - Ancho:"+PantallaDelDispositivo.width+" - Alto: "+PantallaDelDispositivo.height);

        Log.d("Comenzar", "Le digo al director que ejecute la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }
    private Scene EscenaDelJuego(){
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego", "Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver= Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen de fondo");
        CapaDeFondo MiCapaFondo;
        MiCapaFondo=new CapaDeFondo();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener el jugador y los enemigos ");
        CapaDelFrente MiCapaFrente;
        MiCapaFrente=new CapaDelFrente();

        Log.d("EscenaDelJuego", "Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
        EscenaADevolver.addChild(MiCapaFondo, -10);
        EscenaADevolver.addChild(MiCapaFrente, 10);

        Log.d("EscenaDelJuego", "Devuelvo la escena ya armada");
        return EscenaADevolver;
    }

    class CapaDeFondo extends Layer {

        public CapaDeFondo(){
            Log.d("CapaDelFondo", "Comienzo el constructor de la capa del fondo");

            Log.d("CapaDelFondo", "Pongo la imagen del fondo del juego");
            PonerImagenFondo();
        }

        private void PonerImagenFondo(){
            Log.d("PonerImagenFondo", "Comienzo a poner la imagen del fondo");

            Log.d("PonerImagenFondo", "Intancio el sprite");
            Sprite ImagenFondo;
            ImagenFondo= Sprite.sprite("fondo.jpg");

            Log.d("PonerImagenFondo", "La ubico en el cenro de la pantalla");
            ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);

            Float FactorAncho, FactorAlto;
            FactorAncho= PantallaDelDispositivo.width / ImagenFondo.getWidth();
            FactorAlto= PantallaDelDispositivo.height / ImagenFondo.getHeight();

            Log.d("PonerImagenFondo", "Agrando la imagen al doble de su tamaño actual");
            ImagenFondo.runAction(ScaleBy.action(0.1f, FactorAncho, FactorAlto));

            Log.d("PonerImagenFondo", "Lo agrego a la capa");
            super.addChild(ImagenFondo);
        }
    }

    class CapaDelFrente extends Layer{
        boolean Devolvió=true;

        public CapaDelFrente(){
            Log.d("CapaDelFrente", "Comienza el constructor de la capa del frente");

            Log.d("CapaDelFrente", "Pongo el jugador en su posicion inicial");
            PonerTituloJuego();

            while(Devolvió==true) {
                PonerLaBanana();
                PonerElMono();
                Devolvió = InterseccionEntreSprites(mono, banana);
            }
            Log.d("ConstructorCapaDelJuego", "Habilito el control del touch");
            this.setIsTouchEnabled(true);
        }

        Label lblTituloJuego;
        private void PonerTituloJuego(){
            CCColor3B ColorTitulo = new CCColor3B(128,100,200);

            Log.d("PonerTitulo", "Comienzo a poner el titulo");
            lblTituloJuego= Label.label("AGARRAME ESTA","Verdana", 50);

            lblTituloJuego.setColor(ColorTitulo);
            float AltoDelTitulo;
            AltoDelTitulo=lblTituloJuego.getHeight();

            lblTituloJuego.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height-AltoDelTitulo);

        }

        public void PonerLaBanana()
        {
            Log.d("PonerLaBanana", "Instancio el sprite del enemigo");
            banana = Sprite.sprite("banana.png");

            Log.d("PonerLaBanana", "Determino la posicion inicial");
            Float PosicionInicialX;
            Float PosicionInicialY;
            Float AlturaBanana;
            Float AnchoBanana;
            AlturaBanana= banana.getHeight();
            AnchoBanana= banana.getWidth();
            PosicionInicialY= PantallaDelDispositivo.height + AlturaBanana/2;
            PosicionInicialX= PantallaDelDispositivo.width + AnchoBanana/2;

            Log.d("PonerLaBanana", "Determino la posicion X al azar");
            Random GeneradorAzarY;
            GeneradorAzarY=new Random();
            Random GeneradorAzarX;
            GeneradorAzarX=new Random();
            int PosicionInicialIntY;
            int PosicionInicialIntX;
            PosicionInicialIntX= GeneradorAzarX.nextInt((int) (PantallaDelDispositivo.width - AnchoBanana));
            PosicionInicialIntX += AnchoBanana/2;

            PosicionInicialIntY=GeneradorAzarY.nextInt((int) (PantallaDelDispositivo.height - AlturaBanana));
            PosicionInicialIntY+= AlturaBanana/2;

            Log.d("PonerLaBanana", "La ubico en las posiciones que determiné");
            banana.setPosition(PosicionInicialIntX, PosicionInicialIntY);

            Log.d("PonerLaBanana", "Agrego el sprite a la capa");
            super.addChild(banana);
        }
        public void PonerElMono()
        {
            Log.d("PonerElMono", "Instancio el sprite del enemigo");
            mono = Sprite.sprite("mono.png");

            Log.d("PonerElMono", "Determino la posicion inicial");
            Float PosicionInicialX;
            Float PosicionInicialY;
            Float AlturaMono;
            Float AnchoMono;
            AlturaMono= mono.getHeight();
            AnchoMono= mono.getWidth();
            PosicionInicialY= PantallaDelDispositivo.height + AlturaMono/2;
            PosicionInicialX= PantallaDelDispositivo.width + AnchoMono/2;

            Log.d("PonerElMono", "Determino la posicion X al azar");
            Random GeneradorAzarY;
            GeneradorAzarY=new Random();
            Random GeneradorAzarX;
            GeneradorAzarX=new Random();
            int PosicionInicialIntY;
            int PosicionInicialIntX;
            PosicionInicialIntX= GeneradorAzarX.nextInt((int) (PantallaDelDispositivo.width - AnchoMono));
            PosicionInicialIntX += AnchoMono/2;

            PosicionInicialIntY=GeneradorAzarY.nextInt((int) (PantallaDelDispositivo.height - AlturaMono));
            PosicionInicialIntY+= AlturaMono/2;

            Log.d("PonerElMono", "La ubico en las posiciones que determiné");
            mono.setPosition(PosicionInicialIntX, PosicionInicialIntY);

            Log.d("PonerElMono", "Agrego el sprite a la capa");
            super.addChild(mono);
        }

        boolean InterseccionEntreSprites(Sprite mono, Sprite banana)
        {
            boolean Devolver;
            Devolver=false;

            int SpriteMonoIzquierda, SpriteMonoDerecha, SpriteMonoAbajo, SpriteMonoArriba;
            int SpriteBananaIzquierda, SpriteBananaDerecha, SpriteBananaAbajo, SpriteBananaArriba;

            SpriteMonoIzquierda=(int) (mono.getPositionX() - mono.getWidth()/2);
            SpriteMonoDerecha=(int) (mono.getPositionX() - mono.getWidth()/2);
            SpriteMonoAbajo=(int) (mono.getPositionY() - mono.getWidth()/2);
            SpriteMonoArriba=(int) (mono.getPositionY() - mono.getWidth()/2);

            SpriteBananaIzquierda=(int) (banana.getPositionX() - banana.getWidth()/2);
            SpriteBananaDerecha=(int) (banana.getPositionX() - banana.getWidth()/2);
            SpriteBananaAbajo=(int) (banana.getPositionY() - banana.getWidth()/2);
            SpriteBananaArriba=(int) (banana.getPositionY() - banana.getWidth()/2);

            Log.d("Interseccion", "SpMono-Izq: "+SpriteMonoIzquierda+" - Der:"+SpriteMonoDerecha+" -Aba:"+ SpriteMonoAbajo+" Arr:" +SpriteMonoArriba);
            Log.d("Interseccion", "SpBanana-Izq: "+SpriteBananaIzquierda+" - Der:"+SpriteBananaDerecha+" -Aba:"+ SpriteBananaAbajo+" Arr:" +SpriteBananaArriba);

            //Borde izq y borde inf de Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoIzquierda,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoAbajo, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","1");
                Devolver=true;
            }

            //Borde izq y borde sup de Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoIzquierda,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoArriba, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","2");
                Devolver=true;
            }

            //Borde der y borde sup Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoDerecha,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoArriba, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","3");
                Devolver=true;
            }

            //Borde der y borde inf Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoDerecha,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoAbajo, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","4");
                Devolver=true;
            }

            //Borde izq y borde inf de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaIzquierda,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaAbajo, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","5");
                Devolver=true;
            }
            //Borde izq y borde sup de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaIzquierda,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaArriba, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","6");
                Devolver=true;
            }
            //Borde der y borde sup de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaDerecha,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaArriba, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","7");
                Devolver=true;
            }
            //Borde izq y borde inf de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaDerecha,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaAbajo, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","8");
                Devolver=true;
            }
            return Devolver;
        }

        boolean EstaEntre (int NumeroAComparar, int NumeroMenor, int NumeroMayor){
            boolean Devolver;

            Log.d("EstaEntre", "NumeroMenor: "+NumeroMenor+ " - NumeroMayor: "+NumeroMayor);
            if (NumeroMenor > NumeroMayor){
                Log.d("EstaEntre", "Me los mandaron invertidos, los ordeno");
                int Auxiliar;
                Auxiliar=NumeroMayor;
                NumeroMayor=NumeroMenor;
                NumeroMenor=Auxiliar;
            }
            if (NumeroAComparar>= NumeroMenor && NumeroAComparar <=NumeroMayor){
                Log.d("EstaEntre", "EstaEntre");
                Devolver=true;
            }else{
                Log.d("EstaEntre", "NoEstaEntre");
                Devolver=false;
            }
            return Devolver;
        }

        void ColisionMonoBanana(float DiferenciaTiempo)
        {
            boolean Devolver;
            Devolver=false;

            int SpriteMonoIzquierda, SpriteMonoDerecha, SpriteMonoAbajo, SpriteMonoArriba;
            int SpriteBananaIzquierda, SpriteBananaDerecha, SpriteBananaAbajo, SpriteBananaArriba;

            SpriteMonoIzquierda=(int) (mono.getPositionX() - mono.getWidth()/2);
            SpriteMonoDerecha=(int) (mono.getPositionX() - mono.getWidth()/2);
            SpriteMonoAbajo=(int) (mono.getPositionY() - mono.getWidth()/2);
            SpriteMonoArriba=(int) (mono.getPositionY() - mono.getWidth()/2);

            SpriteBananaIzquierda=(int) (banana.getPositionX() - banana.getWidth()/2);
            SpriteBananaDerecha=(int) (banana.getPositionX() - banana.getWidth()/2);
            SpriteBananaAbajo=(int) (banana.getPositionY() - banana.getWidth()/2);
            SpriteBananaArriba=(int) (banana.getPositionY() - banana.getWidth()/2);

            Log.d("Interseccion", "SpMono-Izq: "+SpriteMonoIzquierda+" - Der:"+SpriteMonoDerecha+" -Aba:"+ SpriteMonoAbajo+" Arr:" +SpriteMonoArriba);
            Log.d("Interseccion", "SpBanana-Izq: "+SpriteBananaIzquierda+" - Der:"+SpriteBananaDerecha+" -Aba:"+ SpriteBananaAbajo+" Arr:" +SpriteBananaArriba);

            //Borde izq y borde inf de Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoIzquierda,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoAbajo, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","1");
                Devolver=true;
            }

            //Borde izq y borde sup de Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoIzquierda,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoArriba, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","2");
                Devolver=true;
            }

            //Borde der y borde sup Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoDerecha,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoArriba, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","3");
                Devolver=true;
            }

            //Borde der y borde inf Sprite 1 esta dentro de Sprite 2
            if (EstaEntre(SpriteMonoDerecha,SpriteBananaIzquierda,SpriteBananaDerecha) && EstaEntre(SpriteMonoAbajo, SpriteBananaAbajo, SpriteBananaArriba)){
                Log.d("Interseccion","4");
                Devolver=true;
            }

            //Borde izq y borde inf de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaIzquierda,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaAbajo, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","5");
                Devolver=true;
            }
            //Borde izq y borde sup de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaIzquierda,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaArriba, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","6");
                Devolver=true;
            }
            //Borde der y borde sup de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaDerecha,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaArriba, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","7");
                Devolver=true;
            }
            //Borde izq y borde inf de Sprite 2 esta dentro de Sprite 1
            if (EstaEntre(SpriteBananaDerecha,SpriteMonoIzquierda,SpriteMonoDerecha) && EstaEntre(SpriteBananaAbajo, SpriteMonoAbajo, SpriteMonoArriba)){
                Log.d("Interseccion","8");
                Devolver=true;
            }


            if(Devolver==true && ImagenTocada==0)
            { //El mono toco la banana, la banana se mueve

                Log.d("Se tocaron"," el mono toco a la banana");

            }
            if(Devolver==true && ImagenTocada==1)
            { //La banana toco al mono, el mono se mueve
                Log.d("Se tocaron"," La banana toco al mono");

            }
        }




        @Override
        public boolean ccTouchesBegan(MotionEvent event){
            Log.d("Toque comienza", "X: "+event.getX()+" - Y: "+event.getY());

           float YReal= PantallaDelDispositivo.getHeight()-event.getY();

            float BordeIzquierdoMono= mono.getPositionX() - mono.getWidth()/2;
            float BordeDerechoMono= mono.getPositionX()+mono.getWidth()/2;
            float BordeSuperiorMono=mono.getPositionY()+mono.getHeight()/2;
            float BordeInferiorMono=mono.getPositionY()-mono.getHeight()/2;

            float BordeIzquierdoBanana= banana.getPositionX() - banana.getWidth()/2;
            float BordeDerechoBanana= banana.getPositionX()+banana.getWidth()/2;
            float BordeSuperiorBanana=banana.getPositionY()+banana.getHeight()/2;
            float BordeInferiorBanana=banana.getPositionY()-banana.getHeight()/2;


            if (event.getX()>=BordeIzquierdoMono && event.getX()<=BordeDerechoMono && YReal>=BordeInferiorMono && YReal<=BordeSuperiorMono){
                ImagenTocada=0;
                Log.d("Toque mueve1", "X: "+event.getX()+" - Y: "+event.getY());
            }
            else{
                Log.d("Toque mueve2", "X: "+event.getX()+" - Y: "+event.getY());
                if (event.getX()>=BordeIzquierdoBanana && event.getX()<=BordeDerechoBanana && YReal>=BordeInferiorBanana && YReal<=BordeSuperiorBanana){
                    ImagenTocada=1;
                    Log.d("Toque mueve3", "X: "+event.getX()+" - Y: "+event.getY());
                }
            }

            Log.d("Toque mueve3", "ImagenTocada: "+ImagenTocada);


            return true;
        }
        @Override
        public boolean ccTouchesMoved(MotionEvent event){

            //ESTE ES EL TIMER QUE ME DEBERIA LLEVAR A LA FUNCION DE COLISION
            super.schedule("ColisionMonoBanana, 0.25f");

            float XTocada, YTocada;
            XTocada= event.getX();
            YTocada =PantallaDelDispositivo.getHeight() - event.getY();

            if(ImagenTocada==0){
                Log.d("Toque mueve", "X: "+XTocada+" - Y: "+YTocada);
                mono.setPosition(XTocada, YTocada);
            }
            if(ImagenTocada ==1){
                banana.setPosition(XTocada,YTocada);
            }





            return true;
        }
         void MoverMono(float DestinoX, float DestinoY){


           float MovimientoHorizontal, MovimientoVertical, SuavizadorDeMovimiento;
            MovimientoHorizontal= DestinoX - PantallaDelDispositivo.getWidth()/2;
            MovimientoVertical=DestinoY-PantallaDelDispositivo.getHeight()/2;

            SuavizadorDeMovimiento=20;
            MovimientoHorizontal=MovimientoHorizontal/SuavizadorDeMovimiento;
            MovimientoVertical=MovimientoVertical/SuavizadorDeMovimiento;
            mono.setPosition(mono.getPositionX()+MovimientoHorizontal, mono.getPositionY() + MovimientoVertical);

            Log.d("MoverJugador", "Obtengo la posicion final a la que deberia ir el jugador");
            float PosicionFinalX, PosicionFinalY;
            PosicionFinalX=mono.getPositionX()+MovimientoHorizontal;
            PosicionFinalY=mono.getPositionY()+MovimientoVertical;

            Log.d("MoverJugador", "Me fijo si no se fue de los limites de la pantalla");
            if(PosicionFinalX<mono.getWidth()/2){
                Log.d("MoverJugador", "Se fue por la izquierda");
                PosicionFinalX=mono.getWidth()/2;
            }
            if (PosicionFinalX>PantallaDelDispositivo.getWidth()-mono.getWidth()/2){
                Log.d("MoverJugador", "Se fue por la derecha");
                PosicionFinalX=PantallaDelDispositivo.getWidth()-mono.getWidth();
            }
            if (PosicionFinalY <mono.getHeight()/2){
                Log.d("MoverJugador", "Se fue por abajo");
                PosicionFinalY=mono.getHeight()/2;
            }
            if (PosicionFinalY>PantallaDelDispositivo.getHeight()-mono.getHeight()/2){
                Log.d("MoverJugador", "Se fue por arriba");
                PosicionFinalY=PantallaDelDispositivo.getHeight()-mono.getHeight()/2;
            }
            Log.d("MoverJugador","Lo ubico en X: "+PosicionFinalX+" - Y: "+PosicionFinalY);
            mono.setPosition(PosicionFinalX, PosicionFinalY);
        }
        /*void MoverBanana (float DestinoX, float DestinoY){
            float MovimientoHorizontal, MovimientoVertical, SuavizadorDeMovimiento;
            MovimientoHorizontal= DestinoX - PantallaDelDispositivo.getWidth()/2;
            MovimientoVertical=DestinoY-PantallaDelDispositivo.getHeight()/2;

            SuavizadorDeMovimiento=20;
            MovimientoHorizontal=MovimientoHorizontal/SuavizadorDeMovimiento;
            MovimientoVertical=MovimientoVertical/SuavizadorDeMovimiento;
            banana.setPosition(banana.getPositionX()+MovimientoHorizontal, banana.getPositionY() + MovimientoVertical);

            Log.d("MoverBanana", "Obtengo la posicion final a la que deberia ir el banana");
            float PosicionFinalX, PosicionFinalY;
            PosicionFinalX=banana.getPositionX()+MovimientoHorizontal;
            PosicionFinalY=banana.getPositionY()+MovimientoVertical;

        }*/
        @Override
        public boolean ccTouchesEnded(MotionEvent event){
            Log.d("Toque termina", "X: "+event.getX()+" - Y: "+event.getY());
            ImagenTocada=-1;
            return true;
        }

    }


}

