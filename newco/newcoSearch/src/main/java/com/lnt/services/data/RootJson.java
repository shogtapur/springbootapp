package com.lnt.services.data;

public class RootJson {
	
	Contents contents[];
	
	Facets facets;
	
	String error;
	Header header;
	public RootJson() {
		// TODO Auto-generated constructor stub
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Facets getFacets() {
		return facets;
	}
	public void setFacets(Facets facets) {
		this.facets = facets;
	}
	
	public Contents[] getContents() {
		return contents;
	}
	public void setContents(Contents[] contents) {
		this.contents = contents;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println("facets :"+facets);
		System.out.println("error :"+error);
		System.out.println("header/Total :"+header.getTotal());
		System.out.println("contents.length "+contents.length);
		for(int i=0;i<contents.length;i++) {
			System.out.println("************Contents*********");
			System.out.println("type :  "+contents[i].getType());
			System.out.println("adult :  "+contents[i].isAdult());
			System.out.println("source :  "+contents[i].getSource());
			System.out.println("instanceID :  "+contents[i].getInstanceId());
			System.out.println("title :  "+contents[i].getTitle());
			System.out.println("provider :  "+contents[i].getProvider());
			System.out.println("isCatchUp :  "+contents[i].isCatchUp);
			System.out.println("isRestart :  "+contents[i].isRestart);
			System.out.println("ParentalRating/rating :  "+contents[i].parentalRating.getRating());
			System.out.println("ParentalRating/scheme :  "+contents[i].parentalRating.getScheme());
			if(contents[i].media != null) {
			for(int j=0;j<contents[i].media.length;j++) {
				System.out.println("*****************Media**********");
				
				System.out.println("Media/Classification : "+contents[i].media[j].classification);
				System.out.println("Media/MimeType : "+contents[i].media[j].mimeType);
				System.out.println("Media/URI : "+contents[i].media[j].getUri());
				System.out.println("Media/adult : "+contents[i].media[j].adult);
			}
			}
		}
		return super.toString();
	}
	
	
}
