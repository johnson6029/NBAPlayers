package com.example.nbaplayers.presenter

import com.example.nbaplayers.LocalDataStorage
import com.example.nbaplayers.api.TeamsListModel
import com.example.nbaplayers.interfaces.TeamListContract
import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.FilterTeams
import com.example.nbaplayers.utils.FilterTypes

class TeamsPresenter(private var teamsListView: TeamListContract.View?) :
    TeamListContract.Presenter, TeamListContract.Model.OnFinishedListener {

    private var teamsListModel: TeamListContract.Model? = null

    init {
        teamsListModel = TeamsListModel()
    }

    override fun onFinished(teamsList: ArrayList<Team>?) {
        teamsListView?.setDataToView(teamsList)
        teamsListView?.hideProgress()
        if (teamsList != null) LocalDataStorage.teamsList = teamsList
    }

    override fun onFailure(t: Throwable?) {
        teamsListView?.onResponseFailure(t)
    }

    override fun requestDataFromServer() {
        teamsListView?.showProgress()
        teamsListModel?.getTeamsList(this)
    }

    override fun filterTeams(events: FilterTypes) {
        val teams = LocalDataStorage.teamsList
        val filteredTeams = FilterTeams(teams).filterTeams(events)
        teamsListView?.filterTeams(filteredTeams)
    }
}