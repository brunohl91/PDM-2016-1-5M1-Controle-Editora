package br.edu.ifsul.bruno.pdm_5n1_trabalho_editora;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsul.bruno.pdm_5n1_trabalho_editora.Banco.Apoiador;

public class Main extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtAuthor;
    private EditText edtYear;
    private Apoiador apoiador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtAuthor = (EditText) findViewById(R.id.edt_autor);
        edtYear = (EditText) findViewById(R.id.edt_ano);

        apoiador = new Apoiador(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void cadastrar(View view) {

        try {

            String title = edtTitle.getText().toString();
            String author = edtAuthor.getText().toString();
            Integer year = Integer.parseInt(edtYear.getText().toString());

            SQLiteDatabase banco = apoiador.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("author", author);
            cv.put("year", year);

            long result = banco.insert("livros", "_id", cv);

            if (result != -1) {
                Toast.makeText(Main.this, R.string.strMsgInsertSucesso, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Main.this, R.string.strMsgInsertFalha, Toast.LENGTH_SHORT).show();
            }
            banco.close();
            limpar(null);

        } catch (Exception e) {
            Toast.makeText(Main.this, R.string.msgInvalidYear, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpar(View view) {
        edtTitle.setText("");
        edtAuthor.setText("");
        edtYear.setText("");
    }

    public void listar(View view) {
        startActivity(new Intent(this, Listagem.class));
    }
}
