package com.example.stockerr

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.util.*

@Suppress("DEPRECATION")
class Tools(context: Context) {


    private var progressDialog: ProgressDialog

    init {
        progressDialog = ProgressDialog(context)
    }

    fun showDialog() {
        progressDialog.setTitle("Fetching...")
        progressDialog.setMessage("We are fetching data...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun showDialog(title: String, data: String, cancelable: Boolean) {
        progressDialog.setTitle("$title")
        progressDialog.setMessage("$data")
        progressDialog.setCancelable(cancelable)
        progressDialog.show()
    }

    fun hideDialog() {
        progressDialog.cancel()
    }

    fun getJsonObject(jsonStr: String): JsonObject {
        return JsonParser().parse(jsonStr).asJsonObject
    }


}