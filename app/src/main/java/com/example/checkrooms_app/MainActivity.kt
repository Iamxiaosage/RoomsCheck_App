package com.example.checkrooms_app

import android.app.AlertDialog
<<<<<<< HEAD
import android.app.PendingIntent.getActivity
=======
>>>>>>> fd961cca89bd3aaf227b7655d6bd0c75ae0d1ea7
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkrooms_app.adapter.RoomsRvAdapter
import kotlinx.android.synthetic.main.activity_main.*
<<<<<<< HEAD

=======
>>>>>>> fd961cca89bd3aaf227b7655d6bd0c75ae0d1ea7


//dataerror3
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

//                for (r2 in roomsList!!)

                var msg: StringBuilder = StringBuilder();
<<<<<<< HEAD
                var msg_hold: StringBuilder = StringBuilder();
                for (r in roomsList!!) {
                    if (r.isHold){
                        msg_hold?.append(r)
//                        r.append
                    }else{
                        msg?.append(r)
=======
                var msg_nobody: StringBuilder = StringBuilder();
                for (r in roomsList!!) {
                    if (r.isNobody){
                        msg?.append(r)
//                        r.append
                    }else{
                        msg_nobody?.append(r)
>>>>>>> fd961cca89bd3aaf227b7655d6bd0c75ae0d1ea7

                    }

                }
<<<<<<< HEAD
                msg.append(msg_hold);
=======
                msg.append(msg_nobody);
>>>>>>> fd961cca89bd3aaf227b7655d6bd0c75ae0d1ea7

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
//        rv_rooms.addItemDecoration(DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_List));
//        rv_rooms.addItemDecoration(
//            DividerItemDecoration(
//                this@MainActivity, DividerItemDecoration.HORIZONTAL
//            )
//        )
        rv_rooms.addItemDecoration( DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        roomsRvAdapter = RoomsRvAdapter(this);
        rv_rooms.setAdapter(roomsRvAdapter);

    }
}