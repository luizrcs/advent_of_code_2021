typealias Board = Array<IntArray>

fun main() {
	val partOne = partOne@{ list: List<String> ->
		val boards = readBoards(list)
		val drawings = readDrawings(list)
		drawings.forEach { drawing ->
			boards.forEach { board ->
				val sum = mark(board, drawing)
				if (sum > 0) return@partOne sum * drawing
			}
		}
	}
	
	val partTwo = partTwo@{ list: List<String> ->
		val boards = readBoards(list).toMutableList()
		val drawings = readDrawings(list)
		
		var score = 0
		val skip = IntArray(boards.size)
		drawings.forEach { drawing ->
			if (skip.sum() < boards.size) {
				boards.forEachIndexed boards@{ index, board ->
					if (skip[index] == 0) {
						val sum = mark(board, drawing)
						if (sum > 0) {
							score = sum * drawing
							skip[index] = 1
							return@boards
						}
					}
				}
			} else return@partTwo score
		}
	}
	
	val testInput = readInputLines("day_4_test")
	checkEquals(partOne(testInput), 4512)
	checkEquals(partTwo(testInput), 1924)
	
	val input = readInputLines("day_4")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}

fun readDrawings(inputLines: List<String>) = inputLines.first().split(",").map(String::toInt)

fun readBoards(inputLines: List<String>) = inputLines.asSequence()
	.drop(1)
	.filter(String::isNotEmpty)
	.chunked(5)
	.map { list ->
		val values = list.map { it.split(" ").filter(String::isNotEmpty).map(String::toInt) }
		Array(5) { x -> IntArray(5) { y -> values[x][y] } }
	}
	.toList()

fun check(board: Board): Boolean {
	if (board.any { it.sum() == -5 }) return true
	
	for (y in 0 until 5) {
		var sum = 0
		for (x in 0 until 5) sum += board[x][y]
		if (sum == -5) return true
	}
	
	return false
}

fun mark(board: Board, drawing: Int): Int {
	var sum = 0
	var check = false
	
	for (x in 0 until 5) {
		for (y in 0 until 5) {
			if (board[x][y] == drawing) {
				board[x][y] = -1
				check = check(board)
			} else if (board[x][y] != -1) sum += board[x][y]
		}
	}
	
	return if (check) sum else 0
}