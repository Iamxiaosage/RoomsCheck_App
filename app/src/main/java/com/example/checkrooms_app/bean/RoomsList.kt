package com.example.checkrooms_app.bean

/**
 * @author fansx
 * @des
 * @version $
 * @updateAuthor $
 * @updateDes
 */
class RoomsList(c: MutableCollection<out RoomsInfo>) : ArrayList<RoomsInfo>(c) {
    override fun toString(): String {
        return super<java.util.ArrayList>.toString()
    }
}