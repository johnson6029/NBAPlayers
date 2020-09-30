package com.example.nbaplayers.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.DataManager
import com.example.nbaplayers.Events
import com.example.nbaplayers.R
import com.example.nbaplayers.TeamsPresenter
import com.example.nbaplayers.adepter.TeamsAdapter
import com.example.nbaplayers.interfaces.ItemClickListeners
import com.example.nbaplayers.interfaces.TeamListContract
import com.example.nbaplayers.models.Team
import kotlinx.android.synthetic.main.main.*


class TeamsActivity : AppCompatActivity(), TeamListContract.View, ItemClickListeners {
    private lateinit var presenter: TeamsPresenter
    private lateinit var teamsAdapter: TeamsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        teamsAdapter = TeamsAdapter(this)
        teamsListRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        teamsListRV.adapter = teamsAdapter
        presenter = TeamsPresenter(this)
        presenter.requestDataFromServer()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setDataToRecyclerView(teamsList: ArrayList<Team>?) {
        if (teamsList != null) DataManager.setTeamsData(teamsList)
        presenter.filterTeams(Events.Alpahbetic)
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Log.d("Data", throwable!!.localizedMessage)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.filter_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortByAplhabets -> {
                presenter.filterTeams(Events.Alpahbetic)
                return true
            }
            R.id.sortByWins -> {
                presenter.filterTeams(Events.sortedByWins)
                return true
            }
            R.id.sortByLosses -> {
                presenter.filterTeams(Events.sortedByLosses)
                return true
            }
        }
        return true
    }
}