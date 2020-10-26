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


 class RoomsRvAdapter(context: Context,roomList:ArrayList<RoomsInfo>?) : RecyclerView.Adapter<RoomsRvAdapter.RoomsRVHolder?>() {
    private val mLayoutInflater: LayoutInflater
    private val mContext: Context
    private val mRoomsList: ArrayList<RoomsInfo>?
    private val timeIndex=0;

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
        Log.e("test","下标："+timeIndex);
        try {
            roomsInfo?.isEdited?.set(timeIndex, true);

        }catch (e:Exception){
            Log.e("test","异常："+e.toString());
        }
        tv_Roomsid.setTextColor(Color.parseColor("#00FF00"));

    }
    override fun onBindViewHolder(holder: RoomsRVHolder, pos: Int) {


        val cb_Isnobody = holder.cb_ishold_am;
        val tv_Roomsid = holder.tv_roomsId_am;
        val tv_other = holder.tv_other_am;
        val cb_isNormal = holder.cb_isNormal_am;

//        val cb_Isnobody = holder.cb_ishold_am;
//        val tv_Roomsid = holder.tv_roomsId_am;
//        val tv_other = holder.tv_other_am;
//        val cb_isNormal = holder.cb_isNormal_am;

        val mRoom:RoomsInfo = mRoomsList?.get(pos)!!;

        Log.v("msg",mRoom.roomsId[timeIndex])

        cb_isNormal.setChecked(mRoom?.isNormal[timeIndex]);
        cb_Isnobody.setChecked(mRoom?.isNobody[timeIndex]);
        tv_Roomsid.setText(mRoom?.roomsId[timeIndex]);
        tv_other.setText(mRoom?.other[timeIndex]);

        if (mRoom.isEdited[timeIndex]){
            havaEdited(tv_Roomsid,null);
        }


        cb_isNormal.setOnCheckedChangeListener { compoundButton, is_checked ->
            havaEdited(tv_Roomsid, mRoom)
            mRoom.isNormal[timeIndex]=is_checked;

//            if (is_checked){
//                mRoom.isNormal=true;
//
//            }else{
//                mRoom.isNormal=false;dd
//            }
        }

        cb_Isnobody.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, is_checked: Boolean) {
//                holder.set
                havaEdited(tv_Roomsid,mRoom)

                mRoom?.isNobody[timeIndex]=is_checked;
                if (!is_checked){
                    mRoom.isNormal[timeIndex]=false;
                    cb_isNormal.setChecked(false);

                }

                //不可用
//                if (is_checked){
//                    mRoom.isNobody=true;
//                    //可用
//                }else{
//                    mRoom.isNobody=false;
//                }
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
                    et_other.setText(mRoom.other[timeIndex])
                }else{
                    if (pos-1>=0)
                        et_other.setText(mRoomsList.get(pos-1).other[timeIndex]);
                }

                builder.setView(et_other)
                builder.setPositiveButton(mContext.getString(R.string.Ok),DialogInterface.OnClickListener()
                { dialogInterface: DialogInterface, i: Int ->
                    val str_other = et_other.text.toString();
                    tv_other.setText(str_other)
                    mRoom.other[timeIndex]=str_other;

                })

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
        var tv_roomsId_am: TextView

        var tv_other_am:TextView
        var cb_ishold_am:CheckBox
        var cb_isNormal_am:CheckBox

        var tv_other_pm:TextView
        var cb_ishold_pm:CheckBox
        var cb_isNormal_pm:CheckBox

        init {
            tv_other_am = itemView.findViewById(R.id.et_note_am)
            tv_roomsId_am = itemView.findViewById(R.id.tv_roomsId_am)
             cb_ishold_am = itemView.findViewById(R.id.cb_is_hold_am)
            cb_isNormal_am = itemView.findViewById(R.id.cb_is_normal_am)

            tv_other_pm = itemView.findViewById(R.id.et_note_pm)
//            tv_roomsId_pm = itemView.findViewById(R.id.tv_roomsId_pm)
            cb_ishold_pm = itemView.findViewById(R.id.cb_is_hold_pm)
            cb_isNormal_pm = itemView.findViewById(R.id.cb_is_normal_pm)
        }
    }

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mContext = context

        if (roomList.isNullOrEmpty()){
            val roomsArray = mContext.resources.getStringArray(R.array.rooms);
            mRoomsList = ArrayList()
            for (i in roomsArray) {
                val room = RoomsInfo();
                room.roomsId[timeIndex]=i
                System.out.println("遍历："+i)
                mRoomsList.add(room)
            }
        }else{
            mRoomsList=roomList;
        }

    }
}

