import kotlin.io.path.*

fun readInputLines(name: String) = Path("input/$name.txt").readLines()

fun checkEquals(value: Any, expected: Any) = check(value == expected) { "Expected $expected, got $value" }