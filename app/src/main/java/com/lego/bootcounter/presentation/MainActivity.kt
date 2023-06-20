package com.lego.bootcounter.presentation

import android.os.Bundle
import com.lego.bootcounter.R
import com.lego.bootcounter.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.lego.bootcounter.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.textView.text = getString(R.string.no_boot)

        viewModel.counter.observe(this) {

        }
    }

}