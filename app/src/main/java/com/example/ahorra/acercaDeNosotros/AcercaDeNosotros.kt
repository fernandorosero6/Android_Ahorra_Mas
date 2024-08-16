package com.example.ahorra.acercaDeNosotros

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahorra.ExpandibleListViewAdapter
import com.example.ahorra.databinding.FragmentAcercaDeNosotrosBinding
import com.example.ahorra.enviarPregunta_acercadenosotros
import com.example.ahorra.pantalla2_acercadenosotros

class AcercaDeNosotros : Fragment() {
    private var _binding: FragmentAcercaDeNosotrosBinding? = null
    private val binding get() = _binding!!
    private lateinit var listViewAdapter: ExpandibleListViewAdapter
    private lateinit var chapterList: List<String>
    private lateinit var topicList: HashMap<String, List<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAcercaDeNosotrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showlist()

        listViewAdapter = ExpandibleListViewAdapter(requireContext(), chapterList, topicList)
        binding.listView.setAdapter(listViewAdapter)

        setupButtons()
    }

    private fun showlist() {
        chapterList = ArrayList()
        topicList = HashMap()

        (chapterList as ArrayList<String>).add("chapter 1")
        (chapterList as ArrayList<String>).add("chapter 2")
        (chapterList as ArrayList<String>).add("chapter 3")
        (chapterList as ArrayList<String>).add("chapter 4")
        (chapterList as ArrayList<String>).add("chapter 5")

        val topic1: MutableList<String> = ArrayList()
        topic1.add("Topic 1")
        topic1.add("Topic 2")
        topic1.add("Topic 3")

        val topic2: MutableList<String> = ArrayList()
        topic2.add("Topic 1")
        topic2.add("Topic 2")
        topic2.add("Topic 3")

        val topic3: MutableList<String> = ArrayList()
        topic3.add("Topic 1")
        topic3.add("Topic 2")
        topic3.add("Topic 3")

        val topic4: MutableList<String> = ArrayList()
        topic4.add("Topic 1")
        topic4.add("Topic 2")
        topic4.add("Topic 3")

        topicList[chapterList[0]] = topic1
        topicList[chapterList[1]] = topic2
        topicList[chapterList[2]] = topic3
        topicList[chapterList[3]] = topic4
    }

    private fun setupButtons() {
        binding.buttonSugerencia.setOnClickListener {
            val intent = Intent(requireContext(), pantalla2_acercadenosotros::class.java)
            startActivity(intent)
        }

        binding.buttonPregunta.setOnClickListener {
            val intent = Intent(requireContext(), enviarPregunta_acercadenosotros::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
