package com.peoplegroup.updatedHoldingsAssignment

import com.upstox.updatedHoldingsAssignment.utilities.commaSeparatedValue
import com.upstox.updatedHoldingsAssignment.utilities.round
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilitiesTest {
    @Test
    fun `Rounding double value test`() {
        assertEquals("10.03", 10.03422.round())
    }

    @Test
    fun `Comma separating value test 1`() {
        assertEquals("10.03", "10.03".commaSeparatedValue())
    }

    @Test
    fun `Comma separating value test 2`() {
        assertEquals("1,000.03", "1000.03".commaSeparatedValue())
    }

    @Test
    fun `Comma separating value test 3`() {
        assertEquals("1,01,45,300.031423", "10145300.031423".commaSeparatedValue())
    }

    @Test
    fun `Comma separating value test 4`() {
        assertEquals("10,14,530.031423", "1014530.031423".commaSeparatedValue())
    }
}