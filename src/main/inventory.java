package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class inventory 
{
	public static slot[] slots =new slot[74];
	public static crafting c=new crafting();
	public static byte selected=0;
	public static int x,y;
	static int id=0;
	static int id2=0; 
	static int count=0;
	public static int container=0;
	public static boolean open=false, pickedUp=false;
	public final static int FULL=-1, NO_SLOT_SELECTED=-1, OUT_OF_INVENTORY=-2;
	public static boolean isOpen() {
		return open;
	}
	public static void pickUp(int slot)
	{
		int asc=0;
		for(int x=0; x<74; x++)
		{
			if(slots[x].getChanged())asc++;
		}if(asc<1)
		{
			id=slots[36].getID();
			id2=slots[36].getID2();
			count=slots[36].getCount();
			for(int x=0; x<74; x++)
			{
				slots[x].setOldCount(slots[x].getCount());
			}
		}
		System.out.println(asc);
		if((slot!=NO_SLOT_SELECTED)&&(slot!=OUT_OF_INVENTORY))
		{
			if(slot==46)
			{
				keyboard.setLbutton(false);
				if(slots[slot].getID()!=0)
				{
					if(keyboard.isShift())
					{
						if(slots[slot].getCount()>0)
						{
							int oldID, oldID2, oldCount;
							oldID=slots[slot].getID();
							oldID2=slots[slot].getID2();
							int ffs=getFirstSlot(oldID, oldID2);
							oldCount=slots[ffs].getCount();	
							slots[ffs].setID(oldID);
							slots[ffs].setID2(oldID2);
							if(slots[ffs].getCount()+slots[slot].getCount()<64)
							{
								slots[ffs].setCount(slots[slot].getCount()+oldCount);
								slots[slot].setCount(0);
								slots[slot].setID(0);
								slots[slot].setID2(0);
							}else 
							{
								slots[ffs].setCount(64);
								slots[slot].setCount(slots[slot].getCount()-(64-oldCount));
								slots[getFirstSlot(oldID, oldID2)].setCount(slots[getFirstSlot(oldID, oldID2)].getCount()+slots[slot].getCount());
							}
							if(container!=2)
							{
								if(slots[37].getCount()>0)slots[37].setCount(slots[37].getCount()-1);
								if(slots[38].getCount()>0)slots[38].setCount(slots[38].getCount()-1);
								if(slots[39].getCount()>0)slots[39].setCount(slots[39].getCount()-1);
								if(slots[40].getCount()>0)slots[40].setCount(slots[40].getCount()-1);
								if(slots[41].getCount()>0)slots[41].setCount(slots[41].getCount()-1);
								if(slots[42].getCount()>0)slots[42].setCount(slots[42].getCount()-1);
								if(slots[43].getCount()>0)slots[43].setCount(slots[43].getCount()-1);
								if(slots[44].getCount()>0)slots[44].setCount(slots[44].getCount()-1);
								if(slots[45].getCount()>0)slots[45].setCount(slots[45].getCount()-1);
							}
						}
					}
					else
					{
						if(slots[36].getID()==slots[slot].getID()&&slots[36].getID2()==slots[slot].getID2())
						{
							int oldID, oldID2, oldCount;
							oldID=slots[slot].getID();
							oldID2=slots[slot].getID2();
							oldCount=slots[slot].getCount();	
							slots[slot].setID(0);
							slots[36].setID(oldID);
							slots[36].setID2(oldID2);
							slots[slot].setCount(0);
							slots[36].setCount(slots[36].getCount()+oldCount);
							if(container!=2)
							{
								if(slots[37].getCount()>0)slots[37].setCount(slots[37].getCount()-1);
								if(slots[38].getCount()>0)slots[38].setCount(slots[38].getCount()-1);
								if(slots[39].getCount()>0)slots[39].setCount(slots[39].getCount()-1);
								if(slots[40].getCount()>0)slots[40].setCount(slots[40].getCount()-1);
								if(slots[41].getCount()>0)slots[41].setCount(slots[41].getCount()-1);
								if(slots[42].getCount()>0)slots[42].setCount(slots[42].getCount()-1);
								if(slots[43].getCount()>0)slots[43].setCount(slots[43].getCount()-1);
								if(slots[44].getCount()>0)slots[44].setCount(slots[44].getCount()-1);
								if(slots[45].getCount()>0)slots[45].setCount(slots[45].getCount()-1);
							}
						}
						else 
						{
							int oldID, oldID2, oldCount;
							oldID=slots[slot].getID();
							oldID2=slots[slot].getID2();
							oldCount=slots[slot].getCount();	
							slots[slot].setID(slots[36].getID());
							slots[36].setID(oldID);
							slots[36].setID2(oldID2);
							slots[slot].setCount(slots[36].getCount());
							slots[36].setCount(oldCount);
							if(container!=2)
							{
								if(slots[37].getCount()>0)slots[37].setCount(slots[37].getCount()-1);
								if(slots[38].getCount()>0)slots[38].setCount(slots[38].getCount()-1);
								if(slots[39].getCount()>0)slots[39].setCount(slots[39].getCount()-1);
								if(slots[40].getCount()>0)slots[40].setCount(slots[40].getCount()-1);
								if(slots[41].getCount()>0)slots[41].setCount(slots[41].getCount()-1);
								if(slots[42].getCount()>0)slots[42].setCount(slots[42].getCount()-1);
								if(slots[43].getCount()>0)slots[43].setCount(slots[43].getCount()-1);
								if(slots[44].getCount()>0)slots[44].setCount(slots[44].getCount()-1);
								if(slots[45].getCount()>0)slots[45].setCount(slots[45].getCount()-1);
							}
						}
					}
				}
			}
			else
			{
				if(slots[36].getID()==0&&keyboard.isShift())
				{
					slots[slot].setChanged(true);
					int oldID, oldID2, oldCount;
					oldID=slots[slot].getID();
					oldID2=slots[slot].getID2();
					int ffs=getFirstSlot(oldID, oldID2);
					oldCount=slots[ffs].getCount();	
					slots[ffs].setID2(oldID2);
					slots[ffs].setID(oldID);
					if(slots[ffs].getCount()+slots[slot].getCount()<64)
					{
						if(ffs!=slot)
						{
							slots[ffs].setCount(slots[slot].getCount()+oldCount);
							slots[slot].setCount(0);
							slots[slot].setID(0);
							slots[slot].setID2(0);
						}	
					}else 
					{
						slots[ffs].setCount(64);
						slots[slot].setCount(slots[slot].getCount()-(64-oldCount));
					}
				}
				else
				{
					if(slots[36].getID()==slots[slot].getID()&&slots[36].getID2()==slots[slot].getID2())
					{
						if(slots[slot].getChanged()==false)
						{
							slots[slot].setChanged(true);
							if(asc<1)
							{
								int oldID, oldID2, oldCount;
								oldID=slots[slot].getID();
								oldID2=slots[slot].getID2();
								oldCount=slots[slot].getCount();	
								slots[slot].setID(oldID);
								slots[slot].setID2(oldID2);
								if(slots[slot].getCount()+slots[36].getCount()<64)
								{
									slots[slot].setCount(slots[36].getCount()+oldCount);
									slots[36].setCount(0);
									slots[36].setID(0);
									slots[36].setID2(0);
								}else 
								{
									slots[slot].setCount(64);
									slots[36].setCount(slots[36].getCount()-(64-oldCount));
								}
							}else 
							{
								int c=0;
								for(int x=0; x<74; x++)
								{
									if(slots[x].getChanged()||x==slot)
									{
									    c++;
									}
								}
								int n=0;
								for(int x=0; x<74; x++)
								{
									if(slots[x].getChanged()||x==slot)
									{
										if(slots[x].getID()==id||slots[x].getID()==item.AIR)
										{
											slots[x].setCount(slots[x].getOldCount()+count/c);
											n+=count/c;
											slots[x].setID(id);
											slots[x].setID2(id2);
										}else slots[x].setChanged(false);
										
									}
								}

								slots[36].setCount(count-n);
								slots[36].setID(id);
								slots[36].setID2(id2);
							}
						}
					}
					else 	
					if(slots[slot].getChanged()==false)
					{
						if(asc<1)
						{
							slots[slot].setChanged(true);
							int oldID, oldID2, oldCount;
							oldID=slots[36].getID();
							oldID2=slots[36].getID2();
							oldCount=slots[36].getCount();
							slots[36].setID(slots[slot].getID());
							slots[36].setID2(slots[slot].getID2());
							slots[slot].setID(oldID);
							slots[slot].setID2(oldID2);
							slots[36].setCount(slots[slot].getCount());
							slots[slot].setCount(oldCount);
						}
						else 
						{
							int c=0;
							for(int x=0; x<74; x++)
							{
								if(slots[x].getChanged()||x==slot)
								{
								    c++;
								}
							}
							for(int x=0; x<74; x++)
							{
								if(slots[x].getChanged()||x==slot)
								{
									if(slots[x].getID()==id||slots[x].getID()==item.AIR)
									{
										slots[x].setCount(slots[x].getOldCount()+count/c);
										slots[x].setID(id);
										slots[x].setID2(id2);
									}else slots[x].setChanged(false);
								}
							}
						}
					}
				}
			}
		}
		if(slot==OUT_OF_INVENTORY)
		{
			if(asc<1)
			{
				drop(slots[36].getID(), slots[36].getID2(), slots[36].getCount());
				slots[36].setID(0);
				slots[36].setCount(0);
				pickedUp=false;
			}
		}
	}
	public static void pickUpR(int slot)
	{
		int asc=0;
		for(int x=0; x<74; x++)
		{
			if(slots[x].getChanged())asc++;
			id=0;
			id2=0; 
			count=0;
		}
		if((slot!=NO_SLOT_SELECTED)&&(slot!=OUT_OF_INVENTORY))
		{
			if(slot==46)
			{
			}
			else 
			{
				if(slots[36].getID()==slots[slot].getID()&&slots[36].getID2()==slots[slot].getID2())
				{
					if(slots[36].getCount()>0)
					{	
						if(slots[slot].getChanged()==false)
						{
							slots[slot].setChanged(true);
							slots[slot].setCount(slots[slot].getCount()+1);
							slots[36].setCount(slots[36].getCount()-1);
						}
					}
				} 
				if(0==slots[slot].getID())
				{
					if(slots[36].getCount()>0)
					{
						if(slots[slot].getChanged()==false)
						{
							slots[slot].setChanged(true);
							slots[slot].setCount(slots[slot].getCount()+1);
							slots[slot].setID(slots[36].getID());
							slots[slot].setID2(slots[36].getID2());
							slots[36].setCount(slots[36].getCount()-1);
						}
					}
				}
				if(0==slots[36].getID())
				{
					if(asc==0)
					{
						slots[slot].setChanged(true);
						int oldID, oldID2, oldCount;
						oldID=slots[slot].getID();
						oldID2=slots[slot].getID2();
						oldCount=slots[slot].getCount();	
						slots[slot].setCount(oldCount/2);
						slots[36].setCount(slots[36].getCount()+(int)((oldCount+1)/2));
						slots[36].setID(oldID);
						slots[36].setID2(oldID2);
						keyboard.setRbutton(false);
					}
				}
			}
		}
		if(slot==OUT_OF_INVENTORY)
		{
			if(asc==0)
			{
				drop(slots[36].getID(), slots[36].getID2(), (int)((slots[36].getCount())/2));
				slots[36].setCount((int)(slots[36].getCount()+1)/2);
				keyboard.setRbutton(false);
			}
		}
	}
	public static void update()
	{	
		if(open)
		{
			if(container==2)
			{
				simulation.blocks[x][y].setSlots(0, slots[37].getID(), slots[37].getID2(), slots[37].getCount());
				simulation.blocks[x][y].setSlots(1, slots[38].getID(), slots[38].getID2(), slots[38].getCount());
				simulation.blocks[x][y].setSlots(2, slots[46].getID(), slots[46].getID2(), slots[46].getCount());
			}
			if(container==3)
			{
				for(int i=47; i<74; i++)
				{
					simulation.blocks[x][y].setSlots(i-47, slots[i].getID(), slots[i].getID2(), slots[i].getCount());
				}
			}
			
			updateCrafting();
			if(keyboard.isLbuttonr())
			{
				keyboard.lbuttonr=false;
				for(int i=0; i<74; i++)
				{
					slots[i].setChanged(false);
				}
			}
			if(keyboard.isRbuttonr())
			{
				keyboard.rbuttonr=false;
				for(int i=0; i<74; i++)
				{
					slots[i].setChanged(false);
				}
			}
			if(keyboard.lbutton)
			{	
				pickUp(getSlotFromMouse(keyboard.mx, keyboard.my));
			}
			if(keyboard.rbutton)
			{	
				pickUpR(getSlotFromMouse(keyboard.mx, keyboard.my));
			}
		}
		else 
		{
			if(keyboard.isQr())
			{
				keyboard.setQr(false);
				if(slots[selected].getCount()>0)
				{
					drop(getHandItem(), getHandItem2(), 1);
					slots[selected].setCount(slots[selected].getCount()-1);	
				}
			}
		}
		for(int x=0; x<47; x++)
		{
			if(slots[x].getCount()<=0)
			{
				slots[x].setID(0);
				slots[x].setID2(0);
				slots[x].setCount(0);
			}
		}
		
		if(keyboard.k1)inventory.setSelected((byte) 0);
		if(keyboard.k2)inventory.setSelected((byte) 1);
		if(keyboard.k3)inventory.setSelected((byte) 2);
		if(keyboard.k4)inventory.setSelected((byte) 3);
		if(keyboard.k5)inventory.setSelected((byte) 4);
		if(keyboard.k6)inventory.setSelected((byte) 5);
		if(keyboard.k7)inventory.setSelected((byte) 6);
		if(keyboard.k8)inventory.setSelected((byte) 7);
		if(keyboard.k9)inventory.setSelected((byte) 8);
		if(keyboard.e)
		{
			if(inventory.isOpen())
			{
				open=false;
				drop(slots[36].getID(), slots[36].getID2(), slots[36].getCount());
				slots[36].setID(0);
				slots[36].setCount(0);
				if(container!=2&&container!=3)
				{
					for(int i=36; i<46; i++)
					{
						drop(slots[i].getID(), slots[i].getID2(), slots[i].getCount());
						slots[i].setID(0);
						slots[i].setCount(0);
					}
				}
				else if(container==2)
				{
					for(int i=36; i<47; i++)
					{
						slots[i].setID(0);
						slots[i].setCount(0);
					}
				}
				else if(container==3)
				{
					for(int i=36; i<74; i++)
					{
						slots[i].setID(0);
						slots[i].setCount(0);
					}
				}
			}else 
			{
				open=true;
				container=0;
			}
			keyboard.setE(false);
		}
	}
	inventory()
	{	
		for(int x=0; x<74; x++)
		{	
			slots[x]=new slot(0,0);
		}
		slots[0].setID(item.CRAFTING_TABLE);
		slots[0].setCount(1);
		slots[1].setID(item.FURNACE);
		slots[1].setCount(1);
		slots[2].setID(item.PLANKS);
		slots[2].setCount(64);
		slots[3].setID(item.COBBLE);
		slots[3].setCount(64);
		slots[4].setID(item.DOOR);
		slots[4].setCount(64);
	}
	public static void updateCrafting()
	{
		if(container==0||container==1)
		{
			slots[46].setID(crafting.checkCrafting(slots[37].getID(), slots[38].getID(), slots[39].getID(), slots[40].getID(), slots[41].getID(), slots[42].getID(), slots[43].getID(), slots[44].getID(), slots[45].getID(),  container));
//			slots[46].setID(crafting.checkCrafting2(slots[37].getID(), slots[38].getID(), slots[39].getID(), slots[40].getID(), slots[41].getID(), slots[42].getID(), slots[43].getID(), slots[44].getID(), slots[45].getID(),  container));
			slots[46].setCount(crafting.checkCount(slots[37].getID(), slots[38].getID(), slots[39].getID(), slots[40].getID(), slots[41].getID(), slots[42].getID(), slots[43].getID(), slots[44].getID(), slots[45].getID(),  container));
		}
	}
	public static void drop(int id, int id2, int count)
	{
		double rand=((Math.random()-0.5)/2);
		int p=0;
		int i=0;
		while(p<count && i<1000)
		{
			i++;
			if(!simulation.items[i].isAlive())
			{
				simulation.items[i].drop(id, id2, (-1*player.getPx())+7.4, (-1*player.getPy())+3.75);
				simulation.items[i].setVelocityX(rand);
				simulation.items[i].setVelocityY(-0.25);
				simulation.items[i].setAccelarationX(1.1);
				simulation.items[i].setAccelarationY(1.1);
				simulation.items[i].setCantcollect(100);
				p++;
			}
		}
	}
	public static void drop(int id, int id2, int count, int x, int y)
	{
		double rand=((Math.random()-0.5)/2);
		int p=0;
		int i=0;
		while(p<count && i<1000)
		{
			i++;
			if(!simulation.items[i].isAlive())
			{
				simulation.items[i].drop(id, id2, x, y);
				simulation.items[i].setVelocityX(rand);
				simulation.items[i].setVelocityY(-0.25);
				simulation.items[i].setAccelarationX(1.1);
				simulation.items[i].setAccelarationY(1.1);
				simulation.items[i].setCantcollect(100);
				p++;
			}
		}
	}
	public static void draw(Graphics g)
	{
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.white);

		
		if(open)
		{
			if(container==0)
			{
				g.drawImage(imageLoader.inv, frame.getWIDTH()/2-imageLoader.inv.getWidth()/4,frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4,imageLoader.inv.getWidth()/2, imageLoader.inv.getHeight()/2, null);
				g.drawImage(imageLoader.skin, frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+(int)(1000/15),frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+(int)(frame.getRHEIGHT()/23),imageLoader.skin.getWidth()/4, imageLoader.skin.getHeight()/4, null);
			}
			if(container==1)
			{
				g.drawImage(imageLoader.crafting, frame.getWIDTH()/2-imageLoader.crafting.getWidth()/4,frame.getRHEIGHT()/2-imageLoader.crafting.getHeight()/4,imageLoader.crafting.getWidth()/2, imageLoader.crafting.getHeight()/2, null);
			}
			if(container==2)
			{
				g.drawImage(imageLoader.oven, frame.getWIDTH()/2-imageLoader.oven.getWidth()/4,frame.getRHEIGHT()/2-imageLoader.oven.getHeight()/4, imageLoader.oven.getWidth()/2, imageLoader.oven.getHeight()/2, null);
				BufferedImage dst = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);    
				dst=imageLoader.pb;
				dst=dst.getSubimage(0, 0, (int)(simulation.blocks[x][y].getPb())+2, 67);
				
				g.drawImage(dst, frame.getWIDTH()/2-imageLoader.oven.getWidth()/4+157,frame.getRHEIGHT()/2-imageLoader.oven.getHeight()/4+68, ((int)(simulation.blocks[x][y].getPb())+2)/2, imageLoader.pb.getHeight()/2, null);
			}
			if(container==3)
			{
				g.drawImage(imageLoader.chest, frame.getWIDTH()/2-imageLoader.chest.getWidth()/4,frame.getRHEIGHT()/2-imageLoader.chest.getHeight()/4,imageLoader.chest.getWidth()/2, imageLoader.chest.getHeight()/2, null);
			}
			for(int x=0; x<74; x++)
			{
				if(x!=36)
				{
					g.drawImage(imageLoader.getTextures()[slots[x].getID()][0][slots[x].getID2()], getDrawPosX(x), getDrawPosY(x), 28, 28, null);
					if(slots[x].getCount()!=0&&slots[x].getCount()!=1)
					{
						g.drawString(""+slots[x].getCount(), getDrawPosX(x), getDrawPosY(x));
					}
				}
			}
		}
		g.drawImage(imageLoader.gui, frame.getWIDTH()/4, frame.getRHEIGHT()-frame.getRHEIGHT()/8, frame.getWIDTH()/2, frame.getWIDTH()/20, null);
		for(int x=0; x<9; x++)
		{
			g.drawImage(imageLoader.getTextures()[slots[x].getID()][0][slots[x].getID2()], x*(int)(frame.getWIDTH()/18.13)+(int)(frame.getWIDTH()/3.75), (frame.getRHEIGHT()-(int)(frame.getRHEIGHT()/8)+(int)(frame.getWIDTH()/80)), frame.getWIDTH()/35, frame.getWIDTH()/35 ,null);
			if(slots[x].getCount()!=0&&slots[x].getCount()!=1)
			{
				g.drawString(""+slots[x].getCount(), x*(int)(frame.getWIDTH()/18.13)+(int)(frame.getWIDTH()/3.75), (frame.getRHEIGHT()-(int)(frame.getRHEIGHT()/8)+(int)(frame.getWIDTH()/80)));
			}
		}
		g.drawImage(imageLoader.sel, (selected)*frame.getWIDTH()/18+(int)(frame.getWIDTH()/3.85), (frame.getRHEIGHT()-(int)(frame.getRHEIGHT()/8)+(int)(frame.getWIDTH()/130)), frame.getWIDTH()/25, frame.getWIDTH()/25 ,null);
		if(slots[36].getCount()!=0&&slots[36].getCount()!=1)
		{
			g.drawString(""+slots[36].getCount(), keyboard.getMx(), keyboard.getMy());
		}
		g.drawImage(imageLoader.getTextures()[slots[36].getID()][0][slots[36].getID2()], keyboard.getMx(), keyboard.getMy(), 28, 28, null);
	}
	public static byte getSelected() {
		return selected;
	}
	public static int getDrawPosX(int slot)
	{
		if(container==0)
		{
			if(slot<9)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+slot*35;
			}
			if(slot<18)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-9)*35;
			}
			if(slot<27)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-18)*35;
			}
			if(slot<36)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-27)*35;
			}
			if(slot<39)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-32)*35;
			}
			if(slot<41)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-34)*35;
			}
			if(slot==46)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+30+(slot-38)*35;
			}
		}if(container==1)
		{
			if(slot<9)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+slot*35;
			}
			if(slot<18)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-9)*35;
			}
			if(slot<27)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-18)*35;
			}
			if(slot<36)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-27)*35;
			}
			if(slot<40)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4-78+(slot-33)*35;
			}
			if(slot<43)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4-78+(slot-36)*35;
			}
			if(slot<46)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4-78+(slot-39)*35;
			}
			if(slot<47)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+40+(slot-40)*35;
			}
		}
		if(container==2)
		{
			if(slot<9)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+slot*35;
			}
			if(slot<18)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-9)*35;
			}
			if(slot<27)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-18)*35;
			}
			if(slot<36)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-27)*35;
			}
			if(slot<38)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+10+(slot-34)*35;
			}
			if(slot<39)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+10+(slot-35)*35;
			}
			if(slot==46)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-40)*35;
			}
		}
		if(container==3)
		{
			if(slot<9)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+slot*35;
			}
			if(slot<18)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-9)*35;
			}
			if(slot<27)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-18)*35;
			}
			if(slot<36)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-27)*35;
			}
			if(slot<56&&slot>46)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-47)*35;
			}
			if(slot<65)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-56)*35;
			}
			if(slot<74)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-65)*35;
			}
		}
		if(container==4)
		{
			if(slot<9)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+slot*35;
			}
			if(slot<18)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-9)*35;
			}
			if(slot<27)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-18)*35;
			}
			if(slot<36)
			{
				return frame.getWIDTH()/2-imageLoader.inv.getWidth()/4+24+(slot-27)*35;
			}
		}return 0;
	}
	public static int getDrawPosY(int slot)
	{
		if(container==0)
		{
			if(slot<9)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+287;
			}
			if(slot<18)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+174;
			}
			if(slot<27)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+209;
			}
			if(slot<36)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+244;
			}
			if(slot<39)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+40;
			}
			if(slot<41)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+75;
			}
			if(slot==46)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+55;
			}
		}if(container==1)
		{
			if(slot<9)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+287;
			}
			if(slot<18)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+174;
			}
			if(slot<27)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+209;
			}
			if(slot<36)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+244;
			}
			if(slot<40)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+40;
			}
			if(slot<43)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+75;
			}
			if(slot<46)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+110;
			}
			if(slot<47)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+75;
			}
		}
		if(container==2)
		{
			if(slot<9)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+287;
			}
			if(slot<18)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+174;
			}
			if(slot<27)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+209;
			}
			if(slot<36)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+244;
			}
			if(slot<38)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+38;
			}
			if(slot<39)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+108;
			}
			if(slot==46)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+70;
			}
		}
		if(container==3)
		{
			if(slot<9)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+287;
			}
			if(slot<18)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+174;
			}
			if(slot<27)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+209;
			}
			if(slot<36)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+244;
			}
			if(slot<56&&slot>46)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+34;
			}
			if(slot<65)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+69;
			}
			if(slot<74)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+104;
			}
		}
		if(container==4)
		{
			if(slot<9)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+340;
			}
			if(slot<18)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+227;
			}
			if(slot<27)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+262;
			}
			if(slot<36)
			{
				return frame.getRHEIGHT()/2-imageLoader.inv.getHeight()/4+297;	
			}
		}
		return 0;
	}
	public static void setSelected(byte selected) {
		inventory.selected = selected;
	}
	public static int getHandItem()
	{
		return slots[selected].getID();
	}
	public static int getHandItem2()
	{
		return slots[selected].getID2();
	}
	public static void collectItem(int iD,int id2) 
	{
		int ffs=getFirstSlot(iD, id2);
		slots[ffs].setCount(slots[ffs].getCount()+1);
		slots[ffs].setID(iD);
		slots[ffs].setID2(id2);
	}
	public static int getSlotFromMouse(int x, int y)
	{
		int slot=NO_SLOT_SELECTED;
		double x2,y2,w2,h2;
		x2=frame.getWIDTH()/2-imageLoader.crafting.getWidth()/4;
		y2=frame.getRHEIGHT()/2-imageLoader.crafting.getHeight()/4;
		w2=x2+imageLoader.crafting.getWidth()/2;
		h2=y2+imageLoader.crafting.getHeight()/2;
		if(!(x2<=x && x<=w2 && y2<=y && y<=h2))
		{
			slot=OUT_OF_INVENTORY;
		}
		for(int i=0; i<74; i++)
		{
			x2=getDrawPosX(i);
			y2=getDrawPosY(i);
			w2=getDrawPosX(i)+35;
			h2=getDrawPosY(i)+35;
			
			if(x2<=x && x<=w2 && y2<=y && y<=h2)
			{
				slot=i;
			}
		}
		return slot;
	}
	public static int getFirstSlot(int ID, int ID2)
	{
		for(int x=0; x<9; x++)
		{
			if(slots[x].getID()==ID&&slots[x].getID2()==ID2)
			{
				if(slots[x].getCount()<=63)
				{
					return  x;
				}
			}
		}
		for(int x=0; x<36; x++)
		{
			if(slots[x].getID()==ID&&slots[x].getID2()==ID2)
			{
				if(slots[x].getCount()<=63)
				{
					return  x;
				}
			}
			if(slots[x].getID()==0)
			{
				return x;
			}
		}
		return FULL;
	}
}
