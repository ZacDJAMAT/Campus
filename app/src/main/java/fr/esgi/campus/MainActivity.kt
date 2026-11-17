package fr.esgi.campus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.esgi.campus.ui.theme.CampusTheme

data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val location: String?,
    val capacity: Int
)

val sampleEvents = listOf(
    Event(
        id = 1,
        title = "Conférence brainrot",
        category = "Tech",
        location = "Springfield",
        capacity = 50
    ),
    Event(
        id = 2,
        title = "Tournoi Beyblade",
        category = "Loisirs",
        location = null,
        capacity = 32
    ),
    Event(
        id = 3,
        title = "Atelier gambling",
        category = "Carrière",
        location = "Casino d'Enghien",
        capacity = 0
    ),
    Event(
        id = 4,
        title = "Futchampion",
        category = "Skills",
        location = "Lab Skills",
        capacity = 20
    ),
    Event(
        id = 5,
        title = "Soirée Babinski",
        category = "Vie étudiante",
        location = "Salle Babinski",
        capacity = 100
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CampusTheme {
        Greeting("Android")
    }
}