package me.ilker.dota2compose.domain

data class Hero(
    val attackType: String?,
    val baseAgi: Int?,
    val baseArmor: Double?,
    val baseAttackMax: Int?,
    val baseAttackMin: Int?,
    val baseHealth: Int?,
    val baseInt: Int?,
    val baseMana: Int?,
    val baseMr: Int?,
    val baseStr: Int?,
    val cmEnabled: Boolean?,
    val heroId: Int?,
    val icon: String?,
    val id: Int?,
    val img: String?,
    val legs: Int?,
    val localizedName: String?,
    val moveSpeed: Int?,
    val name: String?,
    val primaryAttr: String?,
    val projectileSpeed: Int?,
    val roles: List<String>?
)
