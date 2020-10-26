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
    var roomsId:Array<String> =arrayOf("000","000")


    var isNobody:Array<Boolean> =arrayOf(true,true);
    var isNormal:Array<Boolean> =arrayOf(true,true);
    var isEdited:Array<Boolean> =arrayOf(false,false);



    var other:Array<String> = arrayOf("","");

    var normal:Array<String> = arrayOf("正常；","正常；");



    fun get_IsNormal(index:Int):String{
        if (isNormal.get(index))return "正常；"

        else return "";
    }


    fun get_Isnobody(index:Int):String {
        if (isNobody.get(index))return ""
        else return "有人；";
    };




    fun getInfoString():String{

        return  "$normal, $other"

    }

    override fun toString(): String {
        var index=0;

//        return  "$roomsId ： ${get_Isnobody()}${get_IsNormal()}$other\n";
        return  "${roomsId.get(0)}（上午） ：${get_Isnobody(0)}${get_IsNormal(0)}${other.get(index)}\n" +
//                "         （下午） ：${get_Isnobody(1)}${get_IsNormal(1)}${other.get(index)}\n";
                "\t\t\t\t\t（下午） ：${get_Isnobody(1)}${get_IsNormal(1)}${other.get(index)}\n";

    }
//    var roomsId:String="000";
//    var isNobody:Boolean=true;
//    var isNormal:Boolean=true;
//    var isEdited:Boolean=false;
//
//    fun get_IsNormal():String{
//        if (isNormal)return "正常；"
//
//        else return "";
//    }
//
//
//    fun get_Isnobody():String {
//        if (isNobody)return ""
//        else return "有人；";
//    };
//
//    var other:String="";
//
//    var normal:String="正常；";
//
//
//    fun getInfoString():String{
//
//        return  "$normal, $other"
//
//    }
//
//    override fun toString(): String {
//
//        return  "$roomsId ： ${get_Isnobody()}${get_IsNormal()}$other\n";
//
//    }
}