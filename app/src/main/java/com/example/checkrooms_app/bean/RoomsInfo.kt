<<<<<<< HEAD
//package com.example.checkrooms_app.bean
//
//import com.example.checkrooms_app.R
//import kotlinx.android.synthetic.main.item_rv.view.*
//
///**
// * @author fansx
// * @version $
// * @des
// * @updateAuthor $
// * @updateDes
// */
////git test11111
//class RoomsInfo{
//    var roomsId:String="000";
//    var isHold:Boolean=false;
//    var isNormal:Boolean=true;
//
//    fun get_IsNormal():String{
//        if (isNormal)return "正常；"
//
//        else return "";
//    }
//
//
////    fun get_Is
//
//    fun get_IsHold():String {
//        if (isHold)return "有人"
//        else return "";
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
//
//
//
//    override fun toString(): String {
////        return if (isHold) roomsId + ":有人" + "\n"
////        return  roomsId + get_IsHold()+other+ "\n";
//        return  "$roomsId ： ${get_IsHold()}${get_IsNormal()}$other\n";
//
//
//
////        else               roomsId + ":" + normal + "\n";
//    }
//
//}
=======
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
    var isNobody:Boolean=true;
    var isNormal:Boolean=true;
    var isEdited:Boolean=false;

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
//        return if (isHold) roomsId + ":有人" + "\n"
//        return  roomsId + get_IsHold()+other+ "\n";
        return  "$roomsId ： ${get_Isnobody()}${get_IsNormal()}$other\n";



//        else               roomsId + ":" + normal + "\n";
    }

}
>>>>>>> fd961cca89bd3aaf227b7655d6bd0c75ae0d1ea7
