package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bridge.androidtechnicaltest.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar(R.string.pupilList)


        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            fm.beginTransaction()
                    .add(R.id.container, PupilListFragment())
                    .commit()
        }

        mainActivityViewModel.getContentObservable().observe(this, LiveDataEventObserver { navigate(it) })


     /*   fb_add_pupil.setOnClickListener {
            val fm = supportFragmentManager
            fm.beginTransaction()
                    .add(R.id.container, PupilListFragment())
                    .commit()
        }*/
    }

    private fun navigate(it: MainActivityViewModel.Navigation) {
        when (it) {
            is MainActivityViewModel.Navigation.ToPupilDetails -> {
                val fm = supportFragmentManager
                fm.beginTransaction().replace(R.id.container,PupilDetailFragment.newInstance(it.pupil))
                        .commit()

            }
        }

    }
    private fun setupActionBar(@StringRes titleId: Int) {
        setSupportActionBar(toolbar_pupil_list)
        supportActionBar?.run {
            applyToolbarUp(showTitle = true)
            setTitle(titleId)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_reset) {
            TODO("Implement actions")
        }
        return super.onOptionsItemSelected(item)
    }
}