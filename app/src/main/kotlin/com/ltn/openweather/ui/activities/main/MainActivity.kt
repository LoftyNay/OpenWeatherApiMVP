package com.ltn.openweather.ui.activities.main

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ltn.openweather.R
import com.ltn.openweather.adapters.WeatherAdapter
import com.ltn.openweather.model.Weather
import com.ltn.openweather.ui.activities.gps.GpsActivity
import com.ltn.openweather.ui.activities.main.presenter.MainActivityPresenter
import com.ltn.openweather.ui.activities.main.view.MainActivityView
import com.ltn.openweather.ui.fragments.CityAddDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : MvpAppCompatActivity(), MainActivityView, WeatherAdapter.OnCardItemClickListener,
    WeatherAdapter.OnCardItemLongClickListener, DialogInterface.OnDismissListener, View.OnClickListener,
    TimePickerDialog.OnTimeSetListener {

    @InjectPresenter
    lateinit var mainActivityPresenter: MainActivityPresenter

    private lateinit var recyclerAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityPresenter.attach()

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        updateButtonMain.setOnClickListener(this)
        setTimeRefreshWeather.setOnClickListener(this)

        initRecycler()
        mainActivityPresenter.loadWeathersList()
    }

    private fun initRecycler() {
        val recyclerViewMain = findViewById<RecyclerView>(R.id.recyclerViewMain)
        recyclerViewMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerAdapter = WeatherAdapter(this, this)
        recyclerViewMain.adapter = recyclerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addCityMenuItem -> {
                CityAddDialogFragment.getInstance().show(supportFragmentManager, CityAddDialogFragment.TAG)
            }
            R.id.gpsMenuItem -> {
                startActivity(Intent(this, GpsActivity::class.java))
            }
        }
        return true
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mainActivityPresenter.setTimeToRefreshWeather(hourOfDay, minute)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        mainActivityPresenter.loadWeathersList()
    }

    override fun showWeathersList(weathersList: MutableList<Weather>) {
        recyclerAdapter.addAll(weathersList)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.updateButtonMain -> {
                mainActivityPresenter.updateWeatherFromService()
            }
            R.id.setTimeRefreshWeather -> {
                TimePickerDialogFragment.getInstance().show(supportFragmentManager, TimePickerDialogFragment.TAG)
            }
        }
    }

    override fun onItemClick(weather: Weather) {
    }

    override fun showConnectionProblem() {
        Toast.makeText(applicationContext, resources.getString(R.string.connection_problem), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
        updateButtonMain.visibility = View.INVISIBLE
        recyclerViewMain.isEnabled = false
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
        updateButtonMain.visibility = View.VISIBLE
        recyclerViewMain.isEnabled = true
    }

    override fun onLongItemClick(position: Int, id: Long): Boolean {
        mainActivityPresenter.deleteWeather(id)
        recyclerAdapter.removeItem(position)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityPresenter.detach()
    }
}
