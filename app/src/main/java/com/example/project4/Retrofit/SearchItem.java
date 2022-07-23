package com.example.project4.Retrofit;

import com.google.gson.annotations.SerializedName;

public class SearchItem{
	private String name;

	@Override
	public String toString() {
		return "SearchItem{" +
				"name='" + name + '\'' +
				", type='" + type + '\'' +
				", year='" + year + '\'' +
				", imdbID='" + imdbID + '\'' +
				", poster='" + poster + '\'' +
				", title='" + title + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@SerializedName("Type")
	private String type;

	@SerializedName("Year")
	private String year;

	@SerializedName("imdbID")
	private String imdbID;

	@SerializedName("Poster")
	private String poster;

	@SerializedName("Title")
	private String title;

	public String getType(){
		return type;
	}

	public String getYear(){
		return year;
	}

	public String getImdbID(){
		return imdbID;
	}

	public String getPoster(){
		return poster;
	}

	public String getTitle(){
		return title;
	}
}