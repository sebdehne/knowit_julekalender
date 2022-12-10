package com.dehnes.adventofcode.v2022

import org.junit.jupiter.api.Test
import java.time.Duration

class Day10 {

    @Test
    fun run() {

        var r = 125000
        var u = 3500
        val rmax = 1000000
        val a = 0.2
        val g = 0.000075
        val l = 83
        val b = 0.1

        var year = 0L
        var direction = 0

        val cycles: MutableSet<List<Int>> = mutableSetOf()
        val currentCycle: MutableList<Int> = mutableListOf()
        var repeating: List<List<Int>>? = null

        val start = System.nanoTime()

        val target = 1_000_000_000_000
        while (year < target) {
            if (repeating != null) {
                val rest = (target - year).mod(133)
                println(repeating!!.flatten()[rest - 1]) // 110497 correct!!
                println(Duration.ofNanos(System.nanoTime() - start).toMillis())
                System.exit(0)
            }

            currentCycle.add(r)

            val uNew = (u + ((g * u * r) / l) - b * u).toInt()
            val rNew = (r + ((a * r * (rmax - r)) / rmax) - g * u * r).toInt()

            if (rNew < r) {
                if (direction == 1) {
                    // snu punkt
                    if (!cycles.add(currentCycle.toList())) {
                        if (cycles.indexOfFirst { it == currentCycle } == 56) {
                            val myList = cycles.toList()
                            repeating = listOf(
                                myList[54],
                                myList[55],
                                myList[56],
                            ) // == 133 years
                            continue
                        }
                    }
                    currentCycle.clear()
                }
                direction = -1
            } else {
                direction = 1
            }

            u = uNew
            r = rNew
            year++
        }

    }

}