package ua.ck.zabochen.devlifeapp.data.network

import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.devlifeapp.data.model.Entry
import ua.ck.zabochen.devlifeapp.data.parser.AppParser

class AppNetworkImpl(private val appParser: AppParser) : AppNetwork, AnkoLogger {

    override suspend fun getNewEntries(page: Int): List<Entry> {
        val response: String = NetworkService().getNewEntry(page).execute().body() ?: ""
        return appParser.getEntryList(response)
    }
}