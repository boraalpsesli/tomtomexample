package com.example.tomtomexample.response

data class testResponse(
    val results: List<Result>,
    val summary: Summary
)

data class Result(
    val address: Address,
    val boundingBox: BoundingBox,
    val dataSources: DataSources,
    val entityType: String,
    val entryPoints: List<EntryPoint>,
    val id: String,
    val info: String,
    val poi: Poi,
    val position: PositionX,
    val score: Double,
    val type: String,
    val viewport: Viewport
)

data class Summary(
    val fuzzyLevel: Int,
    val numResults: Int,
    val offset: Int,
    val query: String,
    val queryIntent: List<Any>,
    val queryTime: Int,
    val queryType: String,
    val totalResults: Int
)

data class Address(
    val country: String,
    val countryCode: String,
    val countryCodeISO3: String,
    val countrySecondarySubdivision: String,
    val countrySubdivision: String,
    val countrySubdivisionCode: String,
    val countrySubdivisionName: String,
    val freeformAddress: String,
    val localName: String,
    val municipality: String,
    val municipalitySecondarySubdivision: String,
    val municipalitySubdivision: String,
    val postalCode: String,
    val streetName: String,
    val streetNumber: String
)

data class BoundingBox(
    val btmRightPoint: BtmRightPoint,
    val topLeftPoint: TopLeftPoint
)

data class DataSources(
    val geometry: Geometry
)

data class EntryPoint(
    val position: PositionX,
    val type: String
)

data class Poi(
    val categories: List<String>,
    val categorySet: List<CategorySet>,
    val classifications: List<Classification>,
    val name: String,
    val phone: String,
    val url: String
)

data class PositionX(
    val lat: Double,
    val lon: Double
)

data class Viewport(
    val btmRightPoint: BtmRightPoint,
    val topLeftPoint: TopLeftPoint
)

data class BtmRightPoint(
    val lat: Double,
    val lon: Double
)

data class TopLeftPoint(
    val lat: Double,
    val lon: Double
)

data class Geometry(
    val id: String
)

data class CategorySet(
    val id: Int
)

data class Classification(
    val code: String,
    val names: List<Name>
)

data class Name(
    val name: String,
    val nameLocale: String
)