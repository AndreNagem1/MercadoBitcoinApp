package praonde.com.mercadobitcointeste.exchangeList.domain.model

data class ExchangeDetails(
    val exchangeId: String,
    val name: String?,
    val rank: Int,
    val volume1dayUsd: Double,
    val volume1hrsUsd: Double,
    val volume1mthUsd: Double,
    val website: String
)

fun exchangeDetailsNotMapped(): ExchangeDetails {
    return ExchangeDetails(
        exchangeId = "Not Mapped",
        name = "Not Mapped",
        rank = 0,
        volume1dayUsd = 0.0,
        volume1hrsUsd = 0.0,
        volume1mthUsd = 0.0,
        website = "Not Mapped"
    )
}

