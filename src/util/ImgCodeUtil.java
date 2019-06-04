package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class ImgCodeUtil {
	private ByteArrayInputStream image;// ͼ��
	private String str;// ��֤��

	private ImgCodeUtil() {
		init();// ��ʼ������
	}

	/*
	 * ȡ��RandomNumUtilʵ��
	 */
	public static ImgCodeUtil Instance() {
		return new ImgCodeUtil();
	}

	/*
	 * ȡ����֤��ͼƬ
	 */
	public ByteArrayInputStream getImage() {
		return this.image;
	}

	/*
	 * ȡ��ͼƬ����֤��
	 */
	public String getString() {
		return this.str;
	}

	private void init() {
		// ���ڴ��д���ͼ��
		int width = 70, height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		// ���������
		Random random = new Random();
		// �趨����ɫ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// �趨����
		g.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(200, 250));
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(6);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl+90, y + yl+20);
		}
		// ȡ�����������֤��
		String old = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // ��֤ͼƬ���������ַ�
		String sRand = "";
		int j = 0;
		for (int i = 0; i < 4; i++) {
			j = random.nextInt(old.length());
			String rand = String.valueOf(old.substring(j, j + 1));
			sRand += rand;
			
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
			g.drawString(rand, 13 * i + 8, 25);
		}
		shear(g, 70, 30,getRandColor(200, 250));// ʹͼƬŤ��
		// ��ֵ��֤��
		this.str = sRand;
		// ͼ����Ч
		g.dispose();
		  
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO
					.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("��֤��ͼƬ�������ִ���" + e.toString());
		}
		this.image = input;/* ��ֵͼ�� */
	}

	/*
	 * ������Χ��������ɫ
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	
	//ʹͼƬŤ������
	 private static void shear(Graphics g, int w1, int h1, Color color) {  
	        shearX(g, w1, h1, color);  
	        shearY(g, w1, h1, color);  
	    }  
	      
	    private static void shearX(Graphics g, int w1, int h1, Color color) {  
	    	Random random = new Random();
	        int period = random.nextInt(2);  
	  
	        boolean borderGap = true;  
	        int frames = 1;  
	        int phase = random.nextInt(2);  
	  
	        for (int i = 0; i < h1; i++) {  
	            double d = (double) (period >> 1)  
	                    * Math.sin((double) i / (double) period  
	                            + (6.2831853071795862D * (double) phase)  
	                            / (double) frames);  
	            g.copyArea(0, i, w1, 1, (int) d, 0);  
	            if (borderGap) {  
	                g.setColor(color);  
	                g.drawLine((int) d, i, 0, i);  
	                g.drawLine((int) d + w1, i, w1, i);  
	            }  
	        }  
	  
	    }  
	  
	    private static void shearY(Graphics g, int w1, int h1, Color color) {  
	    	Random random = new Random();
	        int period = random.nextInt(12) + 10; // 50;  
	  
	        boolean borderGap = true;  
	        int frames = 20;  
	        int phase = 7;  
	        for (int i = 0; i < w1; i++) {  
	            double d = (double) (period >> 1)  
	                    * Math.sin((double) i / (double) period  
	                            + (6.2831853071795862D * (double) phase)  
	                            / (double) frames);  
	            g.copyArea(i, 0, 1, h1, 0, (int) d);  
	            if (borderGap) {  
	                g.setColor(color);  
	                g.drawLine(i, (int) d, i, 0);  
	                g.drawLine(i, (int) d + h1, i, h1);  
	            }  
	  
	        }  
	  
	    }  
	
	
	
}
