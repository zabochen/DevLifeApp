package ua.ck.zabochen.devlifeapp.ui.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import ua.ck.zabochen.devlifeapp.R
import ua.ck.zabochen.devlifeapp.data.network.AppNetworkImpl
import ua.ck.zabochen.devlifeapp.ui.base.BaseActivity
import java.io.IOException

class MainActivity : BaseActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        networkRequest(0)
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // Button
        button.setOnClickListener {
            networkRequest(1)
        }

    }

    private fun networkRequest(page: Int) = launch {
        try {
            val asyncBody = async(Dispatchers.IO) { AppNetworkImpl().getLatestEntries(page) }
            val body = asyncBody.await()

            val document: Document = Jsoup.parse(body)

            val entryList: Elements = document.select("div[class=entry]")

            for (i in 0 until entryList.size) {
                // Author
                val author: String = entryList.select("div[class=comment]")
                    .select("a")
                    .eq(i)
                    .text()
                info { "Author: $author" }

                // Description
                val description: String = entryList.select("div[class=code]")
                    .select("span[class=value]")
                    .eq(i)
                    .text()
                info { "Description: $description" }

                // Gif
                val gifUrl: String = "https:" +
                        entryList.select("div[class=image]")
                            .select("div[class=image]")
                            .select("div[class=gif]")
                            .select("backup_img")
                            .eq(i)
                            .attr("src")
                info { "Gif Url: $gifUrl" }
            }

        } catch (e: IOException) {
            info { e.printStackTrace() }
        }
    }


}
