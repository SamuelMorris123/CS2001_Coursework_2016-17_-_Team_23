package com.sammorris.groupforums;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mCatList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Category");
        mCatList = (RecyclerView) findViewById(R.id.cat_list);
        mCatList.setHasFixedSize(true);
        mCatList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Category, CatViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Category, CatViewHolder>(
                Category.class,
                R.layout.cat_row,
                CatViewHolder.class,
                mDatabase) {
            @Override
            protected void populateViewHolder(CatViewHolder viewHolder, Category model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };
        mCatList.setAdapter(firebaseRecyclerAdapter);


    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public CatViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setTitle(String title) {

            TextView catpost_title = (TextView) mView.findViewById(R.id.catpost_title);
            catpost_title.setText(title);
        }

        public void setDesc(String desc){

            TextView catpost_desc = (TextView) mView.findViewById(R.id.catpost_desc);
            catpost_desc.setText(desc);
        }

        public void setImage(Context ctx,String image){
            ImageView catpost_image = (ImageView) mView.findViewById(R.id.catpost_image);
            Picasso.with(ctx).load(image).into(catpost_image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.action_add){
            startActivity(new Intent(MainActivity.this,Post_Activity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
