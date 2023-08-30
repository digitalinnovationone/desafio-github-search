package br.com.igorbag.githubsearch.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.databinding.RepositoryItemBinding
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int = repositories.size

    inner class ViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.tvPreco.text = repository.name

            binding.ivFavorite.setOnClickListener {
                shareRepositoryLink(repository.htmlUrl, binding.root.context)
            }

            binding.root.setOnClickListener {
                openBrowser(repository.htmlUrl, binding.root.context)
            }
        }
    }

    private fun shareRepositoryLink(urlRepository: String, context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    private fun openBrowser(urlRepository: String, context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlRepository))
        context.startActivity(browserIntent)
    }
}