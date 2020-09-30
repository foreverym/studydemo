package club.banyuan.playsong;

import java.util.Objects;

/**
 * 歌曲类（Song）
 * 属性：
 *
 * 歌曲id(id)、歌曲名(name)、演唱者(singer)、均为字符串类型
 *
 * 方法：
 *
 * 构造方法
 *
 * getter 和 setter方法
 *
 * hashCode()和equals()方法
 *
 * toString()方法
 *
 */
public class Song {

    private String id;
    private String name;
    private String singer;

    public Song(String id, String name, String singer) {
        this.id = id;
        this.name = name;
        this.singer = singer;
    }

    public Song() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(name, song.name) &&
                Objects.equals(singer, song.singer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, singer);
    }

}
