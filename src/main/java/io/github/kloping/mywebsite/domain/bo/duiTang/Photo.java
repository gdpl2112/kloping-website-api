package io.github.kloping.mywebsite.domain.bo.duiTang;

public class Photo {
	private String path;
	private Number size;
	private Number width;
	private Number file_type_code;
	private Number height;

	public String getPath(){
		return this.path;
	}

	public Photo setPath(String path) {
		this.path = path;
		return this;
	}

	public Number getSize(){
		return this.size;
	}

	public Photo setSize(Number size) {
		this.size = size;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public Photo setWidth(Number width) {
		this.width = width;
		return this;
	}

	public Number getFile_type_code(){
		return this.file_type_code;
	}

	public Photo setFile_type_code(Number file_type_code) {
		this.file_type_code = file_type_code;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public Photo setHeight(Number height) {
		this.height = height;
		return this;
	}
}