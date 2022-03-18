/*
 * Copyright 2016 Daniel Logan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.growingstems.scouting

import android.app.AlertDialog
import org.frc836.database.DBActivity
import android.widget.TextView
import android.widget.CheckBox
import android.os.Bundle
import org.growingstems.scouting.R
import android.content.Intent
import org.growingstems.scouting.MatchStartActivity
import org.growingstems.scouting.DashboardActivity
import org.frc836.yearly.PitsActivity
import org.growingstems.scouting.data.DataActivity
import org.growingstems.scouting.PickActivity
import android.preference.PreferenceManager
import org.growingstems.scouting.Prefs
import org.growingstems.scouting.DashboardActivity.VersionCallback
import android.view.LayoutInflater
import android.content.DialogInterface
import org.growingstems.scouting.MainMenuSelection
import org.frc836.database.HttpCallback
import com.android.volley.VolleyError
import org.growingstems.scouting.MatchSchedule
import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import org.frc836.database.DB
import java.lang.Exception

class DashboardActivity : DBActivity() {
    private val match: Button? by lazy { findViewById(R.id.matchB) }
    private val pits: Button? by lazy { findViewById(R.id.pitB) }
    private val data: Button? by lazy { findViewById(R.id.dataB) }
    private val picklist: Button? by lazy { findViewById(R.id.picklistB) }
    private val beeLogo: ImageView? by lazy { findViewById(R.id.beeLogo) }
    private val stemsLogo: ImageView? by lazy { findViewById(R.id.stemsLogo) }
    private val fmsApiLink: TextView? by lazy { findViewById(R.id.fmsApiLink) }
    private val HELPMESSAGE: String? by lazy {
		"""
            Version: ${getString(R.string.VersionID)}
            Date: ${getString(R.string.VersionDate)}
            Refer all questions or comments to ${getString(R.string.dev_email)}
            """.trimIndent()
    }
    private var versionCode: String? = null
    private var doNotAskAgainC: CheckBox? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        if (intent.getBooleanExtra("ExitApp", false)) {
            finish()
            return
        }
        versionCode = ""
        match?.setOnClickListener {
			val intent = Intent(
				baseContext,
				MatchStartActivity::class.java
			)
			startActivityForResult(intent, MATCH_ACTIVITY_CODE)
		}
		pits?.setOnClickListener {
			val intent = Intent(
				baseContext,
				PitsActivity::class.java
			)
			startActivityForResult(intent, PITS_ACTIVITY_CODE)
		}
		data?.setOnClickListener {
			val intent = Intent(baseContext, DataActivity::class.java)
			startActivityForResult(intent, DATA_ACTIVITY_CODE)
		}
		picklist?.setOnClickListener {
			val intent = Intent(baseContext, PickActivity::class.java)
			startActivityForResult(intent, PICK_ACTIVITY_CODE)
		}
		beeLogo?.setOnClickListener {
			val uri = Uri.parse("http://robobees.org")
			val intent = Intent(Intent.ACTION_VIEW, uri)
			startActivity(intent)
		}
		stemsLogo?.setOnClickListener {
            val uri = Uri.parse("http://growingstems.org")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        fmsApiLink?.setOnClickListener {
            val uri = Uri.parse("https://frc-events.firstinspires.org/services/API")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        val db = DB(baseContext, binder)
        PreferenceManager.setDefaultValues(this, R.xml.mainprefs, false)
        val url = Prefs.getScoutingURLNoDefault(applicationContext)
        if (url.isNotEmpty()) {
            db.checkVersion(VersionCallback())
        } else if (!Prefs.getDontPrompt(applicationContext, false)) {
            val doNotAskView = LayoutInflater.from(this).inflate(
                R.layout.donotaskagaincheckbox, null
            )
            doNotAskAgainC = doNotAskView
                .findViewById(R.id.donotaskagainC)
            val builder = AlertDialog.Builder(this)
            builder.setMessage(URL_MESSAGE)
                .setCancelable(true)
                .setView(doNotAskView)
                .setPositiveButton(
                    "Yes"
                ) { dialog: DialogInterface, _: Int ->
                    Prefs.setDontPrompt(
                        applicationContext,
                        doNotAskAgainC?.isChecked ?: false
                    )
                    MainMenuSelection
                        .openSettings(this@DashboardActivity)
                    dialog.cancel()
                }
                .setNegativeButton(
                    "No"
                ) { dialog: DialogInterface, _: Int ->
                    Prefs.setDontPrompt(
                        applicationContext,
                        doNotAskAgainC?.isChecked ?: false
                    )
                    dialog.cancel()
                }
            builder.show()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.getBooleanExtra("ExitApp", false)) {
            finish()
        }
    }

	override fun getHelpMessage(): String {
        return HELPMESSAGE!!
    }

    private inner class VersionCallback : HttpCallback {
        override fun onResponse(resp: String) {
            try {
                versionCode = resp.trim { it <= ' ' }
                VERSION_MESSAGE = """
                    The server you have linked to was made for a different version of this app.
                    Your Version: ${getString(R.string.VersionID)}
                    Server Version: $versionCode
                    Would you like to download the correct version?
                    """.trimIndent()
                val verCode = versionCode!!.substring(
                    0,
                    versionCode!!.lastIndexOf(".")
                )
                var localVersion = getString(R.string.VersionID)
                localVersion = localVersion.substring(
                    0,
                    localVersion.lastIndexOf(".")
                )
                if (verCode != localVersion) {
                    val builder = AlertDialog.Builder(this@DashboardActivity)
                    builder.setMessage(VERSION_MESSAGE)
                        .setCancelable(true)
                        .setPositiveButton(
                            "Yes"
                        ) { _: DialogInterface?, _: Int ->
                            val uri = Uri.parse(
                                getString(
                                    R.string.APKURL, versionCode
                                )
                            )
                            val intent = Intent(
                                Intent.ACTION_VIEW, uri
                            )
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton(
                            "No"
                        ) { dialog: DialogInterface, _: Int -> dialog.cancel() }
                    builder.show()
                }
            } catch (e: Exception) {
                //TODO
            }
        }

        override fun onError(e: VolleyError) {
            //TODO
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Prefs.PREFS_ACTIVITY_CODE -> {
                val schedule = MatchSchedule()
                val prefs = PreferenceManager
                    .getDefaultSharedPreferences(baseContext)
                schedule.updateSchedule(
                    prefs.getString("eventPref", "Chesapeake Regional"), this,
                    false
                )
            }
            PITS_ACTIVITY_CODE, MATCH_ACTIVITY_CODE, PICK_ACTIVITY_CODE, DATA_ACTIVITY_CODE -> {
                val db = DB(baseContext, binder)
                db.checkVersion(VersionCallback())
            }
            else -> {}
        }
    }

    companion object {
        private const val PITS_ACTIVITY_CODE = 4639
        private const val MATCH_ACTIVITY_CODE = 4640
        private const val DATA_ACTIVITY_CODE = 4641
        private const val PICK_ACTIVITY_CODE = 4642
        private const val URL_MESSAGE =
            "You have not set a web site for this app to interface with.\nWould you like to do so now?"
        private var VERSION_MESSAGE: String? = null
    }
}