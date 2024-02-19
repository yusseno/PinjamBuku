package com.example.pinjambuku.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinjambuku.R
import com.example.pinjambuku.ui.theme.PinjamBukuTheme

@Composable
fun DateScreen(
    options: List<String>, // List opsi tanggal yang tersedia
    onSelectionChanged: (String) -> Unit = {}, // Fungsi yang dipanggil saat opsi tanggal dipilih
    onCancelButtonClicked: () -> Unit = {}, // Fungsi yang dipanggil saat tombol "Cancel" ditekan
    onNextButtonClicked: () -> Unit = {}, // Fungsi yang dipanggil saat tombol "Next" ditekan
    modifier: Modifier = Modifier // Modifier untuk mengatur tata letak
) {
    var selectedValue by rememberSaveable { mutableStateOf("") } // Variabel untuk menyimpan nilai tanggal yang dipilih

    Column(
        modifier = modifier, // Mengatur modifier untuk tata letak
        verticalArrangement = Arrangement.SpaceBetween // Mengatur penataan vertikal
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            options.forEach { item -> // Mengulangi setiap opsi tanggal
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item, // Menentukan apakah opsi tanggal terpilih
                        onClick = {
                            selectedValue = item // Menetapkan nilai tanggal yang dipilih
                            onSelectionChanged(item) // Memanggil fungsi saat opsi tanggal dipilih
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically // Penataan vertikal
                ) {
                    RadioButton(
                        selected = selectedValue == item, // Menentukan apakah opsi tanggal terpilih
                        onClick = {
                            selectedValue = item // Menetapkan nilai tanggal yang dipilih
                            onSelectionChanged(item) // Memanggil fungsi saat opsi tanggal dipilih
                        }
                    )
                    Text(item) // Menampilkan teks opsi tanggal
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)), // Mengatur padding untuk tombol
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)), // Mengatur jarak antar tombol secara horizontal
            verticalAlignment = Alignment.Bottom // Penataan vertikal untuk tombol
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f), // Mengatur bobot tombol
                onClick = onCancelButtonClicked // Menentukan fungsi yang dipanggil saat tombol "Cancel" ditekan
            ) {
                Text(stringResource(R.string.cancel)) // Teks untuk tombol "Cancel"
            }
            Button(
                modifier = Modifier.weight(1f), // Mengatur bobot tombol
                // tombol diaktifkan saat pengguna membuat pilihan
                enabled = selectedValue.isNotEmpty(), // Menentukan apakah tombol "Next" diaktifkan
                onClick = onNextButtonClicked // Menentukan fungsi yang dipanggil saat tombol "Next" ditekan
            ) {
                Text(stringResource(R.string.next)) // Teks untuk tombol "Next"
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 420, heightDp = 1000)
@Composable
fun DateScreenPreview() {
    PinjamBukuTheme {
        DateScreen(
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}
