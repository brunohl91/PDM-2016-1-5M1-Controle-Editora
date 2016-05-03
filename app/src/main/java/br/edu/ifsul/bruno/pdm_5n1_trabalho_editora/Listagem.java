package br.edu.ifsul.bruno.pdm_5n1_trabalho_editora;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsul.bruno.pdm_5n1_trabalho_editora.Banco.Apoiador;

/**
 * Created by Bruno on 26/04/2016.
 */
public class Listagem extends ListActivity implements AdapterView.OnItemClickListener {

    private Apoiador apoiador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apoiador = new Apoiador(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // inserir código para gerar lista
        montaLista();
    }

    private void montaLista() {
        // qual a key da origem?
        String[] origem = { "idPegado", "autorPegado", "tituloPegado", "anoPegado" };
        // ligação com xml
        int[] destino = { R.id.txtViewId, R.id.txtViewAuthor, R.id.txtViewTitle, R.id.txtViewYear };

        SimpleAdapter adaptador = new SimpleAdapter(this, consultaBanco(), R.layout.layout_lista_completa, origem, destino);
        // mostra o adaptador
        setListAdapter(adaptador);

        // tornar sensível
        getListView().setOnItemClickListener(this);
    }

    private List<? extends Map<String, ?>> consultaBanco() {
        SQLiteDatabase banco = apoiador.getReadableDatabase();
        Cursor cursor = banco.query("livros", new String[]{ "_id", "author", "title", "year" }, null, null, null, null, "author");

        // cria um list do tipo hashmap
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // criar um mapa para guardar row
            Map<String, Object> item = new HashMap<String, Object>();
            // colocar o que foi pego pelo cursor
            item.put("idPegado", cursor.getInt(0));
            item.put("autorPegado", cursor.getString(1));
            item.put("tituloPegado", cursor.getString(2));
            item.put("anoPegado", cursor.getString(3));
            result.add(item);
        }

        cursor.close();
        banco.close();
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Map<String, String> itemClicado = (Map<String, String>) parent.getItemAtPosition(position);
        // Toast.makeText(Listagem.this, "ID: " + String.valueOf(itemClicado.get("idPegado")), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder montador = new AlertDialog.Builder(this);
        montador.setTitle(R.string.strEscolhaOpcao);

        final String[] itensDialog = new String[]{getString(R.string.strDeletar), getString(R.string.strEditar)};

        montador.setItems(itensDialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String escolha = itensDialog[which].toString();
                if (escolha.equals(getString(R.string.strDeletar))) {
                    removeId(String.valueOf(itemClicado.get("idPegado")));
                } else if (escolha.equals(getString(R.string.strEditar))) {
                    editarId(
                            String.valueOf(itemClicado.get("idPegado")),
                            String.valueOf(itemClicado.get("autorPegado")),
                            String.valueOf(itemClicado.get("tituloPegado")),
                            String.valueOf(itemClicado.get("anoPegado"))  );
                } else {
                    Toast.makeText(Listagem.this, R.string.msgOpcaoInvalida, Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialogShow = montador.create();
        dialogShow.show();
    }

    private void editarId(String id, String autor, String titulo, String ano) {
        Integer requestCode = 299;
        Intent intent = new Intent(this, Editar.class);
        intent.putExtra("idPegado", id);
        intent.putExtra("autorPegado", autor);
        intent.putExtra("tituloPegado", titulo);
        intent.putExtra("anoPegado", ano);
        startActivityForResult(intent, requestCode);
    }

    // pegar a resposta e fazer alguma coisa
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 299) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(Listagem.this, R.string.msgSuccessUpdate, Toast.LENGTH_SHORT).show();
                onResume();
            }
            else {
                Toast.makeText(Listagem.this, R.string.msgFailUpdate, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeId(String idPegado) {
        SQLiteDatabase db = apoiador.getWritableDatabase();
        long result = db.delete("livros" ,"_id=?", new String[]{ String.valueOf(idPegado) } );
        if (result > -1) {
            Toast.makeText(Listagem.this, R.string.strMsgRemovidoSucesso, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Listagem.this, R.string.strMsgRemovidoFalha, Toast.LENGTH_SHORT).show();
        }
        db.close();
        onResume();
    }
}
