package com.guagua.googletrendsvisualize.googletrends

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.guagua.googletrendsvisualize.R
import kotlinx.android.synthetic.main.fragment_adjust_row_and_column.*

class RowColumnDialog(context: Context): Dialog(context), View.OnClickListener {

    lateinit var presenter: GoogleTrendsContract.Presenter

    constructor(context: Context, presenter: GoogleTrendsContract.Presenter) : this(context){
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_adjust_row_and_column)
        row_col_cancel.setOnClickListener(this)
        set.setOnClickListener(this)

        row.setText(presenter.getRow().toString())
        col.setText(presenter.getColumn().toString())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.set -> {
                val row = row.text.toString().toInt()
                val column = col.text.toString().toInt()
                presenter.setRowAndCol(row, column)
                dismiss()
            }
            R.id.row_col_cancel -> {
                dismiss()
            }
        }
    }

}