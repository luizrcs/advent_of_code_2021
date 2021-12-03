fun main() {
	val partOne = { list: List<Int> -> list.zipWithNext().count { (a, b) -> b > a } }
	val partTwo = { list: List<Int> -> list.windowed(3).map { it.sum() }.zipWithNext().count { (a, b) -> b > a } }
	
	val testInput = readInputLines("day_1_test").map { it.toInt() }
	checkEquals(partOne(testInput), 7)
	checkEquals(partTwo(testInput), 5)
	
	val input = readInputLines("day_1").map { it.toInt() }
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}