package com.upstox.updatedHoldingsAssignment.persentation.main_screen

/**
 *  @param loading To maintain the loading state of API calling.
 *  @param dataSyncedFromApi To track if the data is initially synced from API or not.
 */
data class MainScreenState(
    val loading: Boolean = false,
    val dataSyncedFromApi: Boolean = false,
    val currentValue: Double = 0.0,
    val totalInvestment: Double = 0.0,
    val todayProfitAndLoss: Double = 0.0,
    val totalProfitAndLoss: Double = 0.0,
    val profitAndLossPercentage: Double = 0.0
)