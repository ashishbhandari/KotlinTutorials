package com.androidifygeek

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTextView.setOnClickListener {
            //            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, {
//                setTextAfterDelay(2, "Hello from a coroutine!")


            val startTime = System.currentTimeMillis()
            val job = GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) {
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        launch(Dispatchers.Main) {
                            mainTextView.text = i++.toString()
                        }
                        nextPrintTime += 1000L
                    }
                }
            })
//            })
        }

//        mainTextView.setOnClickListener {
//            GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, {
//                job.cancelAndJoin()
//                mainTextView.text = "Cancelled"
//            })
//        }

    }


    /**
     * A suspending function is just a regular Kotlin function with an additional suspend modifier
     * which indicates that the function can suspend the execution of a coroutine
     */
    private suspend fun setTextAfterDelay(seconds: Long, s: String) {
        delay(TimeUnit.SECONDS.toMillis(seconds))
        mainTextView.text = s
    }
}
