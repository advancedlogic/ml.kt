package com.al.ml.kt.primitives

/**
 * Created by skywalker on 11/9/16.
 */

typealias XY = Pair<Double,Double>
typealias XYTSeries = Array<Pair<Double,Double>>
typealias DMatrix<T> = Array<Array<T>>
typealias DVector<T> = Array<T>
typealias DblMatrix = DMatrix<Double>
typealias DblVector = Array<Double>

fun int2Double(n:Int):Double = n.toDouble()
fun vectorT2DblVector(vt:DVector<Double>):DblVector = vt.map(Double::toDouble).toTypedArray()
fun double2DblVector(x:Double):DblVector = arrayOf(x)
fun dblPair2DblVector(x:Pair<Double,Double>):DblVector = arrayOf(x.first, x.second)
fun dblPairs2DblRows(x:Pair<Double,Double>):DblMatrix = arrayOf(arrayOf(x.first, x.second))

fun <T:Double> Op(v:DVector<T>, w:DblVector, op:(T,Double) -> Double):DblVector = v.mapIndexed { i, t -> op(t, w[i]) }.toTypedArray()
fun DblVector.div(n: Int):DblVector = this.map { it / n }.toTypedArray()
fun DblMatrix.div(n:Int, z:Double):DblMatrix {
    (0..this[n].size - 1).forEach { this[n][it] /= z }
    return this
}