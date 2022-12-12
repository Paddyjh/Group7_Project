package project;

public class Item {
	String itemName; 
	String itemType;
	String expirationDate;
	int itemId;
	double price;
	
	public Item(String itemName, String itemType, String expirationDate, int itemId, double price)
	{
		this.itemName = itemName;
		this.itemType = itemType;
		this.expirationDate = expirationDate;
		this.itemId = itemId;
		this.price = price;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public void setItemType(String itemType)
	{
		this.itemType = itemType;
	}
	
	public void setExpirationDate(String expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	//Getter methods
	
	public String getItemName()
	{
		return this.itemName;
	}
	
	public String getItemType()
	{
		return this.itemType;
	}
	
	public String getExpirationDate()
	{
		return this.expirationDate;
	}

	public int getItemId()
	{
		return this.itemId;
	}
	
	public double getPrice()
	{
		return this.price;
	}
}
