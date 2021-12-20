package com.solucioneshr.soft.testreign.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solucioneshr.soft.testrappi.viewmodel.FunViewModels
import com.solucioneshr.soft.testreign.R
import com.solucioneshr.soft.testreign.data.Hit
import com.solucioneshr.soft.testreign.data.SwipeToDeleteCallback
import com.solucioneshr.soft.testreign.viewmodel.OnLoadMoreListener

class DisplayActivity : AppCompatActivity(), Items_RVAdapter.OnItemClickListener {
    private lateinit var vm: FunViewModels
    private lateinit var adapter: Items_RVAdapter
    private lateinit var rv_home: RecyclerView
    private var itemsCells: ArrayList<Hit> = ArrayList()
    private var tempItemsCells: ArrayList<Hit> = ArrayList()
    private lateinit var layout_progress: CardView
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        rv_home = findViewById(R.id.rvDisplay)
        vm = ViewModelProvider(this)[FunViewModels::class.java]
        vm.getDataJson()

        layout_progress = findViewById(R.id.layoutProgress)

        vm.getModelLiveData?.observe(this, Observer{
            if (it!=null){
                itemsCells.addAll(it.hits)

                for (i in 0..9) {
                    tempItemsCells.add(itemsCells[i])
                }

            }else{
                showToast("No existen datos .....")
            }
            layout_progress.visibility = View.GONE

            initAdapter()

            initScrollListener()

        })

    }

    private fun initAdapter() {
        adapter = Items_RVAdapter(tempItemsCells, this)
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rv_home)
    }

    private fun initScrollListener() {
        rv_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                        tempItemsCells.size - 1) {
                        //bottom of list!
                        layout_progress.visibility = View.VISIBLE
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun loadMore() {
        tempItemsCells.add(Hit(".....Espere ...."))
        adapter.notifyItemInserted(tempItemsCells.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            tempItemsCells.removeAt(tempItemsCells.size - 1)
            val scrollPosition = tempItemsCells.size
            adapter.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 1
            tempItemsCells.add(itemsCells[currentSize])
            adapter.notifyDataSetChanged()
            isLoading = false
            layout_progress.visibility = View.GONE
        }, 2000)
    }

    override fun onItemClicked(data: Hit) {
        if (data.story_url != null) {
            val intento1 = Intent(this, DataActivity::class.java)
            intento1.putExtra("URL", data.story_url.toString())
            startActivity(intento1)
        } else{
            val intento1 = Intent(this, DataActivity::class.java)
            intento1.putExtra("URL", data.url.toString())
            startActivity(intento1)
        }
    }
}

