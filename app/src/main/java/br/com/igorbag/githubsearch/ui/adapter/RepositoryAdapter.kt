package br.com.igorbag.githubsearch.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.viewHolder.ViewHolder

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<ViewHolder>() {

    var repositoryItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nome.text = repositories[position].name

        holder.cardView.setOnClickListener {
            repositoryItemLister(repositories[position])
            Log.e("Opa", "Opa")
        }

        holder.imgFavorite.setOnClickListener {
            btnShareLister(repositories[position])
            Log.e("Favorite", "Favorite")
        }
    }
    override fun getItemCount(): Int =repositories.size
}


