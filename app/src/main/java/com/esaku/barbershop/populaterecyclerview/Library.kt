package com.esaku.barbershop.populaterecyclerview

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.esaku.barbershop.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")

fun setFadeAnimation(view: View) {
    val anim = AlphaAnimation(0.0f, 1.0f)
    anim.duration = 500
    view.startAnimation(anim)
}

fun getScreenHeight(): Int = Resources.getSystem().displayMetrics.heightPixels

fun getScreenWidth(activity: Activity): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = activity.windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}

fun reverseVisibility(visible:Int):Int{
    return if(visible==View.VISIBLE){
        View.INVISIBLE
    }else{
        View.VISIBLE
    }
}

fun periodeNameFromMM(MM:String) :String {
    return when (MM) {
        "01" -> {
            "Januari"
        }
        "02" -> {
            "Februari"
        }
        "03" -> {
            "Maret"
        }
        "04" -> {
            "April"
        }
        "05" -> {
            "Mei"
        }
        "06" -> {
            "Juni"
        }
        "07" -> {
            "Juli"
        }
        "08" -> {
            "Agustus"
        }
        "09" -> {
            "September"
        }
        "10" -> {
            "Oktober"
        }
        "11" -> {
            "November"
        }
        "12" -> {
            "Desember"
        }
        else -> {
            ""
        }
    }
}

fun lightStatusBar(window: Window, context: Context){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
    } else {
        window.decorView.systemUiVisibility =0
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
    }
}

fun darkStatusBar(window: Window, context: Context){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
    }
}

fun blackStatusBar(window:Window,context:Context){
    window.statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
}

fun translucentStatusBar(window: Window){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}

fun darkMode(darkMode:Boolean){
    if (darkMode){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }else{
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

fun ddMMyyy2ddMMMMyyy(value:String):String{
    val newdate: Date = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(value)
    return SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in_ID")).format(newdate)
}

fun intToCurrency(value:Int): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val formatted = formatter.format(value)
    return formatted.replace(",",".")
}

fun floatToCurrency(value:Float): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    return formatter.format(value)
}

fun doubleToCurrency(value:Double): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val formatted = formatter.format(value)
    return formatted.replace(",",".")
}

fun Activity.showToastShort(value:String){
    Toast.makeText(this,value,Toast.LENGTH_SHORT).show()
}

fun Activity.showToastLong(value:String){
    Toast.makeText(this,value,Toast.LENGTH_LONG).show()
}

fun Fragment.showToastLong(value:String){
    Toast.makeText(this.context,value,Toast.LENGTH_LONG).show()
}

fun Fragment.showToastShort(value:String){
    Toast.makeText(this.context,value,Toast.LENGTH_SHORT).show()
}

fun Context.showToastLong(value:String){
    Toast.makeText(this,value,Toast.LENGTH_LONG).show()
}

fun Context.showToastShort(value:String){
    Toast.makeText(this,value,Toast.LENGTH_SHORT).show()
}

fun getPeriode(): String {
    val dateFormat: DateFormat = SimpleDateFormat("yyyyMM")
    val date = Date()
    return dateFormat.format(date)
}

fun getMonthM(): String {
    val dateFormat: DateFormat = SimpleDateFormat("M")
    val date = Date()
    return dateFormat.format(date)
}

fun getMonthMM(): String {
    val dateFormat: DateFormat = SimpleDateFormat("MM")
    val date = Date()
    return dateFormat.format(date)
}

fun copyStringToClipboard(text: String, context: Context){
    val clipData: ClipData = ClipData.newPlainText("keuangan", text)
    val myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    myClipboard.setPrimaryClip(clipData)
    Toast.makeText(context, "Berhasil di salin", Toast.LENGTH_SHORT).show()
}

fun copyDoubleToClipboard(nilai: Double, context: Context){
    val clipData: ClipData = ClipData.newPlainText("warga_klip", nilai.toString().substringBefore("."))
    val myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    myClipboard.setPrimaryClip(clipData)
    Toast.makeText(context, "Berhasil disalin ke papan klip", Toast.LENGTH_SHORT).show()
}

fun getYear(): String {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy")
    val date = Date()
    return dateFormat.format(date)
}

fun toRequestBody(value: String): RequestBody {
    return value.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun toRupiah(nilai: Double): String? {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    return formatRupiah.format(nilai)
}

fun rotate180(): RotateAnimation {
    val rotate = RotateAnimation(
        0F,
        180F,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    rotate.duration = 5000
    rotate.interpolator = LinearInterpolator()
    return rotate
}

fun toRupiahClean(nilai: Double): String? {
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(nilai).substringBeforeLast(".")
}

fun dateTodayddmmyy(): String {
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
}

fun dateTodaymmddyy(): String {
    return SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(Date())
}

fun dateTodayyymmdd(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}
