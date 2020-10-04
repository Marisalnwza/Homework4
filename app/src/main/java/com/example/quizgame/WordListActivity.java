package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.model.WordItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordListActivity extends AppCompatActivity {

    static public WordItem[] items = {
            new WordItem(R.drawable.animal_cat,"CAT"),
            new WordItem(R.drawable.animal_dog,"DOG"),
            new WordItem(R.drawable.animal_bird ,"BIRD"),
            new WordItem(R.drawable.animal_penquin,"PENQUIN"),
            new WordItem(R.drawable.animal_turtle,"TURTLE")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        List<WordItem> wordList = Arrays.asList(items);


        //สร้าง Adapter object
        MyAdapter adapter = new MyAdapter(WordListActivity.this,wordList);
        //สร้าง Layout manager
        LinearLayoutManager lm = new LinearLayoutManager(WordListActivity.this);

        //เข้าถึง RecycleView ใน Layout
        RecyclerView rv = findViewById(R.id.word_list_recycler_view);
        rv.setLayoutManager(lm); //กำหนดLayout Managerใฟ้กัย RecycleView
        rv.setAdapter(adapter); // กำหนด Adapter ให้กับ RecycleView

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        final Context mContext;
        final List<WordItem> mWordList;

        public MyAdapter(Context context, List<WordItem> wordList){
            this.mContext = context;
            this.mWordList = wordList;

        }

    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word,parent,false);
            MyViewHolder vh = new MyViewHolder(mContext,v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

                holder.imageView.setImageResource(mWordList.get(position).imageResID);
                holder.wordTextView.setText(mWordList.get(position).word);
                holder.item = mWordList.get(position);


        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            View rootView;
            ImageView imageView;
            TextView wordTextView;
            WordItem item;

            public MyViewHolder(@NonNull final Context context, View itemView) {
                super(itemView);
                rootView = itemView;
                imageView = itemView.findViewById(R.id.image_view);
                wordTextView = itemView.findViewById(R.id.word_text_View);



                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,item.word,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(context, WordDetailsActivity.class);
                        /*intent.putExtra("word",item.word);
                        intent.putExtra("Image",item.imageResID);*/

                        String itemJson  = new Gson().toJson(item);
                        intent.putExtra("item",itemJson);

                        context.startActivity(intent);

                        /*new AlertDialog.Builder(context)
                                .setTitle("My dialog")
                                .setMessage(item.word)
                                .setPositiveButton("OK",null)
                                .show();*/

                    }
                });


            }


        }
    }
}