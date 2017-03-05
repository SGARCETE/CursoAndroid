package com.example.sgarcete.android_maps;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Acá utilizamos la libería butter knife para obtener las vistas y sus items
    @Bind(R.id.ubicarme)
    Button ubicarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //ubicarme = (Button)findViewById(R.id.ubicarme); Esto ahora lo hacemos con el bind

        /*ubicarme.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, MapsActivity.class); //Le decimos al boton que al hacer click vaya al map activity
                startActivity(intent);

            }
        }); Esta cosa fea también */
    }

    //Configuramos el OnClick usando Butter Knife
    @OnClick(R.id.ubicarme)
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class); //Le decimos al boton que al hacer click vaya al map activity
        startActivity(intent);
    }


}
