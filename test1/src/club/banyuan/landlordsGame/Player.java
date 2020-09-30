package club.banyuan.landlordsGame;

import java.util.Arrays;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/12 7:45 下午
 */
public class Player {

  //玩家有姓名
  private String name;
  //玩家是否是地主
  private Boolean flag = false;//默认都不是地主
  //玩家存放牌
  private String[] str;//
  private int size = 0;

  public Player() {
  }

  public Player(String name) {
    this.name = name;
    str = new String[0];
  }

  public Player(String name, Boolean flag, String[] str) {
    this.name = name;
    this.flag = flag;
    this.str = str;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getFlag() {
    return flag;
  }

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }

  public String[] getStr() {
    return str;
  }

  public void setStr(String[] str) {
    this.str = str;
  }

  //玩家有抓牌的功能
  public void addPlayingCards(String string) {
    //str数组的长度刚开始为0
    str = Arrays.copyOf(str, str.length + 1); //每次添加完了都会重新扩容
    str[size++] = string;
  }

  //判断自己是否是地主
  public void checkDiZhu(String string) {
    for (int i = 0; i < str.length; i++) {
      if (string.equals(str[i])) {
        flag = true;
      }
    }
  }

  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        ", flag=" + flag +
        ", str=" + Arrays.toString(str) +
        '}';
  }
}
