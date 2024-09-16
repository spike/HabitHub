package com.successfultriggers.triggers.ui.navigation.main


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.successfultriggers.triggers.home.ui.HomeUIRoute
import com.successfultriggers.triggers.core.ui.MAIN
import com.successfultriggers.triggers.core.ui.Screen
import com.successfultriggers.triggers.add.ui.AddUiRoute
import com.successfultriggers.triggers.home.ui.ViewUIRoute2


/**
 * This code defines the main navigation graph for an Android application using Jetpack Compose
 * and the Navigation component. The navigation graph specifies the destinations and routes within
 * the app, allowing users to navigate between different screens.
 *
 * Composable Function:
 * - `MainNavGraph(navController: NavHostController, padding: PaddingValues)`: The primary composable
 *   function that sets up the main navigation graph for the application. It configures the NavHost
 *   with various composable destinations corresponding to different screens.
 *
 * Destinations and Composables:
 * - The code defines several composable destinations for the NavHost.
 *   1. `Screen.Task.route`: Represents the "Task" screen and displays the text "Next Task" along
 *      with a windwatersnowScreen composable.
 *   2. `Screen.PhotoT.route`: Represents the "Photo" screen and displays a PhotoRoute composable.
 *   3. `Screen.List.route`: Represents the "List" screen and displays a ListRoute composable.
 *   4. "details/{id}": A dynamic route that takes an "id" parameter and displays a DetailsRoute
 *      composable for showing details related to a specific item.
 *   5. `Screen.Cat.route`: Represents the "Cat" screen and displays the text "Next Cat" along with
 *      a ListwindwatersnowScreen composable.
 *   6. `photoNavGraph(navController, padding)`: Invokes a separate navigation graph for the "Photo" tab.
 *
 * Overall, this code defines the structure of the main navigation graph for the app, setting up
 * different destinations and their associated composable functions. Users can navigate between
 * these screens using the provided NavHostController, creating a smooth navigation experience.
 */


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        route = MAIN,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            Screen.HomeScreen.route,
        ) {
            HomeUIRoute(
                modifier = Modifier.padding(padding),
                navTo = {path -> navController.navigate(path)},
                navController = navController
            )
        }
        composable(
            Screen.ViewScreen.route.plus("/{triggerId}"),
            arguments = listOf(navArgument("triggerId") { type = NavType.StringType })
        ) {
            val triggerId = it.arguments?.getString("triggerId")
            ViewUIRoute2(
                modifier = Modifier.padding(padding),
                navTo = {path -> navController.navigate(path)},
                navController = navController,
                triggerId = triggerId!!.toInt()
            )
        }
        composable(
            Screen.SettingsScreen.route
        ) {
            AddUiRoute(
                modifier = Modifier.padding(padding),
                navTo = {path -> navController.navigate(path)},
                navController = navController
            )
            /*SettingsScreen(
                paddingValues = padding,
                //navTo = { path -> navController.navigate(path) }
            )*/
        }
    }
}

@Composable
fun HoldCompose(
    modifier: Modifier = Modifier,
    navTo: (String) -> Unit
) {
    Column {
        Text("Hold")
    }
}
