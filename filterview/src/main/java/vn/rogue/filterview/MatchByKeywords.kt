package vn.rogue.filterview

interface MatchByKeywords {

    fun match(keywords: String): Boolean

    fun match(keywords: String, vararg strings: String): Boolean {
        val simpleKeywords = keywords.simplify()
        for (s in strings) {
            if (s.simplify().contains(simpleKeywords)) {
                return true
            }
        }
        return false
    }

}