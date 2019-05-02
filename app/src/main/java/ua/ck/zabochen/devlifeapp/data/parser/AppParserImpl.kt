package ua.ck.zabochen.devlifeapp.data.parser

import org.jetbrains.anko.AnkoLogger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import ua.ck.zabochen.devlifeapp.data.model.Entry
import ua.ck.zabochen.devlifeapp.data.model.EntryImpl

class AppParserImpl : AppParser, AnkoLogger {

    override fun getEntryList(text: String): List<Entry> {
        val document: Document = Jsoup.parse(text)
        val rawEntryList: Elements = document.select("div[class=entry]")

        // Fill the list
        val entryList: ArrayList<Entry> = arrayListOf()

        for (i in 0 until rawEntryList.size) {

            // Id
            val rawId: String = rawEntryList.select("a[class=entryLink]")
                .eq(i)
                .text()
            val id: Int = rawId.substring(rawId.indexOf("Entry") + 5).toInt()
            // info { "Id: $id" }

            // Author
            val author: String = rawEntryList.select("div[class=comment]")
                .select("a")
                .eq(i)
                .text()
            // info { "Author: $author" }

            // Date
            val dateRaw: String = rawEntryList.select("div[class=comment]")
                .eq(i)
                .text()

            val date: String = dateRaw.substring(dateRaw.indexOf("Date:") + 6, dateRaw.indexOf(" */"))
            // info { "Date: $date" }

            // Description
            val description: String = rawEntryList.select("div[class=code]")
                .select("span[class=value]")
                .eq(i)
                .text()
            // info { "Description: $description" }

            // Preview Image Url
            val previewImageUrl: String = "https:" +
                    rawEntryList.select("div[class=image]")
                        .select("div[class=image]")
                        .select("div[class=gif]")
                        .select("video")
                        .eq(i)
                        .attr("poster")
            // info { "Preview Image Url: $previewImageUrl" }

            // Gif Url
            val gifUrl: String = "https:" +
                    rawEntryList.select("div[class=image]")
                        .select("div[class=image]")
                        .select("div[class=gif]")
                        .select("backup_img")
                        .eq(i)
                        .attr("src")
            // info { "Gif Url: $gifUrl" }

            // Filling
            entryList.add(
                EntryImpl(
                    id = id,
                    author = author,
                    date = date,
                    description = description,
                    previewImageUrl = previewImageUrl,
                    gifUrl = gifUrl
                )
            )
        }
        return entryList
    }
}