package br.edu.ifsul.bruno.pdm_5n1_trabalho_editora.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno on 26/04/2016.
 */
public class Apoiador extends SQLiteOpenHelper {

    private static final String banco = "livros.db";
    private static final int version = 1;

    public Apoiador(Context context) {
        super(context, banco, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // código para criação do banco de dados
        String sql = "CREATE TABLE livros (_id INTEGER PRIMARY KEY, title TEXT, author TEXT, year INTEGER);";
        db.execSQL(sql);
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Silmarillion', 'J. R. R. Tolkien', 1977)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Dataclisma', 'C. Rudder', 2015)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Lord of The Rings', 'J. R. R. Tolkien', 1954)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Hobbit', 'J. R. R. Tolkien', 1937)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Creativity Inc.', 'Ed Catmull', 2014)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('My Name Is Lucy Barton' , 'Elizabeth Strout ', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Passenger', 'Alexandra Bracken', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('When Breath Becomes Air' , 'Paul Kalanithi ', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('A Court of Mist and Fury', 'Sarah J. Maas', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Empire of Storms', 'Sarah J. Maas', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Glass Sword', 'Victoria Aveyard', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Harry Potter and the Cursed Child ', 'J.K. Rowling ', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Heartless  Heartless ', 'Marissa Meyer', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Lady Midnight', 'Cassandra Clare', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Morning Star', 'Pierce Brown', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Never Never: Part Three', 'Colleen Hoover', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('One with You', 'Sylvia Day', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Rebel of the Sands', 'Alwyn Hamilton', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Salt to the Sea  ', 'Ruta Sepetys', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Stars Above  Stars Above ', 'Marissa Meyer', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Crown', 'Kiera Cass', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Last Star', 'Rick Yancey ', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Nest The Nest ', 'Cynthia DAprix Sweeney', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Raven King', 'Maggie Stiefvater', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Siren  The Siren ', 'Kiera Cass', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('The Widow  The Widow ', 'Fiona Barton ', 2016)");
        db.execSQL("INSERT INTO livros (title, author, year) VALUES ('Truthwitch', 'Susan Dennard', 2016)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
