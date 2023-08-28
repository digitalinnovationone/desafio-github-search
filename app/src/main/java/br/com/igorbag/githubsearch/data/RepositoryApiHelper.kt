package br.com.igorbag.githubsearch.data


import br.com.igorbag.githubsearch.domain.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryApiHelper(private val listener: ApiListener) {

    private val githubApi = RetrofitService.githubApi

    fun getAllReposByUserName(username: String) {
        listener.showLoading()

        githubApi.getAllRepositoriesByUser(username).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                listener.hideLoading()
                if (response.isSuccessful) {
                    val repositories = response.body()
                    repositories?.let {
                        listener.setupAdapter(it)
                    }
                } else {
                    listener.showError("Erro na chamada à API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                listener.hideLoading()
                listener.showError("Falha na chamada à API: ${t.message}")
            }
        })
    }
        interface ApiListener {
            fun showLoading()
            fun hideLoading()
            fun setupAdapter(list: List<Repository>)
            fun showError(message: String)
        }
    }