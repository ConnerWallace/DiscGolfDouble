@file:OptIn(ExperimentalMaterial3Api::class)

package com.discgolfdoubles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.widget.GridLayout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.discgolfdoubles.ui.theme.DiscGolfDoublesTheme
import java.io.Serializable

class MainActivity : ComponentActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscGolfDoublesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column {
                        PlayerList()
                        SimpleFilledTextFieldSample()

                    }

                }
            }
        }
    }
}

data class Player(val name: String, val cali: Boolean) : Serializable
val players = mutableStateListOf<Player>();
var sixPersonCard = mutableStateOf(false);

@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("q") }
    var cali by remember { mutableStateOf(true) }
    val sixP by remember { sixPersonCard}
    val activity = LocalContext.current as Activity

    Column (modifier = Modifier.fillMaxWidth()){
        Row{
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter player Names") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { addPlayer(text, cali);text = "";cali = true}
                ),

            )
            Spacer(Modifier.width(10.dp))
            Switch(
                checked = cali,
                onCheckedChange = {
                    cali = it
                },
            )
        }
        Row {
            Text(text = "allow 6 person card")
            Switch(
                checked = sixP,
                onCheckedChange = {
                    sixPersonCard.value =  it
                },
            )
            Spacer(modifier = Modifier.weight(.75f))
            Button(onClick = {lanchTeamMaker(activity)}) {Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Favorite",
                modifier = Modifier.size(20.dp)
            )  }
        }
    }
}

fun lanchTeamMaker(activity: Activity){

    val intent = Intent(activity,MakeTeamsActivity::class.java)
    intent.putExtra("players",players.toTypedArray())
    activity.startActivity(intent)

}

fun addPlayer(name: String,  cali : Boolean){
    if(name == "") return
    players.add(Player(name,cali))
}

@Composable
fun PlayerList() {
     remember {players}
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        players.forEach { player ->
            PlayerRow(player)
        }
    }
}

@Composable
fun PlayerRow(player: Player) {
    Card(modifier = Modifier
        .padding(all = 3.dp)
        .fillMaxWidth()) {
        Row (){
            Text(player.name, fontSize = 25.sp, fontWeight = FontWeight.W700)
            Spacer(modifier = Modifier.weight(1.0f))
            if (player.cali) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),

                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                )
            }
        }
    }
}