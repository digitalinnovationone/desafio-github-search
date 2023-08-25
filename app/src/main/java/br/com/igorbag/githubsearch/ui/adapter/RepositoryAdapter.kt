package br.com.igorbag.githubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    // Cria uma nova view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteúdo da view e troca pela informação de item de uma lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.nomeRepositorio.text = repository.name

        holder.btnShare.setOnClickListener {
            shareRepositoryLink(repository.htmlUrl, holder.itemView.context)
        }

        holder.itemView.setOnClickListener {
            openBrowser(repository.htmlUrl, holder.itemView.context)
        }
    }

    // Pega a quantidade de repositórios da lista
    override fun getItemCount(): Int = repositories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeRepositorio: TextView = view.findViewById(R.id.tv_preco)
        val btnShare: ImageView = view.findViewById(R.id.iv_favorite)
    }

    // Método responsável por compartilhar o link do repositório selecionado
    private fun shareRepositoryLink(urlRepository: String, context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    // Método responsável por abrir o browser com o link informado do repositório
    private fun openBrowser(urlRepository: String, context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlRepository))
        context.startActivity(browserIntent)
    }
}