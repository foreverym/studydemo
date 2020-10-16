
## 为二进制字符串开发正则表达式
```
{0|1}[1,]  {0|1}+
应该匹配成功
0
1
00
10
111010101010111
应该匹配失败
cat
123
he110, w0r1d
```

## 为任何表示偶数的无符号二进制字符串开发一个正则表达式

```
{0|1}+0
应该匹配成功
0
10
1110
010
00011000110110
应该匹配失败
1
011
0101
0001101
```

## 为任何偶数长度的二进制字符串开发正则表达式
```
?{n/2}+
应该匹配成功
00
01
1010
0001
0001101100011011
应该匹配失败
0
1
111
010
0001101
```



## 开发一个正则表达式，它将接受以下三个字符串: {"pickup truck", "pick up truck", "pick-up truck"}
(pickup truck)|(pick up truck)|(pick-up truck)
```

应该匹配成功
pickup truck
pick up truck
pick-up truck
应该匹配失败
pickuptruck
pick.up.truck
pick- up truck
pick -up truck
volkswagon
```

## 开发一个正则表达式，它可以接受三个或四个单词

```

应该匹配成功
one two three
one two three four
1 2 3 4
how many words?
应该匹配失败
word
word word
word word word word word
```



## 开发匹配金额的正则表达式。

```
应该匹配成功
$0
$0.00
$0.99
$4
$4.00
$10
$10.00
$1000
$1000.00
$1234567.89
应该匹配失败
$-0
$ 0
$1.9
$.99
bannana
$333.33
$12,34
$$$
$$$0
3$
```
