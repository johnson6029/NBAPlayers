package com.example.nbaplayers.utils

import com.example.nbaplayers.models.Team

class FilterTeams(private val teams: ArrayList<Team>) {

    fun filterTeams(events: FilterTypes): List<Team> {
        return when (events) {
            FilterTypes.sortedByWins -> {
                teams.sortedByDescending { it.wins }
            }
            FilterTypes.SortByAlpahbets -> {
                teams.sortedWith(compareBy { it.fullName })
            }
            FilterTypes.sortedByLosses -> {
                teams.sortedByDescending { it.losses }
            }
        }
    }
}