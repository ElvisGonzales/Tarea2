package com.example.tarea2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.WebService.Asynchtask;
import com.example.myapplication.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<String> lstBancos = new  ArrayList<String> ();
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos.add(banco.getString("id").toString());
            lstBancos.add(banco.getString("name").toString());
            lstBancos.add(banco.getString("email").toString());
            lstBancos.add(banco.getString("gender").toString());
            lstBancos.add(banco.getString("status").toString());
        }
        TextView txtview = findViewById(R.id.txtView);
        txtview.setText(lstBancos.toString());
    }
    public void Enviar (View view){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos,MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
}