package com.example.stockerr

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import java.util.*


class HomeAdapter(
        private val context: Context,
        private val OrderItems: DataModel,
        private val mainactivity: MainActivity,
        private var tools: Tools
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.d_rowitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val order=OrderItems.get(position)
        holder.bind(order)

        holder.linkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(order.Link))
            mainactivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return  OrderItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val company = view.findViewById<TextView>(R.id.dOid)
        val linkButton = view.findViewById<MaterialButton>(R.id.cdelivery)
        val announcementDate = view.findViewById<TextView>(R.id.announcement)
        val recordDate = view.findViewById<TextView>(R.id.record)
        val ex_BonusDate = view.findViewById<TextView>(R.id.ex_Bonus)
        val bonusRation = view.findViewById<EditText>(R.id.outlinedTextEdit)

        @SuppressLint("SetTextI18n")
        fun bind(order: DataModel.DataModelItem) {

            company.text = "Company : ${order.Company}"
            announcementDate.text = "Announcement Date : ${order.Announcement_Date}"
            recordDate.text = "Announcement Date : ${order.Record_Date}"
            ex_BonusDate.text = "Announcement Date : ${order.ExBonus_Date}"
            bonusRation.setText(order.Bonus_Ratio)

        }
    }
}