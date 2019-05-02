package ua.ck.zabochen.devlifeapp.data.parser

import ua.ck.zabochen.devlifeapp.data.model.Entry

interface AppParser {
    fun getEntryList(text: String): List<Entry>
}