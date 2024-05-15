package com.upstox.updatedHoldingsAssignment.persentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.upstox.updatedHoldingsAssignment.AppClass
import com.upstox.updatedHoldingsAssignment.domain.model.HoldingInfo
import com.upstox.updatedHoldingsAssignment.ui.theme.UpdatedHoldingsAssignmentTheme
import com.upstox.updatedHoldingsAssignment.utilities.HoldingListItem

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
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
//        Text(
//            modifier = Modifier.padding(all = 16.dp),
//            text = stringResource(id = R.string.profile_matches),
//            style = TextStyle.Default.copy(
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold
//            )
//        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = UpdatedHoldingsAssignmentTheme.dimens.dp16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = holdingInfoList) { item ->
                HoldingListItem(
                    modifier = Modifier.fillMaxWidth(),
                    holdingInfo = item
                )
            }
        }
    }
}