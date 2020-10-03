package com.example.nbaplayers.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.LocalDataStorage
import com.example.nbaplayers.R
import com.example.nbaplayers.presenter.TeamsPresenter
import com.example.nbaplayers.adepter.TeamsAdapter
import com.example.nbaplayers.interfaces.ItemClickListeners
import com.example.nbaplayers.interfaces.TeamListContract
import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.FilterTypes
import kotlinx.android.synthetic.main.main.*


class TeamsActivity : AppCompatActivity(), TeamListContract.View, ItemClickListeners {
    //properties
    private lateinit var presenter: TeamsPresenter
    private lateinit var teamsAdapter: TeamsAdapter

    // creation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setUpActionBar()
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.filter_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortByAplhabets -> {
                presenter.filterTeams(FilterTypes.SortByAlpahbets)
                return true
            }
            R.id.sortByWins -> {
                presenter.filterTeams(FilterTypes.sortedByWins)
                return true
            }
            R.id.sortByLosses -> {
                presenter.filterTeams(FilterTypes.sortedByLosses)
                return true
            }
        }
        return true
    }

    // helpers
    private fun setUpActionBar() {
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val textView = TextView(this)
        textView.text = getString(R.string.app_name)
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.gravity = Gravity.CENTER
        textView.setTextColor(resources.getColor(android.R.color.white))
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.customView = textView
    }

    private fun initUI() {
        teamsAdapter = TeamsAdapter(this)
        teamsListRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        teamsListRV.adapter = teamsAdapter
        presenter = TeamsPresenter(this)
        presenter.requestDataFromServer()
    }

    // implementation
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setDataToView(teamsList: ArrayList<Team>?) {
        if (teamsList != null) teamsAdapter.setAdapterData(teamsList!!)
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Toast.makeText(this,R.string.fail_response,Toast.LENGTH_SHORT).show()
    }

    override fun filterTeams(teamsList: List<Team>?) {
        val filterList: ArrayList<Team> = arrayListOf()
        teamsList?.forEach { filterList.add(it) }
        teamsAdapter.setAdapterData(filterList)
    }

    override fun onTeamsItemClicked(id: Int) {
        val intent = Intent(this, PlayerActivity::class.java)
            .apply { putExtra("TeamId", id) }
        startActivity(intent)
    }
}