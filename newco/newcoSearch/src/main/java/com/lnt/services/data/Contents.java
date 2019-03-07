package com.lnt.services.data;

import java.util.Arrays;

public class Contents {

	String id;
	String type;
	boolean adult;
	String source;
	String instanceId;
	String title;
	String provider;
	ParentalRating parentalRating;
	int userNibbles;
	String locator;
	public boolean isCatchUp = false;
	public boolean isRestart = false;
	Media media[];
	int duration;

	public Contents() {
		parentalRating = new ParentalRating();
	}

	public ParentalRating getParentalRating() {
		return parentalRating;
	}

	public void setParentalRating(ParentalRating rating) {
		this.parentalRating = rating;
	}

	public Media[] getMedia() {
		return media;
	}

	public void setMedia(Media[] media) {
		this.media = media;
	}

	public int getUserNibbles() {
		return userNibbles;
	}

	public void setUserNibbles(int userNibbles) {
		this.userNibbles = userNibbles;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	/*
	 * public boolean isCatchUp() { return isCatchUp; } public void
	 * setCatchUp(boolean isCatchUp) { this.isCatchUp = isCatchUp; } public boolean
	 * isRestart() { return isRestart; } public void setRestart(boolean isRestart) {
	 * this.isRestart = isRestart; }
	 */
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceID) {
		this.instanceId = instanceID;
	}

	@Override
	public String toString() {
		return "Contents [id=" + id + ", type=" + type + ", adult=" + adult + ", source=" + source + ", instanceId="
				+ instanceId + ", title=" + title + ", provider=" + provider + ", parentalRating=" + parentalRating
				+ ", userNibbles=" + userNibbles + ", locator=" + locator + ", isCatchUp=" + isCatchUp + ", isRestart="
				+ isRestart + ", media=" + Arrays.toString(media) + ", duration=" + duration + "]";
	}

}