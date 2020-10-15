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
    var note:String="正常";

    override fun toString(): String {
        return if (isHold) roomsId + ":有人" + "\n"


        else               roomsId + ":" + note + "\n";
    }

}