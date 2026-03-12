package sc.android.recipeapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


//how the main screen would look
@Composable
fun RecipeScreen(
    viewState: MainViewModel.RecipeState,
    navigateToDetail : (CategoryItem) -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp)
    ){


        when {
            //when the data is loading
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    trackColor = Color(0xFFFFD6BB),
                    color = Color(0xFFFFB07D),
                    strokeWidth = 15.dp,
                    strokeCap = StrokeCap.Round
                )
            }

            //when there is error
            viewState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "AN ERROR HAS OCCURRED !!!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        viewState.error,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
            }

            //when the data is loaded (display categories
            else -> {
                CategoryView(
                    categories = viewState.list,
                    navigateToDetail
                )
            }
        }
    }
}

//how the category list would look
@Composable
fun CategoryView(
    categories : List<CategoryItem>,
    navigateToDetail : (CategoryItem) -> Unit
){

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories){
            category ->
            CategoryItemView(
                category = category,
                navigateToDetail
            )
        }
    }
}

//how each category would look
@Composable
fun CategoryItemView(
    category: CategoryItem,
    navigateToDetail: (CategoryItem) -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable{ navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .padding(4.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFE9D8)
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            border = BorderStroke(
                width = 3.dp,
                color = Color(0xFFFFD6BB)
            )

        ) {
            Image(
                painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                contentDescription = "category image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .aspectRatio(1f)
            )

            Text(
                text = category.strCategory,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

/*  --- PREVIEWS ---
//loading state preview
@Preview(showBackground = true)
@Composable
fun RecipeScreenLoadingPreview() {

    val previewState = MainViewModel.RecipeState(
        loading = true,
        list = emptyList(),
        error = null
    )

    RecipeScreen(
        modifier = Modifier,
        viewState = previewState,
        navigateToDetail = {}
    )
}

//error state preview
@Preview(showBackground = true)
@Composable
fun RecipeScreenErrorPreview() {

    val previewState = MainViewModel.RecipeState(
        loading = false,
        list = emptyList(),
        error = "Network error occurred"
    )

    RecipeScreen(
        modifier = Modifier,
        viewState = previewState,
        navigateToDetail = {}
    )
}

//single item preview
@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {

    CategoryItemView(
        category = CategoryItem(
            "1",
            "Chicken",
            "https://www.themealdb.com/images/category/chicken.png",
            "Chicken dishes"
        ),
        navigateToDetail = {}
    )
}

//data loaded preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeScreenPreview() {

    val sampleCategories = listOf(
        CategoryItem(
            "1",
            "Beef",
            "https://www.themealdb.com/images/category/beef.png",
            "Beef dishes"
        ),
        CategoryItem(
            "2",
            "Chicken",
            "https://www.themealdb.com/images/category/chicken.png",
            "Chicken dishes"
        ),
        CategoryItem(
            "3",
            "Dessert",
            "https://www.themealdb.com/images/category/dessert.png",
            "Dessert dishes"
        ),CategoryItem(
            "4",
            "Rice",
            "https://www.themealdb.com/images/category/dessert.png",
            "Dessert dishes"
        ),CategoryItem(
            "5",
            "Main",
            "https://www.themealdb.com/images/category/dessert.png",
            "Dessert dishes"
        ),CategoryItem(
            "6",
            "Sub",
            "https://www.themealdb.com/images/category/dessert.png",
            "Dessert dishes"
        ),
        CategoryItem(
            "7",
            "Seafood",
            "https://www.themealdb.com/images/category/seafood.png",
            "Seafood dishes"
        ),
        CategoryItem(
            "8",
            "Water",
            "https://www.themealdb.com/images/category/seafood.png",
            "Seafood dishes"
        )
    )

    val previewState = MainViewModel.RecipeState(
        loading = false,
        list = sampleCategories,
        error = null
    )

    RecipeScreen(
        modifier = Modifier,
        viewState = previewState,
        navigateToDetail = {}
    )
}

 */