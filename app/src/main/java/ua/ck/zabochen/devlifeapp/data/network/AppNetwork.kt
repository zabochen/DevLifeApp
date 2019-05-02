package ua.ck.zabochen.devlifeapp.data.network

import ua.ck.zabochen.devlifeapp.data.model.Entry

interface AppNetwork {
    suspend fun getNewEntries(page: Int): List<Entry>
}