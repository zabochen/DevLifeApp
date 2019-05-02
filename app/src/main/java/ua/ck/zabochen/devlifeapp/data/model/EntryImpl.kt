package ua.ck.zabochen.devlifeapp.data.model

data class EntryImpl(
    override val id: Int,
    override val author: String,
    override val date: String,
    override val description: String,
    override val previewImageUrl: String,
    override val gifUrl: String
) : Entry