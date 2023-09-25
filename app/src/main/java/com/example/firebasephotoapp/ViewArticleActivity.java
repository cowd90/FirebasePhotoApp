package com.example.firebasephotoapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasephotoapp.asset.ArticleData;
import com.squareup.picasso.Picasso;

public class ViewArticleActivity extends AppCompatActivity {

    TextView tv_title, tv_desc;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        tv_title = findViewById(R.id.grid_data);
        iv_image = findViewById(R.id.image_view);
        tv_desc = findViewById(R.id.character_description);

        int id = (int) getIntent().getLongExtra("id", 0);

        Picasso.get().load(ArticleData.getPhotoFromId(id).getArticle_image()).resize(300, 400).centerCrop().into(iv_image);
        tv_title.setText(ArticleData.getPhotoFromId(id).getArticle_title());
        tv_desc.setText(ArticleData.getPhotoFromId(id).getArticle_description());
    }
}