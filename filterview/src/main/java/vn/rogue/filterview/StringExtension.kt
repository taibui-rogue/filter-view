package vn.rogue.filterview

import java.text.Normalizer
import java.util.*

private val SPECIAL_CHARACTERS = charArrayOf(
        'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í',
        'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'ă', 'đ',
        'ĩ', 'ũ', 'ơ', 'ư', 'ạ', 'ả', 'ấ', 'ầ', 'ẩ',
        'ẫ', 'ậ', 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ', 'ẹ', 'ẻ',
        'ẽ', 'ế', 'ề', 'ể', 'ễ', 'ệ', 'ỉ', 'ị', 'ọ',
        'ỏ', 'ố', 'ồ', 'ổ', 'ỗ', 'ộ', 'ớ', 'ờ', 'ở',
        'ỡ', 'ợ', 'ụ', 'ủ', 'ứ', 'ừ', 'ử', 'ữ', 'ự'
)

private val REPLACEMENTS = charArrayOf(
        'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i',
        'o', 'o', 'o', 'o', 'u', 'u', 'y', 'a', 'd',
        'i', 'u', 'o', 'u', 'a', 'a', 'a', 'a', 'a',
        'a', 'a', 'a', 'a', 'a', 'a', 'a', 'e', 'e',
        'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'o',
        'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',
        'o', 'o', 'u', 'u', 'u', 'u', 'u', 'u', 'u'
)

fun removeAccent(ch: Char): Char {
    val index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch)
    if (index >= 0) {
        return REPLACEMENTS[index]
    }
    return ch
}

fun String.removeAccent(): String {
    val sb = StringBuilder(this)
    for (i in 0 until sb.length) {
        sb.setCharAt(i, removeAccent(sb[i]))
    }

    val r = Normalizer.normalize(sb.toString(), Normalizer.Form.NFD)
    return r.replace("[^\\p{ASCII}]".toRegex(), "")
}

fun String.simplify(): String {
    return this.removeAccent().toLowerCase()
}