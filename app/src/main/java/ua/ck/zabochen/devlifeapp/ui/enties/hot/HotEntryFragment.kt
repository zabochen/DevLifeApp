package ua.ck.zabochen.devlifeapp.ui.enties.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.ui.base.BaseFragment

class HotEntryFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_entry_hot, container, false)
    }

}