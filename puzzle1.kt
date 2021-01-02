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
  
  return product(inputs)
}

fun findPairMatchingSum(numbers: List<Int>, sum: Int): List<Int> {
  for (i in 0 until numbers.count() - 3 step 1) {
    val isEqualToSum = { x: Int, y: Int -> x + y == sum }
    val isHigherThanSum = { x: Int, y: Int -> x + y > sum }

    for (j in i+1 until numbers.count() step 1) {
      if (isHigherThanSum(numbers[i], numbers[j])) break
      if (isEqualToSum(numbers[i], numbers[j])) return listOf(numbers[i], numbers[j])
    }
  }

  return listOf()
}