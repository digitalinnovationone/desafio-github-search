package br.com.igorbag.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var repoItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteudo da view e troca pela informacao de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //@TODO 8 -  Realizar o bind do viewHolder

        holder.repoName.text = repositories[position].name

        holder.itemView.setOnClickListener {
            repoItemLister(repositories[position])
        }
        holder.favIcon.setOnClickListener {
            btnShareLister(repositories[position])
        }

    }

    // Pega a quantidade de repositorios da lista
    //@TODO 9 - realizar a contagem da lista
    override fun getItemCount(): Int = repositories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //@TODO 10 - Implementar o ViewHolder para os repositorios

        var repoName: TextView
        val favIcon: AppCompatImageView

        init {
            view.apply {
                repoName = findViewById(R.id.tvRepoName)
                favIcon = findViewById(R.id.ivFavorite)
            }
        }

    }
}


