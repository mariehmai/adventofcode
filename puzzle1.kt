import java.io.File

fun main() {
  val numbers = readFileAsLinesUsingBufferedReader("./input1.txt").map(String::toInt)
  println("[Part1] product result: ${part1(numbers)}")
}

fun readFileAsLinesUsingBufferedReader(fileName: String): List<String>
  = File(fileName).bufferedReader().readLines()

fun product(numbers: List<Int>): Int
  = numbers.fold(1) { product, element -> product * element }

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