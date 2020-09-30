package club.banyuan.landlordsGame;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/12 7:52 下午
 */
public interface PlayCards {

  String [] colors={"♥","♣","♠","♦️"};

  String [] cards={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

  String [] jokes={"🃏","🃏"};

  //自动洗牌的功能
  void  autoShuffle();

}
