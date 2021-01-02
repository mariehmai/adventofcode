package adventofcode.puzzle2

import adventofcode.common.*
import java.io.File

fun main() {
  val input = readFileAsLinesUsingBufferedReader("./input.txt")
  println("[Part1] valid rules count: ${part1(input)}")
}

class Rule(
  val min: Int,
  val max: Int,
  val letter: String,
  val password: String
) {

  val matchingRule: () -> Boolean =
    { this.password.count { this.letter.contains(it) } in this.min..this.max }
}

fun part1(input: List<String>): Int {
  val passwordMatchingRules = input
    .map {
      val (rule, letter, password) = it.split(" ")
      val (min, max) = rule.split("-")

      Rule(min.toInt(), max.toInt(), letter.take(1), password)
    }
    .filter { it.matchingRule() }

  return passwordMatchingRules.count()
}