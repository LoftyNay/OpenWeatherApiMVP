package com.ltn.openweather.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ltn.openweather.App
import com.ltn.openweather.R
import com.ltn.openweather.Utils
import com.ltn.openweather.adapters.WeatherAdapter
import com.ltn.openweather.model.Weather
import com.ltn.openweather.ui.fragments.presenter.CityAddDialogFragmentPresenter
import com.ltn.openweather.ui.fragments.view.AddCityFragmentView
import kotlinx.android.synthetic.main.fragment_add_city.*
import javax.inject.Inject


class CityAddDialogFragment : MvpAppCompatDialogFragment(), AddCityFragmentView, View.OnClickListener, TextWatcher,
    WeatherAdapter.OnCardItemClickListener {

    @InjectPresenter
    lateinit var cityAddDialogFragmentPresenter: CityAddDialogFragmentPresenter

    @Inject
    lateinit var utils: Utils

    private lateinit var recyclerAdapter: WeatherAdapter

    companion object {
        const val TAG = "CityAddDialogFragment"
        fun getInstance(): CityAddDialogFragment {
            return CityAddDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this)
        return layoutInflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityAddDialogFragmentPresenter.attach()
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        closeDialogButton.setOnClickListener(this)
        findCityEdit.addTextChangedListener(this)
        utils.showSoftKeyboard(findCityEdit)
    }

    private fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAddFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerAdapter = WeatherAdapter(this)
        recyclerView.adapter = recyclerAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.closeDialogButton -> dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
    }

    override fun onItemClick(weather: Weather) {
        cityAddDialogFragmentPresenter.addCityWeather(weather)
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFragment)
    }

    override fun afterTextChanged(s: Editable?) {
        cityAddDialogFragmentPresenter.loadCities(s.toString().toLowerCase().trim())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun showCitiesOnRecycler(citiesWeather: MutableList<Weather>) {
        recyclerAdapter.addAll(citiesWeather)
    }

    override fun showConnectionProblem() {
        Toast.makeText(activity, getString(R.string.connection_problem), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {}
    override fun hideProgress() {}
}