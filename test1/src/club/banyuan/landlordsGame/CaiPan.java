package club.banyuan.landlordsGame;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/12 7:58 下午
 */
public class CaiPan implements PlayCards {

  private String[] CardsList = new String[54]; //存牌列表
  private int count = 0;
  private ShuffleSolution solution=new ShuffleSolution(CardsList);

  public String[] getCardsList() {
    return CardsList;
  }
  @Override
  public void autoShuffle() {  //自动洗牌功能
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < cards.length; j++) {
        CardsList[count++] = colors[i] + cards[j];
      }
    }
    CardsList[count++]=jokes[0];
    CardsList[count++]=jokes[1];
    //对拿到的牌打乱顺序
    solution.shuffle();
  }


//  //拿到底牌  一般默认是最后三张为底牌
//  public String[] getDiPai() { //得到底牌
//    String[] strings = new String[3];
//    String st="";
//    for (int i = 0; i < strings.length; i++) {
//      int index = (int) (Math.random() * 54);
//      st+=index;
//    }
//    char[] chars = st.toCharArray();
//    for (int i = 0; i <chars.length; i++) {
//      int i1 = st.indexOf(chars[i]);
//      int i2 = st.lastIndexOf(chars[i]);
//      if(i1!=i2){
//       getDiPai();
//      }
//    }
//    //对得到的字符串进行转换
//    for (int i = 0; i <st.length(); i++) {
//        strings[i]=CardsList[st.charAt(i)-'0'];
//    }
//    return strings;
//  }

}
