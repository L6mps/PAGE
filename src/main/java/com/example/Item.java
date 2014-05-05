package com.example;

import java.util.ArrayList;
import java.util.List;

public class Item extends Object{
	private int id;
	private String name;
	private int price;
	private String loc1;
	private String loc2;
	private String[] additionals;
	public Item(int id, String name, int price, int loc1, int loc2, int mainopts) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		locToString(loc1, loc2);
		addAdditionals(mainopts);
		generateBinary();
	}
	
	private void addAdditionals(int mainopts) {
		List<String> temp=new ArrayList<String>();
		if(mainopts >= 8) {
			temp.add("Paid services");
			mainopts-=8;
		}
		if(mainopts >= 4) {
			temp.add("Nursing");
			mainopts-=4;
		}
		if(mainopts >= 2) {
			temp.add("Wheelchair");
			mainopts-=2;
		}
		if(mainopts >= 1) {
			temp.add("Demented");
		}
		additionals=new String[temp.size()];
		temp.toArray(additionals);
		
	}

	public Item(int id, String name, int price, int loc1, int loc2) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.additionals = null;
		locToString(loc1, loc2);
		
		System.out.println(this.loc2+" "+this.loc1);
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
	public String getLoc2() {
		return loc2;
	}
	public String[] getAdditionals() {
		return additionals;
	}
	public void setAdditionals(String[] additionals) {
		this.additionals = additionals;
	}
	private void locToString(int l1, int l2) {
		switch(l1) {
			case 1: { 
				loc1 = "Harju maakond";
				switch(l2) {
					case 1: loc2="Aegviidu vald"; break;
					case 2: loc2="Anija vald"; break;
					case 3: loc2="Harku vald"; break;
					case 4: loc2="J6el2htme vald"; break;
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
					case 3: loc2="K2ina vald"; break;
					case 4: loc2="Pyhalepa vald"; break;
				}
				break;
			}
			case 3: {
				loc1 = "Ida-Viru maakond";
				switch(l2) {
					case 1: loc2="Alaj6e vald"; break;
					case 2: loc2="Aseri vald"; break;
					case 3: loc2="Avinurme vald"; break;
					case 4: loc2="Iisaku vald"; break;
					case 5: loc2="Illuka vald"; break;
					case 6: loc2="J6hvi vald"; break;
					case 7: loc2="Kohtla vald"; break;
					case 8: loc2="Kohtla-N6mme vald"; break;
					case 9: loc2="Lohusuu vald"; break;
					case 10: loc2="Lyganuse vald"; break;
					case 11: loc2="Maidla vald"; break;
					case 12: loc2="M2etaguse vald"; break;
					case 13: loc2="Sonda vald"; break;
					case 14: loc2="Toila vald"; break;
					case 15: loc2="Tudulinna vald"; break;
					case 16: loc2="Vaivara vald"; break;
				}
				break;
			}
			case 4: {
				loc1 = "J6geva maakond";
				switch(l2) {
					case 1: loc2="J6geva vald"; break;
					case 2: loc2="Kasep22 vald"; break;
					case 3: loc2="Pajusi vald"; break;
					case 4: loc2="Pala vald"; break;
					case 5: loc2="Palamuse vald"; break;
					case 6: loc2="Puurmani vald"; break;
					case 7: loc2="P6ltsamaa vald"; break;
					case 8: loc2="Saare vald"; break;
					case 9: loc2="Tabivere vald"; break;
					case 10: loc2="Torma vald"; break;
				}
				break;
			}
			case 5: {
				loc1 = "J2rva maakond";
				switch(l2) {
					case 1: loc2="Albu vald"; break;
					case 2: loc2="Ambla vald"; break;
					case 3: loc2="Imavere vald"; break;
					case 4: loc2="J2rva-Jaani vald"; break;
					case 5: loc2="Kareda vald"; break;
					case 6: loc2="Koeru vald"; break;
					case 7: loc2="Koigi vald"; break;
					case 8: loc2="Paide vald"; break;
					case 9: loc2="Roosna-Alliku vald"; break;
					case 10: loc2="Tyri vald"; break;
					case 11: loc2="V22tsa vald"; break;
				}
				break;
			}
			case 6: {
				loc1 = "L22ne maakond";
				switch(l2) {
					case 1: loc2="Hanila vald"; break;
					case 2: loc2="Kullamaa vald"; break;
					case 3: loc2="Lihula vald"; break;
					case 4: loc2="Martna vald"; break;
					case 5: loc2="Noarootsi vald"; break;
					case 6: loc2="N6va vald"; break;
					case 7: loc2="Oru vald"; break;
					case 8: loc2="Ridala vald"; break;
					case 9: loc2="Risti vald"; break;
					case 10: loc2="Taebla vald"; break;
					case 11: loc2="Vormsi vald"; break;
				}
				break;
			}
			case 7: {
				loc1 = "L22ne-Viru maakond";
				switch(l2) {
					case 1: loc2="Haljala vald"; break;
					case 2: loc2="Kadrina vald"; break;
					case 3: loc2="Laekvere vald"; break;
					case 4: loc2="Rakke vald"; break;
					case 5: loc2="Rakvere vald"; break;
					case 6: loc2="R2gavere vald"; break;
					case 7: loc2="Saksi vald"; break;
					case 8: loc2="S6meru vald"; break;
					case 9: loc2="Tamsalu vald"; break;
					case 10: loc2="Vihula vald"; break;
					case 11: loc2="Vinni vald"; break;
					case 12: loc2="Viru-Nigula vald"; break;
					case 13: loc2="V2ike-Maarja vald"; break;
				}
				break;
			}
			case 8: {
				loc1 = "P6lva maakond";
				switch(l2) {
					case 1: loc2="Ahja vald"; break;
					case 2: loc2="Kanepi vald"; break;
					case 3: loc2="K6lleste vald"; break;
					case 4: loc2="Laheda vald"; break;
					case 5: loc2="Mikitam2e vald"; break;
					case 6: loc2="Mooste vald"; break;
					case 7: loc2="Orava vald"; break;
					case 8: loc2="P6lva vald"; break;
					case 9: loc2="R2pina vald"; break;
					case 10: loc2="Valgj2rve vald"; break;
					case 11: loc2="Vastse-Kuuste vald"; break;
					case 12: loc2="Veriora vald"; break;
					case 13: loc2="V2rska vald"; break;
				}
				break;
			}
			case 9: {
				loc1 = "P2rnu maakond";
				switch(l2) {
					case 1: loc2="Are vald"; break;
					case 2: loc2="Audru vald"; break;
					case 3: loc2="Halinga vald"; break;
					case 4: loc2="H22demeeste vald"; break;
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
					case 18: loc2="T6stamaa vald"; break;
					case 19: loc2="Varbla vald"; break;
					case 20: loc2="V2ndra vald"; break;
				}
				break;
			}
			case 10: {
				loc1 = "Rapla maakond";
				switch(l2) {
					case 1: loc2="Juuru vald"; break;
					case 2: loc2="J2rvakandi vald"; break;
					case 3: loc2="Kaiu vald"; break;
					case 4: loc2="Kehtna vald"; break;
					case 5: loc2="Kohila vald"; break;
					case 6: loc2="K2ru vald"; break;
					case 7: loc2="M2rjamaa vald"; break;
					case 8: loc2="Raikkyla vald"; break;
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
					case 3: loc2="K2rla vald"; break;
					case 4: loc2="Laimjala vald"; break;
					case 5: loc2="Leisi vald"; break;
					case 6: loc2="Lymanda vald"; break;
					case 7: loc2="Muhu vald"; break;
					case 8: loc2="Mustjala vald"; break;
					case 9: loc2="Orissaare vald"; break;
					case 10: loc2="Pihtla vald"; break;
					case 11: loc2="P8ide vald"; break;
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
					case 8: loc2="M2ksa vald"; break;
					case 9: loc2="N6o vald"; break;
					case 10: loc2="Peipsi22re vald"; break;
					case 11: loc2="Piirissaare vald"; break;
					case 12: loc2="Puhja vald"; break;
					case 13: loc2="Rannu vald"; break;
					case 14: loc2="R6ngu vald"; break;
					case 15: loc2="Tartu vald"; break;
					case 16: loc2="T2htvere vald"; break;
					case 17: loc2="Vara vald"; break;
					case 18: loc2="V6nnu vald"; break;
					case 19: loc2="ylenurme vald"; break;
				}
				break;
			}
			case 13: {
				loc1 = "Valga maakond";
				switch(l2) {
					case 1: loc2="Helme vald"; break;
					case 2: loc2="Hummuli vald"; break;
					case 3: loc2="Karula vald"; break;
					case 4: loc2="Otep22 vald"; break;
					case 5: loc2="Palupera vald"; break;
					case 6: loc2="Puka vald"; break;
					case 7: loc2="P6drala vald"; break;
					case 8: loc2="Sangaste vald"; break;
					case 9: loc2="Taheva vald"; break;
					case 10: loc2="T6lliste vald"; break;
					case 11: loc2="6ru vald"; break;
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
					case 5: loc2="K6o vald"; break;
					case 6: loc2="K6pu vald"; break;
					case 7: loc2="Olustvere vald"; break;
					case 8: loc2="Suure-Jaani vald"; break;
					case 9: loc2="Tarvastu vald"; break;
					case 10: loc2="Vastem6isa vald"; break;
					case 11: loc2="Viljandi vald"; break;
				}
				break;
			}
			case 15: {
				loc1 = "V6ru maakond";
				switch(l2) {
					case 1: loc2="Antsla vald"; break;
					case 2: loc2="Haanja vald"; break;
					case 3: loc2="Lasva vald"; break;
					case 4: loc2="Merem2e vald"; break;
					case 5: loc2="Misso vald"; break;
					case 6: loc2="M6niste vald"; break;
					case 7: loc2="R6uge vald"; break;
					case 8: loc2="S6merpalu vald"; break;
					case 9: loc2="Urvaste vald"; break;
					case 10: loc2="Varstu vald"; break;
					case 11: loc2="Vastseliina vald"; break;
					case 12: loc2="V6ru vald"; break;
				}
				break;
			}
		}
	}
}
