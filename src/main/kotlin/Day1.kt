import kotlin.io.path.*

fun main() {
	val input = Path("input/day_1.txt").readLines().asSequence().map { it.toInt() }
	
	val partOne = input.zipWithNext().count { (a, b) -> b > a }
	println("Part one: $partOne")
	
	val partTwo = input.windowed(3).map { it.sum() }.zipWithNext().count { (a, b) -> b > a }
	println("Part two: $partTwo")
}