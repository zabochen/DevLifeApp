package ua.ck.zabochen.devlifeapp.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.job = SupervisorJob()
    }

    override fun onDetach() {
        super.onDetach()
        if (this::job.isInitialized) job.cancel()
    }
}