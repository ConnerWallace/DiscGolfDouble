package com.discgolfdoubles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.discgolfdoubles.ui.theme.DiscGolfDoublesTheme

class MakeTeamsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscGolfDoublesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   CardList()
                }
            }
        }
    }
}

@Composable
fun CardList() {
    val random = players.shuffled().toMutableList()
    var cali: Player? = null
    if(random.size % 2 == 1){
        cali = random.firstOrNull { player -> player.cali }
    }
    random.remove(cali);
    if (cali != null) {
        random.add(cali)
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        random.chunked(4).forEachIndexed(){ index, card ->
            CardCard(index,card)
        }
    }
}
@Composable
fun CardCard(index: Int, card : List<Player>){
    Card(modifier = Modifier
        .fillMaxWidth()){
        Text("Hole ${index+1}")

        card.forEachIndexed(){index2 , player -> CardPlayer(index2 ,player)}
    }
}

@Composable
fun CardPlayer(index: Int, player: Player){
    if(index %2 ==1) { //index starts at zero
        Text(player.name, fontSize = 25.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp))
    } else {
        Text(player.name, fontSize = 25.sp, fontWeight = FontWeight.W700)
    }
}