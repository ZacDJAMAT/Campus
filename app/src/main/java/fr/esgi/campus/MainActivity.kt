package fr.esgi.campus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.esgi.campus.ui.theme.CampusTheme

private const val TAG = "Campus"

data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val location: String?,
    val capacity: Int,
    val description: String
)

val sampleEvents = listOf(
    Event(
        id = 1,
        title = "Conférence brainrot",
        category = "Tech",
        location = "Springfield",
        capacity = 50,
        description = "Une conférence sur les nouvelles tendances technologiques."
    ),
    Event(
        id = 2,
        title = "Tournoi Beyblade",
        category = "Loisirs",
        location = null,
        capacity = 32,
        description = "Un tournoi convivial autour des toupies Beyblade."
    ),
    Event(
        id = 3,
        title = "Atelier gambling",
        category = "Carrière",
        location = "Casino d'Enghien",
        capacity = 0,
        description = "Un atelier de découverte des mécanismes du gambling."
    ),
    Event(
        id = 4,
        title = "Futchampion",
        category = "Skills",
        location = "Lab Skills",
        capacity = 20,
        description = "Un atelier pratique pour développer ses compétences."
    ),
    Event(
        id = 5,
        title = "Soirée Babinski",
        category = "Vie étudiante",
        location = "Salle Babinski",
        capacity = 100,
        description = "Une soirée pour se retrouver et partager un moment convivial."
    )
)

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    onBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.titleLarge)
            Text(text = event.category)
            Text(text = event.location ?: stringResource(R.string.event_location_unknown))
            Text(text = pluralStringResource(R.plurals.available_seats, event.capacity, event.capacity))
            Button(
                onClick = onBook,
                enabled = event.capacity > 0
            ) {
                Text(text = stringResource(R.string.event_book))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>) {
    val categories = events.map { it.category }.distinct()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.campus_title)) })
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        Text(text = category)
                    }
                }
            }
            items(events, key = { it.id }) { event ->
                EventCard(
                    event = event,
                    onClick = { Log.d(TAG, "Clic sur l'événement ${event.id}") },
                    onBook = { Log.d(TAG, "Réservation de l'événement ${event.id}") }
                )
            }
        }
    }
}

@Composable
fun EventDetailScreen(
    event: Event,
    onBook: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = event.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(text = event.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = event.category)
        Text(text = event.location ?: stringResource(R.string.event_location_unknown))
        Text(text = event.description)
        Text(text = pluralStringResource(R.plurals.available_seats, event.capacity, event.capacity))
        Button(
            onClick = onBook,
            enabled = event.capacity > 0
        ) {
            Text(text = "Réserver")
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusTheme {
                EventListScreen(events = sampleEvents)
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

@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    CampusTheme {
        EventCard(
            event = Event(
                id = 3,
                title = "Atelier gambling",
                category = "Carrière",
                location = "Casino d'Enghien",
                capacity = 0,
                description = "Un atelier de découverte des mécanismes du gambling."
            ),
            onClick = {},
            onBook = {}
        )
    }
}