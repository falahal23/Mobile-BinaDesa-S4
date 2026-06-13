package com.example.falahal_wrold.data.model

data class DisasterEventResponse(
    val title: String? = null,
    val description: String? = null,
    val events: List<DisasterEventModel> = emptyList()
)

data class DisasterEventModel(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val categories: List<DisasterCategoryModel> = emptyList(),
    val geometry: List<DisasterGeometryModel> = emptyList()
)

data class DisasterCategoryModel(
    val id: String? = null,
    val title: String? = null
)

data class DisasterGeometryModel(
    val date: String? = null,
    val type: String? = null,
    val coordinates: Any? = null
)
