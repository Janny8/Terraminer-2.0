package main;

import java.awt.Color;
import java.awt.Graphics;

public class block
{
	int ID=0;
	int lg=1;
	int neededBlocks[]=new int[8];
	static int size=15;
	int [] rnd=new int[256];
	boolean mark=false;
	boolean collision=true;
	boolean needsBlock=false;
	boolean background=false;
	
	public boolean isBackground() {
		return background;
	}
	public void setBackground(boolean background) {
		this.background = background;
	}
	public boolean isCollision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	int x, y;
	public void update()
	{
		if(ID==17)
		{
			if(Math.random()>0.9995)
			{
				if(lg==0)simulation.breackBlock(x, y);
			}
		}
		
		
		if(needsBlock)
		{
			if((neededBlocks[0]!=simulation.blocks[x][y+1].getID()))
			{
				if((neededBlocks[1]!=simulation.blocks[x][y+1].getID()))
				{
					if((neededBlocks[2]!=simulation.blocks[x][y+1].getID()))
					{
						simulation.breackBlock(x, y);
					}
				}
			}
		}		
	}
	public int getLg() {
		return lg;
	}
	public void setLg(int lg) {
		this.lg = lg;
	}
	public int[] getNeededBlocks() {
		return neededBlocks;
	}
	public void setNeededBlocks(int[] neededBlocks) {
		this.neededBlocks = neededBlocks;
	}
	public void draw(Graphics g)
	{
//		System.out.println(player.getPx()+", "+player.getPy());
		double px,py;
		if(player.getPx()>0){
			px=0;
		}else px= player.getPx();
		if(player.getPy()<-246){
			py=-246;
		}else py= player.getPy();
		
		g.drawImage(imageLoader.getTextures()[ID][rnd[ID]], (int)((x*frame.getWIDTH()/size)+px*frame.getWIDTH()/size), (int)((y*frame.getWIDTH()/size)+py*frame.getWIDTH()/size), frame.getWIDTH()/size+2, frame.getWIDTH()/size+2,null);
		if(mark)g.fillRect((int)((x*frame.getWIDTH()/size)+player.getPx()*frame.getWIDTH()/size), (int)((y*frame.getWIDTH()/size)+player.getPy()*frame.getWIDTH()/size), frame.getWIDTH()/size+2, frame.getWIDTH()/size+2);
	}
	public void drawMinimap(Graphics g)
	{
		g.setColor(getTextureColor());
		g.fillRect(x*(minimap.size/(simulation.blocks.length/minimap.zoom)),y*(minimap.size/(simulation.blocks.length/minimap.zoom)), minimap.size/(simulation.blocks.length/minimap.zoom), minimap.size/(simulation.blocks.length/minimap.zoom));
	}
	
	public static int getSize() {
		return size;
	}
	public Color getRandomTextureColor()
	{
		if(ID!=0)
		{
			for(int i=0; i<100; i++)
			{
				Color c = new Color(imageLoader.getTextures()[ID][0].getRGB((int)(Math.random()*64), (int)(Math.random()*64)));
				if(ID==17)
				{
					
				}
				if(c.getRed()!=0&&c.getBlue()!=0&&c.getGreen()!=0)
				{
					return c;
				}
			}
		}
		return null;
	}
	public Color getTextureColor()
	{
		if(ID!=0)
		{
		Color c = new Color(imageLoader.getTextures()[ID][0].getRGB(1,1));
		return c;
		}return Color.WHITE;
	}

	public static void setSize(int size) {
		block.size = size;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getID()
	{
		return ID;
	}
	public void setID(int id)
	{
		if(id==0&&ID==16)
		{
			simulation.blocks[x+1][y].setLg(0);
			simulation.blocks[x-1][y].setLg(0);
			simulation.blocks[x][y+1].setLg(0);
			simulation.blocks[x][y-1].setLg(0);
		}
		
		if(id==0||id==12||id==13||id==14||id==15||id==16||id==17)
		{
			setCollision(false);
		}else 
		{
			setCollision(true);
			setBackground(false);
		}
		
		
		if(id==12||id==13||id==14||id==15)
		{
			if(Math.random()>0.5)
			{
				setBackground(true);
			}else 
			{
				setBackground(false);
			}
			neededBlocks[0]=1;
			neededBlocks[1]=2;
			neededBlocks[2]=2;
			setNeedsBlock(true);
			
		}else
		{
			setNeedsBlock(false);
			setBackground(false);
		}if(id==16||id==17)
		{
			setBackground(true);
		}if(id==16)
		{
			neededBlocks[0]=1;
			neededBlocks[1]=2;
			neededBlocks[2]=16;
			setNeedsBlock(true);
		}
		ID=id;
		int textures=0;
		for(int x=0; x<16; x++)
		{
			if(imageLoader.getTextures()[ID][x]!=null)
			{
				textures+=1;
			}
		}
		rnd[ID]=(int)((Math.random()*(textures-1)));
	}
	public boolean isNeedsBlock() {
		return needsBlock;
	}
	public void setNeedsBlock(boolean needsBlock) {
		this.needsBlock = needsBlock;
	}

}