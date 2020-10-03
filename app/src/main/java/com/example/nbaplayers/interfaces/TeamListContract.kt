package com.example.nbaplayers.interfaces

import com.example.nbaplayers.utils.FilterTypes
import com.example.nbaplayers.models.Team

interface TeamListContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(teamsList: ArrayList<Team>?)
            fun onFailure(t: Throwable?)
        }

        fun getTeamsList(onFinishedListener: OnFinishedListener?)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToView(teamsList: ArrayList<Team>?)
        fun onResponseFailure(throwable: Throwable?)
        fun filterTeams(teamsList: List<Team>?)
    }

    interface Presenter {
        fun requestDataFromServer()
        fun filterTeams(events: FilterTypes)
    }
}
