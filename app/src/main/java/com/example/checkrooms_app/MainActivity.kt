package com.example.checkrooms_app

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.system.Os.remove
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkrooms_app.adapter.RoomsRvAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

      lateinit var roomsRvAdapter:RoomsRvAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.show -> {

                val builder = AlertDialog.Builder(this);
                builder.setTitle("rooms巡检信息：")
                val editText = EditText(this);

                val roomsList = roomsRvAdapter.getRoomsList();

                 var msg:StringBuilder=StringBuilder();
                for (r in roomsList!!){
                    msg?.append(r)

                }

//                editText.setText(roomsList.toString());
                editText.setText(msg);

                builder.setView(editText);
                builder.show();
            }
            R.id.clear -> {
                Toast.makeText(this@MainActivity, "清空", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
        return true
    }

    private fun initView() {
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this);
        rv_rooms.setLayoutManager(linearLayoutManager);

        roomsRvAdapter = RoomsRvAdapter(this);
        rv_rooms.setAdapter(roomsRvAdapter);

    }
}