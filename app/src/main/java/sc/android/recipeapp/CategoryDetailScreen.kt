package sc.android.recipeapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun CategoryDetail(
    categoryItem: CategoryItem,
    navigateBack : () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            contentAlignment = Alignment.TopStart
        ){
            Button(
                onClick = {navigateBack()},
                shape = CircleShape,
                modifier = Modifier
                    .size(56.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF3E69F)
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF6B4309).copy(alpha = 0.1f)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                ),
            ) {

                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color(0xFF6B4309),
                    modifier = Modifier.size(40.dp)
                )

            }
        }

        Image(
            painter = rememberAsyncImagePainter(model = categoryItem.strCategoryThumb),
            contentDescription = "category image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .padding(8.dp)
        )

        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF0A6)
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = categoryItem.strCategory,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, bottom = 8.dp)
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFF9A5D00).copy(alpha = 0.3f)
                )

                Text(
                    text = categoryItem.strCategoryDescription,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }

    }
}

/* --- PREVIEW ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryDetailPreview() {

    val sampleCategory = CategoryItem(
        idCategory = "1",
        strCategory = "Chicken",
        strCategoryThumb = "https://www.themealdb.com/images/category/chicken.png",
        strCategoryDescription = "Chicken is one of the most common types of poultry in the world. " +
                "It is widely used in cuisines across cultures because of its versatility and flavor." +
                "Chicken dishes range from grilled and roasted preparations to soups, curries, and fried delicacies."
    )

    CategoryDetail(
        categoryItem = sampleCategory,
        navigateBack = {}
    )
}
 */