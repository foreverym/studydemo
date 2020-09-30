package club.banyuan.optional.video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class VideoStore implements Serializable {

  public static final String MAX_VIDEO = "max.video";
  public static final String STORE_FILE_PATH = "store.file.path";
  private List<Video> videos = new ArrayList<>();
  private int count;

  public List<Video> getVideos() {
    return videos;
  }

  public void setVideos(List<Video> videos) {
    this.videos = videos;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  // 从文件加载内容
  public static VideoStore load() {

    return new VideoStore();
  }

  /**
   * 读取properties 文件获取文件路径
   * @return
   */
  public static String loadPath() {
    InputStream resourceAsStream = null;
    String path = "";
    try {
      resourceAsStream = VideoStore.class.getResourceAsStream("/video.properties");
      Properties properties = new Properties();
      properties.load(resourceAsStream);
      path = (String)properties.get("store.file.path");
      //System.out.println(path);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        resourceAsStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return path;
  }


  /**
   * 加载对象序列化文件
   * @return
   */
  public static VideoStore loadObj() {
    String path = loadPath();
    List<Video> videoList = new ArrayList<>();
    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(new FileInputStream(path));
      if (objectInputStream == null) {
        return new VideoStore();
      }
      videoList = (List<Video>) objectInputStream.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        objectInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    VideoStore videoStore = new VideoStore();
    videoStore.setVideos(videoList);
    return videoStore;
  }

  /**
   * 将对象序列化到文件中
   */
  public void saveObj() {
    String path = loadPath();
    ObjectOutputStream objectOutputStream = null;
    try {
      objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
      objectOutputStream.writeObject(videos);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        objectOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 添加一个新的电影
   * 通过电影的名称创建一个video对象，将对象保存在数组中
   *
   * @param name 电影的名称
   */
  public void addVideo(String name) {

    Video v1 = new Video();
    v1.setName(name);
    //videos.set(count, v1);
    videos.add(v1);
    count++;
  }

  /**
   * 根据片名借出电影
   *
   * @return 成功借出电影，返回true,如果片名不存在，则返回false
   */
  public boolean checkOut(String videoName) {
    Video video = findVideoByName(videoName);
    if (video != null) {
      return video.setRent(true);
    }
    return false;
  }

  /**
   * 归还电影
   *
   * @return 成功归还电影，返回true,如果片名不存在，则返回false
   */
  public boolean returnVideo(String videoName) {
    Video video = findVideoByName(videoName);
    if (video != null) {
      return video.setRent(false);
    }
    return false;
  }

  /**
   * 根据 电影名称查询电影
   *
   * @param videoName
   * @return 匹配到的电影，如果找不到对应名称的电影，返回null
   */
  private Video findVideoByName(String videoName) {
    for (int i = 0; i < videos.size(); i++) {
      if (videos.get(i).getName().equals(videoName)) {
        return videos.get(i);
      }
    }
    System.out.println("没有这个电影：" + videoName);
    return null;
  }

  /**
   * 设置用户对电影的评分(1~5)，收到评分之后，计算该电影的平均评分，
   */
  public void receiveRating(String videoName, int mark) {
    Video videoByName = findVideoByName(videoName);
    if (videoByName != null) {
      videoByName.setMark(mark);
    }
  }

  /**
   * 列出整个库存的电影。
   * 电影名称  是否被借出   平均评分
   */
  public void listInventory() {
    for (int i = 0; i < videos.size(); i++) {
      String rentMsg = "没有被借出";
      if (videos.get(i).isRent()) {
        rentMsg = "已被借出";
      }
      System.out.println("电影名称是" + videos.get(i).getName() + rentMsg + "平均评分为" + videos.get(i).getMark());
    }
  }


  public void addVideo() {
    System.out.println("要添加的电影名称：");
    Scanner scanner = new Scanner(System.in);
    String videoName = scanner.nextLine();
    addVideo(videoName);
    saveObj();
    System.out.println("操作成功");
  }


  // TODO 借出电影
  public void checkOut() {

    System.out.println("要借的电影名称：");
    Scanner scanner = new Scanner(System.in);
    String videoName = scanner.nextLine();
    Video video = findVideoByName(videoName);
    if (video == null) {
      System.out.println("没有该电影");
      return;
    }
    int index = videos.indexOf(video);
    video.setRent(true);
    videos.set(index, video);
    saveObj();

    System.out.println("借出成功");
  }

  // TODO 归还电影
  public void returnVideo() {
    System.out.println("要归还的电影名称：");
    Scanner scanner = new Scanner(System.in);
    String videoName = scanner.nextLine();
    Video video = findVideoByName(videoName);
    if (video == null) {
      System.out.println("没有该电影");
      return;
    }
    int index = videos.indexOf(video);
    video.setRent(false);
    videos.set(index, video);
    saveObj();
    System.out.println("归还成功");
  }

}
