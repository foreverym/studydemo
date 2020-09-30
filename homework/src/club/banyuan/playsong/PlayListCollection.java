package club.banyuan.playsong;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 属性：
 *
 * 存放播放列表的集合(playListMap)： Map
 *
 * 方法
 *
 * 构造方法
 *
 * getter和setter方法
 *
 * 添加播放列表:public void addPlayList(PlayList playList);
 *
 * 删除播放列表:public void deletePlayList(PlayList playList);
 *
 * 通过名字查询:public PlayList searchPlayListByName(String playListName);
 *
 * 显示所有播放列表名称：public void displayPlayListName();
 *
 *
 */
public class PlayListCollection {

    Map<String, PlayList> playListMap;

    public PlayListCollection(Map<String, PlayList> playListMap) {
        this.playListMap = playListMap;
    }

    public PlayListCollection() {

    }

    public Map<String, PlayList> getPlayListMap() {
        return playListMap;
    }

    public void setPlayListMap(Map<String, PlayList> playListMap) {
        this.playListMap = playListMap;
    }

    @Override
    public String toString() {
        return "PlayListCollection{" +
                "playListMap=" + playListMap +
                '}';
    }

    public void addPlayList(PlayList playList) {
        playListMap.put(playList.getPlayListName(), playList);
    }

    public void deletePlayList(PlayList playList) {
        playListMap.remove(playList.getPlayListName());
    }

    public PlayList searchPlayListByName(String playListName) {
        PlayList playList = playListMap.get(playListName);
        return playList;
    }

    public void displayPlayListName() {
        Set<Map.Entry<String, PlayList>> entries = playListMap.entrySet();
        Iterator<Map.Entry<String, PlayList>> iterator1 = entries.iterator();
        while(iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        //Iterator<String> iterator = (Iterator<String>) iterator1

    }

}
