package com.mxcsyounes.scaleexample

import aclasdriver.AclasScale
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class MainActivity : AppCompatActivity(), AclasScale.AclasScaleListener {

    lateinit var scale: AclasScale
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scale = AclasScale(File("somerandom"), AclasScale.MODEWEIGHT, this)
    }

    override fun OnError(p0: Int) {
        Log.d("MainActivity", "AclasScale onError $p0 ---------")
    }

    override fun OnDataReceive(data: AclasScale.St_Data) {
        if (data.m_iStatus == -1) {
            Log.d("MainActivity", "data error")
        } else {
            Log.d(
                "TAGH",
                data.m_fWeight.toString() + "" + data.m_strUnit + " id:" + scale.GetId()
            )
            Log.d("SCALE", data.toString())
        }
    }

    override fun OnReadTare(fVal: Float, bFlag: Boolean) {
        val string = if (bFlag) fVal.toString() else "Error"
        Log.d("TAG", "data len OnReadTare:$bFlag $fVal, $string")
    }
}