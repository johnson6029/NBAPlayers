package com.example.nbaplayers.adepter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.R
import com.example.nbaplayers.models.Player

class PlayerAdapter(private val playerList: ArrayList<Player>) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_row, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player: Player = playerList[position]
        holder.textFirstName.text = player.firstName
        holder.textLastName.text = player.lastName
        holder.textPosition.text = player.position
        holder.textNumber.text = player.number.toString()
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}

class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textFirstName: TextView = itemView.findViewById(R.id.textFirstName)
    val textLastName: TextView = itemView.findViewById(R.id.textLastName)
    val textPosition: TextView = itemView.findViewById(R.id.textPlayerPosition)
    val textNumber: TextView = itemView.findViewById(R.id.textPlayerNumber)
}