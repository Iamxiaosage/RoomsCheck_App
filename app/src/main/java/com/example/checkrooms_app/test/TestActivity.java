package com.example.checkrooms_app.test;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author fansx
 * @version $
 * @des
 * @updateAuthor $
 * @updateDes
 */
class TestActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = TestActivity.this;
    }
}
