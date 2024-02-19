package com.example.pinjambuku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pinjambuku.ui.theme.PinjamBukuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinjamBukuTheme {  // Menerapkan tema PinjamBukuTheme ke seluruh UI dalam activity
                // A surface container using the 'background' color from the theme
                Surface(  // Menambahkan lapisan UI utama
                    modifier = Modifier.fillMaxSize(),  // Mengisi seluruh ruang yang tersedia di layar
                    color = MaterialTheme.colorScheme.background  // Menggunakan warna latar belakang dari tema Material
                ) {
                    PinjamBukuApp()  // Memuat komponen utama aplikasi PinjamBukuApp
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 1000)
@Composable
fun GreetingPreview() {
    PinjamBukuTheme {  // Menerapkan tema PinjamBukuTheme ke pratinjau komponen
        PinjamBukuApp()  // Memuat komponen utama aplikasi PinjamBukuApp dalam pratinjau
    }
}
