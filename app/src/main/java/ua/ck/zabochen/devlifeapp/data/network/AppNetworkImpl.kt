package ua.ck.zabochen.devlifeapp.data.network

import org.jetbrains.anko.AnkoLogger

class AppNetworkImpl : AppNetwork, AnkoLogger {

    override suspend fun getLatestEntries(page: Int): String? {
        return NetworkService().getLatestEntry(page).execute().body()
    }
}