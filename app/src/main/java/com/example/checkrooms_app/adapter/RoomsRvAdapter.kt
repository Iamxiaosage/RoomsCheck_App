package com.example.checkrooms_app.adapter

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
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

    override fun onBindViewHolder(holder: RoomsRVHolder, pos: Int) {

        val cb_Ishold = holder.cb_ishold;
        val tv_Roomsid = holder.tv_roomsId;
        val et_Note = holder.et_note;

        val mRoom:RoomsInfo = mRoomsList?.get(pos)!!;

        cb_Ishold.setChecked(mRoom?.isHold);
        tv_Roomsid.setText(mRoom?.roomsId)
        et_Note.setText(mRoom?.note!!);

        cb_Ishold.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, is_checked: Boolean) {


                mRoom?.isHold=is_checked;

                if (is_checked){
                    mRoom.note=mContext.getString(R.string.has_people);
                    et_Note.setText(R.string.has_people)
                    et_Note.setTextColor(Color.GRAY)

                }else{
                    mRoom.note=mContext.getString(R.string.nomal);
                    et_Note.setText(R.string.nomal)
                    et_Note.setTextColor(Color.BLACK)
                }
            }
        })

        et_Note.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                text: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
            }
            override fun beforeTextChanged(
                text: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                //text  输入框中改变前的字符串信息
                //start 输入框中改变前的字符串的起始位置
                //count 输入框中改变前后的字符串改变数量一般为0
                //after 输入框中改变后的字符串与起始位置的偏移量
            }
            override fun afterTextChanged(edit: Editable) {
                val roomsNote = et_Note.text.toString();
                val roomsInfo = mRoomsList?.get(pos);
                roomsInfo?.note=roomsNote;

                for (i in mRoomsList!!){
                    System.out.println(i.toString())
                }
            }
        })
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return if (mRoomsList == null) 0 else mRoomsList.size
    }

    inner class RoomsRVHolder(itemView: View,position: Int) : ViewHolder(itemView) {
        var tv_roomsId: TextView
        var et_note:EditText
        var cb_ishold:CheckBox

        init {
            et_note = itemView.findViewById(R.id.et_note)
            tv_roomsId = itemView.findViewById(R.id.tv_roomsId)
             cb_ishold = itemView.findViewById(R.id.cb_is_hold)
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
            mRoomsList.add(room)
        }
    }
}