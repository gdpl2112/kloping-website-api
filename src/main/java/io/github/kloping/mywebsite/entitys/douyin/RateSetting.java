package io.github.kloping.mywebsite.entitys.douyin;

public class RateSetting {
	private Number memorySize;
	private Number cpuCore;

	public Number getMemorySize(){
		return this.memorySize;
	}

	public RateSetting setMemorySize(Number memorySize) {
		this.memorySize = memorySize;
		return this;
	}

	public Number getCpuCore(){
		return this.cpuCore;
	}

	public RateSetting setCpuCore(Number cpuCore) {
		this.cpuCore = cpuCore;
		return this;
	}
}