package com.ext.android_appsizehelpers

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ext.android_app_size_helpers.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1️⃣ Device Info
        findViewById<TextView>(R.id.tvDeviceInfo).apply {
            text = "Screen: ${AppSizeHelper.screenWidth(this@MainActivity)} × " +
                    "${AppSizeHelper.screenHeight(this@MainActivity)} px"
            setTextSizePercent(0.03f)
        }

        // 2️⃣ 50% Width Example
        findViewById<TextView>(R.id.view50).apply {
            setSizePercent(0.5f, 0.06f)
            text = "50% Width View"
            setTextSizePercent(0.03f)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
        }

        // 3️⃣ Aspect Ratio (16:9)
        findViewById<ImageView>(R.id.imgAspect).apply {
            setSizeWithAspectRatio(0.8f, AppSizeHelper.AspectRatio.RATIO_16_9)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_light))
        }

        // 4️⃣ Responsive Text
        findViewById<TextView>(R.id.tvResponsiveText).apply {
            text = "Text =Responsive text"
            setTextSizePercent(0.04f)
        }

        // 5️⃣ Padding Percentage
        findViewById<TextView>(R.id.tvPadding).apply {
            setSizePercent(0.7f)
            setPaddingPercent(0.05f, 0.02f, 0.05f, 0.02f)
            text = "Padding in %"
            setTextSizePercent(0.03f)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_light))
        }

        // 6️⃣ Interactive Resize
        val interactive = findViewById<TextView>(R.id.viewInteractive).apply {
            setSizePercent(0.6f, 0.07f)
            text = "Tap button"
            setTextSizePercent(0.03f)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
        }

        findViewById<Button>(R.id.btnResize).setOnClickListener {
            interactive.setSizePercent(0.85f, 0.1f)
        }
    }
}
