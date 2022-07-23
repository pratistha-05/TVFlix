package com.example.project4.Retrofit;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{
	@SerializedName("Response")
	private String response;

	@Override
	public String toString() {
		return "Response{" +
				"response='" + response + '\'' +
				", totalResults='" + totalResults + '\'' +
				", search=" + search +
				'}';
	}

	@SerializedName("totalResults")
	private String totalResults;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public void setSearch(List<SearchItem> search) {
		this.search = search;
	}

	@SerializedName("Search")
	private List<SearchItem> search;

	public List<SearchItem> getSearch(){
		return search;
	}
}