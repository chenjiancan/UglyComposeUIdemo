package com.example.composeuidemo.ui.screens.myutils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CompEntryList(title:String, entryRoutes: List<String>, onEntryClick: (index: Int) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth().padding(0.dp, 8.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(8.dp, 8.dp)
            , verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        ) {

            items(entryRoutes.size) { index ->
                // change IconButton to Card

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
//                    elevation = CardDefaults.cardElevation(
//                        defaultElevation = 6.dp,
//                        pressedElevation = 16.dp,
//                        disabledElevation = 0.dp
//                    ),
//                    shape = CardDefaults.shape,

                    onClick = {
                        onEntryClick(index)
                    },

                    ) {

                    Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Text(entryRoutes[index], modifier = Modifier.align(Alignment.Center), fontWeight = FontWeight.Bold)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Entry",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(5.dp, 0.dp)
                        )
                    }

                }

            }
        }
    }

}