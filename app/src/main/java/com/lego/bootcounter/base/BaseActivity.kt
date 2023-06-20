package com.lego.bootcounter.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<Binding : ViewBinding, ViewModel : BaseViewModel> :
    AppCompatActivity() {
    protected abstract val viewModel: ViewModel
    lateinit var binding: Binding

    private lateinit var connectionErrorSnackBar: Snackbar
    private lateinit var permissionAlertDialog: AlertDialog.Builder

    abstract fun setupViews(savedInstanceState: Bundle?)

    abstract fun getViewBinding(): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupViews(savedInstanceState)
    }

}