package br.com.igorbag.githubsearch.ui.interfaces

import br.com.igorbag.githubsearch.domain.Repository

interface IMainActivityView {

    fun showUserName();
    fun setUpAdapter(list: List<Repository>);
    fun showProgressBar();
}