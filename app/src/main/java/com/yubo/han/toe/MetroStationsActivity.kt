package com.yubo.han.toe

import android.content.Intent
import com.yubo.han.toe.Services.FetchMetroStationsManager
import com.yubo.han.toe.model.MetroStations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import com.yubo.han.toe.Services.MetroStationsAdapter

import kotlinx.android.synthetic.main.activity_metro_stations.*

import org.jetbrains.anko.toast
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast

import kotlinx.android.synthetic.main.row_metro_station.view.*


class MetroStationsActivity : AppCompatActivity(), FetchMetroStationsManager.MetroStationsSearchCompletedListener {


    val LOG_TAG = "MetroStationsActivity"

    lateinit var mFetchMetroStationsManager: FetchMetroStationsManager
    lateinit var mStaggeredLayoutManager: StaggeredGridLayoutManager
    lateinit var mMetroStationsAdapter: MetroStationsAdapter

    lateinit var stationList: ArrayList<MetroStations>


    // Click landmark item listener
    var onItemClickListener = object : MetroStationsAdapter.OnItemClickListener {
        override fun onItemClick(view: View, stationData: MetroStations) {
            // Direct to LandmarkActivity, pass station data to the activity
            val landmarksIntent = Intent(this@MetroStationsActivity,LandmarksActivity::class.java)
            landmarksIntent.putExtra("stationData", stationData)
            startActivity(landmarksIntent)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_stations)

        // Set up tool bar
        setSupportActionBar(metroStationToolbar)
        // Disable android keyboard popup automatically
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        loadMetroStations()
    }

    private fun loadMetroStations() {
        mFetchMetroStationsManager = FetchMetroStationsManager(this)
        //regist itself to metroStationsCompletedListener
        mFetchMetroStationsManager.metroStationsSearchCompletedListener = this

        //query WMATA for all metro stations
        mFetchMetroStationsManager.queryWMATAForAllStations()

    }

    override fun stationsLoaded(stationList: ArrayList<MetroStations>) {
        //toast("${stationList}")

        this.stationList = stationList

        displayStationList()

    }

    override fun stationsNotLoaded() {
        toast("stations not loaded")

    }

    fun displayStationList() {

        mStaggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        metroStationViewList.layoutManager = mStaggeredLayoutManager

        mMetroStationsAdapter = MetroStationsAdapter(this, stationList)
        metroStationViewList.adapter = mMetroStationsAdapter

        // Action on click metro station
        mMetroStationsAdapter.setOnItemClickListener(onItemClickListener)

        // To check if user search the station, if yes, do filter
        searchStation()
    }


    // To check if user search
    private fun searchStation() {
        editTextSearch?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }

    // search and get result
    private fun filter(text: String) {
        val filterdStations = ArrayList<MetroStations>()

        for (station in stationList) {
            val name = station.name

            if (name.toLowerCase().contains(text.toLowerCase())) {
                filterdStations.add(station)
            }
        }

        mMetroStationsAdapter.filterList(filterdStations)
    }

}
