package ua.ck.zabochen.devlifeapp.data.network

interface AppNetwork {
    suspend fun getLatestEntries(page: Int): String?
}