package br.com.igorbag.githubsearch.ui.interfaces

interface IMainActivityPresenter {
    fun onCreate()
    fun onResume()
    fun setUpRetrofit()
    fun getAllReposByUserName(user: String)
    fun saveUserLocal(user:String)
    fun funOpenBrowser(urlRepository: String)
    fun shareRepositoryLink(urlRepository: String)
}