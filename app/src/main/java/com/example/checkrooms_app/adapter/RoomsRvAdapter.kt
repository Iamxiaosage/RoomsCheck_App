package com.example.checkrooms_app.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.checkrooms_app.R
import com.example.checkrooms_app.bean.RoomsInfo

/**
 * @author fansx
 * @des
 * @version $
 * @updateAuthor $
 * @updateDes
 */


 class RoomsRvAdapter(context: Context) : RecyclerView.Adapter<RoomsRvAdapter.RoomsRVHolder?>() {
    private val mLayoutInflater: LayoutInflater
    private val mContext: Context
    private val mRoomsList: ArrayList<RoomsInfo>?

     fun getRoomsList() :ArrayList<RoomsInfo>?{
        return mRoomsList;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsRVHolder {
        val roomsRVHolder =
            RoomsRVHolder(mLayoutInflater.inflate(R.layout.item_rv, parent, false),viewType);
        roomsRVHolder.setIsRecyclable(false);

        return roomsRVHolder
    }

    private fun havaEdited(tv_Roomsid: TextView, roomsInfo: RoomsInfo?) {
        roomsInfo?.isEdited=true;
        tv_Roomsid.setTextColor(Color.parseColor("#00FF00"));

    }
    override fun onBindViewHolder(holder: RoomsRVHolder, pos: Int) {


        val cb_Ishold = holder.cb_ishold;
        val tv_Roomsid = holder.tv_roomsId;
        val tv_other = holder.tv_other;
        val cb_isNormal = holder.cb_isNormal;

        val mRoom:RoomsInfo = mRoomsList?.get(pos)!!;

        Log.v("msg",mRoom.roomsId)

        cb_isNormal.setChecked(mRoom?.isNormal);
        cb_Ishold.setChecked(mRoom?.isNobody);
        tv_Roomsid.setText(mRoom?.roomsId);
        tv_other.setText(mRoom?.other);

        if (mRoom.isEdited){
            havaEdited(tv_Roomsid,null);
        }


        cb_isNormal.setOnCheckedChangeListener { compoundButton, is_checked ->
            havaEdited(tv_Roomsid, mRoom)
            if (is_checked){
                mRoom.isNormal=true;

            }else{
                mRoom.isNormal=false;
            }
        }

        cb_Ishold.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, is_checked: Boolean) {
//                holder.set
//                tv_Roomsid.setTextColor(0x00ff00f)
                havaEdited(tv_Roomsid,mRoom)

                mRoom?.isNobody=is_checked;
                //不可用
                if (is_checked){
//                    mRoom.normal=mContext.getString(R.string.has_people);
                    mRoom.isNobody=true;
                    tv_other.setTextColor(Color.GRAY)
                    //可用
                }else{
//                    mRoom.normal=mContext.getString(R.string.nomal);
                    mRoom.isNobody=false;
                    tv_other.setTextColor(Color.BLACK)
                }
//                et_other.setText(mRoom.getInfoString())
            }
        })



//        var str_other:String=mRoom?.other;

        tv_other.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                havaEdited(tv_Roomsid, mRoom)


                val builder = AlertDialog.Builder(mContext);
                val title = builder.setTitle("请输入备注")
                val et_other = EditText(mContext);

                if (!mRoom.other.isEmpty()){
                    et_other.setText(mRoom.other)
                }else{
                    if (pos-1>=0)
                        et_other.setText(mRoomsList.get(pos-1).other);
                }

                builder.setView(et_other)
                builder.setPositiveButton(mContext.getString(R.string.Ok),DialogInterface.OnClickListener()
                { dialogInterface: DialogInterface, i: Int ->
                    val str_other = et_other.text.toString();
                    tv_other.setText(str_other)
                    mRoom.other=str_other;

                })
//                builder.setNeutralButton(mContext.getString(R.string.clear),DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
//                    et_other.setText("");
//
//                })

                builder.show()

            }

        })
//        tv_other.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(
//                text: CharSequence,
//                start: Int,
//                before: Int,
//                count: Int
//            ) {
////                str_other= text.toString();
//
//                //text  输入框中改变后的字符串信息
//                //start 输入框中改变后的字符串的起始位置
//                //before 输入框中改变前的字符串的位置 默认为0
//                //count 输入框中改变后的一共输入字符串的数量
//            }
//            override fun beforeTextChanged( text: CharSequence, start: Int, count: Int, after: Int) {
//                //text  输入框中改变前的字符串信息
//                //start 输入框中改变前的字符串的起始位置
//                //count 输入框中改变前后的字符串改变数量一般为0
//                //after 输入框中改变后的字符串与起始位置的偏移量
//            }
//            override fun afterTextChanged(edit: Editable) {
//                val roomsOther = tv_other.text.toString();
////                val roomsInfo = mRoomsList?.get(pos);
//
//                System.out.println(""+pos+"难道调用了："+roomsOther)
////                System.out.println("难道调用了："+pos)
//
//                mRoom?.other=roomsOther;
//            }
//        })
    }



    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return if (mRoomsList == null) 0 else mRoomsList.size
    }

    inner class RoomsRVHolder(itemView: View,position: Int) : ViewHolder(itemView) {
        var tv_roomsId: TextView

        var tv_other:TextView
        var cb_ishold:CheckBox
        var cb_isNormal:CheckBox

        init {
            tv_other = itemView.findViewById(R.id.et_note)
            tv_roomsId = itemView.findViewById(R.id.tv_roomsId)
             cb_ishold = itemView.findViewById(R.id.cb_is_hold)
            cb_isNormal = itemView.findViewById(R.id.cb_is_normal)
        }
    }

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mContext = context
        val roomsArray = mContext.resources.getStringArray(R.array.rooms);
        mRoomsList = ArrayList()
        for (i in roomsArray) {
            val room = RoomsInfo();
            room.roomsId=i
            System.out.println("遍历："+i)
            mRoomsList.add(room)
        }
    }
}

