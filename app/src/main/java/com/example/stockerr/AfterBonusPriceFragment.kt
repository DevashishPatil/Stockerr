package com.example.stockerr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.button.MaterialButton

class AfterBonusPriceFragment(
        private var activity: MainActivity,
        private var tools: Tools
) : Fragment() {

    private lateinit var view1: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_after_bonus_price, container, false)

        if (container != null) {
            tools = Tools(container.context)
        }

        if (container != null) {
            getCalc(view1)

        }

        return view1
    }

    private fun getCalc(view1: View) {
        val A = view1.findViewById<EditText>(R.id.outlinedTextEdit1)
        val B = view1.findViewById<EditText>(R.id.outlinedTextEdit2)
        val MarketPriceBefore = view1.findViewById<EditText>(R.id.outlinedTextEdit3)
        val MarketPriceAfter = view1.findViewById<EditText>(R.id.outlinedTextEdit4)
        val calButton = view1.findViewById<MaterialButton>(R.id.calButton)

        calButton.setOnClickListener {
            tools.showDialog()
            val adjustmentFactor = (A.text.toString().trim().toDouble() + B.text.toString().trim().toDouble())/B.text.toString().trim().toDouble()
            val newAdjustmentFactor = String.format("%.2f", adjustmentFactor).toDouble()

            MarketPriceAfter.setText(String.format("%.2f", (MarketPriceBefore.text.toString().trim().toDouble()/newAdjustmentFactor)))
            MarketPriceAfter.isEnabled = false
            tools.hideDialog()
        }

    }
}