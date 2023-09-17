package br.com.igorbag.githubsearch.ui.view
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.adapter.RepositoryAdapter
import br.com.igorbag.githubsearch.ui.interfaces.IMainActivityPresenter
import br.com.igorbag.githubsearch.ui.interfaces.IMainActivityView
import br.com.igorbag.githubsearch.ui.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), IMainActivityView {

    private lateinit var presenter : IMainActivityPresenter
    private lateinit var nomeUsuario: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var listaRepositories: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var isProgressBar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainActivityPresenter(this, this)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        presenter.onCreate()
    }

    fun setupView() {
        nomeUsuario = findViewById(R.id.et_nome_usuario)
        btnConfirmar = findViewById(R.id.btn_confirmar)
        listaRepositories = findViewById(R.id.rv_lista_repositories)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun setupListeners() {
        btnConfirmar.setOnClickListener(View.OnClickListener {
            listaRepositories.visibility = View.GONE
            if(nomeUsuario.text.toString().isNotEmpty()){
                presenter.saveUserLocal(nomeUsuario.text.toString())
                presenter.getAllReposByUserName(nomeUsuario.text.toString())
            }
        })
    }

    @Override
    override fun showUserName() {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name) ,Context.MODE_PRIVATE)
        val nome = sharedPreferences.getString(getString(R.string.nome_usuario), "")
        val editableNome = Editable.Factory.getInstance().newEditable(nome)
        nomeUsuario.text = editableNome
    }

    override fun setUpAdapter(list: List<Repository>) {
        val adapter = RepositoryAdapter(list)
        listaRepositories.adapter = adapter
        listaRepositories.visibility = View.VISIBLE

        adapter.apply {
            repositoryItemLister = {repository ->
                presenter.funOpenBrowser(repository.htmlUrl)
            }
            btnShareLister = {repository ->
                presenter.shareRepositoryLink(repository.htmlUrl)
            }
        }
    }
    override fun showProgressBar() {
        isProgressBar = !isProgressBar
        if(isProgressBar) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }
}