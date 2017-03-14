package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        final TextView topicLink = (TextView) findViewById(R.id.tvTopic1);

        topicLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent topicIntent = new Intent(ForumActivity.this, TopicActivity.class);
                ForumActivity.this.startActivity(topicIntent);
            }
        });
    }
}
