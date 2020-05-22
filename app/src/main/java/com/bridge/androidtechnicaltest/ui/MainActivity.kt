package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.ui.extensions.LiveDataEventObserver
import com.bridge.androidtechnicaltest.ui.extensions.applyToolbarUp
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
                    .add(R.id.container, PupilListFragment(), PupilListFragment.TAG)
                    .addToBackStack(PupilListFragment.TAG)
                    .commit()

        }

        mainActivityViewModel.getContentObservable().observe(this, LiveDataEventObserver { navigate(it) })


        fb_add_pupil.setOnClickListener {
            val fm = supportFragmentManager
            fm.beginTransaction().replace(R.id.container, PupilAddFragment())
                    .addToBackStack(PupilAddFragment.TAG)
                    .commit()
        }
    }

    private fun navigate(it: MainActivityViewModel.Navigation) {
        when (it) {
            is MainActivityViewModel.Navigation.ToPupilDetails -> {
                val fm = supportFragmentManager
                fm.beginTransaction().replace(R.id.container, PupilDetailFragment.newInstance(it.pupil))
                        .addToBackStack(PupilDetailFragment.TAG)
                        .commit()

            }
        }

    }

    private fun setupActionBar(@StringRes titleId: Int) {
        setSupportActionBar(toolbar_pupil_list)
        supportActionBar?.run {
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