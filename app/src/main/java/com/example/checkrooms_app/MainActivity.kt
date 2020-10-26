package com.example.checkrooms_app

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkrooms_app.adapter.RoomsRvAdapter
import com.example.checkrooms_app.bean.RoomsInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*


//dataerror3
class MainActivity : AppCompatActivity() {

    lateinit var roomsRvAdapter: RoomsRvAdapter;
    var path: String =
        Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/people.txt"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView();

        checkReadExternalStoragePermission();

    }


    public fun checkReadExternalStoragePermission()   {
        if (Build.VERSION.SDK_INT>=23)       {
            var request:Int= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (request!= PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.NFC),123);
                return;//
            }
            else
            {
                //权限同意，不需要处理,去掉用拍照的方法               Toast.makeText(this,"权限同意",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //低于23 不需要特殊处理，去掉用拍照的方法
        }
    }

    override public fun onRequestPermissionsResult(requestCode:Int, @NonNull permissions:Array<String>, grantResults:IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {            //当然权限多了，建议使用Switch，不必纠结于此
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限申请成功", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public fun read(){
        var mRoomList:ArrayList<RoomsInfo>?=null;
        try {

            var ois:ObjectInputStream?=null;

            ois = ObjectInputStream(FileInputStream(File(path)));
            mRoomList= ois.readObject() as ArrayList<RoomsInfo>;
            for (r in mRoomList){
                print("serialzable:${r.toString()}")
            }
        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
        setRoomRv(mRoomList);
    }


    public fun save(mRoomsList: ArrayList<RoomsInfo>) {
        var fos: ObjectOutputStream? = null;
        try {
            val file = File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = ObjectOutputStream(FileOutputStream(file));
            fos.writeObject(mRoomsList);
        } catch (e: Exception) {
            e.printStackTrace();
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace();
            }
        }
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

                var msg: StringBuilder = StringBuilder();
                var msg_nobody: StringBuilder = StringBuilder();
                for (r in roomsList!!) {
                    val isAm=true;
                    if (isAm){
                        if (r.isNobody[0]) {
                            msg?.append(r)

                        } else {
                            msg_nobody?.append(r)
                        }
                    }

                }
                msg.append(msg_nobody);

                editText.setText(msg);

                save(roomsList);


                builder.setView(editText);
                builder.show();
            }
            R.id.clear -> {
                Toast.makeText(this@MainActivity, "清空", Toast.LENGTH_SHORT).show()
            }
            R.id.input -> {
                read();
            }
        }
        return true
    }

    private fun initView() {
        setContentView(R.layout.activity_main)

        setRoomRv(null);

    }

    private fun setRoomRv(roomList:ArrayList<RoomsInfo>?) {
        val linearLayoutManager = LinearLayoutManager(this);
        rv_rooms.setLayoutManager(linearLayoutManager);

        rv_rooms.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        roomsRvAdapter = RoomsRvAdapter(this,roomList);
        rv_rooms.setAdapter(roomsRvAdapter);
    }
}