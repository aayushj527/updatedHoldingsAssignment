package com.upstox.updatedHoldingsAssignment.utilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.upstox.updatedHoldingsAssignment.R
import com.upstox.updatedHoldingsAssignment.domain.model.HoldingInfo
import com.upstox.updatedHoldingsAssignment.ui.theme.LossRed
import com.upstox.updatedHoldingsAssignment.ui.theme.Poppins
import com.upstox.updatedHoldingsAssignment.ui.theme.ProfitGreen
import com.upstox.updatedHoldingsAssignment.ui.theme.Roboto
import com.upstox.updatedHoldingsAssignment.ui.theme.UpdatedHoldingsAssignmentTheme

@Composable
fun HoldingListItem(
    modifier: Modifier = Modifier,
    holdingInfo: HoldingInfo
) {
    Column {
        Row(
            modifier = modifier.then(
                Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp16)
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            holdingInfo.symbol?.let {
                Text(
                    text = holdingInfo.symbol,
                    style = TextStyle(
                        fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp20,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins
                    )
                )
            }

            holdingInfo.ltp?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = stringResource(id = R.string.ltp) + " ",
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp12,
                            color = Color.Black.copy(alpha = 0.6f),
                            fontFamily = Roboto
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = Constants.CURRENCY_SYMBOL + " " + holdingInfo.ltp.toString(),
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp16,
                            fontFamily = Roboto
                        )
                    )
                }
            }
        }

        Row(
            modifier = modifier.then(
                Modifier.padding(bottom = UpdatedHoldingsAssignmentTheme.dimens.dp16)
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            holdingInfo.quantity?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = stringResource(id = R.string.net_qty) + " ",
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp12,
                            color = Color.Black.copy(alpha = 0.6f),
                            fontFamily = Roboto
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = holdingInfo.quantity.toString(),
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp16,
                            fontFamily = Roboto
                        )
                    )
                }
            }

            holdingInfo.netProfitOrLoss?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = stringResource(id = R.string.p_and_l) + " ",
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp12,
                            color = Color.Black.copy(alpha = 0.6f),
                            fontFamily = Roboto
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = UpdatedHoldingsAssignmentTheme.dimens.dp20),
                        text = Constants.CURRENCY_SYMBOL + " " + holdingInfo.netProfitOrLoss,
                        style = TextStyle(
                            fontSize = UpdatedHoldingsAssignmentTheme.fontSizes.sp16,
                            fontFamily = Roboto,
                            color = if (holdingInfo.netProfitOrLoss.toDouble() > 0) ProfitGreen else LossRed
                        )
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}