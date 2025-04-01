package me.ilker.dota2composer.model.domain

data class Hero(
    val attackType: String? = null,
    val baseAgi: Int? = null,
    val baseArmor: Double? = null,
    val baseAttackMax: Int? = null,
    val baseAttackMin: Int? = null,
    val baseHealth: Int? = null,
    val baseInt: Int? = null,
    val baseMana: Int? = null,
    val baseMr: Int? = null,
    val baseStr: Int? = null,
    val cmEnabled: Boolean? = null,
    val heroId: Int? = null,
    val icon: String? = null,
    val id: Int? = null,
    val img: String? = null,
    val legs: Int? = null,
    val localizedName: String? = null,
    val moveSpeed: Int? = null,
    val name: String? = null,
    val primaryAttr: String? = null,
    val projectileSpeed: Int? = null,
    val roles: List<String>? = null
)
