package com.example.guilhermedaros.flickr;

import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ZoomControls;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import uk.co.senab.photoview.PhotoViewAttacher;

public class DetailActivity extends AppCompatActivity {

    ImageView mImageView;
    PhotoViewAttacher mPhotoViewAttacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        TextView tvAuthor = (TextView) findViewById(R.id.tv_author);
        TextView tvPublished = (TextView) findViewById(R.id.tv_published);
        TextView tvTags = (TextView) findViewById(R.id.tv_tags);
        mImageView = (ImageView) findViewById(R.id.iv_img_detail);

        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);

        CardImagem ci = (CardImagem) intent.getSerializableExtra("CardImagem");

        Picasso.with(this).load(ci.src.replace("_m", ""))
                .placeholder(R.drawable.nophoto)
                .into(mImageView);

        tvTitle.setText(ci.title);
        tvAuthor.setText(ci.author);
        tvPublished.setText(ci.published);
        tvTags.setText(ci.tags);


    }

}
