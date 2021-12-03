typealias Instruction = Pair<String, Int>

fun main() {
	val partOne = { list: List<Instruction> ->
		var horizontal = 0
		var depth = 0
		
		list.forEach { (instruction, units) ->
			when (instruction) {
				"forward" -> horizontal += units
				"down"    -> depth += units
				"up"      -> depth -= units
			}
		}
		
		horizontal * depth
	}
	
	val partTwo = { list: List<Instruction> ->
		var horizontal = 0
		var depth = 0
		var aim = 0
		
		list.forEach { (instruction, units) ->
			when (instruction) {
				"forward" -> {
					horizontal += units
					depth += aim * units
				}
				"down"    -> aim += units
				"up"      -> aim -= units
			}
		}
		
		horizontal * depth
	}
	
	val testInput = readInputLines("day_2_test").map { line -> line.split(" ").let { split -> split[0] to split[1].toInt() } }
	checkEquals(partOne(testInput), 150)
	checkEquals(partTwo(testInput), 900)
	
	val input = readInputLines("day_2").map { line -> line.split(" ").let { split -> split[0] to split[1].toInt() } }
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}