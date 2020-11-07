package com.example.checkrooms_app.bean

import java.io.Serializable

/**
 * @author fansx
 * @version $
 * @des
 * @updateAuthor $
 * @updateDes
 */
class RoomsInfo :Serializable{
    var roomsId:String="000";
    var isNobody:Boolean=true;
    var isNormal:Boolean=true;
    var isEdited:Boolean=false;

    fun get_Other():String{
        if (other.isNullOrEmpty()){
            return ""

        }else{
            return "($other)";
        }
    }

    fun get_IsNormal():String{
        if (isNormal)return "正常；"

        else return "";
    }


//    fun get_Is

    fun get_Isnobody():String {
        if (isNobody)return ""
        else return "有人；";
    };

    var other:String="";

    var normal:String="正常；";


    fun getInfoString():String{

        return  "$normal, $other"

    }




    override fun toString(): String {

//        return  "$roomsId ： ${get_Isnobody()}${get_IsNormal()}$other";
        return  "$roomsId ${get_Other()}\t";



//        else               roomsId + ":" + normal + "\n";
    }

}