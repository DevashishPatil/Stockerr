package com.example.stockerr

import java.util.*

class DataModel : ArrayList<DataModel.DataModelItem>(){
    data class DataModelItem(
            val Company: String,
            val Bonus_Ratio: String,
            val Announcement_Date: String,
            val Record_Date: String,
            val ExBonus_Date: String,
            val Link:String
    )
}