package com.songxh.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201930056618421158L;

	/**
	 * Constructor of the object.
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/JPEG");
		response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);

	    int width = 60, height = 20;
	    BufferedImage image = new BufferedImage(width, height,
	            BufferedImage.TYPE_INT_RGB);

	    Graphics g = image.getGraphics();

	    Random random = new Random();

	    g.setColor(getRandColor(200, 250));
	    g.fillRect(0, 0, width, height);

	    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

	    g.setColor(getRandColor(160, 200));
	    for (int i = 0; i < 100; i++) {
	        int x = random.nextInt(width);
	        int y = random.nextInt(height);
	        int xl = random.nextInt(12);
	        int yl = random.nextInt(12);
	        g.drawLine(x, y, x + xl, y + yl);
	    }

	    String sRand = "";
	    for (int i = 0; i < 4; i++) {
	        String rand = String.valueOf(random.nextInt(10));
	        sRand += rand;

	        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
	        g.drawString(rand, 13 * i + 6, 16);
	    }

	    request.getSession().setAttribute("code", sRand);

	    g.dispose();

	    ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}