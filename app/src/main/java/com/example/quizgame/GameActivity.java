package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.quizgame.model.WordItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private ImageView mQuestionImageView;
    private Button[] mButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        List<WordItem> itemList = new ArrayList<>(Arrays.asList(WordListActivity.items));

        mQuestionImageView = findViewById(R.id.question_image_view);
        mButtons[0] = findViewById(R.id.choice_1_button);
        mButtons[1] = findViewById(R.id.choice_2_button);
        mButtons[2] = findViewById(R.id.choice_3_button);
        mButtons[3] = findViewById(R.id.choice_4_button);

        Random r = new Random();
        //สุ่ม index ของคำศัพท์
        int answerIndex = r.nextInt(itemList.size());
        //เข้าถึง word Item ตาม index ที่ส่มได้
        WordItem item = itemList.get(answerIndex);

        //แสดงรูปคำถาม
        mQuestionImageView.setImageResource(item.imageResID);

        //สุ่มตำแหน่งปุ่มที่จะแสดงคำตอบ
        int randomButton = r.nextInt(4);
        //แสดงคำศัพท์ที่เป้นคำตอบ
        mButtons[randomButton].setText(item.word);

        //ลบ WordItem ที่เป็นคำตอบออกจาก List
        itemList.remove(item);

        //เอาlistที่เหลือมาshuffle
        Collections.shuffle(itemList);

        //เอาข้อมูลจาก List แสดงปุ่มสามปุ่มที่ไม่ใช่คำตอบ
        for(int i=0; i<4; i++){
            if (i == randomButton){
                continue;
            }
            mButtons[i].setText(itemList.get(i).word);
        }




    }
}