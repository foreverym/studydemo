package club.banyuan.playsong;

import java.util.Iterator;
import java.util.List;

/**
 *
 播放列表类(PlayList)

 属性：

 播放列表名称(playListName)：字符串类型

 播放列表中的歌曲集合(musicList)：List类型

 方法：

 构造方法

 getter和setter方法

 将歌曲添加到播放列表: public void addToPlayList(Song song);

 显示播放列表中所有歌曲:public void displayAllSong();

 通过id查询歌曲:public Song searchSongById(String id);

 通过名称查询歌曲: public Song searchSongByName(String n);

 修改歌曲:public void updateSong(String id,Song song);

 从播放列表(通过id)删除歌曲:public void deleteSong(String id);

 导出所有歌曲
 */
public class PlayList {
    private String playListName;
    private List<Song> musicList;

    public PlayList(String playListName, List<Song> musicList) {
        this.playListName = playListName;
        this.musicList = musicList;
    }

    public PlayList() {

    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public List<Song> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Song> musicList) {
        this.musicList = musicList;
    }

    public void addToPlayList(Song song) {
        musicList.add(song);
    }

    public void displayAllSong() {
        Iterator<Song> iterator = musicList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public Song searchSongById(String id) {
        Iterator<Song> iterator = musicList.iterator();
        Song song = new Song();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                song = iterator.next();
                return song;
            }
        }
        return song;
    }

    public Song searchSongByName(String n) {
        Iterator<Song> iterator = musicList.iterator();
        Song song = new Song();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(n)) {
                song = iterator.next();
                return song;
            }
        }
        return song;
    }

    public void updateSong(String id,Song song) {
        Iterator<Song> iterator = musicList.iterator();
        Song song1 = new Song();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                song1 = iterator.next();
            }
        }
        int index = musicList.indexOf(song1);
        musicList.set(index, song);
    }

    public void deleteSong(String id) {
        Iterator<Song> iterator = musicList.iterator();
        Song song1 = new Song();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                song1 = iterator.next();
            }
        }
        //int index = musicList.indexOf(song1);
        musicList.remove(song1);
    }

}
