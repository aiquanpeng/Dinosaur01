package com.mr.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import com.mr.modle.*;
import com.mr.service.*;
/**
 * 游戏面板
 * 
 * @author mingrisoft
 *
 */
public class GamePanel extends JPanel implements KeyListener {
    private BufferedImage image;// 主图片
//    private BackgroundImage background;// 背景图片
    private Dinosaur golden;// 恐龙
    private Graphics2D g2;// 主图片绘图对象
    private int addObstacleTimer = 0;// 添加障碍计时器
    private boolean finish = false;// 游戏结束标志
    private List<Obstacle> list = new ArrayList<Obstacle>();// 障碍集合
    private final int FREASH = 20;// 刷新时间

    int score = 0;// 得分
    int scoreTimer = 0;// 分数计时器

    public GamePanel() {
    }

    /**
     * 绘制主图片
     */
    private void paintImage() {
    }

    /**
     * 重写绘制组件方法
     */
    public void paint(Graphics g) {
        paintImage();// 绘制主图片内容
        g.drawImage(image, 0, 0, this);
    }

    /**
     * 游戏是否结束
     * 
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * 使游戏结束
     */
    public void gameOver() {
        ScoreRecorder.addNewScore(score);// 记录当前分数
        finish = true;
    }

    /**
     * 实现按下键盘按键方法
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// 获取按下的按键值
        if (code == KeyEvent.VK_SPACE) {// 如果是空格
            golden.jump();// 恐龙跳跃
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}