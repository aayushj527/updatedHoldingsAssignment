package com.upstox.updatedHoldingsAssignment.persentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.upstox.updatedHoldingsAssignment.AppClass
import com.upstox.updatedHoldingsAssignment.R
import com.upstox.updatedHoldingsAssignment.domain.model.HoldingInfo
import com.upstox.updatedHoldingsAssignment.ui.theme.Poppins
import com.upstox.updatedHoldingsAssignment.ui.theme.UpdatedHoldingsAssignmentTheme
import com.upstox.updatedHoldingsAssignment.utilities.AppTopBar
import com.upstox.updatedHoldingsAssignment.utilities.HoldingListItem
import com.upstox.updatedHoldingsAssignment.utilities.HoldingScreenBottomBar
import com.upstox.updatedHoldingsAssignment.utilities.calculateCurrentValue
import com.upstox.updatedHoldingsAssignment.utilities.calculatePercentage
import com.upstox.updatedHoldingsAssignment.utilities.calculateTodayProfitAndLoss
import com.upstox.updatedHoldingsAssignment.utilities.calculateTotalInvestment
import com.upstox.updatedHoldingsAssignment.utilities.round

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var holdingInfoList by remember { mutableStateOf(listOf<HoldingInfo>()) }
    
    LaunchedEffect(key1 = Unit) {
        /**
         *  Observer of network connectivity state to trigger retry when internet
         *  connection is restored.
         */
        AppClass.connectivityState.observe(lifecycleOwner) {
            if (mainScreenViewModel.shouldRetry(it)) {
                mainScreenViewModel.getHoldingDataFromRemote()
            }
        }

        /**
         *  Observer of holding data from DB.
         */
        mainScreenViewModel.holdings.observe(lifecycleOwner) {
            holdingInfoList = it

            val currentValue = calculateCurrentValue(it)
            val totalInvestment = calculateTotalInvestment(it)
            val totalProfitAndLoss = currentValue - totalInvestment

            mainScreenViewModel.state = mainScreenViewModel.state.copy(
                currentValue = currentValue,
                totalInvestment = totalInvestment,
                todayProfitAndLoss = calculateTodayProfitAndLoss(it),
                totalProfitAndLoss = totalProfitAndLoss,
                profitAndLossPercentage = calculatePercentage(totalProfitAndLoss, totalInvestment)
            )
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                headingText = stringResource(id = R.string.portfolio),
                searchIconClicked = {}
            )
        },
        bottomBar = {
            HoldingScreenBottomBar(
                currentValue = mainScreenViewModel.state.currentValue,
                totalInvestment = mainScreenViewModel.state.totalInvestment,
                todayProfitAndLoss = mainScreenViewModel.state.todayProfitAndLoss,
                totalProfitAndLoss = mainScreenViewModel.state.totalProfitAndLoss,
                profitAndLossPercentage = mainScreenViewModel.state.profitAndLossPercentage
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                modifier = Modifier
                    .padding(all = UpdatedHoldingsAssignmentTheme.dimens.dp16)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.holdings),
                style = TextStyle(
                    fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp16,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    textAlign = TextAlign.Center
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = UpdatedHoldingsAssignmentTheme.dimens.dp16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = holdingInfoList) { item ->
                    HoldingListItem(
                        modifier = Modifier.fillMaxWidth(),
                        holdingInfo = item,
                        showBottomBorder = item != holdingInfoList.last()
                    )
                }
            }
        }
    }
}