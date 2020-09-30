package com.example.nbaplayers

import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.Events

object DataManager {
    private var teamsList: ArrayList<Team> = arrayListOf()

    fun setTeamsData(teamsDataList: ArrayList<Team>) {
        teamsList.addAll(teamsDataList)
    }

    fun getTeamsData(): ArrayList<Team> {
        return teamsList
    }

    fun getTeamsDataById(id: Int): Team {
        return teamsList.filter { it.id == id }.map { it }.first()
    }

    fun filter(events: Events): List<Team> {
        return when (events) {
            Events.sortedByWins -> {
                teamsList.sortedByDescending { it.wins }
            }
            Events.Alpahbetic -> {
                teamsList.sortedWith(compareBy { it.fullName })
            }
            Events.sortedByLosses -> {
                teamsList.sortedByDescending { it.losses }
            }
        }
    }
}