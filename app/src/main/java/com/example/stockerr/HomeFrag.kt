package com.example.stockerr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFrag(
        private var activity: MainActivity,
        private var tools: Tools
) : Fragment() {

    private lateinit var view: View
    private lateinit var recyclerView: RecyclerView
    private var TAG: String = "Ho32me"
    private var year = "2021"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false)

        if (container != null) {
            tools = Tools(container.context)
        }

        if (container != null) {
            getShowOrder(container.context)

        }

        recyclerView = view.findViewById(R.id.recycle)

        return view
    }

    fun getShowOrder(context: Context) {
        try {
            tools.showDialog()
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://stockbonus.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val api = retrofit.create(Api::class.java)


            api.getBonus()
                    ?.enqueue(object : Callback<ResponseBody?> {
                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                            t.printStackTrace()
                        }

                        override fun onResponse(
                                call: Call<ResponseBody?>,
                                response: Response<ResponseBody?>
                        ) {
                            val responseStr = "" + response.body()?.string()

                            if (responseStr == "Invalid Inputs" && responseStr.equals("404") && responseStr.equals("405") && responseStr.equals("404.5") && responseStr.equals("NaN") && responseStr.equals("null")
                            ) {
                                Log.d(TAG,"null response ShowOrdersD")
                            } else {

                                Log.d("sdf",""+responseStr)


                                val gson = Gson()

                                val orders = gson.fromJson(responseStr, DataModel::class.java)

                                if (orders != null)
                                    show(orders, context)
                                tools.hideDialog()
                                Log.d(TAG,"Passing Data to adapter")
                            }
                            if (responseStr != null) {
                                Log.d(TAG, responseStr)
                            }
                        }

                    })
        }catch(e:Exception){}
    }

    fun show(orders: DataModel, context: Context) {

        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = HomeAdapter(context, orders, activity, tools)
        recyclerView.setHasFixedSize(true)
    }

    override fun getView(): View? {
        return super.getView()
    }
}