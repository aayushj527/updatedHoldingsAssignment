package com.upstox.updatedHoldingsAssignment.utilities

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.upstox.updatedHoldingsAssignment.AppClass
import com.upstox.updatedHoldingsAssignment.data.local.HoldingEntity
import com.upstox.updatedHoldingsAssignment.domain.model.Holding

fun showToast(text: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(
            AppClass.getContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}

/**
 *  To calculate net profit or loss for a particular stock.
 */
fun calculateProfitAndLoss(holdingEntity: HoldingEntity): String? {
    val priceDifference = holdingEntity.ltp?.minus(holdingEntity.avgPrice ?: return null)
    return priceDifference?.times(holdingEntity.quantity ?: return null)?.round()
}

fun Double.round(decimals: Int = 2): String = "%.${decimals}f".format(this)