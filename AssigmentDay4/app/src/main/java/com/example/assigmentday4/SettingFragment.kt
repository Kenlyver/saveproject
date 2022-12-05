package com.example.assigmentday4

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.assigmentday4.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }

    }

    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var brightness = AppPreferences.brightness?.toInt()
        binding = FragmentSettingBinding.inflate(layoutInflater)
        hasPermissionsToWriteSettings(context as Activity)
        brightness?.let { binding.sbBrightNess.setProgress(it) }
        binding.sbBrightNess.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.sbBrightNess.setProgress(progress)
                AppPreferences.brightness = progress.toString()
                Settings.System.putInt(
                    (context as Activity).contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS,
                    progress
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        settingLoad()
        binding.sbtnAutoBrightness.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                AppPreferences.autobrightness = true
                binding.sbtnAutoBrightness.setChecked(true)
            } else {
                AppPreferences.autobrightness = false
                binding.sbtnAutoBrightness.setChecked(false)
            }
        }
        binding.sbtnLightBlue.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                AppPreferences.lightblue = true
                binding.sbtnLightBlue.setChecked(true)
            } else {
                AppPreferences.lightblue = false
                binding.sbtnLightBlue.setChecked(false)
            }
        }
        binding.loScreenMode.setOnClickListener {
            val listItems = arrayOf("NightMode", "DayMode")
            var save: Int = 0
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("Choose mode")
                setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                    save = i
                }
                setPositiveButton("OK") { dialogInterface, i ->
                    binding.txtScreenmode.text = listItems[save]
                    AppPreferences.screenmode = listItems[save]
                    if (listItems[save].equals("NightMode")) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
                setNeutralButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
            }
            val mDialog = builder.create()
            mDialog.show()
        }
        return binding.root
    }

    private fun settingLoad() {
        if (AppPreferences.autobrightness == true) {
            binding.sbtnAutoBrightness.setChecked(true)
        } else binding.sbtnAutoBrightness.setChecked(false)
        binding.sbtnAutoBrightness.isChecked()
        if (AppPreferences.lightblue == true) {
            binding.sbtnLightBlue.setChecked(true)
        } else binding.sbtnLightBlue.setChecked(false)
        binding.sbtnLightBlue.isChecked()
        binding.txtScreenmode.text = AppPreferences.screenmode
        if (AppPreferences.screenmode?.equals("NightMode")!!) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun hasPermissionsToWriteSettings(context: Activity): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.System.canWrite(context)
        } else {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_SETTINGS
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}