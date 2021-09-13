package com.example.kbc20c31yamamoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var numstr : String = ""
    val numList = ArrayList<Double>()
    val opeList = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 全てのボタンをfindViewById
        val button0: Button = findViewById<Button>(R.id.num0)
        val button1: Button = findViewById<Button>(R.id.num1)
        val button2: Button = findViewById<Button>(R.id.num2)
        val button3: Button = findViewById<Button>(R.id.num3)
        val button4: Button = findViewById<Button>(R.id.num4)
        val button5: Button = findViewById<Button>(R.id.num5)
        val button6: Button = findViewById<Button>(R.id.num6)
        val button7: Button = findViewById<Button>(R.id.num7)
        val button8: Button = findViewById<Button>(R.id.num8)
        val button9: Button = findViewById<Button>(R.id.num9)

        val buttonPlus: Button = findViewById<Button>(R.id.plus)
        val buttonMinus: Button = findViewById<Button>(R.id.minus)
        val buttonMulti: Button = findViewById<Button>(R.id.multi)
        val buttonSlash: Button = findViewById<Button>(R.id.slash)
        val buttonEqual: Button = findViewById<Button>(R.id.equal)
        val buttonPeriod: Button = findViewById<Button>(R.id.period)
        val buttonAC: Button = findViewById<Button>(R.id.ac)
        val buttonPercent: Button = findViewById<Button>(R.id.percent)
        val buttonTaxOn: Button = findViewById<Button>(R.id.taxon)
        val buttonTaxOff: Button = findViewById<Button>(R.id.taxoff)


        // ボタンを押した際の動作
        button0.setOnClickListener {
            textChange("0")
            numstr += "0"
        }
        button1.setOnClickListener {
            textChange("1")
            numstr += "1"
        }
        button2.setOnClickListener {
            textChange("2")
            numstr += "2"
        }
        button3.setOnClickListener {
            textChange("3")
            numstr += "3"
        }
        button4.setOnClickListener {
            textChange("4")
            numstr += "4"
        }
        button5.setOnClickListener {
            textChange("5")
            numstr += "5"
        }
        button6.setOnClickListener {
            textChange("6")
            numstr += "6"
        }
        button7.setOnClickListener {
            textChange("7")
            numstr += "7"
        }
        button8.setOnClickListener {
            textChange("8")
            numstr += "8"
        }
        button9.setOnClickListener {
            textChange("9")
            numstr += "9"
        }

        buttonPlus.setOnClickListener {
            textChange("+")
            addList(numstr,'+')
            numstr = ""
        }
        buttonMinus.setOnClickListener {
            textChange("-")
            addList(numstr,'-')
            numstr = ""
        }
        buttonMulti.setOnClickListener {
            textChange("*")
            addList(numstr,'*')
            numstr = ""
        }
        buttonSlash.setOnClickListener {
            textChange("/")
            addList(numstr,'/')
            numstr = ""
        }
        buttonEqual.setOnClickListener {
            textChange("=")
            addList(numstr)
            var result = calcualte().toString()
            numstr = result
            numList.clear()
            opeList.clear()
        }
        buttonPeriod.setOnClickListener {
            textChange(".")
            numstr += "."
        }
        buttonAC.setOnClickListener {
            textChange("")
            numstr = ""
            numList.clear()
            opeList.clear()
        }
        buttonPercent.setOnClickListener {
            textChange("%")
            addList(numstr,'%')
            numstr = ""
        }
        buttonTaxOn.setOnClickListener {
            textChange("*1.1")
            addList(numstr,'*')
            numstr = ""
            numstr += "1"
            numstr += "."
            numstr += "1"
        }
        buttonTaxOff.setOnClickListener {
            textChange("/1.1")
            addList(numstr,'/')
            numstr = ""
            numstr += "1"
            numstr += "."
            numstr += "1"
        }
    }

    fun textChange(str : String) {
        val resultArea: TextView = findViewById<TextView>(R.id.resultArea)
        if (resultArea.text.length > 10) {
            resultArea.text = "error"
            numstr = ""
            numList.clear()
            opeList.clear()
        }else if (str == "e") {
            resultArea.text = "error"
            numstr = ""
            numList.clear()
            opeList.clear()
        }else if(str == ""){
            resultArea.text = ""
        }else{
            resultArea.text = "${resultArea.text}${str}"
        }
    }
    fun textChange(str : String,isResult : Boolean){
        if(!isResult){
            textChange(str)
        }else{
            val resultArea: TextView = findViewById<TextView>(R.id.resultArea)
            resultArea.text = str
        }
    }

    fun addList(str : String, ope : Char) {
        try {
            var num = str.toDouble()
            numList.add(num)
            opeList.add(ope)
        }catch(e:Exception){
            textChange("e")
        }
    }

    fun addList(str : String) {
        try {
            var num = str.toDouble()
            numList.add(num)
        }catch(e:Exception){
            textChange("e")
        }
    }

    fun calcualte() : Int {

        var i = 0
        while (i < opeList.size) {
            if(opeList[i] == '*' || opeList[i] == '/' || opeList[i] == '%') {
                var result = 0.0
                if (opeList[i] == '*') {
                    result = numList[i] * numList[i + 1]
                } else if (opeList[i] == '/') {
                    if (numList[i] == 0.0) {
                        result = 0.0
                    }else if(numList[i+1] == 0.0){
                        textChange("e")
                        return result.toInt()
                    }else {
                        result = numList[i] / numList[i + 1]
                    }
                } else {
                    result = numList[i] % numList[i + 1]
                }
                numList[i] = result
                numList.removeAt(i+1)
                opeList.removeAt(i)
                i--
            }

            else if(opeList[i] == '-'){
                opeList[i] = '+'
                numList[i+1] = numList[i+1] * -1
            }
            i++
        }

        var result = 0.0
        for (i in numList){
            result += i
        }
        val resultInt = result.toInt()
        val resultStr = resultInt.toString()
        textChange(resultStr,true)
        return resultInt
    }
}