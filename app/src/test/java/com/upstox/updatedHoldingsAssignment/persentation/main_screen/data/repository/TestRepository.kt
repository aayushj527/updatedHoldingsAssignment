package com.upstox.updatedHoldingsAssignment.persentation.main_screen.data.repository

import com.upstox.updatedHoldingsAssignment.data.local.HoldingEntity
import com.upstox.updatedHoldingsAssignment.data.mapper.toHoldingInfo
import com.upstox.updatedHoldingsAssignment.domain.model.HoldingInfo
import com.upstox.updatedHoldingsAssignment.domain.repository.HoldingRepository
import com.upstox.updatedHoldingsAssignment.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestRepository: HoldingRepository {

    private val holdings = mutableListOf<HoldingEntity>()

    override suspend fun getHoldingDataFromRemote(): Flow<Resource<List<HoldingInfo>>> {
        return flow { emit(Resource.Success(holdings.map { it.toHoldingInfo() })) }
    }

    override fun getHoldingDataFromLocal(): Flow<List<HoldingInfo>> {
        return flow { emit(holdings.map { it.toHoldingInfo() }) }
    }

    override suspend fun insertHoldingData(holdings: List<HoldingEntity>) {
        this.holdings.addAll(holdings)
    }
}