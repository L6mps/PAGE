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
	public CatalogueItem(int location, int count, int maakond) {
		this.count = count;
		this.location = locToString(maakond, location);
	}
	
	private String locToString(int l1, int l2) {
		switch(l1) {
			case 1: { 
				switch(l2) {
					case 1: return "Aegviidu vald";
					case 2: return "Anija vald";
					case 3: return "Harku vald";
					case 4: return "J6el2htme vald";
					case 5: return "Keila vald";
					case 6: return "Kernu vald";
					case 7: return "Kiili vald";
					case 8: return "Kose vald";
					case 9: return "Kuusalu vald";
					case 10: return "Nissi vald";
					case 11: return "Padise vald";
					case 12: return "Raasiku vald";
					case 13: return "Rae vald";
					case 14: return "Saku vald";
					case 15: return "Saue vald";
					case 16: return "Vasalemma vald";
					case 17: return "Viimsi vald";
				}
				break;}
			case 2: {
				switch(l2) {
					case 1: return "Emmaste vald";
					case 2: return "Hiiu vald";
					case 3: return "K2ina vald";
					case 4: return "Pyhalepa vald";
				}
				break;
			}
			case 3: {
				switch(l2) {
					case 1: return "Alaj6e vald";
					case 2: return "Aseri vald";
					case 3: return "Avinurme vald";
					case 4: return "Iisaku vald";
					case 5: return "Illuka vald";
					case 6: return "J6hvi vald";
					case 7: return "Kohtla vald";
					case 8: return "Kohtla-N6mme vald";
					case 9: return "Lohusuu vald";
					case 10: return "Lyganuse vald";
					case 11: return "Maidla vald";
					case 12: return "M2etaguse vald";
					case 13: return "Sonda vald";
					case 14: return "Toila vald";
					case 15: return "Tudulinna vald";
					case 16: return "Vaivara vald";
				}
				break;
			}
			case 4: {
				switch(l2) {
					case 1: return "J6geva vald";
					case 2: return "Kasep22 vald";
					case 3: return "Pajusi vald";
					case 4: return "Pala vald";
					case 5: return "Palamuse vald";
					case 6: return "Puurmani vald";
					case 7: return "P6ltsamaa vald";
					case 8: return "Saare vald";
					case 9: return "Tabivere vald";
					case 10: return "Torma vald";
				}
				break;
			}
			case 5: {
				switch(l2) {
					case 1: return "Albu vald";
					case 2: return "Ambla vald";
					case 3: return "Imavere vald";
					case 4: return "J2rva-Jaani vald";
					case 5: return "Kareda vald";
					case 6: return "Koeru vald";
					case 7: return "Koigi vald";
					case 8: return "Paide vald";
					case 9: return "Roosna-Alliku vald";
					case 10: return "Tyri vald";
					case 11: return "V22tsa vald";
				}
				break;
			}
			case 6: {
				switch(l2) {
					case 1: return "Hanila vald";
					case 2: return "Kullamaa vald";
					case 3: return "Lihula vald";
					case 4: return "Martna vald";
					case 5: return "Noarootsi vald";
					case 6: return "N6va vald";
					case 7: return "Oru vald";
					case 8: return "Ridala vald";
					case 9: return "Risti vald";
					case 10: return "Taebla vald";
					case 11: return "Vormsi vald";
				}
				break;
			}
			case 7: {
				switch(l2) {
					case 1: return "Haljala vald";
					case 2: return "Kadrina vald";
					case 3: return "Laekvere vald";
					case 4: return "Rakke vald";
					case 5: return "Rakvere vald";
					case 6: return "R2gavere vald";
					case 7: return "Saksi vald";
					case 8: return "S6meru vald";
					case 9: return "Tamsalu vald";
					case 10: return "Vihula vald";
					case 11: return "Vinni vald";
					case 12: return "Viru-Nigula vald";
					case 13: return "V2ike-Maarja vald";
				}
				break;
			}
			case 8: {
				switch(l2) {
					case 1: return "Ahja vald";
					case 2: return "Kanepi vald";
					case 3: return "K6lleste vald";
					case 4: return "Laheda vald";
					case 5: return "Mikitam2e vald";
					case 6: return "Mooste vald";
					case 7: return "Orava vald";
					case 8: return "P6lva vald";
					case 9: return "R2pina vald";
					case 10: return "Valgj2rve vald";
					case 11: return "Vastse-Kuuste vald";
					case 12: return "Veriora vald";
					case 13: return "V2rska vald";
				}
				break;
			}
			case 9: {
				switch(l2) {
					case 1: return "Are vald";
					case 2: return "Audru vald";
					case 3: return "Halinga vald";
					case 4: return "H22demeeste vald";
					case 5: return "Kihnu vald";
					case 6: return "Koonga vald";
					case 7: return "Lavassaare vald";
					case 10: return "Paikuse vald";
					case 11: return "Saarde vald";
					case 12: return "Sauga vald";
					case 13: return "Surju vald";
					case 14: return "Tahkuranna vald";
					case 15: return "Tali vald";
					case 16: return "Tootsi vald";
					case 17: return "Tori vald";
					case 18: return "T6stamaa vald";
					case 19: return "Varbla vald";
					case 20: return "V2ndra vald";
				}
				break;
			}
			case 10: {
				switch(l2) {
					case 1: return "Juuru vald";
					case 2: return "J2rvakandi vald";
					case 3: return "Kaiu vald";
					case 4: return "Kehtna vald";
					case 5: return "Kohila vald";
					case 6: return "K2ru vald";
					case 7: return "M2rjamaa vald";
					case 8: return "Raikkyla vald";
					case 9: return "Rapla vald";
					case 10: return "Vigala vald";
				}
				break;
			}
			case 11: {
				switch(l2) {
					case 1: return "Kaarma vald";
					case 2: return "Kihelkonna vald";
					case 3: return "K2rla vald";
					case 4: return "Laimjala vald";
					case 5: return "Leisi vald";
					case 6: return "Lymanda vald";
					case 7: return "Muhu vald";
					case 8: return "Mustjala vald";
					case 9: return "Orissaare vald";
					case 10: return "Pihtla vald";
					case 11: return "P8ide vald";
					case 12: return "Ruhnu vald";
					case 13: return "Salme vald";
					case 14: return "Torgu vald";
					case 15: return "Valjala vald";
				}
				break;
			}
			case 12: {
				switch(l2) {
					case 1: return "Alatskivi vald";
					case 2: return "Haaslava vald";
					case 3: return "Kambja vald";
					case 4: return "Konguta vald";
					case 5: return "Laeva vald";
					case 6: return "Luunja vald";
					case 7: return "Meeksi vald";
					case 8: return "M2ksa vald";
					case 9: return "N6o vald";
					case 10: return "Peipsi22re vald";
					case 11: return "Piirissaare vald";
					case 12: return "Puhja vald";
					case 13: return "Rannu vald";
					case 14: return "R6ngu vald";
					case 15: return "Tartu vald";
					case 16: return "T2htvere vald";
					case 17: return "Vara vald";
					case 18: return "V6nnu vald";
					case 19: return "ylenurme vald";
				}
				break;
			}
			case 13: {
				switch(l2) {
					case 1: return "Helme vald";
					case 2: return "Hummuli vald";
					case 3: return "Karula vald";
					case 4: return "Otep22 vald";
					case 5: return "Palupera vald";
					case 6: return "Puka vald";
					case 7: return "P6drala vald";
					case 8: return "Sangaste vald";
					case 9: return "Taheva vald";
					case 10: return "T6lliste vald";
					case 11: return "6ru vald";
				}
				break;
			}
			case 14: {
				switch(l2) {
					case 1: return "Abja vald";
					case 2: return "Halliste vald";
					case 3: return "Karksi vald";
					case 4: return "Kolga-Jaani vald";
					case 5: return "K6o vald";
					case 6: return "K6pu vald";
					case 7: return "Olustvere vald";
					case 8: return "Suure-Jaani vald";
					case 9: return "Tarvastu vald";
					case 10: return "Vastem6isa vald";
					case 11: return "Viljandi vald";
				}
				break;
			}
			case 15: {
				switch(l2) {
					case 1: return "Antsla vald";
					case 2: return "Haanja vald";
					case 3: return "Lasva vald";
					case 4: return "Merem2e vald";
					case 5: return "Misso vald";
					case 6: return "M6niste vald";
					case 7: return "R6uge vald";
					case 8: return "S6merpalu vald";
					case 9: return "Urvaste vald";
					case 10: return "Varstu vald";
					case 11: return "Vastseliina vald";
					case 12: return "V6ru vald";
				}
				break;
			}
			
		}
		return "Muu";
	}
}
