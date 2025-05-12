import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Eco
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.animalsapp.ui.navigation.Screen
import com.example.animalsapp.ui.theme.PastelYellow
import com.example.animalsapp.ui.theme.DarkGreen

@Composable
fun CustomBottomBar(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = PastelYellow,
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
                .padding(16.dp),
            tonalElevation = 4.dp
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate(Screen.AnimalsList.route)
                }) {
                    Icon(Icons.Filled.Pets, contentDescription = null)
                }
                Text("Inicio", color = DarkGreen)
                IconButton(onClick = {
                    navController.navigate(Screen.EnvironmentsList.route)
                }) {
                    Icon(Icons.Filled.Eco, contentDescription = null)
                }
                Text("Ambientes", color = DarkGreen)
            }
        }
    }
}

