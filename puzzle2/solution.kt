package adventofcode.puzzle2

import adventofcode.common.*
import java.io.File

fun main() {
  val input = readFileAsLinesUsingBufferedReader("./input.txt")
  println("[Part1] total valid passwords with letter occurrences count in range: ${part1(input)}")
  println("[Part2] total valid passwords with unique occurrence from given positions: ${part2(input)}")
}

class Rule(
  val range: Pair<Int, Int>,
  val letter: Char,
  val password: String
) {

  val letterOccurrencesIsInRange: () -> Boolean =
    { password.count { letter.equals(it) } in range.first..range.second }

  val letterIsAtUniquePosition: () -> Boolean =
    { (password.get(range.first - 1).equals(letter) && !password.get(range.second - 1).equals(letter))
      || (password.get(range.second - 1).equals(letter) && !password.get(range.first - 1).equals(letter)) }

  companion object {
    fun toRule(input: String): Rule {
      val (rule, letter, password) = input.split(" ")
      val (min, max) = rule.split("-")

      return Rule(Pair(min.toInt(), max.toInt()), letter.get(0), password)
    }
  }
}

fun part1(input: List<String>): Int {
  return input
    .map { Rule.toRule(it) }
    .filter { it.letterOccurrencesIsInRange() }
    .count()
}

fun part2(input: List<String>): Int {
  return input
    .map { Rule.toRule(it) }
    .filter { it.letterIsAtUniquePosition() }
    .count()
}
