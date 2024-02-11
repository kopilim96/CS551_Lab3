package com.example.lab_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MealCard(mealName: String, calory: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ){
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
        ){
            Image(
                painter = painterResource(R.drawable.calories),
                contentDescription = "Calory",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .padding(all = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(1f),
                    text = mealName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(1f),
                    text = calory,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}