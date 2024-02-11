package com.example.lab_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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

@Composable
fun MainLayout(){
    val focusManager = LocalFocusManager.current

    var ascendingOrder by remember {
        mutableStateOf(true)
    }
    var orderName by remember { mutableStateOf(false ) }

    val itemList = remember{ mutableStateListOf <Meal> () }
    val sortedItemList = remember(itemList, ascendingOrder) {
        val sortedList = itemList.sortedBy { it.mealName }
        if (!ascendingOrder) sortedList.reversed() else sortedList
    }

    var mealName by remember { mutableStateOf("Meal" ) }
    var calory by remember { mutableStateOf( "0" ) }

    

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ){

        ListViewLayout(MealList)

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            // This is Meal
            TextField(
                modifier = Modifier
                    .padding(all = 4.dp)
                    .fillMaxWidth(0.7f),
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
                    .fillMaxWidth(0.7f),
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
                    .fillMaxWidth(0.7f)
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = stringResource(R.string.text_orderBy),
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary
                )
                Switch(
                    modifier = Modifier
                        .fillMaxWidth(),
                    checked = orderName,
                    onCheckedChange = {
                        orderName = it
                        ascendingOrder = !ascendingOrder
                    },
                )
            } // end of Row-switch
        } // end of Column

    } // end of column

} // end of mainLayout


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab_4Theme {
        MainLayout()
    }
}