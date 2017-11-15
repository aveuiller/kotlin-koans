package iii_conventions

import iv_properties.toMillis

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return toMillis().compareTo(other.toMillis())
    }

    operator fun plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)
    operator fun plus(range: TimeRange): MyDate = addTimeIntervals(range.timeInterval, range.number)
    operator fun inc(): MyDate = nextDay()
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, endInclusive = other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(number: Int): TimeRange = TimeRange(this, number)

data class TimeRange(val timeInterval: TimeInterval, val number: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator() = object : Iterator<MyDate> {
        private var current = start

        override fun hasNext(): Boolean = current <= endInclusive

        override fun next(): MyDate {
            return current++
        }
    }

    operator fun contains(date: MyDate): Boolean {
        val millis = date.toMillis()
        return millis >= start.toMillis() && millis <= endInclusive.toMillis()
    }
}
