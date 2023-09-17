package br.com.igorbag.githubsearch.ui.presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.interfaces.IMainActivityPresenter
import br.com.igorbag.githubsearch.ui.interfaces.IMainActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityPresenter(private val context:Context, private val view: IMainActivityView) : IMainActivityPresenter {

    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    private lateinit var githubApi: GitHubService

    override fun onCreate() {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        view.showUserName()
        setUpRetrofit()
    }

    override fun onResume() {

    }

    override fun setUpRetrofit() {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        githubApi = retrofit.create(GitHubService::class.java)

    }

    override fun getAllReposByUserName(user: String) {
        view.showProgressBar()

        githubApi.getAllRepositoriesByUser(user).enqueue(object: Callback<List<Repository>>{
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        view.setUpAdapter(it)
                        view.showProgressBar()
                        Toast.makeText(context, "Repositórios encontrados: ${it.size}", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "Corpo Vazio", Toast.LENGTH_SHORT).show()
                    view.showProgressBar()
                }
            }
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Toast.makeText(context, "Erro na Conexão com a Api", Toast.LENGTH_SHORT).show()
                view.showProgressBar()
            }
        })
    }

    override fun saveUserLocal(user: String) {
        editor.putString(context.getString(R.string.nome_usuario), user)
        editor.apply()
    }

    override fun funOpenBrowser(urlRepository: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlRepository)
            )
        )

    }

    override fun shareRepositoryLink(urlRepository: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}