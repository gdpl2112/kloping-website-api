package io.github.kloping.mywebsite.entitys.douyin;

public class PageGrayscale {
	private String mode;
	private BlockList blockList;

	public String getMode(){
		return this.mode;
	}

	public PageGrayscale setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public BlockList getBlockList(){
		return this.blockList;
	}

	public PageGrayscale setBlockList(BlockList blockList) {
		this.blockList = blockList;
		return this;
	}
}