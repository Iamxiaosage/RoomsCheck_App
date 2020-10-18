package com.example.checkrooms_app.bean

/**
 * @author fansx
 * @version $
 * @des
 * @updateAuthor $
 * @updateDes
 */
class RoomsInfo{
    var roomsId:String="000";
    var isHold:Boolean=false;

    fun get_IsHold():String {
        if (isHold)return "有人"
        else return "";
    };

    var other:String="";

    var normal:String="正常";


    fun getInfoString():String{

        return  "$normal, $other"

    }

//    override fun toString(): String {
////        return "RoomsInfo('$roomsId', $isHold, '$normal', '$other')"
//        return "RoomsInfo('$roomsId', $isHold,  '$other')"
//    }

//    val mMap = mutableMapOf(0 to "秦", 1 to "川", 2 to "小", 3 to "将")




    override fun toString(): String {
//        return if (isHold) roomsId + ":有人" + "\n"
        return  roomsId + get_IsHold()+other+ "\n";


//        else               roomsId + ":" + normal + "\n";
    }

}