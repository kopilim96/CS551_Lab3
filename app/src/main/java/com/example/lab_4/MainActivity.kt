package com.example.lab_4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_4.ui.theme.Lab_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainLayout()
                }
            }
        }
    }
} // end of class

@Composable
fun ListViewLayout(mealList: List<Meal>){
    LazyColumn(
        modifier = Modifier
    ){
        items(mealList){meal ->
            MealCard(meal.mealName, meal.calory)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainLayout() {
    val focusManager = LocalFocusManager.current

    var ascendingOrder by remember {
        mutableStateOf(true)
    }
    var orderName by remember { mutableStateOf(false) }

    val itemList = remember { mutableStateListOf<Meal>() }
    itemList.addAll(MealList) // show default value

    var mealName by remember { mutableStateOf("Meal") }
    var calory by remember { mutableStateOf("0") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f)
                ) {
                    items(itemList) { meal ->
                        MealCard(meal.mealName, meal.calory)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Gray)
                        .fillMaxHeight(0.3f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.7f),
                        horizontalAlignment = Alignment.End
                    ) {
                        // This is Meal
                        TextField(
                            modifier = Modifier
                                .padding(all = 4.dp)
                                .fillMaxWidth(),
                            value = mealName,
                            onValueChange = {
                                mealName = it
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                        )

                        // This is Calory
                        TextField(
                            modifier = Modifier
                                .padding(all = 4.dp)
                                .fillMaxWidth(),
                            value = calory,
                            onValueChange = {
                                calory = it
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()

                                    // Add the meal to the MealList
                                    itemList.add(Meal(mealName, calory))

                                    // Reset the value
                                    mealName = "Meal"
                                    calory = "0"
                                }
                            ),
                            label = { Text("Label") }
                        )

                        // Switch button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.text_orderBy),
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Switch(
                                modifier = Modifier,
                                checked = orderName,
                                onCheckedChange = {
                                    orderName = it
                                    ascendingOrder = !ascendingOrder

                                    // Sort the list based on the orderName and ascendingOrder
                                    itemList.sortBy {
                                        if (orderName) it.mealName else it.calory
                                    }
                                    if (!ascendingOrder) itemList.reverse()
                                },
                            )
                        } // end of Row-switch

                    } // end of Column

                } // end of Box - bottom part

            } // end of Box - inner Box

        } // end of content

    ) // end of Scaffold

} // end of function




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab_4Theme {
        MainLayout()
    }
}