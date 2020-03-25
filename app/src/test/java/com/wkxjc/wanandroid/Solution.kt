class Solution {

    var result = ""

    fun longestDupSubstring(S: String): String {
//        var left = 0
//        var right = S.length - 1//5
//        while (left < right) {
//            val middle = (left + right) / 2 //2
//            if (valid(S, middle)) left = middle + 1
//            else right = middle
//        }
        return result
    }

    // S 能否分割出 middle 长度的重复字符串
    private fun valid(s: String, middle: Int): Boolean {
        println("middle:$middle")
        for (i in 0..(s.length - middle)) {//0..3
            println("i:$i")//1..3
            for (j in i + 1..(s.length - middle)) {
                println("j:$j")
                println("stringI:${s.substring(i, i + middle)},stringJ:${s.substring(j, j + middle)}")
                if (s.substring(i, i + middle) == s.substring(j, j + middle)) {
                    result = s.substring(i, i + middle)
                    println("update result:$result")
                    return true
                }
            }
        }
        return false
    }
}