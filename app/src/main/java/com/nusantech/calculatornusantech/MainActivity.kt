package com.nusantech.calculatornusantech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.nusantech.calculatornusantech.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var bind: ActivityMainBinding
    private var value1 : Int = 0
    private var value2 : Int = 0
    private var value3 : Int = 0
    private var resultCalculate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(bind.root)

        bind()
        onChanged()
    }
    fun onChanged() {
        bind.etValue1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                value1 = 0
                bind.cbValue1.setChecked(false)

            }
        })
        bind.etValue2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                value2 = 0
                bind.cbValue2.setChecked(false)
            }
        })
        bind.etValue3.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                value3 = 0
                bind.cbValue3.setChecked(false)
            }
        })
    }
    fun bind(){
        bind.btnPlus.setOnClickListener(this)
        bind.btnMin.setOnClickListener(this)
        bind.btnMultiply.setOnClickListener(this)
        bind.btnDivider.setOnClickListener(this)
        bind.cbValue1.setOnClickListener(this)
        bind.cbValue2.setOnClickListener(this)
        bind.cbValue3.setOnClickListener(this)
    }
    fun calculate(value1 : Int,value2: Int, value3: Int, operator: Char){
        var arrValue = intArrayOf()
        val num1 = bind.cbValue1.isChecked
        val num2 = bind.cbValue2.isChecked
        val num3 = bind.cbValue3.isChecked
        if (!num1 && !num2 && !num3) {
            Toast.makeText(this, "Check Pilihan Value Lebih Dulu", Toast.LENGTH_SHORT).show()
        } else {
            if (num1) arrValue+=value1
            if (num2) arrValue+=value2
            if (num3) arrValue+=value3
            resultCalculate = arrValue[0]
            var valueDouble = resultCalculate.toDouble()
            var code : String = ""
            if (arrValue.size<=1) {
                Toast.makeText(this, "Tidak Bisa Mengkalkulasi, setidaknya checklist 2 checkbox lebih dulu", Toast.LENGTH_SHORT).show()
            } else {
                when(operator) {
                    '+' -> {
                        for (i in 1..arrValue.size-1) {
                            resultCalculate += arrValue[i]
                        }
                    }
                    '-' -> {
                        for (i in 1..arrValue.size-1) {
                            resultCalculate -= arrValue[i]
                        }
                    }
                    '/' -> {
                        for (i in 1..arrValue.size-1) {
                            valueDouble /= arrValue[i].toDouble()
                            code = "double"
                        }
                    }
                    '*' -> {
                        for (i in 1..arrValue.size-1) {
                            resultCalculate *= arrValue[i]
                        }
                    }
                }
            }
            if (code == "double") bind.tvResult.text = valueDouble.toString()
            else bind.tvResult.text = resultCalculate.toString()
        }

    }
    fun toInt(str:String): Int? {
        return try {
            str.toInt()
        } catch (e : NumberFormatException) {
            null
        }
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cb_value1 ->{
                if (bind.etValue1.text?.isEmpty() == true) {
                    bind.etValue1.error = "Masukan Nilai terlebih dulu"
                    bind.cbValue1.setChecked(false)
                } else {
                    val checked = bind.cbValue1.isChecked
                    if (checked == true) {
                        val value = bind.etValue1.text.toString()
                        val num = toInt(value)
                        if (num != null) {
                            value1 = num
                        } else {
                            bind.etValue1.error = "Masukan Nilai yang valid"
                            bind.cbValue1.setChecked(false)
                        }
                    } else {
                        value1 = 0
                    }
                }

            }
            R.id.cb_value2 -> {
                if (bind.etValue2.text?.isEmpty() == true) {
                    bind.etValue2.error = "Masukan Nilai terlebih dulu"
                    bind.cbValue2.setChecked(false)
                } else {
                    val checked = bind.cbValue2.isChecked
                    if (checked) {
                        val value = bind.etValue2.text.toString()
                        val num = toInt(value)
                        if (num != null) {
                            value2 = num
                        } else {
                            bind.etValue2.error = "Masukan Nilai yang valid"
                            bind.cbValue2.setChecked(false)
                        }
                    } else {
                        value2 = 0
                    }
                }
            }
            R.id.cb_value3 ->{
                if (bind.etValue3.text?.isEmpty() == true) {
                    bind.etValue3.error = "Masukan Nilai terlebih dulu"
                    bind.cbValue3.setChecked(false)
                } else {
                    val checked = bind.cbValue3.isChecked
                    if (checked) {
                        val value = bind.etValue3.text.toString()
                        val num = toInt(value)
                        if (num != null) {
                            value3 = num
                        } else {
                            bind.etValue3.error = "Masukan Nilai yang valid"
                            bind.cbValue3.setChecked(false)
                        }
                    } else {
                        value3 = 0
                    }
                }
            }
            R.id.btn_plus -> calculate(value1, value2, value3, '+')
            R.id.btn_min -> calculate(value1, value2, value3, '-')
            R.id.btn_multiply -> calculate(value1,value2,value3, '*')
            R.id.btn_divider -> calculate(value1,value2,value3, '/')
        }
    }
}