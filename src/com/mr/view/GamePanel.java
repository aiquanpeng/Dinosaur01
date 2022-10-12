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
	private BufferedImage image;              //��ͼƬ
	private BackgroundImage backgroundImage;  //����ͼƬ
	private Dinosaur golden;                  //����ģ��
	private Graphics2D g2;                    //����
	private int addTimeObstacle;              //�����ϰ����ʱ��
	private List<Obstacle> list = new ArrayList<Obstacle>();  //�ϰ����б�
	private final int FREASH = 20;            //ˢ��ʱ��
	private boolean finish = false;           //��Ϸ������־
	int score = 0;
	int scoreTimer = 0;
	
	public GamePanel() {
		image = new BufferedImage(800,300,BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();           //��ȡ��ͼ��ͼ����
		golden = new Dinosaur();         
		list.add(new Obstacle());              //��ӵ�һ���ϰ�
		backgroundImage = new BackgroundImage();
		FreashThread f = new FreashThread();    //ˢ��֡�߳�
		f.start();
	}
	
	
	private void paintImage() {
		backgroundImage.roll();
		golden.move();
		g2.drawImage(backgroundImage.image, 0, 0, this);
		g2.drawImage(golden.image,golden.x,golden.y,this);
		
		if(addTimeObstacle >= 1300) {
			if(Math.random() * 100 > 40) {  //ÿ��1300������ж�һ���Ƿ�����ϰ��60%�ĸ���
				list.add(new Obstacle());
			}
			addTimeObstacle = 0;
		}
		
		for(int i = 0;i<list.size();i++){
			Obstacle o = list.get(i);
			if(o.isLive()) {
				o.move();
				g2.drawImage(o.image, o.x, o.y, this);
				if(o.getBounds().intersects(golden.getFootBounds()) ||
						o.getBounds().intersects(golden.getHeadBounds())) {
					gameOver();
				}else {
					list.remove(i);
					i--;
				}
			}
		}
		
	}
	
	//g2ֻ����ͼƬ�Ļ�ͼ���󣬶�JPanel��Ҫ��Graphics���󣬷��򻭲���JPanel��
	public void paint(Graphics g) {
		paintImage();
		g.drawImage(image, 0, 0, this);
	}
	
	public void gameOver() {
		ScoreRecorder.addNewScore(score);
		finish = true;
	}
	
	public boolean isFinish() {
		return finish;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
 
}