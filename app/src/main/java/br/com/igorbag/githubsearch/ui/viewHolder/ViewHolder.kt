package br.com.igorbag.githubsearch.ui.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
     val nome: TextView
     val cardView:CardView
     val imgFavorite:ImageView

    init {
        view.apply {
            nome = findViewById(R.id.nome)
            cardView = findViewById(R.id.cv_car)
            imgFavorite = findViewById(R.id.iv_favorite)
        }
    }
}