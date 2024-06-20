package com.example.ahorra
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorra.databinding.ActivityAcercaDeNosotrosBinding

class AcercaDeNosotros: AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeNosotrosBinding
    private lateinit var listViewAdapter: ExpandibleListViewAdapter
    private lateinit var chapterList: List<String>
    private lateinit var topicList: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca_de_nosotros)
        binding = ActivityAcercaDeNosotrosBinding.inflate(layoutInflater)

        showlist()

        listViewAdapter = ExpandibleListViewAdapter(this, chapterList, topicList)
        listView.setAdapter(listViewAdapter)

    }

    private fun showlist() {
        chapterList = ArrayList()
        topicList = HashMap()

        (chapterList as ArrayList<String>).add("chapter 1")
        (chapterList as ArrayList<String>).add("chapter 2")
        (chapterList as ArrayList<String>).add("chapter 3")
        (chapterList as ArrayList<String>).add("chapter 4")
        (chapterList as ArrayList<String>).add("chapter 5")

        val topic1 : MutableList<String> = ArrayList()
        topic1.add("Topic 1")
        topic1.add("Topic 2")
        topic1.add("Topic 3")

        val topic2 : MutableList<String> = ArrayList()
        topic2.add("Topic 1")
        topic2.add("Topic 2")
        topic2.add("Topic 3")

        val topic3 : MutableList<String> = ArrayList()
        topic3.add("Topic 1")
        topic3.add("Topic 2")
        topic3.add("Topic 3")

        val topic4 : MutableList<String> = ArrayList()
        topic4.add("Topic 1")
        topic4.add("Topic 2")
        topic4.add("Topic 3")

        topicList[chapterList[0]] = topic1
        topicList[chapterList[1]] = topic2
        topicList[chapterList[2]] = topic3
        topicList[chapterList[3]] = topic4



        val btn: Button = findViewById(R.id.buttonSugerencia)
        btn.setOnClickListener{
            val intent: Intent = Intent(this, pantalla2_acercadenosotros::class.java)
            startActivity(intent)


        val btn: Button = findViewById(R.id.buttonPregunta)
        btn.setOnClickListener{
            val intent: Intent = Intent(this, enviarPregunta_acercadenosotros::class.java)
            startActivity(intent)
        }


    }

}