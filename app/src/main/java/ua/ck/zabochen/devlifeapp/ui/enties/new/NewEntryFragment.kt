package ua.ck.zabochen.devlifeapp.ui.enties.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_entry_new.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.data.network.AppNetworkImpl
import ua.ck.zabochen.devlifeapp.data.parser.AppParserImpl
import ua.ck.zabochen.devlifeapp.ui.base.BaseFragment
import java.io.IOException

// TODO: AnkoLogger
class NewEntryFragment : BaseFragment(), AnkoLogger {

    private val newEntryAdapter: NewEntryAdapter by lazy { NewEntryAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_entry_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        networkRequest(0)
    }

    private fun setUi() {
        // RecyclerView
        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.divider_recyclerview)!!)

        fragmentEntryNew_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@NewEntryFragment.activity)
            addItemDecoration(dividerItemDecoration)
            adapter = newEntryAdapter
        }
    }

    private fun networkRequest(page: Int) = launch {
        try {
            val asyncEntryList = async(Dispatchers.IO) { AppNetworkImpl(AppParserImpl()).getNewEntries(page) }
            this@NewEntryFragment.newEntryAdapter.setNewEntryList(asyncEntryList.await())
        } catch (e: IOException) {
            info { e.printStackTrace() }
        }
    }
}