package sc.android.recipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//data class for each category item
@Parcelize
data class CategoryItem(
    val idCategory : String,
    val strCategory : String,
    val strCategoryThumb : String,
    val strCategoryDescription : String
) : Parcelable

//data class for list of category items
data class CategoryList(
    val categories : List<CategoryItem>
)

