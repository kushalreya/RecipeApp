package sc.android.recipeapp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(
    modifier: Modifier,
    navController: NavHostController
){
    //object for accessing the view model
    val recipeViewModel : MainViewModel = viewModel()
    //getting the data from the view model in type of categoriesState
    val viewState by recipeViewModel.categoriesState

    NavHost(
        navController = navController,
        startDestination = Screen.RecipeScreen.route
    ) {

        composable(
            route = Screen.RecipeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                ) + fadeIn(tween(1000))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                ) + fadeOut(tween(300))
            }
        ) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetail = {
                    navController
                        .currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("cat", it)
                    navController.navigate(Screen.DetailScreen.route)
                }
            )
        }

        composable(
            route = Screen.DetailScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                ) + fadeIn(tween(300))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(3000)
                ) + fadeOut(tween(3000))
            }
        ) {
            val category = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<CategoryItem>("cat")

            //goes to the detail screen of the category found, if null then does nothing
            category?.let {
                CategoryDetail(
                    categoryItem = it,
                    navigateBack = {
                        //returns to the previous screen
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}