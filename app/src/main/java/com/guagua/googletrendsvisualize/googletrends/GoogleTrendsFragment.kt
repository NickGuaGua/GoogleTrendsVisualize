package com.guagua.googletrendsvisualize.googletrends

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.PopupMenu
import com.guagua.googletrendsvisualize.R
import com.guagua.googletrendsvisualize.di.ActivityScoped
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_google_trends.*
import javax.inject.Inject


class GoogleTrendsFragment @Inject constructor() : DaggerFragment(), GoogleTrendsContract.View, PopupMenu.OnMenuItemClickListener {

    private val LOG_TAG = "GoogleTrendsFragment"

    @Inject
    lateinit var presenter: GoogleTrendsContract.Presenter

    init {
//        initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        initPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_google_trends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadTrendsAndRegions()

    }

    fun initPresenter(){
        this.presenter.setView(this)
    }

    override fun showTrends(trends: Array<String>) {
        gridLayout.removeAllViewsInLayout()
        for ( i in 0 until presenter.getRow()){
            for (j in 0 until presenter.getColumn()) {

                Log.d(LOG_TAG, "row: $i   column: $j")
                val rowSpec = GridLayout.spec(i , 1f)
                val columnSpec = GridLayout.spec(j , 1f)
                val layoutParams = GridLayout.LayoutParams(rowSpec, columnSpec)
                layoutParams.height = 0
                layoutParams.width = 0

                gridLayout.addView(GoogleTrendsView(this.context!!, trends), layoutParams)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_google_trends_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        val regions = activity!!.findViewById<View>(R.id.region)
        when(item!!.itemId){
            R.id.region -> showPopup(regions)
            R.id.matrix -> RowColumnDialog(this.context!!, presenter).show()
        }

        return true
    }

    fun showPopup(v: View) {
        val popup = PopupMenu(this.context, v)
        popup.setOnMenuItemClickListener(this)
        val regions = presenter.getRegionsMenu()
        for ( i in 0 until regions.size){
            popup.menu.add(Menu.NONE, i, i, regions[i])
        }
        popup.show()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        presenter.getTrendsInRegion(item!!.itemId)
        return true
    }

    override fun onLoadDataCompleted() {
        loadingBar.visibility = View.GONE
        presenter.getTrendsInRegion(41)
    }

}