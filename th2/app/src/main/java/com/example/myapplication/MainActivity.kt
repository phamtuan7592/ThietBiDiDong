package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ProfileXMLLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileXMLLayout(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.nhaptuoi, null)

//            val edtEmail = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
//            val btnCheck = view.findViewById<Button>(R.id.button)
//            val txtError = view.findViewById<TextView>(R.id.textViewError)
//
//            btnCheck.setOnClickListener {
//                val email = edtEmail.text.toString().trim()
//
//                if (email.isEmpty()) {
//                    txtError.text = "Email không hợp lệ"
//                    txtError.visibility = android.view.View.VISIBLE
//                } else if (!email.contains("@")) {
//                    txtError.text = "Email không đúng định dạng"
//                    txtError.visibility = android.view.View.VISIBLE
//                } else {
//                    txtError.visibility = android.view.View.GONE
//                    Toast.makeText(context, "Email hợp lệ", Toast.LENGTH_SHORT).show()
//                }
//            }


            val editTextName = view.findViewById<EditText>(R.id.editTextText)
            val editTextAge = view.findViewById<EditText>(R.id.editTextNumber)
            val buttonCheck = view.findViewById<Button>(R.id.button)
            val textViewResult = view.findViewById<TextView>(R.id.textViewResult)

            buttonCheck.setOnClickListener {
                val name = editTextName.text.toString().trim()
                val ageStr = editTextAge.text.toString().trim()

                val age = ageStr.toIntOrNull() ?: -1

                val membership = if (age > 65) {
                    "Người già (>65 tuổi)"
                } else if (age in 6..65) {
                    "Người lớn (6-65 tuổi)"
                } else if (age in 2..5) {
                    "Trẻ em (2-6 tuổi)"
                } else if (age in 0..1) {
                    "Em bé (<2 tuổi)"
                } else {
                    "Không xác định"
                }

                val message = "$name\nTuổi: $age\n$membership"

                textViewResult.text = message
            }



            view
        },
        modifier = modifier
    )
}
