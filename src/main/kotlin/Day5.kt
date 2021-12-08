import kotlin.math.*

fun main() {
	val partOne = partOne@{ list: List<String> ->
		val (lines, width, height) = readDiagramLines(list, diagonals = false)
		val (_, intersections) = readDiagram(lines, width, height)
		intersections
	}
	
	val partTwo = partTwo@{ list: List<String> ->
		val (lines, width, height) = readDiagramLines(list, diagonals = true)
		val (_, intersections) = readDiagram(lines, width, height)
		intersections
	}
	
	val testInput = readInputLines("day_5_test")
	checkEquals(partOne(testInput), 5)
	checkEquals(partTwo(testInput), 12)
	
	val input = readInputLines("day_5")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}

fun readDiagramLines(inputLines: List<String>, diagonals: Boolean): Triple<List<IntArray>, Int, Int> {
	var width = 0
	var height = 0
	
	val lines = inputLines.mapNotNull {
		val split = it.split(" -> ")
		
		val point1 = split[0].split(",").map { it.toInt() }
		val point2 = split[1].split(",").map { it.toInt() }
		
		val (x1, y1) = point1
		val (x2, y2) = point2
		
		if (x1 == x2 || y1 == y2 || diagonals) {
			width = max(width, max(x1, x2))
			height = max(height, max(y1, y2))
			intArrayOf(x1, y1, x2, y2)
		} else null
	}
	
	return Triple(lines, width + 1, height + 1)
}

fun readDiagram(lines: List<IntArray>, width: Int, height: Int): Pair<Array<IntArray>, Int> {
	val diagram = Array(height) { IntArray(width) }
	var intersections = 0
	
	lines.forEach {
		val (x1, y1, x2, y2) = it
		
		when {
			x1 == x2 -> {
				for (y in min(y1, y2) .. max(y1, y2)) {
					diagram[y][x1]++
					if (diagram[y][x1] == 2) intersections++
				}
			}
			y1 == y2 -> {
				for (x in min(x1, x2) .. max(x1, x2)) {
					diagram[y1][x]++
					if (diagram[y1][x] == 2) intersections++
				}
			}
			else     -> {
				val stepX = if (x2 > x1) 1 else -1
				val stepY = if (y2 > y1) 1 else -1
				for (i in 0 .. abs(x2 - x1)) {
					val x = x1 + stepX * i
					val y = y1 + stepY * i
					
					diagram[y][x]++
					if (diagram[y][x] == 2) intersections++
				}
			}
		}
	}
	
	return Pair(diagram, intersections)
}