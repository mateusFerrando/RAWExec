package br.ifsc.edu.rawexec;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        criarBanco();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cursor cursor = bd.rawQuery("SELECT id, nome FROM alunos ", null);
        cursor.moveToFirst();

        ArrayList<String> itens = new ArrayList<>();
        do {
            String s = cursor.getString( cursor.getColumnIndex("nome"));
            Log.i(" Resultado Sql :",s );
            itens.add(s);
        }while (cursor.moveToNext()) ;


        ListView list;
        list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        list.setAdapter(adapter);




    }

    private void criarBanco(){
        bd = openOrCreateDatabase("banco", MODE_PRIVATE,null);

        bd.execSQL("CREATE TABLE IF NOT EXISTS alunos ( "+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nome VARCHAR )" );

        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Romulo Beninca' )");
        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Mateus Nunes' )");
        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Andreu Carminatti' )");
    }
}
