package com.example.sgarcete.cursoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String tag = MainActivity.class.getSimpleName();
    private View cuadradoFucsia;
    private TextView titulo;

    private View.OnClickListener listenerCuadradoFucsia = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MainActivity.this.titulo.setText("Play 4");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cuadradoFucsia = findViewById(R.id.fucsia);
        this.titulo = (TextView) findViewById(R.id.titulo);
        this,cuadradoFucsia.setOnClickListener(this.listenerCuadradoFucsia.titulo);

    };


}
