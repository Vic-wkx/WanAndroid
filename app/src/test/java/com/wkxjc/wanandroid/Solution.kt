const val X = 26
const val MODULE = Int.MAX_VALUE

class Solution {
    private var result = ""
    private val numbers = mutableListOf<Int>()
    private val hash = mutableListOf<Int>()
    private val pow = mutableListOf<Int>()

    fun longestDupSubstring(S: String): String {
        initHash(S)
        var left = 0
        var right = S.length - 1
        while (left < right) {
            val middle = (left + right) / 2
            if (valid(S, middle)) left = middle + 1
            else right = middle
        }
        return result
    }

    private fun initHash(s: String) {
        numbers.addAll(s.map { it - 'a' })
        // hash 存储从 0 开始，长度为 i 的 hash 值
        hash.add(0)
        // pow 存储 X 的 i 次方
        pow.add(1)
        for (i in 1..s.length) {
            hash.add(hash[i - 1] * X + numbers[i - 1])
            pow.add(pow[i - 1] * X)
        }
    }

    // S 能否分割出 middle 长度的重复字符串
    private fun valid(s: String, middle: Int): Boolean {
        val hashSet = HashSet<Int>()
        var currentHash = hash[middle]
        hashSet.add(currentHash)
//        println("current:${s.substring(0, middle)}")
        for (i in 1..(s.length - middle)) {
//            println("compare with: ${s.substring(i, i + middle)}")
            val nextHash = (currentHash - numbers[i - 1] * pow[middle - 1]) * X + numbers[i + middle - 1]
            if (hashSet.contains(nextHash)) {
//                println("i = $i, middle = $middle")
                result = s.substring(i, i + middle)
                return true
            }
            hashSet.add(nextHash)
            currentHash = nextHash
        }
        return false
    }
}