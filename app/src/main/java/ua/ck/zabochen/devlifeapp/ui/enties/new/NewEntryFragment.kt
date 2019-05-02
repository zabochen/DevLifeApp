package ua.ck.zabochen.devlifeapp.ui.enties.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.ui.base.BaseFragment

class NewEntryFragment : BaseFragment() {

    private lateinit var unbinder: Unbinder

    @BindView(R.id.fragmentEntryNew_group_loading)
    lateinit var loadingGroup: Group

    @BindView(R.id.fragmentEntryNew_recyclerView)
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_entry_new, container, false).also { view ->
            this.unbinder = ButterKnife.bind(this, view)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDetach() {
        super.onDetach()
        if (this::unbinder.isInitialized) unbinder.unbind()
    }
}