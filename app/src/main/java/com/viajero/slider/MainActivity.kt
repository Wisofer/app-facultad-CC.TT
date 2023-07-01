package com.viajero.slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var layoutDots: LinearLayout
    lateinit var textInfo: TextView
    lateinit var imageList: ArrayList<Int>
    lateinit var viewPagerSlider: ViewPager2
    private lateinit var adapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var textList: ArrayList<String>



        textList = ArrayList()
        textList.add("La Facultad de Ciencias y Tecnología de la Universidad Nacional Autónoma de Nicaragua, León (UNAN-León) es una institución educativa dedicada a la formación académica en diversas áreas científicas y tecnológicas. Esta facultad se encuentra ubicada en la ciudad de León, Nicaragua.")
        textList.add("La UNAN-León es una de las principales instituciones de educación superior en Nicaragua y tiene como objetivo brindar una educación de calidad en ciencias y tecnología para contribuir al desarrollo científico, tecnológico y social del país.")
        textList.add("La Facultad de Ciencias y Tecnología de la UNAN-León ofrece una variedad de programas académicos, incluyendo carreras de grado y posgrado en áreas como ingeniería, ciencias de la computación, física, matemáticas, química y biología, entre otras.")
        textList.add("Además de la formación académica, la facultad también se involucra en investigaciones científicas y proyectos tecnológicos en colaboración con otras instituciones y empresas tanto a nivel nacional como internacional.")
        textList.add("La UNAN-León, a través de su Facultad de Ciencias y Tecnología, busca fomentar el conocimiento, la innovación y el desarrollo tecnológico, formando profesionales capaces de enfrentar los desafíos del mundo actual en los campos de la ciencia y la tecnología.")


        var buttonSkip = findViewById<Button>(R.id.btnSkip)
        viewPagerSlider = findViewById(R.id.mViewPager2)
        layoutDots = findViewById(R.id.layoutDots)
        textInfo = findViewById(R.id.textInfo)

        imageList = ArrayList()

        imageList.add(R.drawable.img_uno)
        imageList.add(R.drawable.img_dos)
        imageList.add(R.drawable.img_tres)
        imageList.add(R.drawable.img_cuatro)
        imageList.add(R.drawable.img_seis)
        imageList.add(R.drawable.img_siete)
        imageList.add(R.drawable.img_ocho)
        imageList.add(R.drawable.img_nueve)


        adapter = SliderAdapter(this, imageList)
        viewPagerSlider.adapter = adapter
        addDotsLayout()
        setIndicator(0)

        viewPagerSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setIndicator(position)
                textInfo.text = textList[position]


            }
        })


    }

    private fun setIndicator(index: Int) {
        val childCount = layoutDots.childCount
        for (i in 0 until childCount) {
            val imageView = layoutDots[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.select_dot
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.unselect_dot
                    )
                )
            }
        }
    }

    private fun addDotsLayout() {
        val indicator = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT
        )
        layoutParams.setMargins(8, 8, 8, 0)

        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.unselect_dot
                    )
                )
                this?.layoutParams = layoutParams
            }
            layoutDots.addView(indicator[i])
        }
    }
}