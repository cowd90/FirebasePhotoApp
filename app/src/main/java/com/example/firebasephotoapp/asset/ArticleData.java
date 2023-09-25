package com.example.firebasephotoapp.asset;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.example.firebasephotoapp.Adapter.ArticleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArticleData extends AsyncTask<String, String, String> {
    private Context context;
    private GridView gridView;
    private ArticleAdapter adapter;
    private static ArrayList<Article> data = new ArrayList<>();

    public ArticleData(Context context, GridView gridView) {
        this.context = context;
        this.gridView = gridView;
    }

    public static Article getPhotoFromId(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getArticle_id() == id) {
                return data.get(i);
            }
        }
        return null;
    }

    @Override
    protected String doInBackground(String... strings) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("article_product");
        data = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Article article = dataSnapshot.getValue(Article.class);
                        data.add(article);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter = new ArticleAdapter(data, context);
       gridView.setAdapter(adapter);
    }
}
