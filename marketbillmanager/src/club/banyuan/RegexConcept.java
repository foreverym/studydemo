package club.banyuan;

/**
 * [] 表示其中出现的任意一个即可满足规则,{表示前面规则出现的次数} [1-9][0-9]{4,14}a-fA-F
 * {m,} 最多次数不限制
 * [0][0-9]{2}-[0-9]{8}
 *
 *
 * ?表示{0,1}
 * +——》{1,}
 * *——》{0,}
 *
 * 带有 +/-号的数字，如果是整数可以不带+号，使用/进行转义
 * |表示或，两种规则满足其一就可以
 *
 * [^123]只要不是123都满足的单个字符
 *将或的规则分隔开来，使用一对括号即可
 * （[2][0-3]|[0-1][0-9]）:[0-5][0-9]
 *
 * 20[0-9]{2}-(0[1-9]|1[0-2])-
 * 匹配日期
 *^   $  限定符 行首和行位
 *
 * .表示任意的单个字符
 * \d [0-9]
 * \D [^0-9]
 * \s表示空格、制表、换行、回车
 * \S表示
 *
 *
 *
 */
public class RegexConcept {

    public static void main(String[] args) {

    }

    public boolean checkQQNum(String target) {
        if (target.length() < 5 || target.length() > 15) {
            return false;
        }
        char[] charArray = target.toCharArray();
        for (char i : charArray) {
            if (charArray[0] == '0') {
                return false;
            }
            if (i < '0' || i>'9') {
                return false;
            }
        }
        return true;
    }

}
