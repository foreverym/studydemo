package club.banyuan.landlordsGame;

import java.util.Arrays;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/12 9:17 下午
 */
public class TestPlayGame {

  public static void main(String[] args) {
    CaiPan caiPan = new CaiPan();
    caiPan.autoShuffle();
    System.out.println(caiPan.getCardsList().length);
    String[] cardsList = caiPan.getCardsList(); //获取打乱之后的数组
    //地主的标志
    String st = cardsList[(int) (Math.random() * (cardsList.length-3))];

    System.out.println("----"+st);

    Player player1 = new Player("张三");
    Player player2 = new Player("李四");
    Player player3 = new Player("王五");
    Player[] players = {player1,player2,player3};

    for (int i = 0; i < cardsList.length - 3; i++) {
      if (i % 3 == 0) {
        player1.addPlayingCards(cardsList[i]);
      } else if (i % 3 == 1) {
        player2.addPlayingCards(cardsList[i]);
      } else {
        player3.addPlayingCards(cardsList[i]);
      }
    }
    System.out.println(player1.toString()+"\n"
        +player2.toString()+"\n"+player3.toString());

    //以上没问题
    for (int i = 0; i <players.length; i++) {
      players[i].checkDiZhu(st);
      if(players[i].getFlag()){
        for (int j = cardsList.length-3; j < cardsList.length; j++) {
          System.out.println("--------"+cardsList[j]);
          players[i].addPlayingCards(cardsList[j]);
        }
      }
    }
    System.out.println(player1.toString());
    System.out.println(player2.toString());
    System.out.println(player3.toString());


  }
}
