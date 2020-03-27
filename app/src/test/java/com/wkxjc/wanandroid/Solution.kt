class Solution {
    fun hasGroupsSizeX(deck: IntArray): Boolean {
        // 记录每个数字出现的次数，判断是否每个数字出现次数的最大公约数>1
        val record = HashMap<Int, Int>()
        deck.forEach {
            if(record[it] == null) record[it] = 1
            else record[it] = record[it]!! + 1
        }
        return record.map { it.value }.reduce(::gcd) != 1
    }

    private fun gcd(a:Int, b:Int): Int {
        var max = a.coerceAtLeast(b)
        var min = a.coerceAtMost(b)
        var temp: Int
        while(min!=0){
            temp = max % min
            max = min
            min = temp
        }
        return max
    }
}