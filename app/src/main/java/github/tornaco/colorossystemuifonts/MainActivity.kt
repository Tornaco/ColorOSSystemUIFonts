package github.tornaco.colorossystemuifonts

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager

const val KEY_PRIVACY_ACCEPTED = "privacy_statement_accepted"

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        if (!pref.getBoolean(KEY_PRIVACY_ACCEPTED, false)) {
            AlertDialog.Builder(this)
                .setTitle("隐私声明")
                .setMessage(
                    assets.open("privacy_agreement.text").use { it.readBytes().decodeToString() }
                )
                .setCancelable(false)
                .setPositiveButton(
                    android.R.string.ok
                ) { _, _ ->
                    pref.edit().putBoolean(KEY_PRIVACY_ACCEPTED, true).apply()
                }
                .setNegativeButton(
                    android.R.string.cancel
                ) { _, _ -> finishAffinity() }
                .show()
        }
    }
}