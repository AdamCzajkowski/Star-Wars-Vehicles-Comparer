package com.adamczajkowski.feature.comparer_feature.utils

import com.adamczajkowski.common.models.Starship
import com.adamczajkowski.feature.comparer_feature.model.Category
import com.adamczajkowski.feature.comparer_feature.model.ComparedCategory
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class CompareHelperTest {

    @Test
    fun `compareNumber two starships with single value then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("200", "1000")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.COST_IN_CREDITS)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber two consumable with different time then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = "1 year",
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = "6 weeks",
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("6 weeks", "1 year")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CONSUMABLES)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber two passengers then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = "0",
                cargoCapacity = null,
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = "1",
                cargoCapacity = null,
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("0", "1")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.PASSENGERS)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber two cargo capacity then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("100", "101")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CARGO_CAPACITY)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber two crew then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "0",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "10",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("0", "10")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CREW)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber three crew one is unknown then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "0",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "10",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "unknown",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("0", "10")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CREW)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber three crew one is n a then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "0",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "10",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "n/a",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("0", "10")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CREW)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber three max atmosphering speed one is with km then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = "1000km",
                crew = "0",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = "10",
                crew = "10",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = "unknown",
                crew = "unknown",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("10", "1000km")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.MAX_ATMOSPHERING_SPEED)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber three hyperdrive speed one is double then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "0",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = "2.2",
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "10",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = "1",
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "unknown",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = "2",
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("1", "2.2")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.HYPERDRIVE_RATING)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber three crew one is with comma then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "100,000",
                passengers = "0",
                cargoCapacity = "100",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "1",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = "unknown",
                passengers = "1",
                cargoCapacity = "101",
                consumables = null,
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("1", "100,000")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CREW)

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `compareNumber two consumable with different years and hours time then return good values`() {

        val preparedTwoStarships = listOf(
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "1000",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = "10 years",
                hyperdriveRating = null,
                mglt = null
            ),
            Starship(
                manufacturer = null,
                name = null,
                model = null,
                starshipClass = null,
                costInCredits = "200",
                length = null,
                maxAtmospheringSpeed = null,
                crew = null,
                passengers = null,
                cargoCapacity = null,
                consumables = "5 hours",
                hyperdriveRating = null,
                mglt = null
            )
        )

        val expectedResult = ComparedCategory("5 hours","10 years")

        val result = CompareHelper.compareNumbers(preparedTwoStarships, Category.CONSUMABLES)

        Assert.assertEquals(expectedResult, result)
    }
}