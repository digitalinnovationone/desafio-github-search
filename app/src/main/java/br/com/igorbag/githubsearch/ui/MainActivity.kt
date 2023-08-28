package br.com.igorbag.githubsearch.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.data.RepositoryApiHelper
import br.com.igorbag.githubsearch.databinding.ActivityMainBinding
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.adapter.RepositoryAdapter
import br.com.igorbag.githubsearch.utils.LoadingStatus
import br.com.igorbag.githubsearch.utils.SharedPrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RepositoryApiHelper.ApiListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefsManager: SharedPrefsManager
    private lateinit var repositoryApiHelper: RepositoryApiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefsManager = SharedPrefsManager(this)
        repositoryApiHelper = RepositoryApiHelper(this)

        setupListeners()
        showUserName()
        status()
    }

    private fun setupListeners() {
        binding.btnConfirmar.setOnClickListener {
            val username = binding.etNomeUsuario.text.toString()
            repositoryApiHelper.getAllReposByUserName(username)
            saveUserLocal(username)
        }
    }

    private fun saveUserLocal(username: String) {
        sharedPrefsManager.saveUserName(username)
    }

    private fun showUserName() {
        val savedUsername = sharedPrefsManager.getUserName()
        binding.etNomeUsuario.setText(savedUsername)
    }

    private fun status() {
        // Update your status UI using binding
    }

    override fun showLoading() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.pbProgress.visibility = View.GONE
    }

    override fun setupAdapter(list: List<Repository>) {
        val adapter = RepositoryAdapter(list)
        binding.rvListaRepositories.adapter = adapter
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}