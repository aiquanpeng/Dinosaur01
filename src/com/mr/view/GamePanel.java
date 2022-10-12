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
	private BufferedImage image;              //主图片
	private BackgroundImage backgroundImage;  //背景图片
	private Dinosaur golden;                  //恐龙模块
	private Graphics2D g2;                    //画笔
	private int addTimeObstacle;              //增加障碍物的时间
	private List<Obstacle> list = new ArrayList<Obstacle>();  //障碍物列表
	private final int FREASH = 20;            //刷新时间
	private boolean finish = false;           //游戏结束标志
	int score = 0;
	int scoreTimer = 0;
	
	public GamePanel() {
		image = new BufferedImage(800,300,BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();           //获取主图绘图对象
		golden = new Dinosaur();         
		list.add(new Obstacle());              //添加第一个障碍
		backgroundImage = new BackgroundImage();
		FreashThread f = new FreashThread();    //刷新帧线程
		f.start();
	}
	
	
	private void paintImage() {
		backgroundImage.roll();
		golden.move();
		g2.drawImage(backgroundImage.image, 0, 0, this);
		g2.drawImage(golden.image,golden.x,golden.y,this);
		
		if(addTimeObstacle >= 1300) {
			if(Math.random() * 100 > 40) {  //每过1300毫秒就判断一次是否添加障碍物，60%的概率
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
	
	//g2只是主图片的绘图对象，而JPanel需要用Graphics对象，否则画不到JPanel上
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
 
}