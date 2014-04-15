package com.example;

public class CatalogueItem extends Object {

	private String location;
	public String getLocation() {
		return location;
	}

	public int getCount() {
		return count;
	}

	private int count;
	
	public CatalogueItem(int location, int count) {
		this.count = count;
		if(location == 0)
			this.location = "Muu";
		else if(location == 1)
			this.location = "Harjumaa";
		else if(location == 2)
			this.location = "Hiiumaa";
		else if(location == 3)
			this.location = "Ida-Virumaa";
		else if(location == 4)
			this.location = "J6gevamaa";
		else if(location == 5)
			this.location = "J2rvamaa";
		else if(location == 6)
			this.location = "L22nemaa";
		else if(location == 7)
			this.location = "L22ne-Virumaa";
		else if(location == 8)
			this.location = "P6lvamaa";
		else if(location == 9)
			this.location = "P2rnumaa";
		else if(location == 10)
			this.location = "Raplamaa";
		else if(location == 11)
			this.location = "Saaremaa";
		else if(location == 12)
			this.location = "Tartumaa";
		else if(location == 13)
			this.location = "Valgamaa";
		else if(location == 14)
			this.location = "Viljandimaa";
		else if(location == 15)
			this.location = "V6rumaa";
		
	}
}
