package adventofcode.puzzle1

import adventofcode.common.*
import java.io.File

fun main() {
  val numbers = readFileAsLinesUsingBufferedReader("./input.txt").map(String::toInt)
  println("[Part1] product result: ${part1(numbers)}")
  println("[Part2] product result: ${part2(numbers)}")
}

fun part1(numbers: List<Int>): Int {
  val inputs = findPairMatchingSum(numbers.sorted(), 2020)
  println("[Part1] inputs to multiply: ${inputs}")

  return product(inputs.toList())
}

fun findPairMatchingSum(numbers: List<Int>, sum: Int): Pair<Int, Int> {
  val matchingSum: MutableMap<Int, Int> = mutableMapOf()

  numbers.forEachIndexed { index, element ->
    if (!matchingSum.containsKey(sum - element)) matchingSum.put(element, index)
    else return Pair(element, sum - element)
  }

  return Pair(0, 0)
}


fun part2(numbers: List<Int>): Int {
  val inputs = findTripleMatchingSum(numbers.sorted(), 2020)
  println("[Part2] inputs to multiply: ${inputs}")

  return product(inputs.toList())
}

fun findTripleMatchingSum(numbers: List<Int>, sum: Int): Triple<Int, Int, Int> {
  for (i in 0 until numbers.count() - 3 step 1) {
    var k = sum - numbers[i];
    var low = i + 1;
    var high = numbers.count() - 1;

    while (low < high)
    {
      if (numbers[low] + numbers[high] < k) {
        low++;
      }
      else if (numbers[low] + numbers[high] > k) {
        high--;
      }
      else {
        return Triple(numbers[i], numbers[low], numbers[high])
      }
    }
  }

  return Triple(0, 0, 0)
}

private fun product(numbers: List<Int>): Int
= numbers.fold(1) { product, element -> product * element }
