package com.example.piatnachki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var arrayText = arrayOf(arrayOf<TextView>())
    var numbers = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayText = arrayOf(
            arrayOf<TextView>(findViewById(R.id.textView2), findViewById(R.id.textView4), findViewById(R.id.textView5), findViewById(R.id.textView6)),
            arrayOf<TextView>(findViewById(R.id.textView11), findViewById(R.id.textView12), findViewById(R.id.textView13), findViewById(R.id.textView14)),
            arrayOf<TextView>(findViewById(R.id.textView7), findViewById(R.id.textView8), findViewById(R.id.textView9), findViewById(R.id.textView10)),
            arrayOf<TextView>(findViewById(R.id.textView15), findViewById(R.id.textView16), findViewById(R.id.textView17),findViewById(R.id.textView))
        )

        var count = 1
        for ((i, raw) in arrayText.withIndex()) {
            for ((j, cell) in arrayText[i].withIndex()) {
                if (count == 16)
                    cell.text = ""
                else
                    cell.text = count.toString()
                count += 1
            }
        }
    }

    fun onClick(view: View) {
        for ((i, raw) in arrayText.withIndex()) {
            for ((j, cell) in arrayText[i].withIndex()) {
                if (view == cell) {
                    var tv: TextView
                    var tmp: CharSequence
                    if (i > 0 && arrayText[i - 1][j].text == "")
                        tv = arrayText[i - 1][j]
                    else if (i < 3 && arrayText[i + 1][j].text == "")
                        tv = arrayText[i + 1][j]
                    else if (j > 0 && arrayText[i][j - 1].text == "")
                        tv = arrayText[i][j - 1]
                    else if (j < 3 && arrayText[i][j + 1].text == "")
                        tv = arrayText[i][j + 1]
                    else
                        return
                    tmp = tv.text
                    tv.text = cell.text
                    cell.text = tmp
                }
            }
        }
        for ((i, raw) in arrayText.withIndex()) {
            for ((j, cell) in arrayText[i].withIndex()) {
                var t: String

                if (arrayText[i][j].text == "") {
                    t = "16"
                }
                else {
                    t = (arrayText[i][j].text) as String
                }
                if ((i * 4 + j + 1).toString() != t) {
                    return
                }
            }
        }
        var alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Вы победили!");
        alertDialogBuilder.show()
    }

        fun randomNow(view: View) {
            numbers = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16).apply { shuffle() }
            for ((i, raw) in arrayText.withIndex()) {
                for ((j, cell) in arrayText[i].withIndex()) {
                    arrayText[i][j].text = numbers[i * 4 + j].toString()
                    arrayText[i][j].text = arrayText[i][j].text.replace("16".toRegex(), "")
                }
            }
        }
    }