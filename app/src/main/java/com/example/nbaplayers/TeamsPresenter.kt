package com.example.nbaplayers

import com.example.nbaplayers.api.TeamsListModel
import com.example.nbaplayers.interfaces.TeamListContract
import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.Events

class TeamsPresenter(private var teamsListView: TeamListContract.View?) :
    TeamListContract.Presenter, TeamListContract.Model.OnFinishedListener {

    private var teamsListModel: TeamListContract.Model? = null

    init {
        teamsListModel = TeamsListModel()
    }

    override fun onFinished(teamsList: ArrayList<Team>?) {
        teamsListView?.setDataToRecyclerView(teamsList)
        teamsListView?.hideProgress()
    }

    override fun onFailure(t: Throwable?) {
        teamsListView?.onResponseFailure(t)
    }

    override fun onDestroy() {
        this.teamsListView = null
    }

    override fun requestDataFromServer() {
        teamsListView?.showProgress()
        teamsListModel?.getMovieList(this)
    }

    override fun filterTeams(events: Events) {
        teamsListView?.filterTeams(DataManager.filter(events))
    }
}