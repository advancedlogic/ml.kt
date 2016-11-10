package com.al.ml.kt.primitives

/**
 * Created by skywalker on 11/10/16.
 */
class Stats<T:Double>(val values:DVector<T>) {
    val ZERO_EPS = 0.0
    val INV_SQRT_2PI = (1 / Math.sqrt(2 * Math.PI))

    data class _Stats(var minValue:Double, var maxValue:Double, var sum:Double, var sumSqr:Double)
    val stats = {
        val _stats = _Stats(Double.MAX_VALUE, Double.MIN_VALUE, 0.0, 0.0)
        values.forEach { x ->
            if (x < _stats.minValue) x else _stats.minValue
            if (x > _stats.maxValue) x else _stats.maxValue
            _stats.sum + x
            _stats.sumSqr + x*x
        }
        _stats
    }

    val _stats = stats.invoke()
    val mean:Double by lazy { _stats.sum / values.size }
    val variance:Double by lazy { (_stats.sumSqr - mean * mean * values.size) / (values.size - 1) }
    val stdDev:Double by lazy { if (variance < ZERO_EPS) ZERO_EPS else Math.sqrt(variance) }
    val min:Double by lazy { _stats.minValue }
    val max:Double by lazy { _stats.maxValue }

    fun normalize():DblVector {
        val range = max - min
        return values.map { (it - min) / range }.toTypedArray()
    }

    fun gauss():DblVector =
            values.map {
                val y = it - mean
                INV_SQRT_2PI/stdDev*Math.exp(-0.5 * y * y / stdDev)
            }.toTypedArray()
}