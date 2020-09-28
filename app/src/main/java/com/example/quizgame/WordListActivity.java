package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quizgame.model.WordItem;

public class WordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);


        //สร้าง Adapter object
        MyAdapter adapter = new MyAdapter();
        //สร้าง Layout manager
        LinearLayoutManager lm = new LinearLayoutManager(WordListActivity.this);

        //เข้าถึง RecycleView ใน Layout
        RecyclerView rv = findViewById(R.id.word_list_recycler_view);
        rv.setLayoutManager(lm); //กำหนดLayout Managerใฟ้กัย RecycleView
        rv.setAdapter(adapter); // กำหนด Adapter ให้กับ RecycleView

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        WordItem[] items = {
                new WordItem(R.drawable.animal_cat,"CAT"),
                new WordItem(R.drawable.animal_dog,"DOG"),
                new WordItem(R.drawable.animal_bird ,"BIRD"),
                new WordItem(R.drawable.animal_penquin,"PENQUIN"),
                new WordItem(R.drawable.animal_turtle,"TURTLE")
    };

        public MyAdapter(){

        }

    @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word,parent,false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

                holder.imageView.setImageResource(items[position].imageResID);
                holder.wordTextView.setText(items[position].word);




        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView wordTextView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_view);
                wordTextView = itemView.findViewById(R.id.word_text_View);
            }
        }
    }
}