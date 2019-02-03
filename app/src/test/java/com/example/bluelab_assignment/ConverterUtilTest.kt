package com.example.bluelab_assignment

import org.junit.Assert
import org.junit.Test

class ConverterUtilTest {
    @Test
    fun convertKelvinToCelsius() {
        val category = "Temperature"
        val fromUnit = "Kelvin"
        val toUnit = "Celsius"
        val value = 0.0
        val expected = -273.15

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertKelvinToFahrenheit() {
        val category = "Temperature"
        val fromUnit = "Kelvin"
        val toUnit = "Fahrenheit"
        val value = 0.0
        val expected = -459.67

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertCelsiusToKelvin() {
        val category = "Temperature"
        val fromUnit = "Celsius"
        val toUnit = "Kelvin"
        val value = 0.0
        val expected = 273.15

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertCelsiusToFahrenheit() {
        val category = "Temperature"
        val fromUnit = "Celsius"
        val toUnit = "Fahrenheit"
        val value = 0.0
        val expected = 32.0

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertFahrenheitToKelvin() {
        val category = "Temperature"
        val fromUnit = "Fahrenheit"
        val toUnit = "Kelvin"
        val value = 0.0
        val expected = 255.372

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertFahrenheitToCelsius() {
        val category = "Temperature"
        val fromUnit = "Fahrenheit"
        val toUnit = "Celsius"
        val value = 0.0
        val expected = -17.7778

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertLitreToMillilitre() {
        val category = "Volume"
        val fromUnit = "Litre"
        val toUnit = "Millilitre"
        val value = 1.0
        val expected = 1000.0

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertLitreToUSLiquidGallon() {
        val category = "Volume"
        val fromUnit = "Litre"
        val toUnit = "US liquid gallon"
        val value = 1.0
        val expected = 0.264172

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertMillilitreToLitre() {
        val category = "Volume"
        val fromUnit = "Millilitre"
        val toUnit = "Litre"
        val value = 1.0
        val expected = 0.001

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertMillilitreToUSLiquidGallon() {
        val category = "Volume"
        val fromUnit = "Millilitre"
        val toUnit = "US liquid gallon"
        val value = 1.0
        val expected = 0.0

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convert1000MillilitreToUSLiquidGallon() {
        val category = "Volume"
        val fromUnit = "Millilitre"
        val toUnit = "US liquid gallon"
        val value = 1000.0
        val expected = 0.264172

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertUSLiquidGallonToLitre() {
        val category = "Volume"
        val fromUnit = "US liquid gallon"
        val toUnit = "Litre"
        val value = 1.0
        val expected = 3.78541

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }

    @Test
    fun convertUSLiquidGallonToMillilitre() {
        val category = "Volume"
        val fromUnit = "US liquid gallon"
        val toUnit = "Millilitre"
        val value = 1.0
        val expected = 3785.40999993543

        val actual = ConverterUtil.convert(category, fromUnit, toUnit, value)

        Assert.assertEquals(expected, actual.toUnitValue, 0.1)
    }
}