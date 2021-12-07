fun main() {
	val partOne = { list: List<String> ->
		var gamma = 0
		var epsilon = 0
		
		val length = list.first().length
		for (i in 0 until length) {
			var zeroes = 0
			var ones = 0
			
			list.forEach { if (it[i] == '0') zeroes++ else ones++ }
			
			val bit = if (zeroes > ones) 0 else 1
			gamma = gamma shl 1 or bit
			epsilon = epsilon shl 1 or (bit xor 1)
		}
		
		gamma * epsilon
	}
	
	val partTwo = { list: List<String> ->
		var oxygenList = list
		var co2List = list
		
		val length = list.first().length
		for (i in 0 until length) {
			var zeroes = mutableListOf<String>()
			var ones = mutableListOf<String>()
			
			if (oxygenList.size > 1) {
				oxygenList.forEach { if (it[i] == '0') zeroes += it else ones += it }
				oxygenList = if (ones.size >= zeroes.size) ones else zeroes
			}
			
			zeroes = mutableListOf()
			ones = mutableListOf()
			
			if (co2List.size > 1) {
				co2List.forEach { if (it[i] == '0') zeroes += it else ones += it }
				co2List = if (zeroes.size <= ones.size) zeroes else ones
			}
		}
		
		val oxygen = oxygenList.single().toInt(2)
		val co2 = co2List.single().toInt(2)
		
		oxygen * co2
	}
	
	val testInput = readInputLines("day_3_test")
	checkEquals(partOne(testInput), 198)
	checkEquals(partTwo(testInput), 230)
	
	val input = readInputLines("day_3")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}