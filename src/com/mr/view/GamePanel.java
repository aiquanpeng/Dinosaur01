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
 * ��Ϸ���
 * 
 * @author mingrisoft
 *
 */
public class GamePanel extends JPanel implements KeyListener {
    private BufferedImage image;// ��ͼƬ
//    private BackgroundImage background;// ����ͼƬ
    private Dinosaur golden;// ����
    private Graphics2D g2;// ��ͼƬ��ͼ����
    private int addObstacleTimer = 0;// ����ϰ���ʱ��
    private boolean finish = false;// ��Ϸ������־
    private List<Obstacle> list = new ArrayList<Obstacle>();// �ϰ�����
    private final int FREASH = 20;// ˢ��ʱ��

    int score = 0;// �÷�
    int scoreTimer = 0;// ������ʱ��

    public GamePanel() {
    }

    /**
     * ������ͼƬ
     */
    private void paintImage() {
    }

    /**
     * ��д�����������
     */
    public void paint(Graphics g) {
        paintImage();// ������ͼƬ����
        g.drawImage(image, 0, 0, this);
    }

    /**
     * ��Ϸ�Ƿ����
     * 
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * ʹ��Ϸ����
     */
    public void gameOver() {
        ScoreRecorder.addNewScore(score);// ��¼��ǰ����
        finish = true;
    }

    /**
     * ʵ�ְ��¼��̰�������
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// ��ȡ���µİ���ֵ
        if (code == KeyEvent.VK_SPACE) {// ����ǿո�
            golden.jump();// ������Ծ
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}