package com.example.nbaplayers.adepter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.ColorMatrixColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.R
import com.example.nbaplayers.models.Player
import java.util.*

class PlayerAdapter(private val playerList: ArrayList<Player> ,val context: Context) : RecyclerView.Adapter<PlayerViewHolder>() {

    // implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_row, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val androidColors: IntArray = context.resources.getIntArray(R.array.androidcolors)
        val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
        val player: Player = playerList[position]
        holder.textFirstName.text = player.firstName
        holder.textLastName.text = player.lastName
        holder.textPosition.text = player.position
        holder.textNumber.text = player.number.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.tv_profile.setBackgroundTintList(ColorStateList.valueOf(randomAndroidColor));
        }
        holder.tv_profile.text = player.firstName.substring(0,1).toUpperCase() + player.lastName.substring(0,1).toUpperCase()

    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}
// inner class
class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textFirstName: TextView = itemView.findViewById(R.id.textFirstName)
    val tv_profile: TextView = itemView.findViewById(R.id.tv_profile)
    val textLastName: TextView = itemView.findViewById(R.id.textLastName)
    val textPosition: TextView = itemView.findViewById(R.id.textPlayerPosition)
    val textNumber: TextView = itemView.findViewById(R.id.textPlayerNumber)
}