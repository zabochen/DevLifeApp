package ua.ck.zabochen.devlifeapp.data.model

interface Entry {
    val id: Int
    val author: String
    val date: String
    val description: String
    val previewImageUrl: String
    val gifUrl: String
}