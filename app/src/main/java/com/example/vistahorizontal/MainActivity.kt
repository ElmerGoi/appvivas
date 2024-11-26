package com.example.vistahorizontal
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.vistahorizontal.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuButton: Button = findViewById(R.id.menuButton)
        menuButton.setOnClickListener { showPopupMenu(menuButton) }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.menu_options, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_option1 -> {
                    // Acción para Opción 1
                    true
                }
                R.id.action_option2 -> {
                    // Acción para Opción 2
                    true
                }
                R.id.action_option3 -> {
                    // Acción para Opción 3
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
