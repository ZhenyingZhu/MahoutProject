package zhenying.mahoutrecommender;

public class ItemType implements Comparable<ItemType> {
	private long itemId; 
	private float itemRating;  
	
	public ItemType(long itemId, float itemRating) {
		this.itemId = itemId; 
		this.itemRating = itemRating; 
	}

	public int compareTo(ItemType otherItem) {
		if (this.itemId == otherItem.itemId) {
			return 0;
		} else if (this.itemId > otherItem.itemId) {
			return 1; 
		} else {
			return -1; 
		}
		
	}
	
	public String toString() {
		return itemId + " " + itemRating; 
	}

}
