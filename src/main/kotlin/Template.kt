fun main() {
	val partOne = partOne@{ list: List<String> ->
	}
	
	val partTwo = partTwo@{ list: List<String> ->
	}
	
	val testInput = readInputLines("day_x_test")
	checkEquals(partOne(testInput), 0)
	checkEquals(partTwo(testInput), 0)
	
	val input = readInputLines("day_x")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
