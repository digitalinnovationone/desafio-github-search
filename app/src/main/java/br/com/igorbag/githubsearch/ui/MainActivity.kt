package br.com.igorbag.githubsearch.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.domain.Repository

class MainActivity : AppCompatActivity() {

  lateinit var userName: EditText
  lateinit var btnConfirm: Button
  lateinit var repoList: RecyclerView
  lateinit var githubApi: GitHubService

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupView()
    setupListeners()
    showUserName()
    setupRetrofit()
    getAllReposByUserName()
  }

  fun setupView() {
    userName = findViewById(R.id.et_user_name)
    btnConfirm = findViewById(R.id.btn_confirm)
    repoList = findViewById(R.id.rv_repo_list)
  }

  private fun setupListeners() {
    btnConfirm.setOnClickListener {
      saveUserLocal()
    }
  }
  private fun saveUserLocal() {
    val inputName = userName.text.toString()
    val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

    with (sharedPref.edit()) {
      putString(getString(R.string.saved_name), inputName)
      apply()
    }
  }

  private fun showUserName() {
    val sharedPref = getPreferences(Context.MODE_PRIVATE)
    val saved_Name = sharedPref.getString(getString(R.string.saved_name), "")
    userName.setText(saved_Name)
  }

  //Metodo responsavel por fazer a configuracao base do Retrofit
  fun setupRetrofit() {
    /*
       @TODO 5 -  realizar a Configuracao base do retrofit
       Documentacao oficial do retrofit - https://square.github.io/retrofit/
       URL_BASE da API do  GitHub= https://api.github.com/
       lembre-se de utilizar o GsonConverterFactory mostrado no curso
    */
  }

  //Metodo responsavel por buscar todos os repositorios do usuario fornecido
  fun getAllReposByUserName() {
    // TODO 6 - realizar a implementacao do callback do retrofit e chamar o metodo setupAdapter se retornar os dados com sucesso
  }

  // Metodo responsavel por realizar a configuracao do adapter
  fun setupAdapter(list: List<Repository>) {
    /*
        @TODO 7 - Implementar a configuracao do Adapter , construir o adapter e instancia-lo
        passando a listagem dos repositorios
     */
  }


  // Metodo responsavel por compartilhar o link do repositorio selecionado
  // @Todo 11 - Colocar esse metodo no click do share item do adapter
  fun shareRepositoryLink(urlRepository: String) {
    val sendIntent: Intent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, urlRepository)
      type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
  }

  // Metodo responsavel por abrir o browser com o link informado do repositorio

  // @Todo 12 - Colocar esse metodo no click item do adapter
  fun openBrowser(urlRepository: String) {
    startActivity(
      Intent(
        Intent.ACTION_VIEW,
        Uri.parse(urlRepository)
      )
    )

  }

}