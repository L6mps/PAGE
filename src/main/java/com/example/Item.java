package com.example;

public class Item extends Object{
	private String name;
	private int price;
	private String loc1;
	private String loc2;
	private String[] additionals;
	private int mainopts;
	public Item(String name, int price, int loc1, int loc2, int mainopts) {
		super();
		this.name = name;
		this.price = price;
		this.additionals = null;
		locToString(loc1, loc2);
		this.mainopts = mainopts;
		generateBinary();
	}
	
	public Item(String name, int price, int loc1, int loc2) {
		super();
		this.name = name;
		this.price = price;
		this.additionals = null;
		locToString(loc1, loc2);
	}

	private void generateBinary(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLoc1() {
		return loc1;
	}
	public String[] getAdditionals() {
		return additionals;
	}
	public void setAdditionals(String[] additionals) {
		this.additionals = additionals;
	}
	public int getMainopts() {
		return mainopts;
	}
	public void setMainopts(int mainopts) {
		this.mainopts = mainopts;
	}
	
	private void locToString(int l1, int l2) {
		switch(l1) {
			case 1: { 
				loc1 = "Harju maakond";
				switch(l2) {
					case 1: loc2="Aegviidu vald"; break;
					case 2: loc2="Anija vald"; break;
					case 3: loc2="Harku vald"; break;
					case 4: loc2="Jõelähtme vald"; break;
					case 5: loc2="Keila vald"; break;
					case 6: loc2="Kernu vald"; break;
					case 7: loc2="Kiili vald"; break;
					case 8: loc2="Kose vald"; break;
					case 9: loc2="Kuusalu vald"; break;
					case 10: loc2="Nissi vald"; break;
					case 11: loc2="Padise vald"; break;
					case 12: loc2="Raasiku vald"; break;
					case 13: loc2="Rae vald"; break;
					case 14: loc2="Saku vald"; break;
					case 15: loc2="Saue vald"; break;
					case 16: loc2="Vasalemma vald"; break;
					case 17: loc2="Viimsi vald"; break;
				}
				break;}
			case 2: {
				loc1 = "Hiiu maakond";
				switch(l2) {
					case 1: loc2="Emmaste vald"; break;
					case 2: loc2="Hiiu vald"; break;
					case 3: loc2="Käina vald"; break;
					case 4: loc2="Pühalepa vald"; break;
				}
				break;
			}
			case 3: {
				loc1 = "Ida-Viru maakond";
				switch(l2) {
					case 1: loc2="Alajõe vald"; break;
					case 2: loc2="Aseri vald"; break;
					case 3: loc2="Avinurme vald"; break;
					case 4: loc2="Iisaku vald"; break;
					case 5: loc2="Illuka vald"; break;
					case 6: loc2="Jõhvi vald"; break;
					case 7: loc2="Kohtla vald"; break;
					case 8: loc2="Kohtla-Nõmme vald"; break;
					case 9: loc2="Lohusuu vald"; break;
					case 10: loc2="Lüganuse vald"; break;
					case 11: loc2="Maidla vald"; break;
					case 12: loc2="Mäetaguse vald"; break;
					case 13: loc2="Sonda vald"; break;
					case 14: loc2="Toila vald"; break;
					case 15: loc2="Tudulinna vald"; break;
					case 16: loc2="Vaivara vald"; break;
				}
				break;
			}
			case 4: {
				loc1 = "Jõgeva maakond";
				switch(l2) {
					case 1: loc2="Jõgeva vald"; break;
					case 2: loc2="Kasepää vald"; break;
					case 3: loc2="Pajusi vald"; break;
					case 4: loc2="Pala vald"; break;
					case 5: loc2="Palamuse vald"; break;
					case 6: loc2="Puurmani vald"; break;
					case 7: loc2="Põltsamaa vald"; break;
					case 8: loc2="Saare vald"; break;
					case 9: loc2="Tabivere vald"; break;
					case 10: loc2="Torma vald"; break;
				}
				break;
			}
			case 5: {
				loc1 = "Järva maakond";
				switch(l2) {
					case 1: loc2="Albu vald"; break;
					case 2: loc2="Ambla vald"; break;
					case 3: loc2="Imavere vald"; break;
					case 4: loc2="Järva-Jaani vald"; break;
					case 5: loc2="Kareda vald"; break;
					case 6: loc2="Koeru vald"; break;
					case 7: loc2="Koigi vald"; break;
					case 8: loc2="Paide vald"; break;
					case 9: loc2="Roosna-Alliku vald"; break;
					case 10: loc2="Türi vald"; break;
					case 11: loc2="Väätsa vald"; break;
				}
				break;
			}
			case 6: {
				loc1 = "Lääne maakond";
				switch(l2) {
					case 1: loc2="Hanila vald"; break;
					case 2: loc2="Kullamaa vald"; break;
					case 3: loc2="Lihula vald"; break;
					case 4: loc2="Martna vald"; break;
					case 5: loc2="Noarootsi vald"; break;
					case 6: loc2="Nõva vald"; break;
					case 7: loc2="Oru vald"; break;
					case 8: loc2="Ridala vald"; break;
					case 9: loc2="Risti vald"; break;
					case 10: loc2="Taebla vald"; break;
					case 11: loc2="Vormsi vald"; break;
				}
				break;
			}
			case 7: {
				loc1 = "Lääne-Viru maakond";
				switch(l2) {
					case 1: loc2="Haljala vald"; break;
					case 2: loc2="Kadrina vald"; break;
					case 3: loc2="Laekvere vald"; break;
					case 4: loc2="Rakke vald"; break;
					case 5: loc2="Rakvere vald"; break;
					case 6: loc2="Rägavere vald"; break;
					case 7: loc2="Saksi vald"; break;
					case 8: loc2="Sõmeru vald"; break;
					case 9: loc2="Tamsalu vald"; break;
					case 10: loc2="Vihula vald"; break;
					case 11: loc2="Vinni vald"; break;
					case 12: loc2="Viru-Nigula vald"; break;
					case 13: loc2="Väike-Maarja vald"; break;
				}
				break;
			}
			case 8: {
				loc1 = "Põlva maakond";
				switch(l2) {
					case 1: loc2="Ahja vald"; break;
					case 2: loc2="Kanepi vald"; break;
					case 3: loc2="Kõlleste vald"; break;
					case 4: loc2="Laheda vald"; break;
					case 5: loc2="Mikitamäe vald"; break;
					case 6: loc2="Mooste vald"; break;
					case 7: loc2="Orava vald"; break;
					case 8: loc2="Põlva vald"; break;
					case 9: loc2="Räpina vald"; break;
					case 10: loc2="Valgjärve vald"; break;
					case 11: loc2="Vastse-Kuuste vald"; break;
					case 12: loc2="Veriora vald"; break;
					case 13: loc2="Värska vald"; break;
				}
				break;
			}
			case 9: {
				loc1 = "Pärnu maakond";
				switch(l2) {
					case 1: loc2="Are vald"; break;
					case 2: loc2="Audru vald"; break;
					case 3: loc2="Halinga vald"; break;
					case 4: loc2="Häädemeeste vald"; break;
					case 5: loc2="Kihnu vald"; break;
					case 6: loc2="Koonga vald"; break;
					case 7: loc2="Lavassaare vald"; break;
					case 10: loc2="Paikuse vald"; break;
					case 11: loc2="Saarde vald"; break;
					case 12: loc2="Sauga vald"; break;
					case 13: loc2="Surju vald"; break;
					case 14: loc2="Tahkuranna vald"; break;
					case 15: loc2="Tali vald"; break;
					case 16: loc2="Tootsi vald"; break;
					case 17: loc2="Tori vald"; break;
					case 18: loc2="Tõstamaa vald"; break;
					case 19: loc2="Varbla vald"; break;
					case 20: loc2="Vändra vald"; break;
				}
				break;
			}
			case 10: {
				loc1 = "Rapla maakond";
				switch(l2) {
					case 1: loc2="Juuru vald"; break;
					case 2: loc2="Järvakandi vald"; break;
					case 3: loc2="Kaiu vald"; break;
					case 4: loc2="Kehtna vald"; break;
					case 5: loc2="Kohila vald"; break;
					case 6: loc2="Käru vald"; break;
					case 7: loc2="Märjamaa vald"; break;
					case 8: loc2="Raikküla vald"; break;
					case 9: loc2="Rapla vald"; break;
					case 10: loc2="Vigala vald"; break;
				}
				break;
			}
			case 11: {
				loc1 = "Saare maakond";
				switch(l2) {
					case 1: loc2="Kaarma vald"; break;
					case 2: loc2="Kihelkonna vald"; break;
					case 3: loc2="Kärla vald"; break;
					case 4: loc2="Laimjala vald"; break;
					case 5: loc2="Leisi vald"; break;
					case 6: loc2="Lümanda vald"; break;
					case 7: loc2="Muhu vald"; break;
					case 8: loc2="Mustjala vald"; break;
					case 9: loc2="Orissaare vald"; break;
					case 10: loc2="Pihtla vald"; break;
					case 11: loc2="Pöide vald"; break;
					case 12: loc2="Ruhnu vald"; break;
					case 13: loc2="Salme vald"; break;
					case 14: loc2="Torgu vald"; break;
					case 15: loc2="Valjala vald"; break;
				}
				break;
			}
			case 12: {
				loc1 = "Tartu maakond";
				switch(l2) {
					case 1: loc2="Alatskivi vald"; break;
					case 2: loc2="Haaslava vald"; break;
					case 3: loc2="Kambja vald"; break;
					case 4: loc2="Konguta vald"; break;
					case 5: loc2="Laeva vald"; break;
					case 6: loc2="Luunja vald"; break;
					case 7: loc2="Meeksi vald"; break;
					case 8: loc2="Mäksa vald"; break;
					case 9: loc2="Nõo vald"; break;
					case 10: loc2="Peipsiääre vald"; break;
					case 11: loc2="Piirissaare vald"; break;
					case 12: loc2="Puhja vald"; break;
					case 13: loc2="Rannu vald"; break;
					case 14: loc2="Rõngu vald"; break;
					case 15: loc2="Tartu vald"; break;
					case 16: loc2="Tähtvere vald"; break;
					case 17: loc2="Vara vald"; break;
					case 18: loc2="Võnnu vald"; break;
					case 19: loc2="Ülenurme vald"; break;
				}
				break;
			}
			case 13: {
				loc1 = "Valga maakond";
				switch(l2) {
					case 1: loc2="Helme vald"; break;
					case 2: loc2="Hummuli vald"; break;
					case 3: loc2="Karula vald"; break;
					case 4: loc2="Otepää vald"; break;
					case 5: loc2="Palupera vald"; break;
					case 6: loc2="Puka vald"; break;
					case 7: loc2="Põdrala vald"; break;
					case 8: loc2="Sangaste vald"; break;
					case 9: loc2="Taheva vald"; break;
					case 10: loc2="Tõlliste vald"; break;
					case 11: loc2="Õru vald"; break;
				}
				break;
			}
			case 14: {
				loc1 = "Viljandi maakond";
				switch(l2) {
					case 1: loc2="Abja vald"; break;
					case 2: loc2="Halliste vald"; break;
					case 3: loc2="Karksi vald"; break;
					case 4: loc2="Kolga-Jaani vald"; break;
					case 5: loc2="Kõo vald"; break;
					case 6: loc2="Kõpu vald"; break;
					case 7: loc2="Olustvere vald"; break;
					case 8: loc2="Suure-Jaani vald"; break;
					case 9: loc2="Tarvastu vald"; break;
					case 10: loc2="Vastemõisa vald"; break;
					case 11: loc2="Viljandi vald"; break;
				}
				break;
			}
			case 15: {
				loc1 = "Võru maakond";
				switch(l2) {
					case 1: loc2="Antsla vald"; break;
					case 2: loc2="Haanja vald"; break;
					case 3: loc2="Lasva vald"; break;
					case 4: loc2="Meremäe vald"; break;
					case 5: loc2="Misso vald"; break;
					case 6: loc2="Mõniste vald"; break;
					case 7: loc2="Rõuge vald"; break;
					case 8: loc2="Sõmerpalu vald"; break;
					case 9: loc2="Urvaste vald"; break;
					case 10: loc2="Varstu vald"; break;
					case 11: loc2="Vastseliina vald"; break;
					case 12: loc2="Võru vald"; break;
				}
				break;
			}
		}
	}
}
