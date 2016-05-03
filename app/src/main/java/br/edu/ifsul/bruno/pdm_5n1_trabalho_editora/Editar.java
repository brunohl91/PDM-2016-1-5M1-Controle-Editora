package br.edu.ifsul.bruno.pdm_5n1_trabalho_editora;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsul.bruno.pdm_5n1_trabalho_editora.Banco.Apoiador;

public class Editar extends AppCompatActivity {

    private Intent intent;
    private EditText edtId;
    private EditText edtTitle;
    private EditText edtAuthor;
    private EditText edtYear;
    private Apoiador apoiador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editar);

        apoiador = new Apoiador(this);

        intent = getIntent();
        String id = intent.getStringExtra("idPegado");
        String autor = intent.getStringExtra("autorPegado");
        String titulo = intent.getStringExtra("tituloPegado");
        String ano = intent.getStringExtra("anoPegado");

        edtId = (EditText) findViewById(R.id.edt_id);
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtAuthor = (EditText) findViewById(R.id.edt_autor);
        edtYear = (EditText) findViewById(R.id.edt_ano);

        edtId.setText(id);
        edtAuthor.setText(autor);
        edtTitle.setText(titulo);
        edtYear.setText(ano);

    }

    public void atualizar(View view) {

        try {
            String id = edtId.getText().toString();
            String title = edtTitle.getText().toString();
            String author = edtAuthor.getText().toString();
            Integer year = Integer.parseInt(edtYear.getText().toString());

            SQLiteDatabase banco = apoiador.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("author", author);
            cv.put("year", year);

            long result = banco.update("livros", cv, "_id=?", new String[]{ id });

            if (result == 1) {
                retornarOk(null);
            }
            else {
                retornarCancel(null);
            }
            banco.close();

        } catch (Exception e) {
            Toast.makeText(Editar.this, R.string.msgInvalidYear, Toast.LENGTH_SHORT).show();
        }


    }

    public void retornarOk(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void retornarCancel(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
