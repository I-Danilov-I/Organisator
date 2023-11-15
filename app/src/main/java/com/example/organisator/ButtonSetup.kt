// Importieren Sie die benötigten Android- und Kotlin-Bibliotheken
package com.example.organisator

import android.app.Dialog
import android.content.Intent
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter


class ButtonSetup(private val activity: AppCompatActivity,
                  private val liste: MutableList<String>,
                  private val adapter: ArrayAdapter<String>,
                  private val soundManager: SoundManager) {
    // Methode zum Einrichten der Buttons
    fun setupButtons() {
        val setbuttonanimation = AnimationUtils.loadAnimation(activity, R.anim.set_button)

        // Finden Sie den settingsButton und setzen Sie einen OnClickListener
        val settingsButton = activity.findViewById<ImageButton>(R.id.set_button)
        settingsButton.setOnClickListener {
            // Erstellen Sie einen neuen Dialog
            val dialog = Dialog(activity)
            // Setzen Sie das Layout für den Dialog
            dialog.setContentView(R.layout.dialog_settings)
            // Zeigen Sie den Dialog an
            dialog.show()
        }


        // Animation von Set Button starten
        settingsButton.startAnimation(setbuttonanimation)



        // Finden Sie den addButton und setzen Sie den OnClickListener
        val addButton = activity.findViewById<ImageButton>(R.id.addButton)
        addButton.setOnClickListener {

            // Spielen Sie den Sound ab
            soundManager.playSound(R.raw.sound_file_add)

            // Finden Sie das textEingabe-Feld und holen Sie den eingegebenen Text
            val textEingabe = activity.findViewById<EditText>(R.id.TextEingabe)
            val eingabeText = textEingabe.text.toString()
            // Überprüfen Sie, ob der eingegebene Text nicht leer ist
            if (eingabeText.isNotEmpty()) {

                // Fügen Sie den eingegebenen Text zur Liste hinzu
                liste.add(eingabeText)
                // Löschen Sie den Text im textEingabe-Feld
                textEingabe.text.clear()
                // Benachrichtigen Sie den Adapter, dass sich die Daten geändert haben
                adapter.notifyDataSetChanged()
            }
        }

        // Finden Sie das textEingabe-Feld und setzen Sie den OnFocusChangeListener
        val textEingabe = activity.findViewById<EditText>(R.id.TextEingabe)
        textEingabe.setOnFocusChangeListener { v, hasFocus ->
            // Wenn das textEingabe-Feld den Fokus erhält, löschen Sie den Text
            if (hasFocus) {
                (v as EditText).text.clear()
            }
        }
    }
}
