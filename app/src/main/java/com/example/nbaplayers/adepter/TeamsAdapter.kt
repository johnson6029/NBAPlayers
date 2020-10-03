package com.example.nbaplayers.adepter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.R
import com.example.nbaplayers.activities.TeamsActivity
import com.example.nbaplayers.models.Team

class TeamsAdapter(
    private val teamsActivity: TeamsActivity,
) : RecyclerView.Adapter<TeamsViewHolder>() {
    // properties
    private val teamsList: ArrayList<Team> = arrayListOf()

    //implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(teamsActivity.applicationContext)
            .inflate(R.layout.item_team_row, parent, false)
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team: Team = teamsList[position]
        Log.e("Data", team.fullName)
        holder.textViewLosses.text = team.losses.toString()
        holder.textViewWins.text = team.wins.toString()
        holder.textTeamName.text = team.fullName

        holder.itemView.setOnClickListener {
            teamsActivity.onTeamsItemClicked(team.id)
        }
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    // helpers
    fun setAdapterData(teams: ArrayList<Team>) {
        teamsList.clear()
        teamsList.addAll(teams)
        this.notifyDataSetChanged()
    }
}

// inner class
class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textTeamName: TextView = itemView.findViewById(R.id.teamName)
    val textViewWins: TextView = itemView.findViewById(R.id.textViewWins)
    val textViewLosses: TextView = itemView.findViewById(R.id.textViewLosses)
}