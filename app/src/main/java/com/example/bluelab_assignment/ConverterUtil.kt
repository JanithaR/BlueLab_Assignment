package com.example.bluelab_assignment

//Convert between the following temperature units: Kelvin (K), Celsius (°C) and Fahrenheit (°F).
//Convert between the following volume units: Litre (l), Millilitre (ml), US liquid gallon (US gal)
class ConverterUtil {
    companion object {
        private fun roundTo(value: Double, decimalPoints: Int): Double {
            return String.format("%.${decimalPoints}f", value).toDouble()
        }

        fun convert(
            category: String,
            fromUnit: String,
            toUnit: String,
            value: Double
        ): ConversionResponse {
            return when (category) {
                "Temperature" -> {
                    when (fromUnit) {
                        "Celsius" -> fromCelsius(toUnit, value)
                        "Fahrenheit" -> fromFahrenheit(toUnit, value)
                        "Kelvin" -> fromKelvin(toUnit, value)

                        else -> ConversionResponse(0.0, 0.0)
                    }

                }
                "Volume" -> {
                    when (fromUnit) {
                        "Litre" -> fromLitre(toUnit, value)
                        "Millilitre" -> fromMillilitre(toUnit, value)
                        "US liquid gallon" -> fromUSLiquidGallon(toUnit, value)

                        else -> ConversionResponse(0.0, 0.0)
                    }

                }
                else -> ConversionResponse(0.0, 0.0)
            }
        }

        private fun fromUSLiquidGallon(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Litre" -> {
                    val res: Double = roundTo(value * 3.785, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "Millilitre" -> {
                    val res: Double = roundTo(value * 3785.412, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }

        private fun fromMillilitre(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Litre" -> {
                    val res: Double = roundTo(value / 1000, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "US liquid gallon" -> {
                    val res: Double = roundTo(value / 3785.412, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }

        private fun fromLitre(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Millilitre" -> {
                    val res: Double = roundTo(value * 1000, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "US liquid gallon" -> {
                    val res: Double = roundTo(value / 3.785, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }

        private fun fromKelvin(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Celsius" -> {
                    val res: Double = roundTo(value - 273.15, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "Fahrenheit" -> {
                    val res: Double = roundTo((value - 273.15) * 9 / 5 + 32, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }

        private fun fromFahrenheit(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Celsius" -> {
                    val res: Double = roundTo((value - 32) * 5 / 9, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "Kelvin" -> {
                    val res: Double = roundTo((value - 32) * 5 / 9 + 273.15, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }

        private fun fromCelsius(toUnit: String, value: Double): ConversionResponse {
            return when (toUnit) {
                "Fahrenheit" -> {
                    val res: Double = roundTo((value * 9 / 5) + 32, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }
                "Kelvin" -> {
                    val res: Double = roundTo(value + 273.15, 2)

                    ConversionResponse(fromUnitValue = value, toUnitValue = res)
                }

                else -> ConversionResponse(value, value)
            }
        }
    }
}